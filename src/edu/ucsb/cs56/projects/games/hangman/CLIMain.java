package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.util.*;
import java.awt.*;

/** Main for a CLI version of a Hangman game.

    This is a wrapper around HangmanGame that can choose random words
    from a file.

    @author Gyeonghun Lee
    @author Phill Conrad, cs56 S13 Lecture
    @version S13 Lecture Version
    @see HangmanCLI
    @see WordList
*/



public class CLIMain {
    private static File wordListFile; // null if using default

    //main method that would run the game
    public static void main(String [] args) 
	throws IOException  {
	
        if(args.length == 1) {
            wordListFile = new File(args[0]);
        } else {
            wordListFile = null;
        }
	
	WordList wordList = new WordList(wordListFile);
	new HangmanCLI(wordList).play();
	
    }
}
