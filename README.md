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
 F17 | ferb120 | brjohnson | Graphical hangman game
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

To play single player: 
 * ant runGUI (using a wordfile will randomly select a word from that file)
 * Select single player in main menu
 * Start guessing letters or phrases

To play multiplayer: 
 * ant runGUI (using a wordfile will not affect multiplayer)
 * Select multiplayer in main menu
 * Choose host or join game
 
	Host game:
	* Choose a port number to connect players
	* Your IP address will be shown on the host screen 
	* Choose a word for your opponent to guess
	* Create game

	Join game: 
	* Type in opponents IP address
	* Type shared port number
	* Type in your opponent's word
	* Join game (note: you must create a game before you join it)


F16 final remarks
=================
- The game works as inteneded. At this point the issues with the code just involve adding more features
- The vast majority of the code is in a couple large src files(one being HangmanGUI.java) and the rest of the .java files don't have too much
- Highly recommended to take the time to read through the code before doing anything. It will save you time in the long run and make it much easier to add features if you understand how the code works.
- One helpful tool when working on the code is if you want to use a custom wordlist without going in to the source code to change it you can add  Darg0="/filename/"  to the command line when using ant run


F17 final remarks
=================
- We created a new class hierarchy that correlates with the gui flow. 
  GUIMain -> MainMenuSetup -> 
	(Single Player) -> HangmanGUI -> HangmanGame  
	(Multiplayer) -> Matchmaking -> 
		(Host Game) -> HostServer -> HangmanGUI -> HangmanGame
		(Join Game) -> JoinServer -> HangmanGUI -> HangmanGame
- We recommend trying to make HangmanGUI.java much smaller and more organized by encapulating certain features and moving them to another class. For example, the options button is handled by its listener, and instead of calling an external class to setup the options menu, it is all handled in HangmanGUI.
- When playing multiplayer mode, make sure that if a csil machine is the host then the joining machine must also be a csil machine, or it will not work due to security reasons.
- If you are using ssh to connect to a csil machine to run this program, make sure you download XQuartz and use the -X flag before you enter your credentials to enable X11 forwarding to your mac.  If you are using Windows, download and install MobaXterm and ssh to csil using that instead of an ssh client like PuTTy. 

   

