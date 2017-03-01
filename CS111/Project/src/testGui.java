/**
 * Created by Abhi on 12/2/14.
 */

import javafx.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*public class testGui extends JPanel{

    private JLabel title, size ,threshold, xAgents, oAgents, blankSpace, delays;
    private JTextField sizeN, thresholdPer, xAgentPer, yAgentPer, blankPer, delayPer;
    private JButton reset, start, stop;
    private static final long serialVersionUID = 2L;
    int delay;
    private static final int BUTTON_SIZE = 80;

    private JButton[][] buttons;
    private JPanel gamePanel;
    private boolean isEnabled;

    public testGui(int sizee, int delay){
        //create all JLabels
        title = new JLabel("Cell Sim Project");
        size = new JLabel("Size of the tisue ");
        threshold = new JLabel("Threshold % ");
        xAgents = new JLabel("X Agents % ");
        blankSpace = new JLabel("Empty cells %");
        delays = new JLabel("delay: in milleseconds: ");

        //create all jText fields
        sizeN = new JTextField(5);
        thresholdPer = new JTextField(5);
        xAgentPer = new JTextField(5);
        //yAgentPer = new JTextField(5);
        blankPer = new JTextField(5);
        delayPer = new JTextField(5);

        //create JButtons
        //start = new JButton("CellSim");

        //action listener
        start.addActionListener(new starListener());

        //super("testGui");
        this.isEnabled = true;
        this.delay = delay;
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(sizee * BUTTON_SIZE, sizee * BUTTON_SIZE);

        gamePanel = new JPanel(new GridLayout(sizee, sizee));
        buttons = new JButton[sizee][sizee];


        for (int row = 0; row < sizee; row++){
            for (int col = 0; col < sizee; col++) {
                JButton b = new JButton(" ");
                b.setBackground(Color.WHITE);
                buttons[row][col] = b;
                gamePanel.add(b);
            }
        }

        this.add(gamePanel);
        this.setVisible(true);
        add(title);
        add(size);
        add(threshold);
        add(xAgents);
        add(blankSpace);
        add(delays);
        add(sizeN);
        add(thresholdPer);
        add(xAgentPer);
        add(blankPer);
        add(delayPer);

    }

    public void setCell(int row, int col, Color c){
        try{
            Thread.sleep(delay);}catch(Exception e){}
        buttons[row][col].setBackground(c);
        buttons[row][col].setOpaque(true);
        buttons[row][col].setBorderPainted(false);
        buttons[row][col].repaint();

    }

    private class startListener implements  ActionListener{
        public void actionPerformed (ActionEvent event){

        }
    }
}
*/