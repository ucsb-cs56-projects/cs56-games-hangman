package edu.ucsb.cs56.projects.games.hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/** Main Menu GUI and Button Handling for online multiplayer.
	This is a subdirecting class for online multiplayer.

	@author Blake Johnson, F17
	@author Fernando Mendoza, F17
	@version F17


*/

public class MainMenuSetupGUI extends JFrame{
	
	private JFrame mainMenu;
	private JButton singlePlayer, onlineMultiplayer;
	private JLabel title;
	private JPanel mainPanel;
	private WordList wordList;

	public MainMenuSetupGUI(){this.wordList = new WordList(getAFile("/resources/textFile/WordsList.txt"));}

	public MainMenuSetupGUI(WordList wordList){
		this.wordList = wordList;
	}

	public void menu(){
		mainMenu = new JFrame();
		mainMenu.setSize(250, 150);
		title = new JLabel("Choose Game Type");
		singlePlayer = new JButton("Single Player");
		onlineMultiplayer = new JButton("Online Multiplayer");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainMenu.getContentPane().add(mainPanel, BorderLayout.NORTH);
		mainMenu.setTitle("Main Menu");
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		singlePlayer.addActionListener(new SinglePlayerHandler());
		onlineMultiplayer.addActionListener(new MultiplayerHandler());
		mainMenu.setLocationRelativeTo(null);
		mainPanel.add(title);
		mainPanel.add(singlePlayer);
		mainPanel.add(onlineMultiplayer);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		singlePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		onlineMultiplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenu.setVisible(true);

	}

	public class SinglePlayerHandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try{
				mainMenu.dispose();
				selectionGUI select = new selectionGUI();
				select.menu();

				/*
				HangmanGUI gui = new HangmanGUI(wordList);
				gui.setHardlevel(0);
				gui.play();
				*/
			}
			catch(Exception na){
				na.printStackTrace();
			}
		}
	}

	public File getAFile(String name){
		File file = new File(GUIMain.class.getResource(name).getFile());
		return file;
	}


	public class MultiplayerHandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			new Matchmaking().startGameTypeGUI();
			mainMenu.dispose();
		}
	}

	public static void main(String [] args){

	    MainMenuSetupGUI gui = new MainMenuSetupGUI();
	    gui.menu();
    }

	
}
