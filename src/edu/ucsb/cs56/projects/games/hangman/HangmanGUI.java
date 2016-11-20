package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/** HangmanGUI.java is a program that has added features from a
 * previous Hangman game

 *@author Gyeonghun Lee
 *@author David Borden and Ernesto Cojulun
 *@author Evan West
 *@author Blake Zimmerman, CS56 W14
 *@author Tripti Singhal & Erica Carnohan W15
 *@version Winter 2014, CS56
 *@see HangmanGame
 *@see WordList
 */

public class HangmanGUI extends JFrame implements HangmanInterface {

    // Primitive variables
    public static final boolean soundOn = false;
    private int hintsA, hintsL, numPoints, numWins, numLosses;
    private String optionsOn = "false", randBGColOn = "false", BGColorOn = "false";

    // Swing components
    private HangmanGame hg;
    private JComponent gallow, square;                                                      //ADDED
    private JLabel prompt, board, message, guesses, guessesRemaining, hintsAllowed, hintsLeft, points, wins, losses, gif, redLabel, blueLabel, greenLabel;
    private JTextField lettertf, redTF, blueTF, greenTF ;
    private JButton submit, exit, restart, instructions, hint, options, randBGColor,randHMColor, finish, cancel, CustomColorButton, customBGC, customHMC;
    private WordList wordList;
    private JPanel upper, lower, lowerRight, optionsUpper, optionsLower, optionsCenter, colorPanel;
    private JFrame f, o, big, CustomColorFrame;                // ADDED
    private Applet song;
    private Color randBGC, CustomBGC;
    // default hangman is tan colored
    private Color randHMC = new Color(0xFFBB88);


    // Button handlers
    private SubmitButtonHandler submitHandler;
    private ExitButtonHandler exitHandler;
    private RestartButtonHandler restartHandler;
    private instructButtonHandler instructHandler;
    private hintButtonHandler hintHandler;
    private optionsButtonHandler optionsHandler;
    private finishButtonHandler finishHandler;
    private customButtonHandler customHandler;   //ADDED
    private customHMCButtonHandler customHMCHandler;
    private customBGCButtonHandler customBGCHandler; 
    private cancelButtonHandler cancelHandler;
    private randBGCButtonHandler randBGCHandler;
    private randHMCButtonHandler randHMCHandler;

    // Font for text
    Font newFont = new Font("serif", Font.BOLD, 16);
    
    /** Constructor for GUI that takes a Wordlist parameter
     *@param wordList takes in the wordList.txt by default but will also take in a user created wordList
     */

    public HangmanGUI(WordList wordList) throws IOException {
	hg = new HangmanGame(wordList.getRandomLine());
	this.wordList = wordList;
    }
    
