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
	String lastLine = "TEST";
	String latestLine = "";
	
	public void addMember(Character joiner) {
		conversationalists.add(joiner);
	}
	
	public void submitAction(Character character, Action action) {
		//if you acted last round, you can't act in this one
		if(lastSpoke != null && lastSpoke.equals(character)) {
			return;
		}
		setActions.put(character, action);
		
		if(setActions.size() == conversationalists.size()-1) {
			conversationRound();
		}
	}
	
	public void conversationRound() {
		//set old line
		lastLine = latestLine;
		
		Action dominantAction = null;
		Character dominantChar = null;
		for(Character current: setActions.keySet()) {
			dominantAction = setActions.get(current);//this needs to change when we start with 3+ person conversations
			dominantChar = current;
		}
		if(dominantAction != null) {
			System.out.println(dominantAction.type);
			lastSpoke = dominantChar;
			switch(dominantAction.getType()) {
			case silence:
				latestLine = dominantChar.getName() + " stares blankly";
				stale = true;
				break;
			case transition:
				Topic transitionTopic = (Topic)(dominantAction.getParams()[0]);
				latestLine = "You drift to talking about "+transitionTopic.getSubject().getName();
				currentTopic = transitionTopic;
				break;
			case inform:
				latestLine = dominantChar.getName() + " tells you more about "+currentTopic.getSubject().getName();
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
				latestLine = starter.getLine(this, null);
			}

		}
		//standardize logical position
		logic1 = currentTopic.getSubject().getLocation().get(Dimension.logic1);
		logic2 = currentTopic.getSubject().getLocation().get(Dimension.logic2);
		setActions.clear();
		
		for(Character current: conversationalists) {
			if(current.isPc()) {
				GUI.displayDialogState(lastLine, latestLine, current.generatePossibleActions(this),this,current);
			}
		}
	}
	
	public List<Character> getConversatoinalists(){
		return conversationalists;
	}
		
	public boolean isCharacterTurn(Character character) {
		return lastSpoke != null && !lastSpoke.equals(character) && 
				conversationalists.contains(character) && !setActions.containsKey(character);
	}
	
}
