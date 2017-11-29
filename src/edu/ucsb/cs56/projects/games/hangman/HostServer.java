package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;



public class HostServer{
        private JFrame hostFrame;
        private JLabel hostLabel, ipAddress;
        private JTextField port, opponentWord;
	private JPanel hostPanel;

        public void setup(){
                hostFrame = new JFrame();
                hostFrame.setSize(500, 500);
                hostLabel = new JLabel("Give your opponent your IP address and port number");
                ipAddress = new JLabel("IP Address: 127.0.0.1");
                port = new JTextField("1738");
                hostFrame.setTitle("Host Server Settings");
		opponentWord = new JTextField("enemy");
		hostPanel.setLayout(new BoxLayout(hostPanel, BoxLayout.Y_AXIS));
		hostPanel.add(hostLabel);
		hostPanel.add(ipAddress);
		hostPanel.add(port);
		hostPanel.add(opponentWord);

                hostFrame.getContentPane().add(hostPanel, BorderLayout.NORTH);
		
		hostFrame.setVisible(true);

                hostFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

}

