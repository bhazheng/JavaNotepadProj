import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.*;

public class textEditor extends JFrame{
    private JButton saveButton;
    private JButton loadButton;
    private JButton clearButton;
    private JPanel GUI;
    private JComboBox fontBox;
    private JTextArea editorPane1;
    private JButton fontColorButton;
    private JButton backgroundColor;
    private JSpinner fontSize;
    private JButton imgButton;
    private JLabel label;

    public textEditor() {

        //method untuk jframe
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text Editor");
        this.setContentPane(GUI);
        this.setSize(600,700);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //method default text area
        editorPane1.setFont(new Font("Arial", Font.PLAIN,20));


        //method untuk clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPane1.setText("");
                label.setIcon(null);
                editorPane1.setBackground(Color.white);
                editorPane1.setForeground(Color.black);
                fontSize.setValue(20);
                System.out.println("clear");
            }
        });

        //method untuk save file txt
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fs = new JFileChooser();
                fs.setDialogTitle("Save a File");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                fs.setFileFilter(filter);
                int result = fs.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    String content = editorPane1.getText();
                    File fi = fs.getSelectedFile();
                    try {
                        FileWriter fw = new FileWriter((fi.getPath()));
                        fw.write(content);
                        fw.close();
                    }catch (Exception e3){
                        System.out.println(e3.getMessage());
                    }
                }
            }
        });

        //method untuk membuka file txt
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                fileChooser.setFileFilter(filter);

                int response = fileChooser.showOpenDialog(null);

                if(response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    Scanner fileIn = null;

                    try {
                        fileIn = new Scanner(file);
                        if(file.isFile()) {
                            while(fileIn.hasNextLine()) {
                                String line = fileIn.nextLine()+"\n";
                                editorPane1.append(line);
                            }
                        }
                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    finally {
                        fileIn.close();
                    }
                }
            }
        });

        //method untuk mengubah warna font
        fontColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorChooser = new JColorChooser();

                Color color = colorChooser.showDialog(null, "Choose a color", Color.black);

                editorPane1.setForeground(color);

            }
        });

        //method untuk mengubah background text area
        backgroundColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorChooser = new JColorChooser();

                Color color = colorChooser.showDialog(null, "Choose a color", Color.black);

                editorPane1.setBackground(color);
            }
        });

        //method untuk mengatur font size
        fontSize.setValue(20);
        fontSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                editorPane1.setFont(new Font(editorPane1.getFont().getFamily(),Font.PLAIN,(int) fontSize.getValue()));
            }
        });

        //method untuk mengubah jenis font
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox.setModel(new DefaultComboBoxModel(fonts));
        fontBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPane1.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,editorPane1.getFont().getSize()));
            }
        });

        //method untuk menambahkan gambar di Jlabel
        imgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                //Limit type of file name extensions supported.

                FileNameExtensionFilter filter = new FileNameExtensionFilter("3 Extensions Supported", "jpg", "png", "jpeg");

                fileChooser.setFileFilter(filter);

                int selected = fileChooser.showOpenDialog(null);

                //check if open button has been clicked.

                if (selected == JFileChooser.APPROVE_OPTION) {

                    File file = fileChooser.getSelectedFile();
                    String getselectedImage = file.getAbsolutePath();
                    ImageIcon imIco = new ImageIcon(getselectedImage);
                    Image imFit = imIco.getImage();

                    Image imgFit = imFit.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                    label.setIcon(new ImageIcon(imgFit));
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new textEditor();

    }



}
