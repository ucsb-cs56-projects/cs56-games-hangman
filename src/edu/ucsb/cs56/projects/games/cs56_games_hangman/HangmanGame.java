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

    public boolean guessLetter(char letter) throws AlreadyTriedException, NotAValidLetterException {
	
	//see if guess is a letter
	
	if (!isLetter(letter)){
	    throw new NotAValidLetterException();
	}
	
	//create character of opposite case as argument
	
	char letterOpposite = Character.isLowerCase(letter) ?
	    Character.toUpperCase(letter) : 
	    Character.toLowerCase(letter);
        boolean letterIsInWord = (secretWord.indexOf((int) letter) != -1)||(secretWord.indexOf((int) letterOpposite) != -1);

        if(letterIsInWord) {
	    if((secretWord.indexOf((int) letter)!=-1 && 
		letters[secretWord.indexOf((int)letter)]==true) ||
		(secretWord.indexOf((int) letterOpposite)!=-1 && 
		    letters[secretWord.indexOf((int)letterOpposite)]==true))
		throw new AlreadyTriedException();
	    else{
		flipLetters(letter);
		return true;
	    }
        } else {
            if(wrongLetters.contains(Character.toLowerCase(letter)))  {
		throw new AlreadyTriedException();
            } else {
                wrongLetters.add(Character.toLowerCase(letter));
                wrongAttemptsLeft--;
		return false;
            }
        }
    }

    // flips * to letters
    public void flipLetters(char letter) {
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

    /** Returns the ArrayList of incorrect guesses the player has made
     */
    public ArrayList<Character> getWrongLetters() {
	return this.wrongLetters;
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
     *@return true if the char the player entered is a valid letter; cannot be a letter or symbol
     */
    
    public boolean isLetter(char guess) {
	return Character.isLetter(guess);
    }
    /**
     *@return the amount of wrong attempts the player has left
     */
    
    public int getWrongAttemptsLeft() {
        return wrongAttemptsLeft;
    }

    /**
     *@set amount of wrong attempts the player has left
     */
    
    public void setWrongAttemptsLeft(int wrongAttemptsLeft) {
	this.wrongAttemptsLeft = wrongAttemptsLeft;
    }

    /**
     *@return the word the player must guess
     */
    
    public String getSecretWord() {
	return secretWord;
    }

    public char getNotFlippedLetter() {
	int i=0;
	char letter = getSecretWord().charAt(i);
	while(letters[i] == true) {
	    i++;
	    letter = getSecretWord().charAt(i);
	}
      	return letter;
    }
}


