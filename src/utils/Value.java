package utils;

import java.util.Comparator;

import game.Bot;
import bots.*;


public class Value {

	public static enum suit {HEARTS, DIAMONDS, CLUBS, SPADES};
	public static enum value {_2, _3, _4, _5, _6, _7 , _8, _9, _10, _J, _Q, _K, _A};
	public static enum state {none, checked, folded, raised, called, allIn};
	public static enum hands {highCard, onePair, twoPair, triple, straight, flush, fullHouse, fourOfAKind, straightFlush, royalFlush};
	
	public static Comparator<Bot> stakeComparator = new Comparator<Bot>() {
		
		@Override
		public int compare(Bot o1, Bot o2) {
			return (int) (o2.getTotalStake() - o1.getTotalStake());
		}
	};
	
	
	public static String[] players = {
		"Empty",
		Person.getStaticName(),
		EasyBot.getStaticName()
	};
	
	public static Bot createNewPlayer(String s, int i){
		if (s.equals(Person.getStaticName()))
			return new Person(i);
		if (s.equals(EasyBot.getStaticName()))
			return new EasyBot(i);
		return null;
	}

}
