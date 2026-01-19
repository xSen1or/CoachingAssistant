# Object-Oriented Programming II (Java) - Final Project
- Contributors: 
  - Dimitris Anargyros Skarlatos 
  - Mixalis Saradinoydis
  - Orestis Galanopoylos


# CoachingAssistant

A small Java (Maven) application that provides both a Swing GUI and a terminal CLI to import activity data (\*.tcx), compute activity statistics (calories, VO2, heart-rate zone analysis), and persist activities to a JSON storage file.

## Key features
- Dual modes: GUI (`-gui`) and terminal (`-term`)
- Import `*.tcx` files (CLI) and view detailed results in the GUI
- Add activities manually via the GUI (`AddActivity`)
- Multiple calorie calculation methods (BMR, VO2, zone-based)
- Simple profile management and calorie-tracking screens
- Built-in console logging with levels
- Unit tests and Qodana static analysis configured for CI

## Requirements
- Java JDK 25
- Maven 3.6+
- (Optional) Docker for local Qodana runs
- Recommended IDE: `IntelliJ IDEA 2025.2.3`

## Build
- Build the project:
- `mvn compile | mvn clean package`

## Run
- Example JAR runs (artifact name may vary):
    - GUI mode:
      ```
      java -jar CoachingAssistant-1.0-SNAPSHOT.jar -gui
      ```
    - Terminal mode (processes `*.tcx` files; `-w <weight>` optional):
      ```
      java -jar CoachingAssistant-1.0-SNAPSHOT.jar -term -w 75 activity.tcx
      ```

## CLI usage
- `-term` — terminal mode; accepts optional `-w <weight>` and one or more `*.tcx` files
- `-gui` — opens the Swing-based GUI

## Storage
- Bundled JSON storage: `src/main/java/gr/huadit/Storage/fileContainer.json`
- New activities are appended to the JSON array. Typical fields per entry:
    - `activity_name`
    - `activity_id`
    - `activity_total_distance`
    - `activity_average_pace`
    - `activity_duration` (ISO-8601, e.g. `PT10M`)
    - `activity_average_heart_rate`

## Important code locations
- `src/main/java/gr/huadit/Main.java` — application entrypoint and argument handling
- `src/main/java/gr/huadit/Helpers/ArgumentHandler.java` — CLI parsing and validation
- `src/main/java/gr/huadit/JSONHandler/JSONFileReader.java` and `JSONFileWriter.java` — storage IO
- `src/main/java/gr/huadit/Classes` — domain and calculation classes (ActivityCard, VO2Assessment, HeartRateZoneAnalysis, ProgressCalculator)
- `src/main/java/gr/huadit/GUI` — Swing UI screens (`AddActivity`, `FileResults`, `Client`, `CalorieGoal`, `CalorieInput`, etc.)

## Static analysis / CI
- Qodana configuration: `qodana.yaml`
- CI image: `jetbrains/qodana-jvm:2025.2`
- Example local Qodana run:
- `docker run --rm -v $(pwd):/data --workdir /data jetbrains/qodana-jvm:2025.2`

## Development notes
- Set Project SDK to Java 25 in `File \-> Project Structure` in `IntelliJ IDEA`
- When running from the IDE, ensure the working directory is the project root if you rely on the bundled `fileContainer.json`
- GUI is Swing-based; UI code should run on the EDT (`SwingUtilities.invokeLater` where appropriate)
- CLI argument handling lives in `ArgumentHandler` and `Main` validates and delegates to the correct flow

## Troubleshooting
- If the app exits with usage errors, verify flags and arguments (`-gui` or `-term`) and required parameters like weight or file paths
- Missing or invalid JSON storage: the writer will create and pretty-print `fileContainer.json` when adding entries
- Duration values use ISO-8601 format (exported via `Duration.toString()`)

## Repository
- Remote: `https://github.com/xSen1or/CoachingAssistant.git`
