package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;
import java.net.InetAddress.*;


public class HostServer{
        private JFrame hostFrame, connectFrame;
        private JLabel hostLabel, ipAddress, connectLabel;
        private JTextField portTextField, wordTextField;
	private JPanel hostPanel;
	private JButton submitButton;
	private int hostPortNumber;
	private String oppWord, hostPortString, getIpString, myWord;;
	private ServerSocket serverSocket;
	private Socket sock;
	private PrintWriter writer;

        public void setup(){
                hostFrame = new JFrame();
                hostPanel = new JPanel();
		hostFrame.setSize(500, 500);
                hostLabel = new JLabel("Give your opponent your IP address and port number");
                ipAddress = new JLabel("IP Address: "+getIpAddress());
                portTextField = new JTextField("1738");
                hostFrame.setTitle("Host Server Settings");
		wordTextField = new JTextField("enemy");
		hostPanel.setLayout(new BoxLayout(hostPanel, BoxLayout.Y_AXIS));
		hostFrame.getContentPane().add(hostPanel, BorderLayout.NORTH);
		hostPanel.add(hostLabel);
		hostPanel.add(ipAddress);
		hostPanel.add(portTextField);
		hostPanel.add(wordTextField);
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new SubmitButtonHandler());
		hostFrame.getContentPane().add(submitButton, BorderLayout.SOUTH);
		hostFrame.setVisible(true);
                hostFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

	public void connect()throws IOException{
		serverSocket = new ServerSocket(hostPortNumber);
		sock = serverSocket.accept();
		writer = new PrintWriter(sock.getOutputStream());
		writer.println(oppWord);
		writer.flush();
		
		InputStreamReader streamReader = new InputStreamReader(sock.getInputStream()); 
		BufferedReader reader = new BufferedReader(streamReader);
		String line;
		do{
			line = reader.readLine();
		}while(line == null);
		myWord = line;
		writer.close();
		reader.close();
		startGame();
	}

	public void startGame(){
		try{
			new HangmanGUI(MultiplayerSetupGUI.getWordList(), myWord).play();
			if(connectFrame != null){
				connectFrame.dispose();
			}
		}
		catch(IOException nat){
			nat.printStackTrace();
		}
	}
	
	public void showConnectMessage(){
		connectFrame = new JFrame();
                connectFrame.setSize(350, 90);
                connectLabel = new JLabel("Connecting....Waiting for opponent to connect.");
                connectFrame.getContentPane().add(connectLabel, BorderLayout.NORTH);
                connectFrame.setLocationRelativeTo(null);
                connectFrame.setVisible(true);
                connectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public String getIpAddress(){
	  	try {
                        InetAddress address = InetAddress.getLocalHost();
                        getIpString = address.getHostAddress();
			return getIpString;
                }catch (UnknownHostException ex) {
                        ex.printStackTrace();
			return "";
                }
	}

	public class SubmitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			oppWord = wordTextField.getText();
			hostPortString = portTextField.getText();
			hostPortNumber = Integer.parseInt(hostPortString);
			hostFrame.dispose();
			showConnectMessage();
			try{ connect(); }
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}


}

