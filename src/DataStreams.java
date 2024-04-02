import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.stream.*;

public class DataStreams extends JFrame {
    private JTextArea originalTextArea = new JTextArea();
    private JTextArea filteredTextArea = new JTextArea();
    private JTextField searchField = new JTextField(20);
    private JButton loadButton = new JButton("Load a file");
    private JButton searchButton = new JButton("Search the file");
    private JButton quitButton = new JButton("Quit");
    private Path filePath;

    
}