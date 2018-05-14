package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.AIThread;
import generators.SubjectConstants;

public class Tester {

	public static void main(String[] args) {
		Character player = new Character("Phillip Goodmore");
		player.setPc(true);
		player.addTopic(SubjectConstants.FINE_WINES,SubjectConstants.FINE_WINE_LOVER,4);
		player.addTopic(SubjectConstants.MERCHANT_TRADE,SubjectConstants.MERCHANT_TRADE_BUYER, 2);
		
		Character bob = new Character("Bob");
		bob.addTopic(SubjectConstants.LAZY_LEAF_INN,SubjectConstants.LAZY_LEAF_INN_PATRON,5);
		bob.addTopic(SubjectConstants.MERCHANT_TRADE,SubjectConstants.MERCHANT_TRADE_MERCHANT,5,10);
		
		GUI.createGUI();
		
		Conversation testConvo = new Conversation();
		testConvo.addMember(bob);
		testConvo.addMember(player);
		
		bob.adjustMood(Dimension.happiness, 5);
		
		Thread testThread = new Thread(new AIThread(bob,testConvo));
		testThread.start();
		
		testConvo.conversationRound();
	}
	
}
