cs56-games-hangman
==================

W14 Ready! (Brynn Kiefer)

Hangman game with both Command-line and Graphical interfaces.

project history
===============
```
YES | mastergberry | bzimm | Graphical hangman game
```
```
 W14 | bkiefer13 5pm | bzimm | Graphical hangman game
```
 F16 | diegosegundo | aermakovucsb | Graphical hangman game
```

To run: 
 * CLI version: ant runCLI
 * GUI version: ant runGUI

To use a custom wordlist, use ant argument:
   -Darg0="/filename/"

To play either version, start with either a custom or default word list. Then, guess a letter in the word. You can either use the submit button or simply hit the enter button on the keyboard. Proceed in this manner until either you have exhausted your incorrect guesses or completely guessed the word.

For the GUI version you enter a letter (or phrase) into the text field provided and hit submit. If the letter is correct then the *'s are replaced with that letter. If you guess wrong then the letter appears in the wrong guesses list, you lose a guess remaining, and a portion of the hangman figure appears. If you are having trouble guessing the word you can use the hint button, but this will lower your points earned.

Additional, the ability to change the color of the background and/or the hangman figure is available in the options menu. You can do so at the begginning of the game or while the game is running.

For full phrases, you will be given 1 additional guesses remaining per additional word. Also, spaces appear as a * and can be correctly guessed by inputing a space into the text field.

The Package used for the source code is package "edu.ucsb.cs56.projects.games.hangman"

The source code is stored under the folder named src. The additional images used in the game have been sorted under the resources directory. Note, when accessing one of these files in the code you first have to type "resources/" before the file name.

For the ant targets, you can call "ant compile" to compile, "ant run" to start the game, or "ant test" to run the 8 implemented tests to make sure that the code is running properly.
