package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dialogFlavor.LineFlavoring;

//the actual "instance" of a subject that a character knows and talks about
public class Topic {
	Subject subject;
	Perspective perspective;
	private int knowledge;
	private int passion;
	private Map<Dimension,Integer> location;
	
	public Topic(Subject subject, Perspective perspective, int knowledge, int passion) {
		this.subject = subject;
		this.perspective = perspective;
		this.knowledge = knowledge;
		this.passion = passion;
		
		location = new HashMap<Dimension,Integer>();
		for(Dimension current: subject.getLocation().keySet()) {
			location.put(current, subject.getLocation().get(current) + (int)(3*Math.random()) - 1);
		}
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public Perspective getPerspective() {
		return perspective;
	}
	
	public int getKnowledge() {
		return knowledge;
	}
	
	public int getPassion() {
		return passion;
	}
		
	public String stateFact(Character character) {
		String retval = "";
		String[] factParts = perspective.getFact(knowledge);
		
		for(int index = 0; index < factParts.length; index++) {
			retval += factParts[index];
			if(index < factParts.length-1) {
				retval += LineFlavoring.insertFiller(character);
			}
		}
		return LineFlavoring.capitalizeWhenAppropriate(retval);
	}
	
	public double distanceFrom(Map<Dimension,Integer> position) {
		double retval = 0;
		
		for(Dimension current: position.keySet()) {
			if(location.containsKey(current)) {
				retval += Math.pow(location.get(current) - position.get(current),2);
			}
		}
		return Math.sqrt(retval);
	}
}
