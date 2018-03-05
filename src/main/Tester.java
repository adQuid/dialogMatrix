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
		
		Character bob = new Character();
		bob.addTopic(SubjectConstants.LAZY_LEAF_INN_PATRON);
		bob.addTopic(SubjectConstants.MERCHANT_TRADE);
		
		GUI.createGUI();
		
		Conversation testConvo = new Conversation();
		testConvo.addMember(bob);
		testConvo.addMember(player);
		
		testConvo.conversationRound();
	}
	
}
