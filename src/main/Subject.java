package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

//the abstract idea of something, with natural it's natural tendencies universal to anyone
public class Subject {

	private String name;
	private Map<Dimension,Integer> location;
	
	public Subject(String name, Map<Dimension, Integer> location) {
		super();
		this.name = name;
		this.location = location;
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
}
