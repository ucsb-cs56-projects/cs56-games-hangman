package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;

public class JoinServer{
        private JFrame joinFrame, joiningFrame;
        private JLabel joinLabel, joiningLabel;
        private JTextField portTextField, ipTextField, oppWordTextField;
	private JButton submitButton;
	private String ipString, portString, oppWord, myWord;
	private int hostPortNumber;
	private Socket sock;

        public void setup(){
                joinFrame = new JFrame();
                joinFrame.setSize(500, 500);
                joinLabel = new JLabel("Give your opponent your IP address and port number");
                ipTextField = new JTextField("Enter IP Address of host.");
		oppWordTextField = new JTextField("Enter opponent's word");
                portTextField = new JTextField("Enter port number of host. Default: 1738");
                joinFrame.setTitle("Join Server Settings");
		submitButton = new JButton("Join Game");
		submitButton.addActionListener(new SubmitButtonHandler());
                joinFrame.getContentPane().add(joinLabel, BorderLayout.NORTH);
                joinFrame.getContentPane().add(ipTextField, BorderLayout.WEST);
                joinFrame.getContentPane().add(portTextField, BorderLayout.EAST);
		joinFrame.getContentPane().add(oppWordTextField, BorderLayout.CENTER);
		joinFrame.getContentPane().add(submitButton, BorderLayout.SOUTH);
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

				if (joinFrame != null){
					joinFrame.dispose();
				}
				JLabel errorLabel = new JLabel("The port number or IP address is invalid. Please try again.");
				joiningFrame.getContentPane().add(errorLabel, BorderLayout.SOUTH);
				setup();

			}

	}

	public void startGame(){
		try {
			new HangmanGUI(MultiplayerSetupGUI.getWordList(), myWord).play();
			if(joiningFrame != null){
                                joiningFrame.dispose();
                        }


		}catch (IOException exp){
			exp.printStackTrace();
		
		}
	}

	public void showJoiningFrame(){
		joiningFrame = new JFrame();
                joiningFrame.setSize(300, 90);
                joiningLabel = new JLabel("Joining Game....Waiting for server to respond.");
                joiningFrame.getContentPane().add(joiningLabel, BorderLayout.NORTH);

                joiningFrame.setLocationRelativeTo(null);
                joiningFrame.setVisible(true);
                joiningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	public class SubmitButtonHandler implements ActionListener{
                public void actionPerformed(ActionEvent nat){
                        oppWord = oppWordTextField.getText();
                        portString = portTextField.getText();
			ipString = ipTextField.getText();
                        hostPortNumber = Integer.parseInt(portString);
                        joinFrame.dispose();
                        showJoiningFrame();
                        connect();
                }
        }



}


