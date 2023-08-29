package NumberGuessingGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class NumberGuessingGameUI extends JFrame implements ActionListener  {
	private JLabel titleLabel;
    private JLabel guessLabel;
    private JTextField guessTextField;
    private JButton submitButton;
    private JTextArea resultTextArea;
    
    private int randomNumber;
    private int numberOfGuesses;

    public NumberGuessingGameUI() {
        randomNumber = generateRandomNumber();
        numberOfGuesses = 0;
        
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        
        titleLabel = new JLabel("Welcome to the Number Guessing Game!", SwingConstants.CENTER);
        add(titleLabel); 
        
        guessLabel = new JLabel("Enter your guess (1-100):", SwingConstants.CENTER);
        add(guessLabel);
        
        guessTextField = new JTextField();
        add(guessTextField);
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);
        
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        add(resultTextArea);
        
        pack();
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try {
                int userGuess = Integer.parseInt(guessTextField.getText());
                numberOfGuesses++;
                
                if (userGuess < randomNumber) {
                    resultTextArea.setText("Hint: Try a higher number.\nNumber of guesses: " + numberOfGuesses);
                } else if (userGuess > randomNumber) {
                    resultTextArea.setText("Hint: Try a lower number.\nNumber of guesses: " + numberOfGuesses);
                } else {
                    resultTextArea.setText("Congratulations! You guessed the number " + randomNumber + " correctly in " + numberOfGuesses + " guesses.");
                    guessTextField.setEnabled(false);
                    submitButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                resultTextArea.setText("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameUI game = new NumberGuessingGameUI();
            game.setVisible(true);
        });
    }
}
