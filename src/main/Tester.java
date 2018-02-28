package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester {

	public static void main(String[] args) {
		List<Subject> subjects = generateTestSubjects();
		Character bob = new Character();
		bob.setTopics(subjects);
		bob.waitForTopic();
	}
	
	private static List<Subject> generateTestSubjects(){
		List<Subject> retval = new ArrayList<Subject>();
		
		Map<Dimension, Integer> location = new HashMap<Dimension, Integer>(); 
		location.put(Dimension.logic1, 0);
		location.put(Dimension.logic2, 0);
		location.put(Dimension.happiness, 1);
		
		retval.add(new Subject("Lazy Leaf Inn",location,"It's where we are."));
		
		location = new HashMap<Dimension, Integer>(); 
		location.put(Dimension.logic1, 0);
		location.put(Dimension.logic2, 0);
		location.put(Dimension.happiness, 2);
		location.put(Dimension.respect, 1);
		location.put(Dimension.trust, 1);
		
		retval.add(new Subject("My Trade",location,"I'd rather not talk about it. I'm off the clock."));
		
		return retval;
	}
}
