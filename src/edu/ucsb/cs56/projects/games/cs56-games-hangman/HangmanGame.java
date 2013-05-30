package edu.ucsb.cs56.projects.games.cs56_games_hangman;
import java.io.*;
import java.util.*;

// This handles a game session (with just one word).
public class HangmanGame {
    private ArrayList<Character> wrongLetters = new ArrayList<Character>(0);
    private int wrongAttemptsLeft = 6;
    private String secretWord;
    private boolean [] letters;

    public HangmanGame(String secretWord) {
        this.secretWord = secretWord;
        this.letters = new boolean[secretWord.length()];
        Arrays.fill(letters, false);

    }

    /**
     *@return true if the letter is in the word and false if the letter is not in theword
     *@throws AlreadyTriedException if the letter inputted has already been guessed
     */ 

    public boolean guessLetter(char letter) throws AlreadyTriedException {
	char letterOpposite = Character.isLowerCase(letter) ? Character.toUpperCase(letter) : Character.toLowerCase(letter);
	System.out.println("Guess: "+letter+" -- Opposite: "+letterOpposite);
        boolean letterIsInWord = (secretWord.indexOf((int) letter) != -1)||(secretWord.indexOf((int) letterOpposite) != -1);

        if(letterIsInWord) {
            flipLetters(letter);
            return true;
        } else {
            if(wrongLetters.contains(letter))  {
		throw new AlreadyTriedException();
            } else {
                wrongLetters.add(letter);
                wrongAttemptsLeft--;
		return false;
            }
        }
    }

    // flips * to letters
    private void flipLetters(char letter) {
	//create character of opposite case as argument
	char letterOpposite = Character.isLowerCase(letter) ? Character.toUpperCase(letter) : Character.toLowerCase(letter);
        for(int i = 0; i < secretWord.length(); i++) {
            if(letter == secretWord.charAt(i) || letterOpposite == secretWord.charAt(i))
                letters[i] = true;
        }
    }
    
    //Make a word with * and if player gets right, it reveals the letters.

    /**
     *@return the word with any correct letters the player may have guessed
     */
 
    public String getBoard() {
        String revealedLetters = "";

        for(int i = 0; i < secretWord.length(); i++) {
            if(letters[i] == false)
                revealedLetters += "*";
            else
                revealedLetters += secretWord.substring(i, i+1);
        }
        return revealedLetters;
    }

    //Check if all the values are True or not 

    /**
     *@return true if the player has won and false if the player has not won
     */

    public boolean hasWon() {
        for(int i = 0; i < letters.length; i++) {
            if(!letters[i])
                return false;
        }
        return true;
    }

    //Check if you lost the game

    /**
     *@return true if the player lost the game and false if the player has not lost the game
     */
    public boolean hasLost() {
        return wrongAttemptsLeft == 0;
    }

    /**
     *@return the amount of wront attempts the player has left
     */

    public int getWrongAttemptsLeft() {
        return wrongAttemptsLeft;
    }

    /**
     *@return the word the player must guess
     */

    public String getSecretWord() {
	return secretWord;
    }
}