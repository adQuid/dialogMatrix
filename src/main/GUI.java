package main;

import java.awt.BorderLayout;
import java.awt.Component;
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
		
		dialog.add(new JLabel("The thing the guy just said"));
		
		responses.setLayout(new GridLayout(4,1));
		responses.add(new JButton("Option 1"));
		responses.add(new JButton("Option 2"));
		responses.add(new JButton("Option 3"));
		responses.add(new JButton("Option 4"));
		
		window.add(dialog,BorderLayout.NORTH);
		window.add(responses, BorderLayout.SOUTH);
		
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
	}
	
	public static void displayDialogState(String message, List<Action> options, Conversation convo, Character character) {
		((JLabel)dialog.getComponent(0)).setText(message);
		
		responses.removeAll();
		responses.setLayout(new GridLayout(options.size()+1,1));
		for(int index = 0; index < options.size(); index++) {
			responses.add(createOptionSelecter(options.get(index),convo,character));
		}
		responses.add(silenceAction(convo,character));
		
		window.pack();
		window.setVisible(true);
	}
	
	private static JPanel createOptionSelecter(Action action, Conversation convo, Character character) {
		JPanel retval = new JPanel();
		retval.setLayout(new GridLayout(1,1));
		switch(action.getType()) {
			case inform:
				retval.setLayout(new GridLayout(1,4));
				JButton informButton = new JButton("Inform about "+((Subject)(action.getParams()[0])).getName());
				informButton.addActionListener(new SilenceListener(convo,character,action));
				retval.add(informButton);
				break;
			case transition:
				JButton transitionButton = new JButton("Shift converstaion to "+((Topic)(action.getParams()[0])).getSubject().getName());
				transitionButton.addActionListener(new SilenceListener(convo,character,action));
				retval.add(transitionButton);	
				break;
			default:
				retval.add(new JLabel("UNABLE TO PROCESS ACTION!"));
		}
		return retval;
	}
	
	private static JButton silenceAction(Conversation convo, Character character) {
		JButton retval = new JButton("Silence");
		retval.addActionListener(new SilenceListener(convo,character,new Action(ActionType.silence,new Object[1])));
		return retval;
	}
	
}
