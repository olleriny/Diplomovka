package game;

import utils.Value;
import utils.Value.suit;
import utils.Value.value;

import java.util.Collections;
import java.util.LinkedList;

public class Talon {

	private LinkedList<Card> cards = new LinkedList<>();
	private static Card emptyCard = new Card(null, null);
	
	public Talon(){
		createAllCards();
		shuffle();
	}
	
//	public void trashCard(Card c){
//		if (cards.contains(c))
//			System.out.println("this card is already in talon");
//		else
//			cards.push(c);
//	}
	
	public Card getNextCard(){
		Card c = cards.poll();
		cards.add(c);
		return c;
//		if (cards.isEmpty())
//			return null;
//		return cards.poll();
	}
	
	public static Card getEmptyCard(){
		return emptyCard;
	}
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	private void createAllCards(){
		cards.clear();
		for (suit s: Value.suit.values()){
			for (value v: Value.value.values()){
				cards.push(new Card(v, s));
			}
		}
	}
}
