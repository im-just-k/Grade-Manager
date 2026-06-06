import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Defining the main class for the Grade Manager GUI application.
public class GradeManagerGUI extends JFrame {

    private JTextField[] markFields = new JTextField[10]; // Array to hold text fields for up to 10 course marks
    private JLabel resultLabel; // Label to display calculated average inside the white box
    private JButton calculateButton, clearButton; // Control buttons
    
    // Instance of our modular backend controller
    private final GradeCalculator calculator = new GradeCalculator();

    // Constructor to set up the GUI components and layout
    public GradeManagerGUI() {
        setTitle("Grade Manager"); 
        setSize(400, 550); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); // Centers the window on the screen
    
        // Main panel with GridBagLayout for clean row-by-row alignment
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10); // Margins around elements
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header Title Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across both columns
        JLabel headerLabel = new JLabel("Enter Course Marks (up to 10)");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(headerLabel, gbc);

        gbc.gridwidth = 1; // Reset grid width for individual input rows

        // Loop to dynamically create labels and text fields for up to 10 courses
        for (int i = 0; i < 10; i++) {
            gbc.gridx = 0; // Column 0 for labels
            gbc.gridy = i + 1; // Rows 1 through 10
            panel.add(new JLabel("Mark " + (i + 1) + ": "), gbc);

            gbc.gridx = 1; // Column 1 for text fields
            markFields[i] = new JTextField(10); 
            panel.add(markFields[i], gbc); // Passes 'gbc' to maintain the structural grid alignment
        }

        // Horizontal Separator line matching the mock-up
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        panel.add(new JSeparator(JSeparator.HORIZONTAL), gbc);
        gbc.gridwidth = 1; // Reset

        // Adding Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateAverageGrade()); 
        gbc.gridx = 0; 
        gbc.gridy = 12; 
        panel.add(calculateButton, gbc);

        // Adding Clear button
        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields()); 
        gbc.gridx = 1; 
        gbc.gridy = 12; 
        panel.add(clearButton, gbc);

        // Result Box Panel (Matches the white container block from the mockup)
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        JPanel resultBox = new JPanel(new BorderLayout());
        resultBox.setBackground(Color.WHITE);
        resultBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        resultLabel = new JLabel("<html>Overall Average:<br>—</html>");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Inner container padding
        resultBox.add(resultLabel, BorderLayout.CENTER);
        panel.add(resultBox, gbc);

        // Status bar panel along the baseline frame
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        statusBar.add(new JLabel("Ready"));

        // Assembling the main component wrappers into the layout frame
        add(panel, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
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

    // Clear Logic: Wipes text boxes and resets the white feedback panel text
    private void clearFields() {
        for (JTextField field : markFields) {
            field.setText("");
        }
        resultLabel.setText("<html>Overall Average:<br>—</html>");
    }

    // Driver method execution launch point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GradeManagerGUI().setVisible(true); 
        });
    }
}