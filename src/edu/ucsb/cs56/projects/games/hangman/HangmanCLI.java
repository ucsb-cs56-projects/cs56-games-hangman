package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.util.*;

public class HangmanCLI implements HangmanInterface {
    private Scanner scan;
    private String word;
    private char letter;
    private HangmanGame hg;

    public HangmanCLI(HangmanGame hg) {
	this.hg = hg;
    }

    //begins the game and does error checking

    public void play() {
        scan = new Scanner(System.in);

	while((hg.getWrongAttemptsLeft() != 0) && !hg.hasWon()) {
            System.out.println(getGallows());
            System.out.println(hg.getBoard());

            System.out.print("Guess a letter: ");
            System.out.flush();
	    word = scan.nextLine();

            if(word.length() < 1) {
                System.out.println("Must type something.");
                continue;
            } else if(word.length() > 1) {
                System.out.println("Do not type more than one letter at a time.");
		continue;
            }

            letter = word.charAt(0);
      

	    //  /**commenting out 1:32 6265
      	    
	    try{

		//*/
		if(hg.guessLetter(letter) == true)
		    System.out.println("Good Guess!");
		else
		    System.out.println("wrongAttemptsLeft left: " + hg.getWrongAttemptsLeft());
	} 

	///**
	catch(AlreadyTriedException ex) {
		System.out.println("You have already tried that letter, please try another one.");
	    }

	//*/
	    if(hg.hasWon())
		System.out.println("Congratulations, You have won!");

            if(hg.hasLost()) {
                System.out.println(getGallows());
                System.out.println(hg.getBoard());
		System.out.println("Sorry, you have lost!");
                System.out.println("The secret word was " + hg.getSecretWord());
                System.out.println("Try better next time!");
            }
        }
    }

    //Print the hangman image on console                                                                                                            
    private String getGallows() {
        String gallow[] = new String[7];

        gallow[0] = "    |---|\n    o   |\n   /|\\  |\n   /_\\__|_\n";
        gallow[1] = "    |---|\n    o   |\n   /|\\  |\n   /____|_\n";
        gallow[2] = "    |---|\n    o   |\n   /|\\  |\n    ____|_\n";
        gallow[3] = "    |---|\n    o   |\n   /|   |\n    ____|_\n";
        gallow[4] = "    |---|\n    o   |\n   /    |\n    ____|_\n";
        gallow[5] = "    |---|\n    o   |\n        |\n    ____|_\n";
        gallow[6] = "    |---|\n        |\n        |\n    ____|_\n";

        return gallow[hg.getWrongAttemptsLeft()];
    }
}
