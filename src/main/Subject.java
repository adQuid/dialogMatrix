package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

//the abstract idea of something, with natural it's natural tendencies universal to anyone
public class Subject {

	private String name;
	private Map<Dimension,Integer> location;
	private Factsheet facts;
	
	public Subject(String name, Map<Dimension, Integer> location, Factsheet facts) {
		super();
		this.name = name;
		this.location = location;
		this.facts = facts;
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
	public String[] getFact(int knowledge) {
		Random rand = new Random();
		
		List<String[]> compressedFacts = new ArrayList<String[]>();
		for(int index = 0; index <= knowledge; index++) {
			if(facts.getFacts().get(index) != null) {
				for(String[] curFact: facts.getFacts().get(index)) {
					compressedFacts.add(curFact);
				}
			}
		}
		
		return compressedFacts.get(rand.nextInt(compressedFacts.size()));
	}
	public void addFact(String[] fact, int level) {
		this.facts.addFact(fact, level);
	}
}
