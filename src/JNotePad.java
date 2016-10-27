import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *     Name:        Bither, Joshua
 *     Project:     4
 *     Due:         6/3/2016
 *     Course:      CS-245-01
 *
 *     Description: Java Version of the Windows Notepad
 */
public class JNotePad implements ActionListener{

    JFrame jfrm;
    boolean pass = false;
    ImageIcon icon;
    JTextArea textArea;
    int pos = 0;
    File file = new File("untitled");
    JFileChooser fileChooser = new JFileChooser();


    JNotePad(){
        jfrm = new JFrame("Untitled - JNotepad");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text or Java Files", "txt", "java");
        fileChooser.setFileFilter(filter);

        icon = new ImageIcon("JNotepad.png");

        jfrm.setIconImage(icon.getImage());

        jfrm.setSize(760,420);

        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jfrm.setLocationByPlatform(true);
/*
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
*/
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu format = new JMenu("Format");
        JMenu view = new JMenu("View");
        JMenu help = new JMenu("Help");

        file.setMnemonic(KeyEvent.VK_F);
        edit.setMnemonic(KeyEvent.VK_E);
        format.setMnemonic(KeyEvent.VK_O);
        view.setMnemonic(KeyEvent.VK_V);
        help.setMnemonic(KeyEvent.VK_H);


        JMenuItem New = new JMenuItem("New");
        New.setMnemonic(KeyEvent.VK_N);
        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,Event.CTRL_MASK));
        New.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem saveAs = new JMenuItem("Save As");
        saveAs.addActionListener(this);

        JMenuItem pageSetup = new JMenuItem("Page Setup");
        pageSetup.setMnemonic(KeyEvent.VK_U);
        pageSetup.setEnabled(false);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
        print.setEnabled(false);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        exit.addActionListener(this);

        file.add(New);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(pageSetup);
        file.add(print);
        file.addSeparator();
        file.add(exit);

        JMenuItem undo = new JMenuItem("Undo");
        undo.setEnabled(false);

        JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Event.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setText("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,Event.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem delete = new JMenuItem(new DefaultEditorKit.CutAction());
        delete.setText("Delete");
        delete.setMnemonic(KeyEvent.VK_DELETE);
        delete.addActionListener(this);

        JMenuItem find = new JMenuItem("Find");
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,Event.CTRL_MASK));
        find.addActionListener(this);

        JMenuItem findNext = new JMenuItem("Find Next");
        findNext.addActionListener(this);

        JMenuItem replace = new JMenuItem("Replace");
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,Event.CTRL_MASK));
        replace.setEnabled(false);

        JMenuItem goTo = new JMenuItem("Go To");
        goTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,Event.CTRL_MASK));
        goTo.setEnabled(false);

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,Event.CTRL_MASK));
        selectAll.addActionListener(this);

        JMenuItem timeDate = new JMenuItem("Time/Date");
        timeDate.setMnemonic(KeyEvent.VK_F5);
        timeDate.addActionListener(this);

        edit.add(undo);
        edit.addSeparator();
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
        edit.addSeparator();
        edit.add(find);
        edit.add(findNext);
        edit.add(replace);
        edit.add(goTo);
        edit.addSeparator();
        edit.add(selectAll);
        edit.add(timeDate);

        JMenuItem wordWrap = new JMenuItem("WordWrap");
        wordWrap.addActionListener(this);

        JMenuItem font = new JMenuItem("Font");
        font.addActionListener(this);

        format.add(wordWrap);
        format.add(font);

        JMenuItem statusBar = new JMenuItem("Status Bar");
        statusBar.setEnabled(false);

        view.add(statusBar);

        JMenuItem viewHelp = new JMenuItem("View Help");
        viewHelp.setEnabled(false);

        JMenuItem about = new JMenuItem("About JNotepad");
        about.addActionListener(this);

        help.add(viewHelp);
        help.addSeparator();
        help.add(about);


    /*
        JMenu placement = new JMenu("Placement");
        placement.setMnemonic(KeyEvent.VK_P);
        JMenuItem top = new JMenuItem("Top");
        top.setMnemonic(KeyEvent.VK_T);
        JMenuItem right = new JMenuItem("Right");
        right.setMnemonic(KeyEvent.VK_R);
        JMenuItem bottom = new JMenuItem("Bottom");
        bottom.setMnemonic(KeyEvent.VK_B);
        JMenuItem left = new JMenuItem("Left");
        left.setMnemonic(KeyEvent.VK_L);
        top.addActionListener(this);
        right.addActionListener(this);
        bottom.addActionListener(this);
        left.addActionListener(this);
        placement.add(top);
        placement.add(right);
        placement.add(bottom);
        placement.add(left);

        JMenu layout = new JMenu("Layout");
        layout.setMnemonic(KeyEvent.VK_L);
        JMenuItem scroll = new JMenuItem("Scroll");
        scroll.setMnemonic(KeyEvent.VK_S);
        JMenuItem wrap = new JMenuItem("Wrap");
        wrap.setMnemonic(KeyEvent.VK_W);
        scroll.addActionListener(this);
        wrap.addActionListener(this);
        layout.add(scroll);
        layout.add(wrap);

        JMenuItem defaults = new JMenuItem("Defaults");
        defaults.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,Event.CTRL_MASK));
        defaults.addActionListener(this);

        tabs.add(placement);
        tabs.add(layout);
        tabs.addSeparator();
        tabs.add(defaults);

        JMenuItem about = new JMenuItem("About");

        about.addActionListener(this);
        help.add(about);

    */
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(view);
        menuBar.add(help);

        jfrm.setJMenuBar(menuBar);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /* For displaying usable fonts
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();

        for(String string : fonts){
            System.out.println(string);
        }
        */

        textArea = new JTextArea();
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        textArea.setLineWrap(true);

        JScrollPane js = new JScrollPane(textArea);
        js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jfrm.getContentPane().add(js);

        //28,93

        jfrm.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("Open")){

            int choice = 5;
            if(!textArea.getText().equals("")) {
                String[] options = {"Save", "Don't save", "Cancel"};
                choice = JOptionPane.showOptionDialog(jfrm, "Do you want to save changes?", "Save", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }

            //If Saving
            if(choice == 0){
                int fileChoice = fileChooser.showSaveDialog(jfrm);
                file = fileChooser.getSelectedFile();
                if(fileChoice == JFileChooser.APPROVE_OPTION) {
                    try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(file))) {
                        textArea.write(fileOut);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            if(choice != 2) {
                System.out.println("Choice is: " + choice + " ABOUT TO READ");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {

                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                            sb.append("\n");
                            //textArea.read(br, "textArea");
                        }
                        textArea.setText(sb.toString());
                        System.out.println(selectedFile.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                jfrm.setTitle(file.getName() + " - JNotepad" );
            }
        }
        if(ae.getActionCommand().equals("Exit")){
            jfrm.setVisible(false);
            System.exit(0);
        }

        if(ae.getActionCommand().equals("Save")){
            if(file.toString().equals("untitled")){
                System.out.println("UNTITLED");
                int fileChoice = fileChooser.showSaveDialog(jfrm);
                file = fileChooser.getSelectedFile();
                if(fileChoice == JFileChooser.APPROVE_OPTION) {
                    System.out.println("FILE IS: " + file.getName());
                    try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(file))) {
                        textArea.write(fileOut);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    jfrm.setTitle(file.getName() + " - JNotepad");
                }

            }
            else{
                System.out.println("FILE IS: " + file.getName());
                try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(fileOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(ae.getActionCommand().equals("Save As")){
            int fileChoice = fileChooser.showSaveDialog(jfrm);
            file = fileChooser.getSelectedFile();
            if(fileChoice == JFileChooser.APPROVE_OPTION) {
                System.out.println("FILE IS: " + file.getName());
                try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(fileOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jfrm.setTitle(file.getName() + " - JNotepad");
            }
        }


        if(ae.getActionCommand().equals("New")){

            int choice = 5;
            if(!textArea.getText().equals("")) {
                String[] options = {"Save", "Don't save", "Cancel"};
                choice = JOptionPane.showOptionDialog(jfrm, "Do you want to save changes?", "Save", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }

            if(choice == 0){
                int fileChoice = fileChooser.showSaveDialog(jfrm);
                file = fileChooser.getSelectedFile();
                if(fileChoice == JFileChooser.APPROVE_OPTION) {
                    try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(file))) {
                        textArea.write(fileOut);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if(choice != 2) {
                jfrm.setTitle("Untitled - JNotepad");
                file = new File("untitled");
                textArea.setText("");
            }

        }

        if(ae.getActionCommand().equals("Find Next")){
            pass = true;
        }

        if (ae.getActionCommand().equals("Find") || pass == true) {

            pass = false;
            JDialog dg = new JDialog(jfrm,"Find");
            dg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            JTextField text = new JTextField();
            JPanel pan = new JPanel();
            JButton butt = new JButton("Find");
            pan.add(text);
            text.setColumns(10);
            text.setHorizontalAlignment(JTextField.CENTER);

            butt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Get the text to find...convert it to lower case for eaiser comparision
                    String find = text.getText();
                    // Focus the text area, otherwise the highlighting won't show up
                    textArea.requestFocusInWindow();
                    // Make sure we have a valid search term
                    if (find != null && find.length() > 0) {
                        Document document = textArea.getDocument();
                        int findLength = find.length();
                        try {
                            boolean found = false;
                            // Rest the search position if we're at the end of the document
                            if (pos + findLength > document.getLength()) {
                                pos = 0;
                            }
                            // While we haven't reached the end...
                            // "<=" Correction
                            while (pos + findLength <= document.getLength()) {
                                // Extract the text from teh docuemnt
                                String match = document.getText(pos, findLength).toLowerCase();
                                // Check to see if it matches or request
                                if (match.equals(find)) {
                                    found = true;
                                    break;
                                }
                                pos++;
                            }

                            // Did we find something...
                            if (found) {
                                // Get the rectangle of the where the text would be visible...
                                Rectangle viewRect = textArea.modelToView(pos);
                                // Scroll to make the rectangle visible
                                textArea.scrollRectToVisible(viewRect);
                                // Highlight the text
                                textArea.setCaretPosition(pos + findLength);
                                textArea.moveCaretPosition(pos);
                                // Move the search position beyond the current match
                                pos += findLength;
                            }

                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }

                    }


                }
            });
            pan.add(butt);
            dg.add(pan);
            dg.setSize(350,130);
            dg.setLocationRelativeTo(jfrm);
            dg.setVisible(true);
        }

        if(ae.getActionCommand().equals("Select All")){
            textArea.selectAll();
        }

        if(ae.getActionCommand().equals("Time/Date")){

            Date dNow = new Date( );
            SimpleDateFormat ft =
                    new SimpleDateFormat ("h:mm a MM/dd/yyyy ");

            JOptionPane.showMessageDialog(jfrm, ft.format(dNow));

        }

        if(ae.getActionCommand().equals("WordWrap")){
            textArea.setLineWrap(!textArea.getLineWrap());
        }

        if(ae.getActionCommand().equals("Font")){

        }

        if(ae.getActionCommand().equals("About JNotepad")){
            JDialog dg = new JDialog(jfrm,"About");
            dg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dg.setLocationRelativeTo(jfrm);
            dg.setSize(300,100);
            dg.setModal(true);

            JPanel jpan = new JPanel();
            JLabel jlab = new JLabel("JNotepad Version 1.0 (c) Josh Bither");
            jlab.setHorizontalAlignment(JLabel.CENTER);
            jlab.setVerticalAlignment(JLabel.CENTER);
            jpan.add(jlab);
            dg.add(jpan);

            dg.setVisible(true);
        }
    }

    public static void main(String[] args){
        new JNotePad();
    }
}
