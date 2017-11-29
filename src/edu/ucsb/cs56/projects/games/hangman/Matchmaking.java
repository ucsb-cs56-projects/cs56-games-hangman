package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;

/** Choose host game or join game and connect GUI.
		
	This class creates or joins a multiplayer game and connects. 
       	Then will prompt for opponent word before initializing game instance.

	@author Blake Johnson, F17
	@author Fernando Mendoza, F17
	@version F17

*/

//TODO fix naming and remove commented code. get rid of startgame and ipadress.

public class Matchmaking{
	String ipAdress; 
	private JFrame chooseFrame; 
	private JButton hostButton, joinButton;
	private JLabel chooseLabel;
	private JPanel mainPanel;

	public void choose(){
	/*	try {
			InetAdress adress = InetAdress.getLocaHost();
			ipAdress = adress.getHostAdress();

		}catch (UnknownHostException natnat) {
			System.out.println("Hoest on the loose!");
			natnat.printStackTrace();
		}
*/
		chooseFrame = new JFrame();
		chooseFrame.setSize(350, 100);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		hostButton = new JButton("Host Game");
		joinButton = new JButton("Join Game");
		chooseFrame.setTitle("Server Settings");
		chooseLabel = new JLabel("Choose to host or join a Hangman game");
		chooseFrame.getContentPane().add(mainPanel, BorderLayout.NORTH);
		joinButton.addActionListener(new joinGameListener());
		hostButton.addActionListener(new hostGameListener());

		chooseFrame.setLocationRelativeTo(null);
		
		mainPanel.add(chooseLabel);
		mainPanel.add(hostButton);
		mainPanel.add(joinButton);

		chooseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		hostButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		chooseFrame.setVisible(true);



	}

	public void startGame(){
		
	}

	public class joinGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JoinServer myJoinServer = new JoinServer();
			myJoinServer.setup();
			chooseFrame.dispose();
		}
	}

	public class hostGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			HostServer myHostServer = new HostServer();
			myHostServer.setup();
			chooseFrame.dispose();
		}
	}
}
