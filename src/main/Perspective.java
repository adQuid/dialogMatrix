package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Perspective {

	private Factsheet facts;
	
	public Perspective(Factsheet facts) {
		this.facts = facts;
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
}
