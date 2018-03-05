package main;

import java.util.ArrayList;
import java.util.List;

public class Conversation {

	Topic currentTopic;
	
	List<Character> conversationalists = new ArrayList<Character>();

	Character lastSpoke = null;
	String lastLine = "";
	
	public void addMember(Character joiner) {
		conversationalists.add(joiner);
	}
	
	public void conversationRound() {
		for(Character current: conversationalists) {
			if(current == lastSpoke) {
				continue;
			}else {
				if(current.isPc()) {
					List<String> options = new ArrayList<String>();
					options.add("Insult Lazy Leaf Inn");
					options.add("Joke about Lazy Leaf Inn");
					options.add("I want to talk about something specific");
					
					GUI.displayDialogState(lastLine, options);
				}else {
					//run AI
					if(currentTopic == null) {
						currentTopic = current.introductoryTopic();
					}
					
					lastSpoke = current;
					lastLine = current.getLine(currentTopic, null);
				}
			}
		}
	}
	
	
}
