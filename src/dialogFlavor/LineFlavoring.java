package dialogFlavor;

import main.Character;

public class LineFlavoring {

	public static String insertFiller(Character character) {		
		QuirkPool tempPool = new QuirkPool();
		tempPool.addQuirk(",\" he mutters, taking another sip of his beer. \"", 0.08);
		tempPool.addQuirk(", ya know? ", 0.1);
		tempPool.addQuirk(". ", 0.2);
		tempPool.addQuirk(", ", 0.7);

		return tempPool.rollQuirk();
	}
	
	public static String capitalizeWhenAppropriate(String input) {
		StringBuilder retval = new StringBuilder();
		boolean first = true;
		for(int index=0; index<input.length(); index++) {
			if(java.lang.Character.isLetter(input.charAt(index))) {
				//capitalize first letter in line
				if(first) {
					first = false;
					retval.append(java.lang.Character.toUpperCase(input.charAt(index)));
					continue;
				}
								
			}
			if(input.charAt(index) == '.' ||
					input.charAt(index) == '!' ||
					input.charAt(index) == '?') {
				//special case of the elipse
				if(index+2 < input.length() && input.substring(index, index+3).equals("...")) {
					index += 2;
					retval.append("..");
					first = false;
				}else{
				first = true;
				}
			}
			retval.append(input.charAt(index));
		}
		return retval.toString();
	}
	
}
