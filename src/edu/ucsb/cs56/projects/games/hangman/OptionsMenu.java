package edu.ucsb.cs56.projects.games.hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsMenu {
    HangmanGUI gui;
    private JFrame f,o,CustomColorFrame;
    private JPanel optionsUpper,optionsLower, optionsCenter,colorPanel;
    private JButton randBGColor,randHMColor,CustomColorButton,finish,cancel,customBGC,customHMC;
    private Font newFont;
    private JColorChooser colorChooser;
    private squareComponent square;
    private JComponent gallow;
    // Button handlers
    private randHMCButtonHandler randHMCHandler;
    private randBGCButtonHandler randBGCHandler;
    private finishButtonHandler finishHandler;
    private cancelButtonHandler cancelHandler;
    private customButtonHandler customHandler;
    private customHMCButtonHandler customHMCHandler;
    private customBGCButtonHandler customBGCHandler;
    private Color randHMC;
    private String optionsOn = "false";

    public OptionsMenu(HangmanGUI gui){
        this.gui = gui;
        this.randHMC=gui.getRandHMC();
        this.gallow = gui.getGallow();
        this.f = gui.getF();
        this.o = gui.getO();
        newFont = new Font("alias", Font.PLAIN, 16);
    }
    public void buildOptions() {
        // creates new Options JFrame
        o = new JFrame();
        o.setSize(400, 400);

        // creates upper panel for Options
        optionsUpper = new JPanel();
        optionsUpper.setLayout(new BoxLayout(optionsUpper, BoxLayout.Y_AXIS));
        o.getContentPane().add(optionsUpper, BorderLayout.NORTH);

        randBGColor = new JButton("Random Background Color");
        randBGColor.setFont(newFont);
        randBGCHandler = new randBGCButtonHandler();
        randBGColor.addActionListener(randBGCHandler);

        randHMColor = new JButton("Random Hangman Color");
        randHMColor.setFont(newFont);
        randHMCHandler = new randHMCButtonHandler();
        randHMColor.addActionListener(randHMCHandler);

        CustomColorButton = new JButton("Choose Custom Color");
        CustomColorButton.setFont(newFont);
        customHandler = new customButtonHandler();
        CustomColorButton.addActionListener(customHandler);


        // color switch
        square = new squareComponent();
        o.getContentPane().add(square, BorderLayout.CENTER);

        // add options components to upper panel
        optionsUpper.add(randBGColor);
        randBGColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsUpper.add(randHMColor);
        randHMColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsUpper.add(CustomColorButton);
        CustomColorButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // creates lower panel for Options
        optionsLower = new JPanel();
        optionsLower.setLayout(new FlowLayout());
        o.getContentPane().add(optionsLower, BorderLayout.SOUTH);

        finish = new JButton("Finish");
        finish.setFont(newFont);
        finishHandler = new finishButtonHandler();
        finish.addActionListener(finishHandler);

        cancel = new JButton("Cancel");
        cancel.setFont(newFont);
        cancelHandler = new cancelButtonHandler();
        cancel.addActionListener(cancelHandler);

        // add finish and cancel to lower panel
        optionsLower.add(finish);
        optionsLower.add(cancel);

        o.setLocationRelativeTo(null);
        o.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        o.setTitle("Options for Hangman");
        o.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                f.setEnabled(true);
            }
        });
        o.setVisible(true);
    }

    public class squareComponent extends JComponent {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(randHMC);
            g2.drawRect(150,50,100,100);
            g2.fillRect(150,50,100,100);
        }
    }

    /**
     * Handler for random background color button
     */
    public class randBGCButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // create a random color
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            Color randBGC = new Color(red, green, blue);
            f.getContentPane().setBackground(randBGC);
        }
    }

    public class randHMCButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //create a random color
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            randHMC = new Color(red, green, blue);
            gui.setRandHMC(randHMC);
            gallow.repaint();
            gui.repaintgallow();
            square.repaint();
        }
    }

    public void buildCustomColor() {
        // creates new Color JFrame
        o.dispose();
        CustomColorFrame = new JFrame();
        CustomColorFrame.setSize(650, 400);
        CustomColorFrame.setLocationRelativeTo(null);
        CustomColorFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                f.setEnabled(true);
            }
        });
        colorPanel = new JPanel();

        customBGC = new JButton("Change Background Color");
        customBGC.setFont(newFont);
        customBGCHandler = new customBGCButtonHandler();
        customBGC.addActionListener(customBGCHandler);

        customHMC = new JButton("Change Hangman Color");
        customHMC.setFont(newFont);
        customHMCHandler = new customHMCButtonHandler();
        customHMC.addActionListener(customHMCHandler);

        colorChooser = new JColorChooser();


        colorPanel.setLayout(new FlowLayout());
        colorPanel.add(colorChooser);
        colorPanel.add(customBGC);
        colorPanel.add(customHMC);


        CustomColorFrame.add(colorPanel);
        CustomColorFrame.setVisible(true);
    }

    /**
     * Handler for finish button
     */
    public class finishButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            optionsOn = "true"; // intended to save and active chosen options
            f.setEnabled(true);
            o.dispose();
        }
    }
    /**
     * Handler for cancel button
     */
    public class cancelButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            optionsOn = "false"; // intended to cancel chosen options
            f.setEnabled(true);
            o.dispose();
        }
    }

    public class customButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buildCustomColor();
        }
    }

    //ADDED
    public class customBGCButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color customBack = colorChooser.getColor();
            f.getContentPane().setBackground(customBack);
        }
    }

    //ADDED
    public class customHMCButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            randHMC = colorChooser.getColor();
            gui.setRandHMC(randHMC);
            square.repaint();
            gallow.repaint();
            gui.repaintgallow();
        }
    }
    //Getters and setters
    public JFrame getF() {
        return f;
    }
    public void setF(JFrame f) {
        this.f = f;
    }
    public JFrame getO() {
        return o;
    }
    public void setO(JFrame o) {
        this.o = o;
    }
    public JComponent getGallow() {
        return gallow;
    }
    public void setGallow(JComponent gallow) {
        this.gallow = gallow;
    }
    public Color getRandHMC(){
        return randHMC;
    }
}
