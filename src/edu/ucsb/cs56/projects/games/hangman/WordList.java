package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.util.*;

public class WordList {
    private File wordListFile;


    /**
     *@param takes in a file that contains a word list in which the word to be guessed is chosen.
     */
    public WordList(File wordListFile) {
	this.wordListFile = wordListFile;
    }
    
    //Pick a random word from counted lines

    /**
     *@return a random word from the word list
     *@throws IOException If an input or output exception occured
     */

    public String getRandomLine() throws IOException {
        int lineCount = getNumberOfLines();
        int lineNumber = new Random().nextInt(lineCount);

        BufferedReader reader = new BufferedReader(new InputStreamReader(getWordListInputStream()));

        try {
            for(int i=0; i < lineNumber; i++) {
                reader.readLine();
            }
            return reader.readLine();
        } finally {
            reader.close();
        }
    }

    //Count the number of lines                                                                                                                     
    /**
     *@return the number of lines in the word list
     *@throws IOException If an input or output exception occured
     */

    private int getNumberOfLines() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getWordListInputStream()));

        try {
            int count = 0;
            while(reader.readLine() != null) {
                count++;
            }
            return count;
        } finally {
            reader.close();
        }
    }

    /**
     *@return the input stream of the word list
     *@throws FileNotFoundException if the word list file can not be found
     */

    private InputStream getWordListInputStream() throws FileNotFoundException {
        if(wordListFile == null) {
            // gets the WordsList.txt file from the class path                                                                                       
            return Main.class.getClassLoader().getResourceAsStream("resources/WordsList.txt");
        } else {
            return new FileInputStream(wordListFile);
        }
    }
}
