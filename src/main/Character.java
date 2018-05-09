package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dialogFlavor.LineFlavoring;

public class Character {

	private boolean pc = false;
	private String name;
	
	private int intelligence = 15;
	
	private Map<Dimension,Integer> mood = new HashMap<Dimension,Integer>();
	private List<Topic> topics =  new ArrayList<Topic>();;
	
	public Character(String name) {
		this.name=name;
		for(Dimension current: Dimension.values()) {
			mood.put(current, 0);
		}
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void addTopic(Subject subject, Perspective perspective, int knowledge) {
		topics.add(new Topic(subject, perspective, knowledge));
	}
	
	public Topic introductoryTopic() {
		return nearestTopic();
	}
	
	public String getLine(Conversation convo, Action action) {
		
		String preamble = "<html>";
		if(action == null) {
			preamble = "<html>He opens the conversation by idly rambling about "+convo.getCurrentSubject().getName()+"... ";
			return preamble+"<br>\""+getRelatedTopic(convo.getCurrentSubject()).stateFact(this)+".\"</html>";
		}
		switch(action.getType()) {
		case silence:
			preamble = "<html>"+this.getName() + " stares blankly</html>";
			return preamble;
		case inform:
			if(convo.isStale()) {
				preamble = "<html>Headless to your obvious lack of interest, he continues... ";			
			}else if(action.isContradiction(convo.getLastAction())) {
				preamble = "<html>"+this.getName()+" scoffs at what "+convo.getLastSpoke().getName()+" is saying, and explains how "+convo.getCurrentSubject().getName()+" REALLY works...";
			}else {
				preamble = "<html>"+this.getName() + " talks more about "+convo.getCurrentSubject().getName();
			}
			break;
		case inquire:
			if(action.isContradiction(convo.getLastAction())) {
				preamble = "<html>"+this.getName() + " is skeptical about what "+convo.getLastSpoke()+" is saying, but asks to know more.</html>";
			}else{
				preamble = "<html>"+this.getName() + " asks to know more.</html>";
			}
			return preamble;
		case transition:
			Topic topic = (Topic)(action.getParams()[0]);
			preamble = "<html>"+"You drift to talking about "+topic.getSubject().getName()+"...";
			if(convo.isStale()) {
				preamble = "<html>"+"Abruptly, he changes the topic to "+topic.getSubject().getName()+"...";
			}
			break;
		}
		
		return preamble+"<br>\""+getRelatedTopic(convo.getCurrentSubject()).stateFact(this)+".\"</html>";
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
		
		if(convo.getLastSpoke().equals(this)) {
			return retval;
		}
		if(knowledgeOnSubject(convo.getCurrentSubject()) > 0) {
			retval.add(ActionBuilder.buildInformAction(getRelatedTopic(convo.getCurrentSubject())));
		}
		if(convo.getCurrentSubject() != null) {
			retval.add(ActionBuilder.buildInquireAction(convo.getCurrentSubject()));
		}
		
		for(Topic current: topics) {
			if(!current.getSubject().equals(convo.getCurrentSubject())
					&& current.distanceFrom(mood) < intelligence){
				retval.add(ActionBuilder.buildTransitionAction(current));
			}
		}
		
		retval.add(ActionBuilder.buildSilenceAction());
		
		return retval;
	}
	
	//temporary solution to find appropriate topic
	private Topic getRelatedTopic(Subject subj) {
		for(Topic current: topics) {
			if(current.getSubject().equals(subj)) {
				return current;
			}
		}
		return null;
	}
}
