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
	
	public Factsheet(String[][]...factLists) {
		this();
		int index = 0;
		for(String[][] row: factLists) {
			facts.put(index, new ArrayList<String[]>());
			for(String[] current: row) {
				facts.get(index).add(current);
			}
			index++;
		}
	}
	
	public Factsheet(Factsheet...factsheets) {
		this();
		for(Factsheet current: factsheets) {
			this.facts.putAll(current.facts);
		}
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
