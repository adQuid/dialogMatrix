package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character {

	private boolean pc = false;
	
	private Map<Dimension,Integer> mood = new HashMap<Dimension,Integer>();
	private List<Topic> topics =  new ArrayList<Topic>();;
	
	public Character() {
		for(Dimension current: Dimension.values()) {
			mood.put(current, 0);
		}
	}
	
	public void addTopic(Subject subject) {
		topics.add(new Topic(subject));
	}
	public void setTopics(List<Subject> subjects) {
		for(Subject current: subjects) {
			topics.add(new Topic(current));
		}
	}
	
	public Topic introductoryTopic() {
		return nearestTopic();
	}
	
	public String getLine(Topic topic, Action action) {
		return topic.introduce();
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
	
}
