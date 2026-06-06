// Importing all necessary libraries for GUI components and event handling first.
import java.awt.*;
import javax.swing.*;


// Defining the main class for the Grade Manager GUI application.
public class GradeManagerGUI extends JFrame {

    private JTextField[] markFields = new JTextField[10]; // Array to hold text fields for user input for marks (up to 10 courses)
    private JLabel resultLabel; // Label to display calculated letter grade (only overall average)
    private JButton calculateButton, clearButton; // Buttons for calculating average grades and clearing inputs

    public GradeManagerGUI() {
        setTitle("Grade Manager"); // Setting title of app window
        setSize(400, 400); // Setting size of app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensuring application exits when window is closed
        setLayout(new GridLayout(13, 2)); // Setting layout for the frame (13 rows, 2 columns)
    
        // Main panel with GridBagLayout for clean row-by-row alignment
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 10, 4, 10); // Margins around elements
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Loop to create labels and text fields for up to 10 courses
        for (int i = 0; i < 10; i++) {
            gbc.gridx = 0; // Column 0 for labels
            gbc.gridy = i; // Row i for each course
            panel.add(new Label("Course " + (i + 1) + " Mark: ")); // Label for each course mark input
        }
    
    }
}
