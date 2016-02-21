package edu.ucsb.cs56.projects.games.cs56_games_hangman;
import java.io.*;
import java.util.*;

/** Exception representing an attempt by the player to guess a character that has already been guessed
 */
public class AlreadyTriedException extends Exception {
    public AlreadyTriedException() {
	super();
    }
}