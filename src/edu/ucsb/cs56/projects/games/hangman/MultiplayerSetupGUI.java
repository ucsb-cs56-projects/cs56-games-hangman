package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;

/** Main Menu GUI and Button Handling for online multiplayer.
	This is a subdirecting class for online multiplayer.

	@author Blake Johnson, F17
	@author Fernando Mendoza, F17
	@version F17


*/


public class MultiplayerSetupGUI extends JFrame{
	
	private JFrame MainMenu;
	private JButton singlePlayer, onlineMultiplayer;
	private JLabel title;
	private JPanel mainPanel;
	private static WordList wordList;
		
	public MultiplayerSetupGUI(WordList wordList){
		this.wordList = wordList;
	}

	public void go(){
		MainMenu = new JFrame();
		MainMenu.setSize(250, 90);
		title = new JLabel("Choose Game Type");
		singlePlayer = new JButton("Single Player");
		onlineMultiplayer = new JButton("Online Multiplayer");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		MainMenu.getContentPane().add(mainPanel, BorderLayout.NORTH);
		MainMenu.setTitle("Main Menu");
		MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		singlePlayer.addActionListener(new SinglePlayerHandler());
		onlineMultiplayer.addActionListener(new MultiplayerHandler());

		MainMenu.setLocationRelativeTo(null);

		mainPanel.add(title);
		mainPanel.add(singlePlayer);
		mainPanel.add(onlineMultiplayer);

		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		singlePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		onlineMultiplayer.setAlignmentX(Component.CENTER_ALIGNMENT);

		MainMenu.setVisible(true);

	}

	public void startMatchmaking(){
		new Matchmaking().choose();
	}

	public static WordList getWordList(){
		return wordList;
	}

	public class SinglePlayerHandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try{
				MainMenu.dispose();
				new HangmanGUI(wordList).play();
			}
			catch(IOException natnat){
				System.out.println("Catch that filthy nat!");
				natnat.printStackTrace();
			}
		}
	}

	public class MultiplayerHandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			new Matchmaking().choose();
			MainMenu.dispose();
		}
	}
	
}
