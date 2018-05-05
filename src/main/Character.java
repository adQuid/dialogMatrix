package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character {

	private boolean pc = false;
	
	private int intelligence = 15;
	
	private Map<Dimension,Integer> mood = new HashMap<Dimension,Integer>();
	private List<Topic> topics =  new ArrayList<Topic>();;
	
	public Character() {
		for(Dimension current: Dimension.values()) {
			mood.put(current, 0);
		}
	}
	
	public void addTopic(Subject subject, int knowledge) {
		topics.add(new Topic(subject, knowledge));
	}
	
	public Topic introductoryTopic() {
		return nearestTopic();
	}
	
	public String getLine(Conversation convo, ActionType action) {
		
		String preamble = "<html>He opens the conversation by idly rambling about "+convo.currentTopic.subject.getName()+"... <br>\"";
		if(convo.stale) {
			preamble = "<html>Headless to your obvious lack of interest, he continues... <br>\"";			
		}
		
		return preamble+convo.currentTopic.introduce(this)+".\"</html>";
	}
	
	public void adjustMood(Dimension dimension, int amount) {
		//should never have an NPE since every key is filled at creation
		mood.put(dimension, mood.get(dimension)+amount);
	}
	
	private Topic nearestTopic() {
		Topic nearestTopic = null;
		for(Topic current: topics) {
			if(nearestTopic == null || current.distanceFrom(mood) < nearestTopic.distanceFrom(mood)) {
				nearestTopic = current;
			}
		}
		return nearestTopic;
	}

	public boolean isPc() {
		return pc;
	}

	public void setPc(boolean pc) {
		this.pc = pc;
	}
	
	public int knowledgeOnSubject(Subject sub) {
		int bestKnowledge = 0;
		for(Topic current: topics) {
			if(current.getSubject().equals(sub) && current.getKnowledge() > bestKnowledge) {
				bestKnowledge = current.getKnowledge();
			}
		}
		return bestKnowledge;
	}
	
	public List<Action> generatePossibleActions(Conversation convo){
		List<Action> retval = new ArrayList<Action>();
		
		if(knowledgeOnSubject(convo.currentTopic.getSubject()) > 0) {
			Object[] params = new Object[1];
			params[0] = convo.currentTopic.getSubject();
			retval.add(new Action(ActionType.inform,params));
			
		}
		
		for(Topic current: topics) {
			if(!current.getSubject().equals(convo.currentTopic.getSubject())
					&& current.distanceFrom(mood) < intelligence){
				Object[] params = new Object[1];
				params[0] = current;
				retval.add(new Action(ActionType.transition,params));
			}
		}
		
		return retval;
	}
	
}
