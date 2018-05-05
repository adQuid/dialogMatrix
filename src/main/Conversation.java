package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Conversation {

	Topic currentTopic;
	
	int logic1;
	int logic2;
	
	List<Character> conversationalists = new ArrayList<Character>();
	Map<Character,Action> setActions = new HashMap<Character,Action>();
	
	boolean stale = false;
	Character lastSpoke = null;
	String lastLine = "";
	
	public void addMember(Character joiner) {
		conversationalists.add(joiner);
	}
	
	public void submitAction(Character character, Action action) {
		//if you acted last round, you can't act in this one
		if(lastSpoke.equals(character)) {
			System.out.println("Block!");
			return;
		}
		setActions.put(character, action);
		
		if(setActions.size() == conversationalists.size()-1) {
			conversationRound();
		}
	}
	
	public void conversationRound() {
		Action dominantAction = null;
		Character dominantChar = null;
		for(Character current: setActions.keySet()) {
			dominantAction = setActions.get(current);//this needs to change when we start with 3+ person conversations
			dominantChar = current;
		}
		if(dominantAction != null) {
			lastSpoke = dominantChar;
			switch(dominantAction.getType()) {
			case silence:
				System.out.println("umm, that was awkward");
				stale = true;
				break;
			case transition:
				currentTopic = (Topic)(dominantAction.getParams()[0]);
				break;
			case inform:
				System.out.println("nerd!");
				break;
			default:
				System.err.println("action not recognized at end of round resolution");	
			}
		}else{
			//this should only happen at the very start of a conversation
			Character starter = conversationalists.get(0);
							
			if(starter.isPc()) {
				//this code doesn't run right now
				//GUI.displayDialogState(lastLine, starter.generatePossibleActions(this),this,starter);
			}else {
				//run AI
				if(currentTopic == null) {
					currentTopic = starter.introductoryTopic();
				}else {
					System.err.println("current topic isn't empty!");
				}

				lastSpoke = starter;
				lastLine = starter.getLine(this, null);
			}

		}
		//standardize logical position
		logic1 = currentTopic.getSubject().getLocation().get(Dimension.logic1);
		logic2 = currentTopic.getSubject().getLocation().get(Dimension.logic2);
		setActions.clear();
		
		for(Character current: conversationalists) {
			if(current.isPc()) {
				GUI.displayDialogState(lastLine, current.generatePossibleActions(this),this,current);
			}
		}
	}
		
}
