import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField displayField;
    private JTextField resultField;
    private String currentDisplay = "";
    private String operator = "";
    private double firstOperand = 0;
    private boolean newNumber = true; // Flag to indicate if we are starting a new number

    // Buttons
    private JButton oneButton, twoButton, threeButton, fourButton;
    private JButton plusButton, minusButton, equalsButton;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Display Panel ---
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(2, 1));

        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));
        displayPanel.add(displayField);

        resultField = new JTextField("0");
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        resultField.setFont(new Font("Arial", Font.BOLD, 30));
        resultField.setBackground(Color.LIGHT_GRAY); // Just to differentiate
        displayPanel.add(resultField);

        add(displayPanel, BorderLayout.NORTH);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Rows, Cols, HGap, VGap

        // Initialize buttons
        oneButton = new JButton("1");
        twoButton = new JButton("2");
        threeButton = new JButton("3");
        fourButton = new JButton("4");
        plusButton = new JButton("+");
        minusButton = new JButton("-");
        equalsButton = new JButton("=");

        // Add action listeners
        oneButton.addActionListener(this);
        twoButton.addActionListener(this);
        threeButton.addActionListener(this);
        fourButton.addActionListener(this);
        plusButton.addActionListener(this);
        minusButton.addActionListener(this);
        equalsButton.addActionListener(this);

        // Add buttons to panel
        buttonPanel.add(oneButton);
        buttonPanel.add(twoButton);
        buttonPanel.add(threeButton);
        buttonPanel.add(fourButton);
        buttonPanel.add(plusButton);
        buttonPanel.add(minusButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(new JPanel()); // Placeholder for alignment

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the text of the button pressed

        // Handle number buttons
        if (command.matches("[0-9]")) { // Checks if the command is a digit
            if (newNumber) {
                currentDisplay = command;
                newNumber = false;
            } else {
                currentDisplay += command;
            }
            displayField.setText(currentDisplay);
        }
        // Handle operator buttons
        else if (command.equals("+") || command.equals("-")) {
            if (!currentDisplay.isEmpty()) {
                firstOperand = Double.parseDouble(currentDisplay);
                operator = command;
                newNumber = true; // Ready for the next number
                displayField.setText(currentDisplay + " " + operator); // Update display
            }
        }
        // Handle equals button
        else if (command.equals("=")) {
            if (!currentDisplay.isEmpty() && !operator.isEmpty()) {
                double secondOperand = Double.parseDouble(currentDisplay);
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                }
                resultField.setText(String.valueOf(result));
                currentDisplay = String.valueOf(result); // Set result as current display for chained operations
                operator = ""; // Reset operator
                newNumber = true; // Ready for a new operation or number
                displayField.setText(currentDisplay); // Update display with result
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator());
    }
}
