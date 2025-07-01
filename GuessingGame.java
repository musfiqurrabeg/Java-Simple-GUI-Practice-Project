import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

// A simple Jackpot Guessing Game using Java Swing
public class GuessingGame extends JFrame implements ActionListener {
    private JLabel instructionLabel;     // Top label with instructions
    private JLabel statusLabel;          // Bottom label showing remaining lives
    private JButton[] boxButtons;        // Array of three guess buttons
    private int livesRemaining;          // Number of remaining lives
    private int jackpotLocation;         // The index of the winning box (0, 1, or 2)
    private Random random;               // Random number generator

    // Constructor: sets up the game UI and starts the game
    public GuessingGame() {
        setTitle("Jackpot Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Instruction label at the top
        instructionLabel = new JLabel("Find the Jackpot! You have 3 lives.", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(instructionLabel, BorderLayout.NORTH);

        // Panel with three buttons in the center
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        boxButtons = new JButton[3];

        for (int i = 0; i < 3; i++) {
            boxButtons[i] = new JButton("Box " + (i + 1));
            boxButtons[i].setFont(new Font("Arial", Font.BOLD, 14));
            boxButtons[i].setBackground(Color.ORANGE);
            boxButtons[i].setFocusPainted(false);
            boxButtons[i].addActionListener(this);
            buttonPanel.add(boxButtons[i]);
        }
        add(buttonPanel, BorderLayout.CENTER);

        // Status label at the bottom
        statusLabel = new JLabel("Lives Remaining: 3", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(statusLabel, BorderLayout.SOUTH);

        // Initialize game state
        random = new Random();
        startGame();

        setVisible(true);
    }

    // Resets the game state at the start
    private void startGame() {
        livesRemaining = 3;
        jackpotLocation = random.nextInt(3); // Random box 0, 1, or 2
        updateStatus();
    }

    // Handles button clicks (player guesses)
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int guessedIndex = -1;

        // Find which button was clicked
        for (int i = 0; i < boxButtons.length; i++) {
            if (clickedButton == boxButtons[i]) {
                guessedIndex = i;
                break;
            }
        }

        // If guessed correctly
        if (guessedIndex == jackpotLocation) {
            showWinScreen();
        } else {
            livesRemaining--;

            if (livesRemaining == 0) {
                showGameOverScreen();
            } else {
                statusLabel.setText("Wrong! Lives Remaining: " + livesRemaining + ". Jackpot shuffled!");
                jackpotLocation = random.nextInt(3); // Shuffle jackpot

                // Reset status after delay
                Timer delay = new Timer(1500, evt -> updateStatus());
                delay.setRepeats(false);
                delay.start();
            }
        }
    }

    // Updates the status label
    private void updateStatus() {
        statusLabel.setText("Lives Remaining: " + livesRemaining);
    }

    // Show the win screen
    private void showWinScreen() {
        getContentPane().setBackground(Color.GREEN);
        removeAllComponents();
        JLabel winLabel = new JLabel("YOU WIN!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(winLabel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Show the game over screen
    private void showGameOverScreen() {
        getContentPane().setBackground(Color.RED);
        removeAllComponents();
        JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(gameOverLabel, BorderLayout.CENTER);

        // Disable all buttons
        for (JButton button : boxButtons) {
            button.setEnabled(false);
        }
        revalidate();
        repaint();
    }

    // Removes all components from the frame
    private void removeAllComponents() {
        getContentPane().removeAll();
    }

    // Main method: entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessingGame());
    }
} 