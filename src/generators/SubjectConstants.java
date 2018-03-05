package generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Dimension;
import main.Factsheet;
import main.Subject;

public class SubjectConstants {

	public static Subject LAZY_LEAF_INN_VISITOR;
	public static Subject LAZY_LEAF_INN_PATRON;
	public static Subject LAZY_LEAF_INN_LOCAL;
	public static Subject MERCHANT_TRADE;

	
	static{
		Map<Dimension, Integer> location = new HashMap<Dimension, Integer>(); 
		location.put(Dimension.logic1, -1);
		location.put(Dimension.logic2, -1);
		location.put(Dimension.happiness, 1);
		
		Factsheet facts = new Factsheet();
		facts.addFact(new String[] {"it's the place we're at","settled neatly near the edge of the city"}, 1);
		
		LAZY_LEAF_INN_VISITOR = new Subject("Lazy Leaf Inn",location,facts);
		LAZY_LEAF_INN_VISITOR.addFact(new String[] {"a cute little place. The closest thing to cute the Rythins get, at least"}, 1);
		
		facts.addFact(new String[] {"I think I've come here for a few years now","it's the prices, really"}, 2);
			
		LAZY_LEAF_INN_PATRON = new Subject("Lazy Leaf Inn",location,facts);
		
		facts.addFact(new String[] {"you should meet the cook","good kid","his mother put him up to this job I think"}, 2);

		LAZY_LEAF_INN_LOCAL = new Subject("Lazy Leaf Inn",location,facts);
		
		location = new HashMap<Dimension, Integer>(); 
		location.put(Dimension.logic1, -2);
		location.put(Dimension.logic2, 0);
		location.put(Dimension.happiness, 2);
		location.put(Dimension.respect, 1);
		location.put(Dimension.trust, 1);
		
		facts = new Factsheet();
		facts.addFact(new String[] {"I'm a traveling merchant","came into town to hawk my wares, but that doesn't mean I can't enjoy down time"}, 1);
		facts.addFact(new String[] {"the real trick is to buy low and sell high"}, 2);
		
		MERCHANT_TRADE = new Subject("Merchant Trade",location,facts);
	}
	
	
}
