package local;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    static JFrame frame;
    static JButton singleInputButton, fileInputButton;
    static JLabel instructionsLabel;

    public static void main(String[] args) {
        Main main = new Main();
    }

    Main() {
        frame = new JFrame("IBAN Program");
        instructionsLabel = new JLabel("Select IBAN number validation function");
        singleInputButton = new JButton("Single");
        fileInputButton = new JButton("Text file");

        JPanel instructionsPanel = new JPanel();
        instructionsPanel.add(instructionsLabel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(singleInputButton);
        buttonsPanel.add(fileInputButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout((new BoxLayout(mainPanel, BoxLayout.Y_AXIS)));
        mainPanel.add(instructionsPanel);
        mainPanel.add(buttonsPanel);

        frame.add(mainPanel);
        frame.setSize(350, 150);
        frame.show();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        singleInputButton.addActionListener(e -> {
            frame.dispose();
            SingleNumber singleNumber = new SingleNumber();
        });

        fileInputButton.addActionListener(e -> {
            frame.dispose();
            TextFile textFile = new TextFile();
        });
    }
}