    /** Initializes and shows all GUI elements
     */
    public void play() {
	f = new JFrame();
	f.setSize(700, 600);

	big = new JFrame();
	big.pack();
        big.setLocationRelativeTo(null);

	// create upper panel
	JPanel upper = new JPanel();
	upper.setLayout(new BoxLayout(upper,BoxLayout.Y_AXIS));
	upper.setOpaque(false);
	
	// create upper panel items
     	gallow = new hanger();
    	message = new JLabel();
	gif = new JLabel();
	guessesRemaining = new JLabel("Guesses Remaining: "+hg.getWrongAttemptsLeft());
	message.setFont(newFont);
	guessesRemaining.setFont(newFont);
	board = new JLabel(hg.getBoard());
	board.setFont(newFont);
	guesses = new JLabel("Wrong guesses: ");
	guesses.setFont(newFont);
	prompt = new JLabel("Guess a letter: ");
	prompt.setFont(newFont);
	lettertf = new JTextField(3);

	submit = new JButton("Submit");
	submit.setFont(newFont);
	submitHandler = new SubmitButtonHandler();
	submit.addActionListener(submitHandler);

	String numOfWins = "Wins: " + numWins;
	wins = new JLabel(numOfWins);
	wins.setFont(newFont);

	String numOfLosses = "Losses: " + numLosses;
	losses = new JLabel(numOfLosses);
	losses.setFont(newFont);

	String numOfPoints = "Points: " + numPoints;
	points = new JLabel(numOfPoints);
	points.setFont(newFont);

	f.getRootPane().setDefaultButton(submit); //Use "Enter key" as default for submit button.
	f.getContentPane().add(upper,BorderLayout.NORTH);
	
	//add items to the upper panel
	upper.add(gallow);
	upper.add(message);
	upper.add(board);
	upper.add(guessesRemaining);
	upper.add(guesses);
	upper.add(prompt);
	upper.add(lettertf);
	upper.add(submit);
	upper.add(wins);
	upper.add(losses);
	upper.add(points);

	big.add(gif);   //ADDED
	
	// center upper panel items
	message.setAlignmentX(Component.CENTER_ALIGNMENT);
	board.setAlignmentX(Component.CENTER_ALIGNMENT);
	guesses.setAlignmentX(Component.CENTER_ALIGNMENT);
	guessesRemaining.setAlignmentX(Component.CENTER_ALIGNMENT);
	prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
	submit.setAlignmentX(Component.CENTER_ALIGNMENT);
	wins.setAlignmentX(Component.CENTER_ALIGNMENT);
	losses.setAlignmentX(Component.CENTER_ALIGNMENT);
	points.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	// create lower panel
	JPanel lower = new JPanel();
	lower.setLayout(new FlowLayout());
	lower.setOpaque(false); //T
	

	// create lower panel items
	instructions = new JButton("Instructions");
	instructions.setFont(newFont);
	instructHandler = new instructButtonHandler();
	instructions.addActionListener(instructHandler);

	exit = new JButton("Exit");
	exit.setFont(newFont);
	exitHandler = new ExitButtonHandler();
	exit.addActionListener(exitHandler);
	
	options = new JButton("Options");
	options.setFont(newFont);
	optionsHandler = new optionsButtonHandler();
	options.addActionListener(optionsHandler);

	restart = new JButton("Restart");
	restart.setFont(newFont);
	restartHandler = new RestartButtonHandler();
	restart.addActionListener(restartHandler);

	hint = new JButton("Hint");
	hint.setFont(newFont);
        hintHandler = new hintButtonHandler();
	hint.addActionListener(hintHandler);

	// create a new panel in the lower right part of the frame
	JPanel lowerRight = new JPanel();
	lowerRight.setLayout(new BoxLayout(lowerRight,BoxLayout.Y_AXIS));
	lowerRight.setOpaque(false);	//T
	String hintsAllowedString = "Hints allowed: " + getHintsAllowed();
	hintsAllowed = new JLabel(hintsAllowedString);
	hintsAllowed.setFont(newFont);
	String hintsLeftString = "Hints left: " + hintsL;
	hintsLeft = new JLabel(hintsLeftString);
	hintsLeft.setFont(newFont);

	f.getContentPane().add(lower,BorderLayout.SOUTH);

	//add items to lower panel
	lower.add(instructions);
	lower.add(options);
	lower.add(exit);
	lower.add(restart);
	lower.add(hint);
	lowerRight.add(hintsAllowed);
	lowerRight.add(hintsLeft);
	lower.add(lowerRight);
	
	f.setLocationRelativeTo(null); // centers frame on screen
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	f.setTitle("Hangman Game: GUI Version");
	f.setVisible(true);

	//   ADDED
        big.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	big.setTitle("Game Over");
	big.setVisible(false);
	
    }

	//Specify handlers for each button and add (register) ActionListeners to each button.
    
    /** Inner class for Handler for Submit button
	(button that handles guess commits)
	Handles all game state and UI changes
     */
    public class SubmitButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    String word = lettertf.getText();   
	    repaint();
	    MakeSound sound = new MakeSound();

	    lettertf.requestFocusInWindow();
	    
