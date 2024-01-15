import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class PythonGUI {
    private int count = 0;
    private JFrame root;
    private JButton buttonIncrement;
    private JButton buttonOpenGUI2;
    private JButton buttonResetIncrement;
    private JLabel label;
    private JMenu fileMenu;
    private JMenuItem openMenuItem;
    private JMenuItem saveAsMenuItem;
    private JPanel panel;
    private JLabel labelFilePath;
    private JLabel labelInput;
    private JTextField tf;
    private JButton send;
    private JButton reset;
    private JButton goBack;
    private JTextArea ta;
    private String filePath;

    public PythonGUI() {
        root = new JFrame("My first GUI");
        root.setLayout(new BorderLayout());

        buttonIncrement = new JButton("Increment Count");
        buttonIncrement.addActionListener(e -> incrementCount());
        buttonOpenGUI2 = new JButton("Fullscreen editor");
        buttonOpenGUI2.addActionListener(e -> openTestGUI2());
        buttonResetIncrement = new JButton("Reset the Increment");
        buttonResetIncrement.addActionListener(e -> resetIncrement());

        label = new JLabel("Number of clicks: 0");

        JMenuBar menuBar = new JMenuBar();
        fileMenu = new JMenu("FILE");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(e -> showHelp());
        openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(e -> openFile());
        saveAsMenuItem = new JMenuItem("Save as");
        saveAsMenuItem.addActionListener(e -> saveFile());

        fileMenu.add(openMenuItem);
        fileMenu.add(saveAsMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenuItem);

        panel = new JPanel(new GridLayout(2, 1));

        JPanel filePanel = new JPanel(new GridLayout(1, 5));
        labelFilePath = new JLabel("File Path: Not opened");
        labelFilePath.setHorizontalAlignment(SwingConstants.CENTER);
        labelInput = new JLabel("Enter Text");
        tf = new JTextField(10);
        send = new JButton("Send");
        send.addActionListener(e -> sendClicked());
        reset = new JButton("Reset");
        reset.addActionListener(e -> resetClicked());
        goBack = new JButton("Exit");
        goBack.addActionListener(e -> goBackClicked());

        filePanel.add(labelFilePath);
        filePanel.add(labelInput);
        filePanel.add(tf);
        filePanel.add(send);
        filePanel.add(reset);
        filePanel.add(goBack);

        ta = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ta);

        panel.add(filePanel);
        panel.add(scrollPane);

        root.add(buttonIncrement, BorderLayout.NORTH);
        root.add(buttonOpenGUI2, BorderLayout.CENTER);
        root.add(buttonResetIncrement, BorderLayout.SOUTH);
        root.add(label, BorderLayout.SOUTH);
        root.setJMenuBar(menuBar);
        root.add(panel, BorderLayout.SOUTH);

        root.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        root.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        filePath = null;
    }

    public void incrementCount() {
        count++;
        label.setText("Number of clicks: " + count);
    }

    public void openTestGUI2() {
        new PythonGUI2(root);
    }

    public void resetIncrement() {
        count = 0;
        label.setText("Number of clicks: " + count);
    }

    public void showHelp() {
        JOptionPane.showMessageDialog(root, "This is a simple GUI application.");
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(root);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            labelFilePath.setText("File Path: " + filePath);
            try {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                ta.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
        if (filePath != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(ta.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveFileAs();
        }
    }

    public void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(root);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            labelFilePath.setText("File Path: " + filePath);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(ta.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendClicked() {
        if (!tf.getText().trim().isEmpty()) {
            ta.append(tf.getText() + "\n");
            tf.setText("");
        }
    }

    public void resetClicked() {
        tf.setText("");
        ta.setText("");
    }

    public void goBackClicked() {
        root.dispose();
    }

    public void onExit() {
        root.dispose();
    }

    public void run() {
        root.pack();
        root.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PythonGUI().run());
    }
}

class PythonGUI2 {
    private JFrame root;
    private JTextArea ta;

    public PythonGUI2(JFrame root) {
        this.root = new JFrame("PythonGUI2");

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("FILE");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(e -> showHelp());
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(e -> openFile());
        JMenuItem saveAsMenuItem = new JMenuItem("Save as");
        saveAsMenuItem.addActionListener(e -> saveFile());

        fileMenu.add(openMenuItem);
        fileMenu.add(saveAsMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenuItem);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel filePanel = new JPanel(new GridLayout(1, 5));

        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10);
        JButton send = new JButton("Send");
        send.addActionListener(e -> sendClicked(tf));
        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> resetClicked(tf));
        JButton goBack = new JButton("Return");
        goBack.addActionListener(e -> goBackClicked());

        filePanel.add(label);
        filePanel.add(tf);
        filePanel.add(send);
        filePanel.add(reset);
        filePanel.add(goBack);

        ta = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ta);

        panel.add(filePanel);
        panel.add(scrollPane);

        this.root.add(panel);
        this.root.setJMenuBar(menuBar);
        this.root.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.root.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        this.root.pack();
        this.root.setVisible(true);
    }

    public void showHelp() {
        JOptionPane.showMessageDialog(root, "This is PythonGUI2.");
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(root);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                ta.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(root);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath()))) {
                writer.write(ta.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendClicked(JTextField tf) {
        if (!tf.getText().trim().isEmpty()) {
            ta.append(tf.getText() + "\n");
            tf.setText("");
        }
    }

    public void resetClicked(JTextField tf) {
        tf.setText("");
        ta.setText("");
    }

    public void goBackClicked() {
        root.dispose();
    }

    public void onExit() {
        root.dispose();
    }
} // this was edited in the python GUI

