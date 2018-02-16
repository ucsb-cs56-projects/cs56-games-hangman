Zichen Sun
Sihao Choy
### a
Description of the project: Game of guessing letters in a word within 6 times.
### b
1. As a user I can get a hint so that when I could not find any clue of the word the program could give me an instruction.
2. As a user I can change color of the UI so that when I get board of the color of the UI I could change it.
3. As a user I can choose multiplayers mode so that when I want to play with someone else.
### c
Yes it works.
It is a game for user to guess letters in a word. If the letter they guess is correct the program will fill in the letter. If they did not get the correct answer the program will draw one part of the hangingman, and if the program finished drawing the hangingman the user loss.
### d
1. As a player I could select the level of difficulties so that I could try different level of hardness.
2. As a player I could select the kind of word sets I want to play on so that different people who do well in different fields could select different word set to play.
### e
The README.md file is acutaully not very good. It does not seems like a README.md for a game. It at less have to has piscures for the game. It should be reformated. The important details should be marked.  
1. We can add more details in the instructions like "You can push the hint button when you could not find a clue."
2. We can add some pictures of the game.
### f
Base on the knowledge I had, the build.xml is good, every targets has a description. But the problem is there is not enough information in README.md file to instruct users how to use all the features in the build.xml. There are some other functionalities like CLI mode or inputting word list by user themselves that have not been mentioned in the README.md file.
### g
1000 points are able to finished, but it is not easy. 
Actually I do not think these issues are that important.  I think the most important improvement for this project was the UI. 
#### The issues we plan to deal with:
1. Leaderboard Implementation 100
2. Adding different difficultly levels 200
3. Sound on/off option in Option Menu 50
4. Hangman Image 100
5. Add more javadoc descriptions 100
6. Refactor Hangman GUI 400
7. Hint Limit 200
8. Adding a "Back" button 150
I think the issues are enough for us now to do the project. And all the issues are pretty clear.
### h
(link:https://github.com/ucsb-cs56-projects/cs56-games-hangman/issues/85)
Add different vocabulary sets and set up another UI to let user select different types of vocabulary sets. 200 pts
### i
All the structure of codes in this project is pretty clear. The ex-coders have added descriptions at suitable place.

One screenful of text:

As the instructions in the README.md, user type ant runGUI or ant runCLI to start the game. If the user choose the GUI mode, the program will run the main method in class GUIMain.java. The main function will create call class Wordlist to either grab words from default file or grab words from the file that user input to generate the wordset for the game. And it will call the MainMenuSetupGUI to generate the first UI of the game to let user choose single mode or multiplayer mode.

iIn the multiplayer mode the project will call the class Matchmaking to deal with multiplayer mode game. For single mode, the project will go to the class HangmanGUI which is the UI of the game which is what is showing to the player when the game is running, and the HangmanGUI will call connected with an object of the HangmanGame class to do calculations for the game.

If user choose the CLI mode which is the command line mode, everything is basically the same. The CLIMain will call the HangmanCLI which is the operating the output in the command line. And this class will also call an object of the HangmanGame to do calculations for the game.
### j
There is only one HangmanGameTest.java in it. The test just covers the HangmanGame class which is the inner core class of the game.There are definitely lots of opportunities for us to add some tests for it. We have to add the test code for the issues which we are going to work with because they are all new stuffs for this game, so we think it is necessary.


