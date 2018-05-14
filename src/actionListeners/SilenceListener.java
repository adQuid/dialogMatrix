package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Action;
import main.Conversation;
import main.Character;

public class SilenceListener implements ActionListener{

	Conversation convo;
	Character character;
	Action action;
	
	public SilenceListener(Conversation convo, Character character, Action action) {
		super();
		this.convo = convo;
		this.character = character;
		this.action = action;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		convo.submitAction(character, action);
	}

}
