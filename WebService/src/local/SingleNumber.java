package local;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SingleNumber extends JFrame {

    static JFrame frame;
    static JButton submitButton, backButton;
    static JLabel instructionsLabel, outputLabel;
    static JTextField inputTextField;

    SingleNumber() {
        Validation validation = new Validation();
        frame = new JFrame("Single number validation");
        instructionsLabel = new JLabel("Enter IBAN number");
        outputLabel = new JLabel();
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        inputTextField = new JTextField(20);

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);

        JPanel centerPanel = new JPanel();
        centerPanel.add(instructionsLabel);
        centerPanel.add(inputTextField);
        centerPanel.add(submitButton);

        JPanel outputPanel = new JPanel();
        outputPanel.add(outputLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(backButtonPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(outputPanel);

        frame.add(mainPanel);
        frame.show();
        frame.setVisible(true);
        frame.setSize(500, 150);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        backButton.addActionListener(e -> {
            frame.dispose();
            Main main = new Main();
        });

        submitButton.addActionListener(e -> {
            String input = inputTextField.getText();
            boolean isValid = false;
            try {
                isValid = validation.validate(input, true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (isValid) {
                outputLabel.setText("IBAN number is Valid!");
                outputLabel.setForeground(Color.GREEN);
            } else {
                outputLabel.setText("IBAN number is not valid! Check console for more information");
                outputLabel.setForeground(Color.RED);
            }
        });
    }
}
