package main;

import java.util.Map;

//the abstract idea of something, with natural it's natural tendencies universal to anyone
public class Subject {

	private String name;
	private Map<Dimension,Integer> location;
	private String baseLine;
	
	public Subject(String name, Map<Dimension, Integer> location, String baseLine) {
		super();
		this.name = name;
		this.location = location;
		this.baseLine = baseLine;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Dimension, Integer> getLocation() {
		return location;
	}
	public void setLocation(Map<Dimension, Integer> location) {
		this.location = location;
	}
	public void setPosition(Dimension dimension, int position) {
		this.location.put(dimension, position);
	}
	public String getBaseLine() {
		return baseLine;
	}
	public void setBaseLine(String baseLine) {
		this.baseLine = baseLine;
	}	
}
