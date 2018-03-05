package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factsheet {

	private Map<Integer,List<String[]>> facts;
	
	public Factsheet() {
		this.facts = new HashMap<Integer,List<String[]>>();
	}
	
	public void addFact(String[] fact, int level) {
		if(facts.get(level) == null) {
			facts.put(level,new ArrayList<String[]>());
		}
		
		facts.get(level).add(fact);
	}

	public Map<Integer, List<String[]>> getFacts() {
		return facts;
	}

	public void setFacts(Map<Integer, List<String[]>> facts) {
		this.facts = facts;
	}
}
