package edu.ucsb.cs56.projects.games.hangman;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;


/**
 * Main for a GUI version of a Hangman game.
 * <p>
 * This is a wrapper around HangmanGame that can choose random words
 * from a file.
 *
 * @author Gyeonghun Lee
 * @author Phill Conrad, cs56 S13 Lecture
 * @author Blake Zimmerman, cs56 W14
 * @version W14 Version
 * @see HangmanGUI
 * @see WordList
 */


public class GUIMain {
    private static File wordListFile; // null if using default

    //main method that would run the game
    public static void main(String[] args)
            throws IOException {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //InitGlobalFont(new Font("alias", Font.PLAIN, 12));
            if (args.length == 1) {
                wordListFile = new File(args[0]);
            } else {
                wordListFile = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        WordList wordList = new WordList(wordListFile);

        MainMenuSetupGUI msGUI = new MainMenuSetupGUI(wordList);
        msGUI.menu();
    }

    //Used to set font of the UI
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }

    }
}