	    // inform the player if they left the textfield blank and clicked submit
	    // or if they typed more than one character and clicked submit
	    if(word.length() < 1) {
		message.setText("Must type something!");
		if (soundOn) {
		    sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/Glass.aiff")); 
		}
		
		return;
	    }
	    else if(word.length() > 1) {
		//check to see if player entered a full word guess
		if(word.length() == hg.getSecretWord().length()) {
		    if(hg.guessWord(word)==true) {
			message.setText("You guessed the word!");
			int count = 0;
			for(int i = 0; i<hg.getBoard().length(); i++) {
			    if(hg.getBoard().charAt(i) == '*') 
				count++;
			}
			numPoints += count;
			String numOfPoints = "Points: " + numPoints;
			points.setText(numOfPoints);
			if(soundOn) {
			    sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/Glass.aiff"));
			}
			lettertf.setText("");
			repaint();
		    }
		    //if word is wrong
		    //add bodypart to hangman and decrement guesses left
		    else {
			message.setText("Incorrect");
			guessesRemaining.setText(""+hg.getWrongAttemptsLeft());
			if(soundOn) {
			    sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/Glass.aiff"));
			}
			gallow.repaint();
			repaint();
			lettertf.setText("");
		    }
		}
		else{
		    message.setText("Please enter only one letter or whole word.");
		    if (soundOn) { 
			sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/Sosumi.aiff"));
		    }
		
		    lettertf.setText("");
		    repaint();
		    return;
		}
		board.setText(hg.getBoard());
	    }//end else-if
	    else{
	    char letter = word.charAt(0);
	    lettertf.setText("");
	    
	    try{
		    // inform the player when the guess is correct, one point is earned
		    if(hg.guessLetter(letter) == true) {
			numPoints++;
			String numOfPoints = "Points: " + numPoints;
			points.setText(numOfPoints);
			message.setText("Good Guess!");
			if (soundOn) {
			    sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/Glass.aiff"));
			}
			repaint();
		    }
		    else
			{
			    // inform the player when the guess is incorrect
			    message.setText("Incorrect");
			    guessesRemaining.setText("Guesses Remaining: "+hg.getWrongAttemptsLeft());
			    if (soundOn) {
				sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/Glass.aiff"));
			    }
			    gallow.repaint();
			    repaint();
			    
			}
		    
		} catch(AlreadyTriedException ex) {
		    // inform the player when a guess has already been tried
		    message.setText("You have already tried that letter, please try another one.");
		    if (soundOn) {
			sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/Glass.aiff"));
		    }
		repaint();
		
	    }
		
		board.setText(hg.getBoard());
	    }
	    //update incorrect guesses
	    String wrongGuesses = "Wrong guesses:";
	    for(Character item : hg.getWrongLetters()){
		wrongGuesses+=(" "+item);
	    }
	    guesses.setText(wrongGuesses);

