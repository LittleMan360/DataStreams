import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.stream.*;

public class DataStreams extends JFrame {
    private final JTextArea originalTextArea = new JTextArea();
    private final JTextArea filteredTextArea = new JTextArea();
    private final JTextField searchField = new JTextField(20);
    private Path filePath;

    public DataStreams() {
        setTitle("Data Streams");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));
        add(new JScrollPane(originalTextArea), BorderLayout.WEST);
        add(new JScrollPane(filteredTextArea), BorderLayout.EAST);
        JPanel panel = new JPanel();
        panel.add(searchField);
        JButton loadButton = new JButton("Load a file");
        panel.add(loadButton);
        JButton searchButton = new JButton("Search the file");
        panel.add(searchButton);
        JButton quitButton = new JButton("Quit");
        panel.add(quitButton);
        add(panel, BorderLayout.SOUTH);
        originalTextArea.setEditable(false);
        filteredTextArea.setEditable(false);
        loadButton.addActionListener(e -> loadFile());
        searchButton.addActionListener(e -> searchFile());
        quitButton.addActionListener(e -> System.exit(0));
        setVisible(true);
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().toPath();
            try {
                String contents = new String(Files.readAllBytes(filePath));
                originalTextArea.setText(contents);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "The file could not be loaded.");
            }
        }
    }

    private void searchFile() {
        if (filePath == null) {
            JOptionPane.showMessageDialog(this, "No file has been loaded.");
            return;
        }
        String searchTerm = searchField.getText();
        try {
            String contents = new String(Files.readAllBytes(filePath));
            Stream<String> lines = contents.lines();
            String result = lines.filter(line -> line.contains(searchTerm))
                                .collect(Collectors.joining("\n"));
            filteredTextArea.setText(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "The file could not be searched.");
        }
    }




}