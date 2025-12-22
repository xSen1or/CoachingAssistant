CoachingAssistant

Overview
- CoachingAssistant is a Java 21 application that helps analyze endurance activities (running, cycling, swimming, walking).
- Two ways to use it:
  - Desktop GUI built with Swing to select `.tcx` files, add activities, manage profile data, and track daily calorie goals.
  - CLI to parse input files and compute activity metrics.
- Uses Jackson for JSON and custom XML readers for importing TCX activity data.

Tech stack
- Language: Java 21
- Build tool: Apache Maven (pom.xml)
- GUI: Java Swing (`javax.swing`)
- JSON: Jackson (databind/core/annotations)
- Optional static analysis: JetBrains Qodana (`qodana.yaml`)
- Note: The `pom.xml` includes JavaFX and Gluon dependencies, but the current GUI code uses Swing. TODO: decide whether to remove these deps or migrate GUI to JavaFX.

Entry points
- Main class: `gr.huadit.Main`
  - Delegates to `gr.huadit.Helpers.ArgumentHandler` which supports:
    - `-gui` Opens the Swing GUI (`gr.huadit.GUI.Starting`)
    - `-term` Terminal mode: requires at least one `.tcx` file argument; optional `-w <weight>`

Requirements
- Java Development Kit (JDK) 21
- Maven 3.9+
- macOS/Linux/Windows with internet access to fetch Maven dependencies

Getting started
1) Clone the repository
```
git clone https://github.com/xS3nse/CoachingAssistant.git
cd CoachingAssistant
```

2) Build
```
mvn clean package
```

3) Run
- Run the GUI
```
java -jar target/CoachingAssistant-1.0-SNAPSHOT.jar -gui
```

- Run the CLI in terminal mode
```
# General usage
java -jar target/CoachingAssistant-1.0-SNAPSHOT.jar -term [-w weight] <file1.tcx> [file2.tcx ...]

# Alternative classpath form
java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main -term [-w weight] <file1.tcx> [file2.tcx ...]

# Examples
java -jar target/CoachingAssistant-1.0-SNAPSHOT.jar -term my-activity.tcx
java -jar target/CoachingAssistant-1.0-SNAPSHOT.jar -term -w 72 run1.tcx ride1.tcx
```

Notes on the packaged JAR
- Built with Maven Shade Plugin to produce a runnable JAR with `Main-Class` set to `gr.huadit.Main`.
- If you encounter classpath issues, try the classpath form shown above.

CLI usage details
- Flags handled in `ArgumentHandler`:
  - `-gui`: Opens the GUI
  - `-term`: Terminal mode (requires one or more input files; optional `-w <weight>`)
  - `-w <weight>`: Weight argument used in calculations; only valid in `-term` mode
- Input files:
  - Only `.tcx` files are accepted; otherwise the app will error with "Invalid file type!"
  - XML parsing is implemented via `XMLSingleFileReader` and `XMLMultipleFileReader` routed through `PathParser`.
- When required arguments are missing in `-term` mode, the app prints usage and exits with code 1.

Project structure (from `src/main/java`)
```
CoachingAssistant/
├── pom.xml
├── qodana.yaml
├── src/main/java/gr/huadit/
│   ├── Main.java                        # Entry point (CLI that can launch GUI)
│   ├── GUI/
│   │   ├── Starting.java                # Swing home window
│   │   ├── AddActivity.java
│   │   ├── CalorieGoal.java
│   │   ├── Client.java
│   │   └── FileResults.java
│   ├── ButtonListeners/
│   │   └── StartingPageListener.java
│   ├── Activities/
│   │   ├── Cycling.java
│   │   ├── Running.java
│   │   ├── Swimming.java
│   │   └── Walking.java
│   ├── Helpers/
│   │   ├── ArgumentHandler.java         # Parses CLI flags (-term, -gui, -w)
│   │   ├── PathParser.java
│   │   ├── XMLMultipleFileReader.java
│   │   └── XMLSingleFileReader.java
│   ├── JSONHandler/
│   │   ├── JSONFileReader.java
│   │   └── JSONFileWriter.java
│   ├── Classes/
│   │   ├── ActivityCard.java
│   │   ├── Profile.java
│   │   ├── ProgressCalculator.java
│   │   └── TrackPointResults.java
│   ├── Interfaces/
│   │   ├── Activity.java
│   │   ├── Logger.java
│   │   └── XMLReader.java
│   ├── Loggers/
│   │   └── ConsoleLogger.java
│   ├── Enums/
│   │   └── LoggerLevel.java
│   ├── Holders/
│   │   └── CurrentUser.java
│   ├── Find.java
│   └── Storage/storage.json
└── target/
```

Features (high level)
- Import and analyze activities from TCX files.
- View per-activity stats and aggregated results in the GUI.
- Manage athlete profile and choose a calorie calculation method.
- Set a daily calorie goal and track progress.

Configuration and data
- `src/main/java/gr/huadit/Storage/storage.json` provides local persistence for user/profile data.
- JSON serialization/deserialization uses Jackson Databind.

Environment variables
- None required at the moment.

Testing
- JUnit 3.8.1 is declared, but no test sources are included yet.

Development notes
- The code targets Java 21 (`maven.compiler.source/target`). Ensure your JDK matches.
- Qodana config (`qodana.yaml`) is included for optional static analysis in CI.


Troubleshooting
- If the app exits immediately with a usage message in `-term` mode, verify flags and provide at least one `.tcx` input file (and use `-w` only with `-term`).
- If the GUI does not open, ensure you passed `-gui` and that your JDK is Java 21.
