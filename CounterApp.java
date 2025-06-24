import javax.swing.*;

public class CounterApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Counter");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("10");
        label.setBounds(140, 50, 70, 40);
        frame.add(label);

        JButton plus = new JButton("+");
        plus.setBounds(150, 100, 60, 40);
        frame.add(plus);

        JButton minus = new JButton("–");
        minus.setBounds(90, 100, 60, 40);
        frame.add(minus);

        plus.addActionListener(e -> {
            int value = Integer.parseInt(label.getText());
            label.setText(String.valueOf(value + 1));
        });

        minus.addActionListener(e -> {
            int value = Integer.parseInt(label.getText());
            label.setText(String.valueOf(value - 1));
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
