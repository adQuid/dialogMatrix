package ai;

import java.util.List;

import main.Action;
import main.ActionType;
import main.Character;
import main.Conversation;

public class AIThread implements Runnable{

	private Character me;
	private Conversation convo;
	
	public AIThread(Character me, Conversation convo) {
		super();
		this.me = me;
		this.convo = convo;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(convo.getConversatoinalists().contains(me) && convo.isCharacterTurn(me)) {
				List<Action> choices = me.generatePossibleActions(convo);
				convo.submitAction(me, choices.get((int)(Math.random() * choices.size())));
			}
		}
	}

}
