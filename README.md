Pocket Notes

A lightweight, privacy-focused, open-source Android notes application built with Kotlin and Jetpack Compose.

Pocket Notes helps users create, organize, search, and manage personal notes directly on their Android devices without requiring an internet connection.

Project Status

Version: 1.0.0
Status: Early development
License: MIT
Platform: Android

Contributions, feedback, and testing are welcome.

Features

- Create, edit, and delete notes.
- Search notes by title and content.
- Store notes locally using Android SharedPreferences.
- Switch between light and dark appearance modes.
- Works completely offline.
- No account registration or login.
- No advertisements.
- No analytics or user tracking.
- No internet permission or cloud dependency.

Technology Stack

- Kotlin
- Jetpack Compose
- Android SDK
- Gradle
- Android SharedPreferences

Application Details

Property| Value
Application Name| Pocket Notes
Package ID| "io.github.vasuh10.pocketnotes"
Version| 1.0.0
Version Code| 1
Minimum Android SDK| 23 (Android 6.0)
Target Android SDK| 35
License| MIT
Source Code| https://github.com/vasu-h10/PocketNotes

Important: The package ID above is the intended publishing identifier. It must match the actual "applicationId", "namespace", and Kotlin package in the source code before release.

Build Requirements

- JDK 17
- Android SDK 35
- Gradle 8.9
- Android Gradle Plugin 8.7.3

Building the Application

Clone the repository:

git clone https://github.com/vasu-h10/PocketNotes.git
cd PocketNotes

Build the debug APK from the project root:

gradle :app:assembleDebug

The generated APK will be available at:

app/build/outputs/apk/debug/app-debug.apk

GitHub Actions

The repository includes a GitHub Actions workflow for building the Android application.

To build an APK using GitHub:

1. Open the repository on GitHub.
2. Select the Actions tab.
3. Open the Build Pocket Notes workflow.
4. Select Run workflow.
5. Wait for the build to finish successfully.
6. Download the APK artifact from the completed workflow run.

The GitHub Actions workflow produces a debug APK for testing. A production release requires an appropriate release build and signing process.

Privacy

Pocket Notes is designed with user privacy in mind.

- Notes are stored locally on the user's Android device.
- No personal information is transmitted to external servers.
- No user account is required.
- No cloud synchronization is included.
- No advertisements or analytics SDKs are included.
- The application does not request internet access.

Data storage: Android SharedPreferences.

Important: Users should maintain their own backups of important notes. Uninstalling the application or clearing its application data may permanently delete locally stored notes.

Open Source

Pocket Notes is free and open-source software.

The complete application source code is available at:

https://github.com/vasu-h10/PocketNotes

Developers are welcome to inspect, modify, and contribute to the project under the MIT License.

License

This project is licensed under the MIT License.

SPDX License Identifier: "MIT"

See the "LICENSE" (LICENSE) file for the full license text.

F-Droid Submission

Pocket Notes is intended for submission to the official F-Droid repository.

The application is designed to operate offline without advertisements, analytics, or proprietary cloud services.

Before submission, the following requirements should be verified:

- [ ] Confirm the final application package ID.
- [ ] Build and test the application on an Android device.
- [ ] Verify the open-source license and dependencies.
- [ ] Create a versioned Git tag and release.
- [ ] Confirm the source can be built using the F-Droid build environment.
- [ ] Prepare F-Droid application metadata.
- [ ] Submit the application for F-Droid review.

Official F-Droid submission guide:

https://f-droid.org/docs/Submitting_to_F-Droid_Quick_Start_Guide/

Note: F-Droid publication is subject to review and is not guaranteed.

Contributing

Contributions are welcome.

You can contribute by:

- Reporting bugs through GitHub Issues.
- Suggesting new features.
- Improving documentation.
- Submitting pull requests.
- Testing the application on different Android devices.

Project Repository

GitHub: https://github.com/vasu-h10/PocketNotes

---

Pocket Notes — Simple notes. Offline privacy. Open source.