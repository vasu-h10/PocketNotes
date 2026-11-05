package org.example.pocketnotes

import android.os.Bundle
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.json.JSONArray
import org.json.JSONObject

private data class Note(val id: Long, val title: String, val body: String, val updated: Long)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PocketNotesApp() }
    }
}

private fun loadNotes(context: Context): List<Note> {
    val raw = context.getSharedPreferences("pocket_notes", Context.MODE_PRIVATE)
        .getString("notes", "[]") ?: "[]"
    return try {
        val array = JSONArray(raw)
        (0 until array.length()).map { i ->
            val obj = array.getJSONObject(i)
            Note(obj.getLong("id"), obj.optString("title"), obj.optString("body"), obj.optLong("updated"))
        }.sortedByDescending { it.updated }
    } catch (_: Exception) { emptyList() }
}

private fun saveNotes(context: Context, notes: List<Note>) {
    val array = JSONArray()
    notes.forEach { note ->
        array.put(JSONObject().apply {
            put("id", note.id)
            put("title", note.title)
            put("body", note.body)
            put("updated", note.updated)
        })
    }
    context.getSharedPreferences("pocket_notes", Context.MODE_PRIVATE)
        .edit().putString("notes", array.toString()).apply()
}

@Composable
private fun PocketNotesApp() {
    val context = LocalContext.current
    var notes by remember { mutableStateOf(loadNotes(context)) }
    var query by remember { mutableStateOf("") }
    var editing by remember { mutableStateOf<Note?>(null) }
    var showEditor by remember { mutableStateOf(false) }
    var darkMode by remember { mutableStateOf(false) }

    fun persist(updated: List<Note>) {
        notes = updated.sortedByDescending { it.updated }
        saveNotes(context, notes)
    }

    MaterialTheme(colorScheme = if (darkMode) darkColorScheme() else lightColorScheme()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Pocket Notes", fontWeight = FontWeight.Bold) },
                    actions = {
                        TextButton(onClick = { darkMode = !darkMode }) {
                            Text(if (darkMode) "Light" else "Dark")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    editing = null
                    showEditor = true
                }) { Icon(Icons.Default.Add, contentDescription = "Add note") }
            }
        ) { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp)
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search your notes") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    singleLine = true
                )
                Spacer(Modifier.height(12.dp))
                val filtered = notes.filter {
                    it.title.contains(query, true) || it.body.contains(query, true)
                }
                if (filtered.isEmpty()) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(if (notes.isEmpty()) "No notes yet. Tap + to create one."
                             else "No notes match your search.")
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(bottom = 88.dp)
                    ) {
                        items(filtered, key = { it.id }) { note ->
                            Card(
                                modifier = Modifier.fillMaxWidth().clickable {
                                    editing = note
                                    showEditor = true
                                },
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(Modifier.weight(1f)) {
                                        Text(note.title.ifBlank { "Untitled note" },
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.SemiBold)
                                        Spacer(Modifier.height(4.dp))
                                        Text(note.body, maxLines = 3,
                                            style = MaterialTheme.typography.bodyMedium)
                                    }
                                    IconButton(onClick = { persist(notes.filterNot { it.id == note.id }) }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete note")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (showEditor) {
            NoteEditor(
                initial = editing,
                onDismiss = { showEditor = false },
                onSave = { title, body ->
                    val now = System.currentTimeMillis()
                    val updated = if (editing == null) {
                        notes + Note(now, title.trim(), body.trim(), now)
                    } else {
                        notes.map {
                            if (it.id == editing!!.id) it.copy(title = title.trim(), body = body.trim(), updated = now)
                            else it
                        }
                    }
                    persist(updated)
                    showEditor = false
                }
            )
        }
    }
}

@Composable
private fun NoteEditor(
    initial: Note?,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var title by remember { mutableStateOf(initial?.title ?: "") }
    var body by remember { mutableStateOf(initial?.body ?: "") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initial == null) "New note" else "Edit note") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(title, { title = it }, label = { Text("Title") }, singleLine = true)
                OutlinedTextField(body, { body = it }, label = { Text("Note") },
                    minLines = 4, maxLines = 8)
            }
        },
        confirmButton = {
            TextButton(
                enabled = title.isNotBlank() || body.isNotBlank(),
                onClick = { onSave(title, body) }
            ) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}
