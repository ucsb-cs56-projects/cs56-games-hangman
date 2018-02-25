package edu.ucsb.cs56.projects.games.hangman;

import javax.swing.*;
import java.awt.*;

public class selectGUI {

    private JRadioButton easyRadioButton;
    private JRadioButton normalRadioButton;
    Font newFont = new Font("alias", Font.PLAIN, 16);

    public void menu(){
        JFrame frame = new JFrame("selectGUI");
        frame.setContentPane(new selectGUI().chooseUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(350, 300);
        frame.setVisible(true);
    }

    private JRadioButton hardRadioButton;
    private JRadioButton sportsRadioButton;
    private JRadioButton scienceRadioButton;
    private JRadioButton freeRadioButton;
    private JButton continueButton;
    private JButton backButton;
    private JPanel chooseUI;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
