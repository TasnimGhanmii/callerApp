# CallerApp

A native Android contact-book app written in **Java**. After a login screen, users can add contacts (name, pseudo, phone number), browse them in a searchable list, edit them, and launch the phone dialer with one tap. Contacts are persisted locally in an SQLite database.

Built as a learning project (package `tasnimghanmi.grp1.callerapp`) to practice core Android concepts: multi-activity navigation, intents, `SharedPreferences`, SQLite, custom `ListView` adapters and the Activity Result API.

---

## Table of Contents

- [Features](#features)
- [App Flow](#app-flow)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Data Model](#data-model)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Permissions](#permissions)
- [Implementation Notes](#implementation-notes)
- [Known Limitations](#known-limitations)
- [Roadmap](#roadmap)
- [Author](#author)

---

## Features

- **Login screen** with a "Remember Me" option; when enabled, the app skips the login screen on next launch.
- **Logout** ("Déconnecter") clears the saved session and returns to the login screen.
- **Add contacts** with name, pseudo (nickname) and phone number; all three fields are required and validated.
- **Contact list** sorted alphabetically by name, with an empty-state message when there are no contacts.
- **Live search** that filters as you type, matching name, pseudo or phone number (case-insensitive).
- **Contact cards** showing a colored circular avatar with the contact's initial; the color is derived from the first letter.
- **Contact detail screen** with Call, Edit and Exit actions.
- **Edit contacts**; changes are saved to the database and the list refreshes automatically on return.
- **One-tap dialer**: opens the phone's dialer pre-filled with the number (falls back to a message if no phone app is available).
- **Edge-to-edge UI** with fade/slide animation resources and a Material Components theme with dark-mode support.
- **Local persistence**: everything is stored on-device, no network or account needed.

## App Flow

```
MainActivity (login)
      │  valid credentials
      ▼
Acceuil (home)
  ├── Ajouter ──────► Ajout (add contact form)
  ├── Affichage ────► Affiche (searchable list)
  │                        │ tap a contact
  │                        ▼
  │                  ContactDetailActivity ──► Call (system dialer)
  │                        │ Edit
  │                        ▼
  │                  EditContactActivity ──(result)──► detail ──(result)──► list refresh
  ├── Déconnecter ──► clears saved login, back to MainActivity
  └── Quitter ──────► closes the app
```

## Tech Stack

| Layer            | Technology                                                      |
|------------------|-----------------------------------------------------------------|
| Language         | Java 11 (source/target compatibility)                           |
| Platform         | Android, `minSdk 26` (Android 8.0), `targetSdk` / `compileSdk 35` |
| Build            | Gradle 8.11.1 (wrapper) with Kotlin DSL, Android Gradle Plugin 8.10.1 |
| Dependencies     | Gradle version catalog (`gradle/libs.versions.toml`)            |
| UI               | AndroidX AppCompat 1.7.1, Material Components 1.13.0, ConstraintLayout 2.2.1, CardView 1.0.0 |
| Navigation       | Explicit intents + `ActivityResultLauncher` (AndroidX Activity 1.8.0) |
| Storage          | SQLite (`SQLiteOpenHelper`) for contacts, `SharedPreferences` for the session |
| Testing          | JUnit 4.13.2, AndroidX Test (JUnit ext 1.3.0), Espresso 3.7.0   |

## Project Structure

```
callerApp/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/tasnimghanmi/grp1/callerapp/
│       │   │   ├── MainActivity.java            # Login + "Remember Me"
│       │   │   ├── Acceuil.java                 # Home menu (add / list / logout / quit)
│       │   │   ├── Ajout.java                   # Add-contact form
│       │   │   ├── Affiche.java                 # Contact list + search
│       │   │   ├── ContactDetailActivity.java   # Detail view + call + edit entry point
│       │   │   ├── EditContactActivity.java     # Edit form, returns result to detail
│       │   │   ├── CustomContactAdapter.java    # ListView adapter (avatar, name, pseudo, number)
│       │   │   ├── Contact.java                 # Model (id, nom, pseudo, num)
│       │   │   ├── ContactManager.java          # DB access layer (CRUD)
│       │   │   └── ContactHelper.java           # SQLiteOpenHelper (schema)
│       │   └── res/
│       │       ├── layout/    # one layout per screen + item_contact row
│       │       ├── anim/      # fade_in, slide_up, list layout animations
│       │       ├── drawable/  # icons, card/circle backgrounds, gradient
│       │       └── values/    # colors, themes (+ values-night), styles, strings
│       ├── test/              # local unit tests (template)
│       └── androidTest/       # instrumented tests (template)
├── gradle/                    # wrapper + libs.versions.toml
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

> Class names `Acceuil`, `Ajout` and `Affiche` are French (home, add, display).

## Data Model

Database file: `Contacts.db` (version 1), single table `Contact`:

| Column   | Type                              | Notes                  |
|----------|-----------------------------------|------------------------|
| `ID`     | INTEGER PRIMARY KEY AUTOINCREMENT | Generated by SQLite    |
| `Nom`    | TEXT NOT NULL                     | Contact name           |
| `Pseudo` | TEXT NOT NULL                     | Nickname               |
| `Num`    | TEXT NOT NULL                     | Phone number (as text) |

`ContactManager` exposes: `ouvrir()` / `fermer()` (open/close), `ajout(...)` (insert), `getAllContacts()` (sorted by name), `updateContact(...)` and `supprimerContact(id)` (delete).

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (a recent version that supports AGP 8.10)
- JDK 17 (bundled with recent Android Studio; the code itself targets Java 11)
- An emulator or physical device running **Android 8.0 (API 26) or higher**, with telephony support (see [Permissions](#permissions))

### Run the app

```bash
git clone https://github.com/TasnimGhanmii/callerApp.git
cd callerApp
```

1. Open the folder in Android Studio (**File > Open**) and wait for the Gradle sync.
2. Select a device or emulator and press **Run**.

Or from the command line:

```bash
./gradlew installDebug          # macOS / Linux (gradlew.bat on Windows)
```

### Build an APK

```bash
./gradlew assembleDebug
# app/build/outputs/apk/debug/app-debug.apk
```

### Tests

```bash
./gradlew test                   # local unit tests
./gradlew connectedAndroidTest   # instrumented tests (device/emulator required)
```

The test folders currently contain only the default Android Studio templates.

## Usage

1. **Log in.** The credentials are currently hardcoded: username `tasnim`, password `000` (case-insensitive). Tick **Remember Me** to skip this screen next time.
2. On the home screen, tap **Ajouter** to create a contact: fill in name, pseudo and number, then tap **Add**. The fields clear after each save so you can enter several contacts in a row.
3. Tap **Affichage** to see all contacts. Type in the search bar to filter by name, pseudo or number.
4. Tap a contact to open its details. From there:
   - **Call** opens the dialer with the number ready to go.
   - **Edit** lets you change any field and save.
5. **Déconnecter** logs you out and forgets the "Remember Me" state. **Quitter** closes the app.

## Permissions

| Permission / feature                          | Purpose                                              |
|-----------------------------------------------|------------------------------------------------------|
| `android.permission.CALL_PHONE`               | Declared in the manifest                             |
| `android.hardware.telephony` (required)       | Restricts installation to devices with telephony hardware |

Calls are started with `Intent.ACTION_DIAL`, which opens the dialer for the user to confirm and does **not** need `CALL_PHONE`. Because the telephony feature is marked `required="true"`, the app will not install from the Play Store on Wi-Fi-only tablets, and some emulator images may need telephony enabled.

## Implementation Notes

- **Navigation and results.** Screens are separate activities connected by explicit intents. Edit and detail screens return data through `ActivityResultLauncher`, so the detail view updates immediately and the list reloads when the user comes back.
- **Session handling.** "Remember Me" stores a boolean and the username in a private `SharedPreferences` file (`LoginPrefs`). On launch, `MainActivity` checks it and jumps straight to the home screen.
- **Persistence layer.** `ContactHelper` owns the schema, `ContactManager` wraps all queries, and activities open and close their own connection in `onCreate` / `onDestroy`.
- **Search.** Filtering is done in memory over the loaded list and updates the adapter, plus the empty-state view, on every keystroke.
- **Avatars.** `CustomContactAdapter` maps the first letter of the name to one of 18 colors and tints a circular background drawable.

## Known Limitations

- **Authentication is a placeholder.** The credentials are hardcoded in `MainActivity`; there is no user registration or password hashing. Fine for a demo, not for real use.
- **No delete in the UI.** `ContactManager.supprimerContact()` exists, but no screen calls it yet.
- **Schema upgrades are destructive.** `onUpgrade` drops and recreates the table, so bumping the DB version erases stored contacts.
- **No duplicate or phone-format validation.** Fields are only checked for being non-empty.
- **Disk access on the main thread.** Database calls are synchronous; fine at this scale, but they should move off the UI thread if the data grows.
- **Test coverage is minimal.** Only the default template tests are present.
- **UI strings are hardcoded** and mix French and English; they are not yet extracted into `strings.xml`, so there is no localization.

## Roadmap

- [ ] Delete contacts (swipe or button) using the existing `supprimerContact()`
- [ ] Replace the hardcoded login with proper local registration and hashed passwords
- [ ] Migrate list and DB code to `RecyclerView` + Room
- [ ] Extract strings to resources and add French/English localization
- [ ] Input validation (phone format, duplicate detection)
- [ ] Unit and UI tests for `ContactManager` and the main flows
- [ ] CI with GitHub Actions to build and run tests on every push

## Author

**Tasnim Ghanmi**: [@TasnimGhanmii](https://github.com/TasnimGhanmii)
