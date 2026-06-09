# Grade Manager
A desktop application that allows users to input marks for up to 10 courses and calculate their average.

---

## Features
* **Interactive Minimal GUI:** Easy-to-use desktop window for adding and managing grades, created using Java Swing

* **Instant Calculations:** Separated backend logic (`GradeCalculator`) that computes averages automatically

* **Fully Standalone:** Packaged into a native desktop app with a custom icon

## How to Install and Run

### For macOS Users
1. Download and unzip the `Grade Manager.zip` file.
2. Drag `Grade Manager.app` into your **Applications** folder.
3. **First-time open:** If macOS shows a security warning, go to `System Settings` ➔ `Privacy & Security` and click **Open Anyway**.

### For Developers (Running from VS Code)
If you want to compile and run the raw Java files yourself:
1. Open this project folder in VS Code.
2. Run the following commands in your terminal:
   ```bash
   javac -d out GradeCalculator.java GradeManagerGUI.java
   java -cp out GradeManagerGUI
