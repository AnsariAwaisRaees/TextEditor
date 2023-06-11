import com.sun.org.apache.xpath.internal.domapi.XPathStylesheetDOM3Exception;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame jFrame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, past, selectAll, close;
    JTextArea textArea;
    TextEditor(){
        jFrame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("copy");
        past = new JMenuItem("past");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        cut.addActionListener(this);
        copy.addActionListener(this);
        past.addActionListener(this);
        selectAll.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(past);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        jFrame.setJMenuBar(menuBar);

        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        jPanel.setLayout(new BorderLayout(0,0));

        jPanel.add(textArea, BorderLayout.CENTER);

        JScrollPane jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(jScrollPane);

        jFrame.add(jPanel);
        jFrame.setBounds(200,200,400,400);
        jFrame.setTitle("Text Editor");
        jFrame.setVisible(true);
        jFrame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            textArea.copy();
        }
        if(actionEvent.getSource() == past){
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int chooseOption = jFileChooser.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file1 = jFileChooser.getSelectedFile();
                String filePath = file1.getPath();

                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";

                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }

                    textArea.setText(output);

                }catch (FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            JFileChooser fileChooser = new JFileChooser("C");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file1 = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try {
                    FileWriter fileWriter = new FileWriter(file1);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor textEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor tx = new TextEditor();
    }
}