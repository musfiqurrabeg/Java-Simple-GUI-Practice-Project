import javax.swing.*;
import java.awt.event.*;

public class CalculatorGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JTextField num1Field = new JTextField();
        JTextField num2Field = new JTextField();
        num1Field.setBounds(50, 10, 90, 30);
        num2Field.setBounds(160, 10, 90, 30);
        frame.add(num1Field);
        frame.add(num2Field);

        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        addButton.setBounds(70, 60, 60, 30);
        subButton.setBounds(160, 60, 60, 30);
        frame.add(addButton);
        frame.add(subButton);

        JLabel resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(100, 110, 200, 30);
        frame.add(resultLabel);

        ActionListener listener = e -> {
            try {
                int num1 = Integer.parseInt(num1Field.getText());
                int num2 = Integer.parseInt(num2Field.getText());
                int result = (e.getSource() == addButton) ? num1 + num2 : num1 - num2;
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers");
            }
        };

        addButton.addActionListener(listener);
        subButton.addActionListener(listener);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
