package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//the actual "instance" of a subject that a character knows and talks about
public class Topic {
	Subject subject;
	private Map<Dimension,Integer> location;
	
	public Topic(Subject subject) {
		this.subject = subject;
		
		location = new HashMap<Dimension,Integer>();
		for(Dimension current: subject.getLocation().keySet()) {
			location.put(current, subject.getLocation().get(current) + (int)(3*Math.random()));
		}
	}
	
	public String introduce() {
		String retval = "";
		String[] factParts = subject.getFact(5);
		
		for(int index = 0; index < factParts.length; index++) {
			retval += factParts[index];
			if(index < factParts.length-1) {
				retval += ", ";
			}
		}
		return retval;
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
