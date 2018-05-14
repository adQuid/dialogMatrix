package dialogFlavor;

import java.util.ArrayList;
import java.util.List;

public class QuirkPool {

	List<String> quirks;
	List<Double> weights;
	
	public QuirkPool() {
		quirks = new ArrayList<String>();
		weights = new ArrayList<Double>();
	}
	
	public void addQuirk(String text, double weight) {
		quirks.add(text);
		weights.add(weight);
	}
	
	public String rollQuirk() {
		double roll = Math.random() * totalSize();
		for(int index=0; index<weights.size(); index++) {
			roll -= weights.get(index);
			if(roll < 0.0) {
				return quirks.get(index);
			}
		}
		System.err.println("Quirk roll failed");
		return "ERROR";
	}
	
	private double totalSize() {
		double retval = 0.0;
		for(Double current: weights) {
			retval+=current;
		}
		return retval;
	}
}
