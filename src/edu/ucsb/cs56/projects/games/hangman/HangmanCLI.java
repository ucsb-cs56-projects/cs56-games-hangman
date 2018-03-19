package edu.ucsb.cs56.projects.games.hangman;

import java.io.IOException;
import java.util.Scanner;

/**
 * A class that implements a command-line interface for a hangman-style game
 *
 * @author Evan West
 * @author David Borden and Ernesto Cojulun
 * @version Spring 2013, CS56
 * @see HangmanGame
 * @see WordList
 */

public class HangmanCLI implements HangmanInterface {
    private Scanner scan;
    private String word;
    private char letter;
    private HangmanGame hg;

    /**
     * Constructor for CLI that takes an already-created game instance
     *
     * @param hg An instance of a HangmanGame already created
     */
    public HangmanCLI(HangmanGame hg) {
        this.hg = hg;
    }

    /**
     * Constructor for CLI that takes a wordList parameter to use
     *
     * @param wordList A wordList object that contains the words to select from
     */
    public HangmanCLI(WordList wordList) throws IOException {
        this.hg = new HangmanGame(wordList.getRandomLine());
    }

    /**
     * Begins the game and plays one round
     * incorporates error checking
     */
    public void play() {
        scan = new Scanner(System.in);

        while ((hg.getWrongAttemptsLeft() != 0) && !hg.hasWon()) {
            System.out.println(getGallows());
            System.out.println(hg.getBoard());
            String wrongGuesses = "Wrong guesses:";
            for (Character item : hg.getWrongLetters()) {
                wrongGuesses += (" " + item);
            }
            System.out.println(wrongGuesses);
            System.out.flush();
            System.out.println("Guess a letter: ");
            System.out.flush();
            word = scan.nextLine();

            if (word.length() < 1) {
                System.out.println("Must type something.");
                continue;
            } else if (word.length() > 1) {
                System.out.println("Do not type more than one letter at a time.");
                continue;
            }

            letter = word.charAt(0);

            try {
                if (hg.guessLetter(letter) == true)
                    System.out.println("Good Guess!");
                else
                    System.out.println("Wrong! Guesses left: " + hg.getWrongAttemptsLeft());
            } catch (AlreadyTriedException ex) {
                System.out.println("You have already tried that letter, please try another one.");
            }

            if (hg.hasWon()) {
                System.out.println("Congratulations, You have won!\nThe word was: " + hg.getSecretWord());
            }

            if (hg.hasLost()) {
                System.out.println(getGallows());
                System.out.println(hg.getBoard());
                System.out.println("Sorry, you have lost!");
                System.out.println("The secret word was " + hg.getSecretWord());
                System.out.println("Try better next time!");
            }
        }
    }

    /**
     * Gets the ASCII representation of the gallows
     *
     * @return A String representing the current state of the gallows
     */

    private String getGallows() {
        String gallow[] = new String[7];

        gallow[0] = "    |---|\n    o   |\n   /|\\  |\n   /_\\__|_\n";
        gallow[1] = "    |---|\n    o   |\n   /|\\  |\n   /____|_\n";
        gallow[2] = "    |---|\n    o   |\n   /|\\  |\n    ____|_\n";
        gallow[3] = "    |---|\n    o   |\n   /|   |\n    ____|_\n";
        gallow[4] = "    |---|\n    o   |\n   /    |\n    ____|_\n";
        gallow[5] = "    |---|\n    o   |\n        |\n    ____|_\n";
        gallow[6] = "    |---|\n        |\n        |\n    ____|_\n";
        int wrongAttemptsLeft = hg.getWrongAttemptsLeft();
        if (wrongAttemptsLeft > 6)
            wrongAttemptsLeft = 6;
        return gallow[wrongAttemptsLeft];
    }
}
