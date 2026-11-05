Pocket Notes

A lightweight, privacy-focused offline Android notes application built with Kotlin and Jetpack Compose.

Project Status

Early development – contributions and testing are welcome.

Features

- Create, edit, and delete notes.
- Search note titles and contents.
- Store notes locally using Android SharedPreferences.
- Light and dark appearance modes.
- No account registration.
- No advertisements or analytics.
- No internet permission or cloud dependency.

Technology Stack

- Kotlin
- Jetpack Compose
- Android SDK
- Gradle
- Android SharedPreferences

Build Requirements

- JDK 17
- Android SDK 35
- Gradle 8.9

Build the debug APK from the project root:

gradle :app:assembleDebug

Output:

"app/build/outputs/apk/debug/app-debug.apk"

Application Details

- App name: Pocket Notes
- Package ID: "org.example.pocketnotes"
- Version: 1.0.0
- Licence: MIT

Note: Replace the example package ID with a unique identifier you control before publishing.

Privacy

Pocket Notes is designed to work offline. Notes are stored locally on the device. The application does not require an account or network access.

Licence

This project is licensed under the MIT License. See the "LICENSE" file.

F-Droid Submission

This is an independent open-source Android project. F-Droid inclusion is not guaranteed.

Before submission:

1. Replace the example package ID.
2. Build and test the application.
3. Verify all dependencies and licences.
4. Prepare the required F-Droid metadata.
5. Follow the official F-Droid submission guidelines.

Official guide: https://f-droid.org/docs/Submitting_to_F-Droid_Quick_Start_Guide/
