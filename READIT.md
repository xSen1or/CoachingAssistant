## Object Oriented Programming II | Coaching Assistant 
___
*This Program was developed by a team of three students of the Harokopio University in the context of a course.*
___

#### General Information:
    1. Java Version: 21 
    2. IDE: IntelliJ IDEA Ultimate | VisualStudioCode
    3. Developed on MacOS & Ubuntu Linux 
    4. GitHub Repository: https://github.com/xSen1or/CoachingAssistant.git
___
### Students Information 
    1. Dimitrios Anargyros Skarlatos - it2024151
    2. Michalis Saradinoudis - it2023063
    3. Orestis Pistoli - it2024083
___
### Project Structure: 
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
│   │   ├── FileResultsGUI.java
│   │   └── ProfileGUI.java
│   ├── ButtonListeners/
│   │   ├── HomePageButtonListener.java
│   │   └── ProfileButtonListener.java
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
│   ├── Find.java
│   └── Storage/storage.json
└── target/
```
___
### Usage
    1. Terminal Mode: `Java -jar target/CoachingAssistant-1.0-SNAPSHOT.jar -term [-w <weight>] <filename>`
    2. GUI Mode: `java -jar target/CoachingAssistant-1.0-SNAPSHOT.jar -gui`

*[] indicates optional agrgument*<br>
*<> indicates required argument*
___
### XML File Structure:




