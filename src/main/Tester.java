package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import generators.SubjectConstants;

public class Tester {

	public static void main(String[] args) {
		Character player = new Character();
		player.setPc(true);
		player.addTopic(SubjectConstants.FINE_WINES,4);
		player.addTopic(SubjectConstants.MERCHANT_TRADE, 2);
		
		Character bob = new Character();
		bob.addTopic(SubjectConstants.LAZY_LEAF_INN_PATRON,5);
		bob.addTopic(SubjectConstants.MERCHANT_TRADE,5);
		
		GUI.createGUI();
		
		Conversation testConvo = new Conversation();
		testConvo.addMember(bob);
		testConvo.addMember(player);
		
		bob.adjustMood(Dimension.happiness, 5);
		
		testConvo.conversationRound();
	}
	
}
