package local;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextFile extends JFrame {

    static JFrame frame;
    static JLabel instructions, pathLabel, nameLabel, outputLabel;
    static JButton backButton, submitButton;
    static JTextField nameInputField, pathInputField;

    TextFile() {
        frame = new JFrame("Text File Validation");
        instructions = new JLabel("<html>Please enter the location and name of the text file <br/> " +
                "Example input from clipboard: C:\\Users\\MyPC\\SEB\\data\\testData\\ <br/> " +
                "Name input would be: testData.txt <br/> " +
                "Path input would be: C:\\Users\\MyPC\\SEB\\data</html>");
        pathLabel = new JLabel("Location: ");
        nameLabel = new JLabel("Name: ");
        outputLabel = new JLabel();
        backButton = new JButton("Back");
        submitButton = new JButton("Submit");
        nameInputField = new JTextField(20);
        nameInputField.setToolTipText("E.g. testData");
        pathInputField = new JTextField(20);
        pathInputField.setToolTipText("E.g. C:\\Users\\JavaProject\\data");

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backButton);

        JPanel instructionsPanel = new JPanel();
        instructionsPanel.add(instructions);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameInputField);
        JPanel pathPanel = new JPanel();
        pathPanel.add(pathLabel);
        pathPanel.add(pathInputField);
        inputPanel.add(namePanel);
        inputPanel.add(pathPanel);
        inputPanel.add(submitButton);

        JPanel outputPanel = new JPanel();
        outputPanel.add(outputLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(backButtonPanel);
        mainPanel.add(instructionsPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(outputPanel);

        frame.add(mainPanel);
        frame.show();
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        backButton.addActionListener(e -> {
            frame.dispose();
            Main main = new Main();
        });

        submitButton.addActionListener(e -> {
            String path = pathInputField.getText();
            String name = nameInputField.getText();
            try {
                Validation.readAndValidate(path, name);
                outputLabel.setText("Output generated in main project directory!");
                outputLabel.setForeground(Color.GREEN);
            } catch (FileNotFoundException fileNotFoundException) {
                outputLabel.setText("File not found. Double check your input!");
                outputLabel.setForeground(Color.RED);
            } catch (IOException ioException) {
                outputLabel.setText("Error. Check console for more information!");
                outputLabel.setForeground(Color.RED);
                ioException.printStackTrace();
            }
        });
    }
}
