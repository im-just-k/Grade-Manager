// Importing all necessary libraries for GUI components and event handling first.
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Defining the main class for the Grade Manager GUI application.
public class GradeManagerGUI extends JFrame {

    private JTextField[] markFields = new JTextField[10]; // Array to hold text fields for user input for marks (up to 10 courses)
    private JLabel resultLabel; // Label to display calculated letter grade (only overall average)
    private JButton calculateButton, clearButton; // Buttons for calculating average grades and clearing inputs

    public GradeManagerGUI() {
        setTitle("Grade Manager"); // Setting title of app window
        setSize(400, 400); // Setting size of app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensuring application exits when window is closed
        setLayout(new GridLayout(13, 1)); // Setting layout for the frame (13 rows, 2 columns)
        setLocationRelativeTo(null); // Centering the window on the screen
    
        // Main panel using GridBagLayout for perfectly aligned rows
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel headerLabel = new JLabel("Enter Course Marks (up to 10)");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(headerLabel, gbc);
        
        gbc.gridwidth = 1; // Reset gridwidth for the input rows

        // Loop to dynamically create 10 rows matching the mockup
        for (int i = 0; i < 10; i++) {
            gbc.gridx = 0; // Column 0 for labels
            gbc.gridy = i + 1; // Rows 1 to 10 for mark inputs
            panel.add(new JLabel("Mark " + (i + 1) + ": "), gbc); // Adding label for each mark input

            gbc.gridx = 1; // Column 1 for text fields
            markFields[i] = new JTextField(10);
            panel.add(markFields[i], gbc); // Fixed: added 'gbc' here
        }

        // Horizontal Separator
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        panel.add(new JSeparator(JSeparator.HORIZONTAL), gbc);
        gbc.gridwidth = 1; // Reset gridwidth for the result label

        // Adding Calculate button with action listener
        calculateButton = new JButton("Calculate Average Grade");
        calculateButton.addActionListener(e -> calculateAverageGrade()); // Action to perform when Calculate button is clicked
        gbc.gridx = 0; // Column 0 for Calculate button
        gbc.gridy = 13; // Row 13 for Calculate button
        panel.add(calculateButton, gbc);

        // Adding Clear button with action listener
        clearButton = new JButton("Clear:)");
        clearButton.addActionListener(e -> clearFields()); // Action to perform when Clear button is clicked
        gbc.gridx = 1; // Column 1 for Clear button
        gbc.gridy = 13; // Row 13 for Clear button
        panel.add(clearButton, gbc);

        // Result display panel (White box from mock-up)
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        JPanel resultBox = new JPanel(new BorderLayout());
        resultBox.setBackground(Color.WHITE);
        resultBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        resultLabel = new JLabel("<html>Overall Average:<br>—</html>");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultBox.add(resultLabel, BorderLayout.CENTER);
        panel.add(resultBox, gbc);

        // Crucial Fix: Add the main panel to the frame window
        add(panel, BorderLayout.CENTER);
    }

    // Handles user interaction, hands data to the backend, catches processing errors
    private void calculateAverageGrade() {
        // Collect raw data strings from fields
        String[] rawInputs = new String[10];
        for (int i = 0; i < 10; i++) {
            rawInputs[i] = markFields[i].getText();
        }

        try {
            // Process data inside our standalone logic layer module
            double[] validatedMarks = calculator.processInputs(rawInputs);

            if (validatedMarks.length > 0) {
                double average = calculator.calculateAverage(validatedMarks);
                // HTML format used to easily jump down a line inside a single standard label component
                resultLabel.setText(String.format("<html>Overall Average:<br><b>%.2f</b></html>", average));
            } else {
                JOptionPane.showMessageDialog(this, "Please enter at least one mark.", "No Data", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid entry detected. Please check your inputs and use numbers only!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Range Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
