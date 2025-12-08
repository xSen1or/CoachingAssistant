CoachingAssistant

Overview
- CoachingAssistant is a Java 21 application that helps analyze endurance activities (e.g., running, cycling, swimming, walking). It provides:
  - A GUI to browse and visualize data.
  - A CLI to parse input files and process activity metrics.
- The project uses JavaFX for the UI and Jackson for JSON handling. XML readers are available for importing activity data.

Tech stack
- Language: Java 21
- Build tool: Apache Maven (pom.xml)
- GUI: JavaFX (controls, fxml, graphics) and Gluon Charm Glisten
- JSON: Jackson Databind
- Static analysis (optional): JetBrains Qodana (qodana.yaml)

Entry points
- GUI launcher: `gr.huadit.Application`
  - Starts the JavaFX-based Home Page GUI: `new HomePageGUI()`
- CLI launcher: `gr.huadit.Main`
  - Delegates to `gr.huadit.Helpers.ArgumentHandler` which supports:
    - `-term` Terminal mode: requires input file arguments and optional `-w <weight>`
    - `-gui` Opens the GUI from the CLI entry point

Requirements
- Java Development Kit (JDK) 21
- Maven 3.8+ (3.9+ recommended)
- On macOS/Linux/Windows with internet access to fetch Maven dependencies

Getting started
1) Clone the repository
```
git clone https://github.com/<your-account>/CoachingAssistant.git
cd CoachingAssistant
```

2) Build
```
mvn clean package
```

3) Run
- Run the GUI directly (Application main class)
```
java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Application
```

- Or run via CLI entry point in GUI mode
```
java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main -gui
```

- Run in terminal (batch) mode
```
# General usage (as printed by the app)
java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main <run-type> [-w weight] <filename>

# Examples
java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main -term activities.gpx
java -cp target/CoachingAssistant-1.0-SNAPSHOT.jar gr.huadit.Main -term -w 72 activities1.gpx activities2.gpx
```

Notes on runtime classpath
- The produced JAR is a plain artifact; JavaFX and other dependencies are not shaded into it. Ensure they are resolvable on your system. On most setups launched from an IDE, JavaFX modules are configured automatically. From the command line, you may need to provide JavaFX modules on the module-path if your JDK lacks them.
- TODO: Add a Maven plugin configuration (e.g., javafx-maven-plugin or Maven Shade) to simplify command-line execution without manual module-path configuration.

Project structure (selected)
```
CoachingAssistant/
├── pom.xml
├── qodana.yaml
├── src/main/java/gr/huadit/
│   ├── Application.java                 # GUI entry point
│   ├── Main.java                        # CLI entry point
│   ├── GUI/
│   │   ├── HomePageGUI.java
│   │   └── ProfileGUI.java
│   ├── Activities/                      # Activity domain objects
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
│   │   ├── AthleteCard.java
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
│   ├── Find.java
│   └── Storage/storage.json
└── target/
```

CLI usage details
- Flags handled in `ArgumentHandler`:
  - `-term`: Terminal mode (requires one or more input files; optional `-w <weight>`)
  - `-gui`: Opens the GUI
  - `-w <weight>`: Weight argument used in calculations; only valid in `-term` mode
- Input files:
  - XML parsing is implemented via `XMLSingleFileReader` and `XMLMultipleFileReader` through `PathParser`. Filenames follow the arguments after the mode and optional weight.
- When required arguments are missing in `-term` mode, the app prints usage and exits with code 1.

Environment variables
- No required environment variables are detected in the source.
- TODO: Document any optional variables (e.g., logging level, data directories) if introduced.

Configuration and data
- `src/main/java/gr/huadit/Storage/storage.json` is present and may be used for local persistence.
- JSON serialization/deserialization uses Jackson Databind.

Maven commands (scripts)
- Build: `mvn clean package`
- Format/lint: Configure your preferred tools; Qodana config is included for CI
- Run tests: `mvn test`
- TODO: Optionally add javafx-maven-plugin to support `mvn javafx:run` for the GUI.

Testing
- JUnit is declared (junit:3.8.1), but no test sources are included in the repository at this time.
- Run tests (once tests exist):
```
mvn test
```
- TODO: Upgrade to JUnit 5 (JUnit Jupiter) and add test suites for core helpers (e.g., `ArgumentHandler`, `PathParser`, XML readers, calculators).

Development notes
- JavaFX version is controlled via the Maven property `javafx.version` in `pom.xml`.
- The code targets Java 21 (`maven.compiler.source/target`). Ensure your JDK matches.
- Qodana configuration specifies linter `jetbrains/qodana-jvm:2025.2` and `projectJDK: "25"` for CI. Local development can remain on JDK 21; align in CI as needed.

License
- TODO: Add an open-source license (e.g., MIT, Apache-2.0) or document proprietary status. Create a LICENSE file and reference it here.

Contributing
- TODO: Provide guidelines (branching model, code style, PR checks). Consider enabling Qodana in CI to gate PRs.

Troubleshooting
- JavaFX not found at runtime:
  - Ensure you run with a JDK that includes JavaFX or provide JavaFX modules on the module-path.
  - Consider adding a shaded/fat JAR or JavaFX Maven plugin configuration to simplify execution.
- CLI usage prints: If you see usage printed and the app exits, verify flags and the presence of required filenames in `-term` mode.
