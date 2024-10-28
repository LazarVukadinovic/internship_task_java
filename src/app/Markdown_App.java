package app;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Markdown_App extends JFrame {
    private JPanel myPanel;
    private JButton loadFileButton;
    private JTextArea markdownTextArea;

    public Markdown_App() {
        setupUI();

        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Markdown Files (*.md)", "md"));

                int option = fileChooser.showOpenDialog(Markdown_App.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    loadFileContent(file);
                }
            }
        });
    }

    private void setupUI() {
        // Set up scroll pane with text area
        JScrollPane scrollPane = new JScrollPane(markdownTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Set up panel and add components
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(scrollPane);
    }

    private void loadFileContent(File file) {
        try {
            markdownTextArea.setText(""); // Clear previous content
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines) {
                markdownTextArea.append(line); // No format
            }
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(this,
                    "Error reading file: " + ioException.getMessage(),
                    "File Read Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Markdown_App form = new Markdown_App();
        form.setContentPane(form.myPanel);
        form.setTitle("Markdown Task");
        form.setSize(540, 400);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
