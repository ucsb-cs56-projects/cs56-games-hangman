package edu.ucsb.cs56.projects.games.hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class selectionGUI {
    private JRadioButton easyRadioButton;
    private JRadioButton normalRadioButton;
    private JRadioButton hardRadioButton;
    private JRadioButton sportsRadioButton;
    private JRadioButton scienceRadioButton;
    private JRadioButton freeRadioButton;
    private JButton continueButton;
    private JButton backButton;
    private JPanel leftpanel,rightpanel,northpanel;
    private JLabel title, leftlabel, rightlabel;
    private String gamemode = "/resources/textFile/WordsList.txt";
    private int hardlevel = 1;

    //actions handlers
    private easyRadioButtonHandler easyHandler;
    private normalRadioButtonHandler normalHandler;
    private hardRadioButtonHandler hardHandler;
    private sportsRadioButtonHandler sportsHandler;
    private scienceRadioButtonHandler scienceHandler;
    private freeRadioButtonHandler freeHandler;
    private continueButtonHandler continueHandler;
    private backButtonHandler backHandler;

    //JFrame
    private JFrame frame;
    //button groups
    ButtonGroup leftgroup;
    ButtonGroup rightgroup;

    //the font of the UI
    Font newFont = new Font("alias", Font.PLAIN, 16);

    public void menu(){
        //buttons
        easyRadioButton = new JRadioButton("Easy");
        normalRadioButton = new JRadioButton("Normal");
        hardRadioButton = new JRadioButton("Hard");
        sportsRadioButton = new JRadioButton("Sports");
        scienceRadioButton = new JRadioButton("Science");
        freeRadioButton = new JRadioButton("Free");
        continueButton = new JButton("Countinue");
        backButton = new JButton("Back");

        //initialize button groups
        ButtonGroup leftgroup = new ButtonGroup();
        ButtonGroup rightgroup = new ButtonGroup();

        //initialize actions handlers
        easyRadioButtonHandler easyHandler = new easyRadioButtonHandler();
        normalRadioButtonHandler normalHandler = new normalRadioButtonHandler();
        hardRadioButtonHandler hardHandler = new hardRadioButtonHandler();
        sportsRadioButtonHandler sportsHandler = new sportsRadioButtonHandler();
        scienceRadioButtonHandler scienceHandler = new scienceRadioButtonHandler();
        freeRadioButtonHandler freeHandler = new freeRadioButtonHandler();
        continueHandler = new continueButtonHandler();
        backHandler = new backButtonHandler();

        //add actions handler to buttons
        easyRadioButton.addActionListener(easyHandler);
        normalRadioButton.addActionListener(normalHandler);
        hardRadioButton.addActionListener(hardHandler);
        scienceRadioButton.addActionListener(scienceHandler);
        sportsRadioButton.addActionListener(sportsHandler);
        freeRadioButton.addActionListener(freeHandler);
        continueButton.addActionListener(continueHandler);
        backButton.addActionListener(backHandler);

        //panels
        leftpanel = new JPanel();
        rightpanel = new JPanel();
        northpanel = new JPanel();
        //leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
        //rightpanel.setLayout(new BoxLayout(rightpanel,BoxLayout.Y_AXIS));
        northpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        leftpanel.setLayout(new GridLayout(5,1));
        rightpanel.setLayout(new GridLayout(5,1));

        //settings for frame
        frame = new JFrame();
        frame.getContentPane().add(northpanel, BorderLayout.NORTH);
        northpanel.setPreferredSize(new Dimension(360,30));
        frame.getContentPane().add(leftpanel, BorderLayout.WEST);
        leftpanel.setPreferredSize(new Dimension(180, 270));
        frame.getContentPane().add(rightpanel, BorderLayout.EAST);
        rightpanel.setPreferredSize(new Dimension(180, 270));
        frame.setTitle("Choose the game mode and hard level");
        frame.setFont(newFont);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(385, 300);
        frame.setLocationRelativeTo(null);

        //JLable
        title = new JLabel("Choose the game mode and hard level");
        title.setFont(newFont);
        leftlabel = new JLabel("Free Mode");
        leftlabel.setFont(newFont);
        rightlabel = new JLabel("Normal");
        rightlabel.setFont(newFont);

        //add components into panels
        rightpanel.add(easyRadioButton);
        rightpanel.add(normalRadioButton);
        rightpanel.add(hardRadioButton);
        rightpanel.add(rightlabel);
        rightpanel.add(continueButton);
        rightpanel.setFont(newFont);
        leftpanel.add(sportsRadioButton);
        leftpanel.add(scienceRadioButton);
        leftpanel.add(freeRadioButton);
        leftpanel.add(leftlabel);
        leftpanel.add(backButton);
        leftpanel.setFont(newFont);
        northpanel.add(title);
        northpanel.setFont(newFont);

        //set button groups
        leftgroup.add(easyRadioButton);
        leftgroup.add(normalRadioButton);
        leftgroup.add(hardRadioButton);
        rightgroup.add(sportsRadioButton);
        rightgroup.add(scienceRadioButton);
        rightgroup.add(freeRadioButton);

        //set the frame visible
        frame.setVisible(true);
    }

    //get file function
    public File getAFile(String name){
        File file = new File(GUIMain.class.getResource(name).getFile());
        return file;
    }

    //Action listeners for buttons

    public class easyRadioButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            hardlevel = 0;
            rightlabel.setText("Hard Level: Easy");
        }
    }

    public class normalRadioButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            hardlevel = 1;
            rightlabel.setText("Hard Level: Normal");
        }
    }

    public class hardRadioButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            hardlevel = 2;
            rightlabel.setText("Hard Level: Hard");
        }
    }

    public class scienceRadioButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            gamemode = "/resources/textFile/scienceList.txt";
            leftlabel.setText("Mode: Science Mode");
        }
    }

    public class sportsRadioButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            gamemode = "/resources/textFile/sportList.txt";
            leftlabel.setText("Mode: Sports Mode");

        }
    }

    public class freeRadioButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            leftlabel.setText("Mode: Free Mode");
        }
    }

    public class continueButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try {
                HangmanGUI gui = new HangmanGUI(new WordList(getAFile(gamemode)));
                gui.setHardlevel(hardlevel);
                gui.play();
                frame.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class backButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            MainMenuSetupGUI gui = new MainMenuSetupGUI();
            gui.menu();
            frame.dispose();
        }
    }



}
