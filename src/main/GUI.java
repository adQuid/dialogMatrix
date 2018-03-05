package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

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
	
	public static void displayDialogState(String message, List<String> options) {
		((JLabel)dialog.getComponent(0)).setText(message);
		
		responses.removeAll();
		responses.setLayout(new GridLayout(options.size(),1));
		for(int index = 0; index < options.size(); index++) {
			responses.add(new JButton(options.get(index)));
		}
		
		window.pack();
		window.setVisible(true);
	}
	
}
