package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import actionListeners.SilenceListener;

public class GUI {

	static JFrame window;
	static JPanel dialog;
	static JPanel responses;
	
	public static void createGUI() {
		window = new JFrame("Dialog");
		dialog = new JPanel();
		responses = new JPanel();
		
		dialog.setLayout(new GridLayout(2,1));
		dialog.add(new JLabel("The thing you said"));
		dialog.add(new JLabel("The thing the guy just said"));
		
		responses.setLayout(new GridLayout(4,1));
		responses.add(new JButton("Option 1"));
		responses.add(new JButton("Option 2"));
		responses.add(new JButton("Option 3"));
		responses.add(new JButton("Option 4"));
		
		window.add(dialog,BorderLayout.NORTH);
		window.add(responses, BorderLayout.SOUTH);
		
		window.setSize(600, 400);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
	}
	
	public static void displayDialogState(String oldMessage, String message, List<Action> options, Conversation convo, Character character) {
		((JLabel)dialog.getComponent(0)).setText(oldMessage);
		((JLabel)dialog.getComponent(1)).setText(message);
		
		responses.removeAll();
		responses.setLayout(new GridLayout(options.size(),1));
		for(int index = 0; index < options.size(); index++) {
			responses.add(createOptionSelecter(options.get(index),convo,character));
		}
		
		
		window.setVisible(true);
	}
	
	private static JPanel createOptionSelecter(Action action, Conversation convo, Character character) {
		JPanel retval = new JPanel();
		retval.setLayout(new GridLayout(1,1));
		switch(action.getType()) {
			case inform:
				retval.setLayout(new GridLayout(1,4));
				JButton informButton = new JButton("Inform about "+((Topic)(action.getParams()[0])).getSubject().getName());
				informButton.addActionListener(new SilenceListener(convo,character,action));
				retval.add(informButton);
				break;
			case transition:
				JButton transitionButton = new JButton("Shift converstaion to "+((Topic)(action.getParams()[0])).getSubject().getName());
				transitionButton.addActionListener(new SilenceListener(convo,character,action));
				retval.add(transitionButton);	
				break;
			case inquire:
				JButton inquireButton = new JButton("Ask about "+((Subject)(action.getParams()[0])).getName());
				inquireButton.addActionListener(new SilenceListener(convo,character,action));
				retval.add(inquireButton);	
				break;
			case silence:
				JButton silenceButton = new JButton("Silence");
				silenceButton.addActionListener(new SilenceListener(convo,character,action));
				retval.add(silenceButton);	
				break;
			default:
				retval.add(new JLabel("UNABLE TO PROCESS ACTION!"));
		}
		return retval;
	}
		
}
