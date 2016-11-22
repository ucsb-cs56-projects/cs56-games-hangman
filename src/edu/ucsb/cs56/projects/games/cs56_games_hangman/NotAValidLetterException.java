package edu.ucsb.cs56.projects.games.cs56_games_hangman;
import java.io.*;
import java.util.*;

/** Exception representing an attempt by the player to guess a character that is not a letter
 */

public class NotAValidLetterException extends Exception {
    public NotAValidLetterException(){
	super();
    }
}
