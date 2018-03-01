package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;

/** Joins Hangman Game Server.
		Joins game server with opponents word choice and initializes new hangman game.

		@author Blake Johnson, F17
		@author Fernando Mendoza, F17
		@version F17


*/


public class JoinServer{
		private JFrame joinFrame, joiningFrame;
		private JLabel joinLabel, joiningLabel, enterIP, enterPort, enterWord, space;
		private JTextField portTextField, ipTextField, oppWordTextField;
	private JButton submitButton, backButton;
	private String ipString, portString, oppWord, myWord;
	private int hostPortNumber;
	private Socket sock;
	private JPanel joinPanel;
	private JLabel errorLabel;
	private String errorMessage = "";

	public void setup(){
		joinFrame = new JFrame();
		joinFrame.setSize(500, 500);
		errorLabel = new JLabel(errorMessage);
		errorLabel.setForeground (Color.red);



		joinLabel = new JLabel("Give your opponent your IP address and port number");
				ipTextField = new JTextField();
		oppWordTextField = new JTextField();
				portTextField = new JTextField();
		enterIP = new JLabel("Enter host IP address: ");
		enterPort = new JLabel("Enter port number. Default is 1738: ");
		enterWord = new JLabel("Enter word for opponent to guess: ");
		space = new JLabel("  ");

		submitButton = new JButton("Join Game");
		submitButton.addActionListener(new SubmitButtonHandler());

		backButton = new JButton("Back");
		backButton.addActionListener(new BackButtonHandler());
		joinPanel = new JPanel();
		joinPanel.setLayout(new BoxLayout(joinPanel, BoxLayout.Y_AXIS));
		joinFrame.getContentPane().add(joinPanel, BorderLayout.NORTH);


				joinPanel.add(joinLabel);
		joinPanel.add(space);
		joinPanel.add(enterIP);
				joinPanel.add(ipTextField);
		joinPanel.add(enterPort);
				joinPanel.add(portTextField);
		joinPanel.add(enterWord);
		joinPanel.add(oppWordTextField);
		joinPanel.add(errorLabel);
		joinFrame.getContentPane().add(submitButton, BorderLayout.CENTER);
		joinFrame.getContentPane().add(backButton, BorderLayout.SOUTH);
		
		joinFrame.setTitle("Join Server Settings");
		joinFrame.setLocationRelativeTo(null);	
		joinFrame.setVisible(true);
				joinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	public void connect(){
		try {
			sock = new Socket(ipString, hostPortNumber);
			InputStreamReader reader = new InputStreamReader(sock.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(reader);
				
			String line = null;
			do{
				line = bufferedReader.readLine();
			}while(line == null);
			myWord = line;

			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			writer.println(oppWord);
			writer.flush();
			writer.close();
			bufferedReader.close();
			startGame();
		}catch(IOException ex) {
			printErrorMessage();
		}

	}

	public void printErrorMessage(){
		 if (joinFrame != null){
						joinFrame.dispose();
				 }
				 errorMessage = "Unable to join a server, please check your setting";
				 setup();
	}

	public void startGame(){
		try {
			new HangmanGUI(myWord).play();
			if(joiningFrame != null){
								joiningFrame.dispose();
			}
		}catch (IOException exp){
			exp.printStackTrace();
		}
	}

	public void showJoiningFrame(){
		joiningFrame = new JFrame();
				joiningFrame.setSize(400, 90);
				joiningLabel = new JLabel("Joining Game....Waiting for server to respond.");
				joiningFrame.getContentPane().add(joiningLabel, BorderLayout.NORTH);

				joiningFrame.setLocationRelativeTo(null);
				joiningFrame.setVisible(true);
				joiningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	public class SubmitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent nat){
			try
			{
				oppWord = oppWordTextField.getText();
				portString = portTextField.getText();
				ipString = ipTextField.getText();
				hostPortNumber = Integer.parseInt(portString);
				joinFrame.dispose();
				showJoiningFrame();
				new Thread(new Runnable(){
					@Override
					public void run(){
							connect();
					}
				}).start();
			}
			catch (Exception e)
			{
				printErrorMessage();
			}
		}
	}

	public class BackButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Matchmaking mg = new Matchmaking();
			mg.startGameTypeGUI();
			joinFrame.dispose();
		}
	}

}

