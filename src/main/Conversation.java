package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Conversation {

	private Subject currentSubject;
	private Topic lastTopic;
	private Action lastAction;
	
	int logic1;
	int logic2;
	
	List<Character> conversationalists = new ArrayList<Character>();
	Map<Character,Action> setActions = new HashMap<Character,Action>();
	
	private boolean stale = false;
	private Character lastSpoke = null;
	String lastLine = "TEST";
	String latestLine = "";
		
	public Subject getCurrentSubject() {
		return currentSubject;
	}

	public Topic getLastTopic() {
		return lastTopic;
	}

	public Action getLastAction() {
		return lastAction;
	}

	public List<Character> getConversationalists() {
		return conversationalists;
	}

	public boolean isStale() {
		return stale;
	}

	public Character getLastSpoke() {
		return lastSpoke;
	}

	public String getLastLine() {
		return lastLine;
	}

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
			switch(dominantAction.getType()) {
			case silence:
				latestLine = dominantChar.getLine(this, dominantAction);//dominantChar.getName() + " stares blankly";
				stale = true;
				break;
			case transition:
				Topic transitionTopic = (Topic)(dominantAction.getParams()[0]);
				stateTopic(transitionTopic);
				latestLine = dominantChar.getLine(this, dominantAction);
				stale = false;
				break;
			case inform:
				Topic informTopic = (Topic)(dominantAction.getParams()[0]);
				lastTopic = informTopic;
				latestLine = dominantChar.getLine(this, dominantAction);
				stale = false;
				break;
			case inquire:
				latestLine = dominantChar.getLine(this, dominantAction);
				stale = false;
				break;
			default:
				System.err.println("action not recognized at end of round resolution");	
			}
			lastSpoke = dominantChar;
			lastAction = dominantAction;
		}else{
			//this should only happen at the very start of a conversation
			Character starter = conversationalists.get(0);
							
			if(starter.isPc()) {
				//this code doesn't run right now
				//GUI.displayDialogState(lastLine, starter.generatePossibleActions(this),this,starter);
			}else{
				//run AI
				if(currentSubject == null) {
					currentSubject = starter.introductoryTopic().getSubject();
				}else{
					System.err.println("current topic isn't empty!");
				}

				lastSpoke = starter;
				latestLine = starter.getLine(this, null);
			}

		}
		//standardize logical position
		logic1 = currentSubject.getLocation().get(Dimension.logic1);
		logic2 = currentSubject.getLocation().get(Dimension.logic2);
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
	
	private void stateTopic(Topic topic) {
		currentSubject = topic.getSubject();
		lastTopic = topic;
	}
	
}
