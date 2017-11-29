package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;

public class JoinServer{
        private JFrame joinFrame;
        private JLabel joinLabel;
        private JTextField port, ipAddress;

        public void setup(){
                joinFrame = new JFrame();
                joinFrame.setSize(500, 500);
                joinLabel = new JLabel("Give your opponent your IP address and port number");
                ipAddress = new JTextField("127.0.0.1");
                port = new JTextField("1738");
                joinFrame.setTitle("Host Server Settings");

                joinFrame.getContentPane().add(joinLabel, BorderLayout.NORTH);
                joinFrame.getContentPane().add(ipAddress, BorderLayout.WEST);
                joinFrame.getContentPane().add(port, BorderLayout.EAST);

		joinFrame.setVisible(true);

                joinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }



}


