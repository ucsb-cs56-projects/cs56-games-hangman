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

To run: 
 * CLI version: ant runCLI
 * GUI version: ant runGUI

To use a custom wordlist, use ant argument:
   -Darg0="/filename/"

To play either version, start with either a custom or default word list. Then, guess a letter in the word. Proceed in this manner until either you have exhausted your incorrect guesses or completely guessed the word.

#W16 Final Remarks
 This program is an electronic version of a hangman game. There are two modes to this game, the first mode being one player against a default list of words in .txt file (you can also create a file to use) and the second mode being a two player mode in which one player inputs a word and the next player has to try and guess the word. This brings us to one of the bugs in the program. An exception class needs to be created for the two player option because if the two player checkbox is even accidentally clicked, a null pointer exception will be thrown by the program because the user failed to input a word to be guessed by the second player (75 points). Another bug inside the two player mode is that once you enter the two player mode and want to go back to one player mode, unchecking the box does not restart the game in one player, however the restart button does, so you have to make sure that once the box is unchecked, the one player mode is automatically reinitialized (150 points). In addition to that, another very minor bug that occurs when the GUI is first initialized through the "ant run" command is that part of the lower panel is cut off upon initialization blocking the "hints left" and "hints allowed" that are supposed to appear on the screen (75 points). This program not only supports single letter guesses, but also full word guesses that are the same length as the mystery word. But, unfortunately, another bug inside the program causes the gallow to not update after an incorrect word is guessed by a player, even though the number of attempts the user has to guess another letter decrements (75 points). An addition to the program that is not necessarily a bug but that would expand the game is a implementation of an online two player mode (200 points). 
