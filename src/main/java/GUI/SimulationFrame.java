package GUI;

import BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class SimulationFrame extends JFrame {
    private final JTextField timeLimitField;
    private final JTextField maxProcessingTimeField;
    private final JTextField minProcessingTimeField;
    private final JTextField maxArrivalTimeField;
    private final JTextField minArrivalTimeField;
    private final JTextField numberOfClientsField;
    private final JTextField noServersField;
    private final JTextArea outputArea;

    public SimulationFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color backgroundColor = new Color(30, 30, 30);
        Color textColor = new Color(220, 220, 220);

        getContentPane().setBackground(backgroundColor);

        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.setBackground(backgroundColor);
        add(inputPanel, BorderLayout.NORTH);

        inputPanel.add(createLabel("Time limit:", textColor));
        timeLimitField = createTextField(textColor, backgroundColor);
        inputPanel.add(timeLimitField);

        inputPanel.add(createLabel("Max processing time:", textColor));
        maxProcessingTimeField = createTextField(textColor, backgroundColor);
        inputPanel.add(maxProcessingTimeField);

        inputPanel.add(createLabel("Min processing time:", textColor));
        minProcessingTimeField = createTextField(textColor, backgroundColor);
        inputPanel.add(minProcessingTimeField);

        inputPanel.add(createLabel("Max arrival time:", textColor));
        maxArrivalTimeField = createTextField(textColor, backgroundColor);
        inputPanel.add(maxArrivalTimeField);

        inputPanel.add(createLabel("Min arrival time:", textColor));
        minArrivalTimeField = createTextField(textColor, backgroundColor);
        inputPanel.add(minArrivalTimeField);

        inputPanel.add(createLabel("Number of clients:", textColor));
        numberOfClientsField = createTextField(textColor, backgroundColor);
        inputPanel.add(numberOfClientsField);

        inputPanel.add(createLabel("Number of servers:", textColor));
        noServersField = createTextField(textColor, backgroundColor);
        inputPanel.add(noServersField);

        JButton startButton = new JButton("Start Simulation");
        startButton.setBackground(backgroundColor);
        startButton.setForeground(textColor);
        startButton.addActionListener(e -> startSimulation());
        inputPanel.add(startButton);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(backgroundColor);
        outputArea.setForeground(textColor);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.getViewport().setBackground(backgroundColor);
        scrollPane.getVerticalScrollBar().setBackground(backgroundColor);
        scrollPane.getHorizontalScrollBar().setBackground(backgroundColor);
        scrollPane.setBorder(null);
        UIManager.put("ScrollBar.thumb", backgroundColor.brighter());
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setSize(1400, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        PrintStream printStream = new PrintStream(new CustomOutputStream(outputArea));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private JLabel createLabel(String text, Color textColor) {
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        return label;
    }

    private JTextField createTextField(Color textColor, Color backgroundColor) {
        JTextField textField = new JTextField();
        textField.setForeground(textColor);
        textField.setBackground(backgroundColor);
        textField.setCaretColor(textColor);
        return textField;
    }

    private void startSimulation() {
        outputArea.setText("");

        int timeLimit = Integer.parseInt(timeLimitField.getText());
        int maxProcessingTime = Integer.parseInt(maxProcessingTimeField.getText());
        int minProcessingTime = Integer.parseInt(minProcessingTimeField.getText());
        int maxArrivalTime = Integer.parseInt(maxArrivalTimeField.getText());
        int minArrivalTime = Integer.parseInt(minArrivalTimeField.getText());
        int numberOfClients = Integer.parseInt(numberOfClientsField.getText());
        int noServers = Integer.parseInt(noServersField.getText());

        SimulationManager simulationManager = new SimulationManager(timeLimit, maxProcessingTime, minProcessingTime, maxArrivalTime, minArrivalTime, numberOfClients, noServers);

        Thread simulationThread = new Thread(simulationManager);
        simulationThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimulationFrame::new);
    }

    public static class CustomOutputStream extends OutputStream {
        private final JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}