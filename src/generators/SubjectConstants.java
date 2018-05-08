package generators;

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
	public static Subject FINE_WINES;

	
	static{		
		Factsheet facts = new Factsheet();
		facts.addFact(new String[] {"it's the place we're at","settled neatly near the edge of the city"}, 1);
		
		LAZY_LEAF_INN_VISITOR = new Subject("Lazy Leaf Inn",generateLocation(-1,-1,1,0,0,0),facts);
		LAZY_LEAF_INN_VISITOR.addFact(new String[] {"a cute little place. The closest thing to cute the Rythins get, at least"}, 1);
		
		facts.addFact(new String[] {"I think I've come here for a few years now","it's the prices, mostly, but they do brew a..."}, 2);
			
		LAZY_LEAF_INN_PATRON = new Subject("Lazy Leaf Inn",generateLocation(-1,-1,1,0,0,0),facts);
		
		facts.addFact(new String[] {"...you should meet the cook","good kid","his mother put him up to this job I think"}, 2);

		LAZY_LEAF_INN_LOCAL = new Subject("Lazy Leaf Inn",generateLocation(-1,-1,1,0,0,0),facts);
		
		facts = new Factsheet();
		facts.addFact(new String[] {"I'm a traveling merchant","came into town to hawk my wares, but that doesn't mean I can't enjoy down time"}, 1);
		facts.addFact(new String[] {"the real trick is to buy low and sell high"}, 2);
		
		MERCHANT_TRADE = new Subject("Merchant Trade",generateLocation(-2,0,2,0,1,1),facts);
		
		facts = new Factsheet(new String[][] {{"There is nothing like Dornish wine","save perhaps wine from the Arbor"}},
					new String[][] {{"It is a great irony that wine keeps better in the North","but only comes from where the sun is"}});
		FINE_WINES = new Subject("Fine Wine",generateLocation(-3,0,2,0,1,1),facts);
	}
	
	private static Map<Dimension,Integer> generateLocation(int logic1, int logic2, int happiness, int anger, int respect, int trust){
		Map<Dimension,Integer> retval = new HashMap<Dimension,Integer>();
		retval.put(Dimension.logic1, logic1);
		retval.put(Dimension.logic2, logic2);
		retval.put(Dimension.happiness, happiness);
		retval.put(Dimension.respect, respect);
		retval.put(Dimension.trust, trust);
		
		return retval;
	}
}