	    // inform if the player wins, ten points are earned
	    if(hg.hasWon()) {
		numWins++;
		numPoints += 10;
		String numOfWins = "Wins: " + numWins;
		wins.setText(numOfWins);
		String numOfPoints = "Points: " + numPoints;
		points.setText(numOfPoints);
		submit.setEnabled(false);
		lettertf.setEditable(false);
		prompt.setText("");
		message.setText("Congratulations, you have won!");
			if (soundOn) {
		     sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/BOO.wav"));
			}
		gif.setIcon(new ImageIcon("resources/images/WinLogo.gif"));
		gif.validate();
	      	big.setLocationRelativeTo(null); // centers frame on screen
		gif.setSize(850,247);
		big.pack();
		big.setVisible(true);
			
	    }
	    // inform if the player loses, five points are removed if the player has five or more points
	    if(hg.hasLost()) {
		numLosses++;
		String numOfLosses = "Losses: " + numLosses;
		losses.setText(numOfLosses);
		if (numPoints > 4)
		    numPoints -= 5;
		else
		    numPoints = 0;
		String numOfPoints = "Points: " + numPoints;
		points.setText(numOfPoints);
		submit.setEnabled(false);
		lettertf.setEditable(false);
		prompt.setText("");
	       	message.setText("Sorry, you have lost!" + "  " + "The secret word was " + hg.getSecretWord() + ".  " + "Try again!");
	        gif.setIcon(new ImageIcon("resources/images/LoseLogo.gif"));
		gif.validate();
		big.setLocationRelativeTo(null); // centers frame on screen
		big.setSize(563,155);
		big.pack();
		big.setVisible(true);
		
			if (soundOn) {
		      sound.playSound( GUIMain.class.getClassLoader().getResourceAsStream("resources/sounds/Glass.aiff"));
			}

			big.setVisible(true);	
	    }
	     repaint();
	     
	}
    }
    

    /** Handler for instructions button
	Button displays a messageDialog with text instructions
     */

    public class instructButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    JOptionPane.showMessageDialog(f,
		"Each * represents a letter in a word.\nYour task is to guess each letter in the word until you complete the word.\nYou may either enter a single letter or full word guess.\nEach wrong guess adds a body part to the hanger.\nIf an entire man is created (6 wrong guesses), you lose the game.\nIf you guess the word before then, however, you win!\nTo get a letter that is in the word press the hint button.\nA pop-up message will display with a letter from a random position in the word.\n\nThe point system is the following:\n+10 for a win.\n+1 for a correct guess.\n-5 for a loss.\n-1 for a used hint.\n",
		"Instructions", 
		JOptionPane.INFORMATION_MESSAGE);
	}
    }
    
    /** Handler for exit button.
	Button closes the application
     */
    public class ExitButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    System.exit(0);
	}
    }
    
    /** Handler for Restart Button.
	Clears old game state, create a new game, and updates UI accordingly.
     */
    public class RestartButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    lettertf.requestFocusInWindow();
	    submit.setEnabled(true);
	    lettertf.setEditable(true);
	    big.setVisible(false);
	    prompt.setText("Guess a letter: ");
	    
	    // attempt to start a new game, updating hints allowed and hints left
	    try {
		hg = new HangmanGame(wordList.getRandomLine());
		board.setText(hg.getBoard());
		message.setText("");
		guessesRemaining.setText("Guesses Remaining: " + hg.getWrongAttemptsLeft());
		guesses.setText("Wrong guesses:");
		String hintsAllowedString = "Hints allowed: " + getHintsAllowed();
		hintsAllowed.setText(hintsAllowedString);
		String hintsLeftString = "Hints left: " + hintsL;
		hintsLeft.setText(hintsLeftString);
		repaint();
		gallow.repaint();
	    } catch(IOException ex) {
		throw new RuntimeException(ex);
	    }
	}
    }

    /** Handler for Hint button.
	Button pops up a messageDialog with a letter that is in the word
     */
    public class hintButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    int wordLength = hg.getSecretWord().length();
	    int randomNum = (int) (Math.random()*(wordLength));
	    
	    // subtract from hints left (and one point) for each hint used unless zero hints remain
	    if (hintsL > 0 && !hg.hasWon() && !hg.hasLost()  ) {
		hintsL--;
		if (numPoints > 0)
		    numPoints--;
		else
		    numPoints = 0;
		String numOfPoints = "Points: " + numPoints;
		points.setText(numOfPoints);
		char displayLetter = hg.getSecretWord().charAt(randomNum);
		String alreadyGuessed = hg.getBoard();
		while(alreadyGuessed.indexOf(displayLetter)!= -1){
			randomNum = (int) (Math.random()*(wordLength));
			displayLetter = hg.getSecretWord().charAt(randomNum);
		    }
		JOptionPane.showMessageDialog(f,"Your hint is:  "+displayLetter);
		String hintsLeftString = "Hints left: " + hintsL;
		hintsLeft.setText(hintsLeftString);
	    }
	    else
		JOptionPane.showMessageDialog(f,"No hints remaining.");
	}
    }

   
    /** Handler for options button
	Button displays new JFrame with option selections to choose
    */
    public class optionsButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
        f.setEnabled(false);
	    buildOptions();
	}
    }
    

    /** Handler for finish button
     */
    public class finishButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    optionsOn = "true"; // intended to save and active chosen options
        f.setEnabled(true);
	    o.dispose();
	}
    }

    /** Handler for cancel button
     */
    public class cancelButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    optionsOn = "false"; // intended to cancel chosen options
        f.setEnabled(true);
	    o.dispose();
	}
    }

    /** Handler for random background color button
     */
    public class randBGCButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    // create a random color
	    int red = (int) (Math.random() * 255);
	    int green = (int) (Math.random() * 255);
	    int blue = (int) (Math.random() * 255);
	    Color randBGC = new Color(red,green,blue);
	    f.getContentPane().setBackground(randBGC); 
	}
    }
    //ADDED
     public class customBGCButtonHandler implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		String redString = (redTF.getText());
		String blueString = (blueTF.getText());
	        String greenString = (greenTF.getText());
	        if ( ! (isNumeric(redString) && isNumeric(blueString) && isNumeric(greenString) ) )
		     return;
	        int red = Integer.parseInt(redTF.getText());
	        int green = Integer.parseInt(blueTF.getText());
	        int blue = Integer.parseInt(greenTF.getText());
		if (red < 0 || blue < 0 || green < 0 || red > 255 || blue >255 || green >255)
		    return;
	        Color customBack = new Color(red,green,blue);
	        f.getContentPane().setBackground(customBack);
		redTF.setText("");
		blueTF.setText("");
		greenTF.setText("");
	    }
    }

    public class randHMCButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    //create a random color
	    int red = (int) (Math.random() * 255);
	    int green = (int) (Math.random() * 255);
	    int blue = (int) (Math.random() * 255);
	    randHMC = new Color(red, green, blue);
        gallow.repaint();
	    square.repaint();
	}
    }

	 //ADDED
     public class customHMCButtonHandler implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        String redString = (redTF.getText());
		String blueString = (blueTF.getText());
	        String greenString = (greenTF.getText());
		if ( ! (isNumeric(redString) && isNumeric(blueString) && isNumeric(greenString) ) )
		     return;
	        int red = Integer.parseInt(redTF.getText());
	        int green = Integer.parseInt(blueTF.getText());
	        int blue = Integer.parseInt(greenTF.getText());
		if (red < 0 || blue < 0 || green < 0 || red > 255 || blue >255 || green >255)
		    return;
	        randHMC = new Color(red,green,blue);
		square.repaint();
		gallow.repaint();
		redTF.setText("");
		blueTF.setText("");
		greenTF.setText("");
	    }
    }

     public class customButtonHandler implements ActionListener {
	 public void actionPerformed(ActionEvent e) {
	     buildCustomColor();   
	 }
    }
	 

    public class squareComponent extends JComponent {
	@Override 
	public void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setColor(randHMC);
	    g2.drawRect(150,50,100,100);
	    g2.fillRect(150,50,100,100);
	}
    }

    public static boolean isNumeric(String str){
	if (str.equals("")) return false;
	for (char c : str.toCharArray()){
	    if (!Character.isDigit(c))
		return false;
	}
	return true;
    }
    
    /** Build options frame for optionsButtonHandler
     */
    public void buildOptions() {
	// creates new Options JFrame
	o = new JFrame();
	o.setSize(400,400);

	// creates upper panel for Options
	optionsUpper = new JPanel();
	optionsUpper.setLayout(new BoxLayout(optionsUpper,BoxLayout.Y_AXIS));
	o.getContentPane().add(optionsUpper,BorderLayout.NORTH);
	
	randBGColor = new JButton("Random Background Color");
	randBGColor.setFont(newFont);
	randBGCHandler = new randBGCButtonHandler();
	randBGColor.addActionListener(randBGCHandler);
	
	randHMColor = new JButton("Random Hangman Color");
	randHMColor.setFont(newFont);
	randHMCHandler = new randHMCButtonHandler();
	randHMColor.addActionListener(randHMCHandler);

	CustomColorButton = new JButton("Choose Custom Color");
	CustomColorButton.setFont(newFont);
	customHandler = new customButtonHandler();
	CustomColorButton.addActionListener(customHandler);
	

	// color switch
	square = new squareComponent();
	o.getContentPane().add(square,BorderLayout.CENTER);

	// add options components to upper panel
	optionsUpper.add(randBGColor);
	randBGColor.setAlignmentX(Component.CENTER_ALIGNMENT);
	optionsUpper.add(randHMColor);
	randHMColor.setAlignmentX(Component.CENTER_ALIGNMENT);
	optionsUpper.add(CustomColorButton);
	CustomColorButton.setAlignmentX(Component.CENTER_ALIGNMENT);

	// creates lower panel for Options
	optionsLower = new JPanel();
	optionsLower.setLayout(new FlowLayout());
	o.getContentPane().add(optionsLower,BorderLayout.SOUTH);

	finish = new JButton("Finish");
	finish.setFont(newFont);
	finishHandler = new finishButtonHandler();
	finish.addActionListener(finishHandler);

	cancel = new JButton("Cancel");
	cancel.setFont(newFont);
	cancelHandler = new cancelButtonHandler();
	cancel.addActionListener(cancelHandler);

	// add finish and cancel to lower panel
	optionsLower.add(finish);
	optionsLower.add(cancel);
	
	o.setLocationRelativeTo(null);
	o.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	o.setTitle("Options for Hangman");
    o.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent)
    {
            f.setEnabled(true);
    }
    });
	o.setVisible(true);
    }

    public void buildCustomColor() {
	// creates new Color JFrame
	o.dispose();
	CustomColorFrame = new JFrame();
	CustomColorFrame.setSize(400,200);
	CustomColorFrame.setLocationRelativeTo(null);
    CustomColorFrame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent)
    {
            f.setEnabled(true);
    }
    });
	colorPanel = new JPanel();

	customBGC = new JButton("Change Background Color");
	customBGC.setFont(newFont);
	customBGCHandler = new customBGCButtonHandler();
	customBGC.addActionListener(customBGCHandler);

	customHMC = new JButton("Change Hangman Color");
	customHMC.setFont(newFont);
	customHMCHandler = new customHMCButtonHandler();
	customHMC.addActionListener(customHMCHandler);	

	redLabel = new JLabel("Red Value(0-255):");
	blueLabel = new JLabel("Blue Value(0-255):");
	greenLabel = new JLabel("Green Value(0-255):");

	redTF = new JTextField(3);
	blueTF = new JTextField(3);
	greenTF = new JTextField(3);
		
	setLayout(new FlowLayout());
	colorPanel.add(redLabel);
	colorPanel.add(redTF);
	colorPanel.add(blueLabel);
	colorPanel.add(blueTF);
	colorPanel.add(greenLabel);
	colorPanel.add(greenTF);
	colorPanel.add(customBGC);
	colorPanel.add(customHMC);

	CustomColorFrame.add(colorPanel);
        CustomColorFrame.setVisible(true);
     }
	
    // setter/getter method

    /** Setter/getter for hintsAllowed that gets the hints allowed and sets the hints left
     */
    public int getHintsAllowed() {
	int wordLength = hg.getSecretWord().length();
	// words 1-2 characters in length get 0 hints
	if (wordLength < 3) {
	    hintsA = 0;
	    hintsL = 0;
	}
	// words 3-4 characters in length get 1 hint
	else if (wordLength > 2 && wordLength < 5) {
	    hintsA = 1;
	    hintsL = 1;
	}
	// words 5-6 characters in length get 2 hints
	else if (wordLength > 4 && wordLength < 7) {
	    hintsA = 2;
	    hintsL = 2;
	}
	// words of length 7+ get 3 hints
	else {
	    hintsA = 3;
	    hintsL = 3;
	}
	return hintsA;
    }

        
    /** Class to handle loading and playing of game sounds
     */
    public class MakeSound {
	
	private final int BUFFER_SIZE = 128000;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private  SourceDataLine sourceLine;
	
	/** Plays a sound by filename
	    @param filename Path to the sound file to play
	 */
	public void playSound(String filename){
	        
	    String strFilename = filename;
	    
	    try {
		soundFile = new File(strFilename);
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	    }
	    
	    try {
		audioStream = AudioSystem.getAudioInputStream(soundFile);
	    } catch (Exception e){
		e.printStackTrace();
		System.exit(1);
	    }
	    
	    audioFormat = audioStream.getFormat();
	    
	    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	    try {
		sourceLine = (SourceDataLine) AudioSystem.getLine(info);
		sourceLine.open(audioFormat);
	    } catch (LineUnavailableException e) {
		e.printStackTrace();
		System.exit(1);
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	    }
	    
	    sourceLine.start();
	    
	    int nBytesRead = 0;
	    byte[] abData = new byte[BUFFER_SIZE];
	    while (nBytesRead != -1) {
		try {
		    nBytesRead = audioStream.read(abData, 0, abData.length);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		if (nBytesRead >= 0) {
		    @SuppressWarnings("unused")
			int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
		}
	    }
	    
	    sourceLine.drain();
	    sourceLine.close();
	}

	/** Plays a sound by inputstream
	    @param is InputStream representing file to play
	 */

	public void playSound(InputStream is){
	    
	    try {
		audioStream = AudioSystem.getAudioInputStream(is);
	    } catch (Exception e){
		e.printStackTrace();
		System.exit(1);
	    }
	    
	    audioFormat = audioStream.getFormat();
	    
	    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	    try {
		sourceLine = (SourceDataLine) AudioSystem.getLine(info);
		sourceLine.open(audioFormat);
	    } catch (LineUnavailableException e) {
		e.printStackTrace();
		System.exit(1);
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	    }
	    
	    sourceLine.start();
	    
	    int nBytesRead = 0;
	    byte[] abData = new byte[BUFFER_SIZE];
	    while (nBytesRead != -1) {
		try {
		    nBytesRead = audioStream.read(abData, 0, abData.length);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		if (nBytesRead >= 0) {
		    @SuppressWarnings("unused")
			int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
		}
	    }
	    
	    sourceLine.drain();
	    sourceLine.close();
	}
	
	
    } // inner class MakeSound
    
    /** Component that draws a gallows in progressive states
     */
    public class hanger extends JComponent {
	hanger() {
	    setPreferredSize(new Dimension(300,300));
	}

	/** This is an override from Component, and draws a gallows at proper state directly from getWrongAttemptsLeft retrieved from game instance
	 */
	@Override
	public void paintComponent (Graphics g) {
	    Graphics2D g2 = (Graphics2D)g;
	    
	    // draw the gallow
	    Stroke normalStroke = g2.getStroke();
	    BasicStroke thickStroke = new BasicStroke(10);
	    
	    g2.setColor(new Color(0x663300));
	    g2.setStroke(thickStroke);
	    g2.drawRect(190,250, 200, 15);
	    g2.fillRect(190, 250, 200, 15);
	    g2.drawLine(290, 250, 290, 5);
	    g2.drawLine(290,5,410,5);
	    g2.drawLine(410,5,410,20);
	    
	    g2.setColor(randHMC);
	    // draw the head
	    if(hg.getWrongAttemptsLeft() < 6){ 
		g2.drawOval(385, 20, 50, 50);
		g2.fillOval(385, 20, 50, 50);
	    }
	    
	    // draw the body
	    if(hg.getWrongAttemptsLeft() < 5) 	
		g2.drawLine(410, 70, 410, 150);
	    
	    // draw the left leg
	    if(hg.getWrongAttemptsLeft() < 4) 
		g2.drawLine(410,150,350,190);
	    
	    // draw the right leg
	    if(hg.getWrongAttemptsLeft() < 3)
		g2.drawLine(410,150,470,190);
	    
	    // draw the left arm
	    if(hg.getWrongAttemptsLeft() < 2)
		g2.drawLine(410, 100, 350, 75);
	    
	    // draw the right arm
	    if(hg.getWrongAttemptsLeft() < 1)
		g2.drawLine(410, 100, 470, 75);
	}
    } // inner class hanger
}     
