package players;

import java.util.*;
import javax.swing.*;

import cards.Card;
import cards.FaceValue;
import cards.Suit;

public class Hand {
	private final Card ACEH = new Card(FaceValue.ACE, 
			Suit.HEARTS, 
			new ImageIcon("ACEHEARTS.png"));
	private final Card ACED = new Card(FaceValue.ACE, 
			Suit.DIAMONDS, 
			new ImageIcon("ACEDIAMONDS.png"));
	private final Card ACES = new Card(FaceValue.ACE, 
			Suit.SPADES, 
			new ImageIcon("ACESPADES.png"));
	private final Card ACEC = new Card(FaceValue.ACE, 
			Suit.CLUBS, 
			new ImageIcon("ACECLUBS.png"));
	
	private ArrayList<Card> hand;
	private int size, total, numAces;
	
	/**
	 * Default constructor; sets all values to 0 and initializes hand as a new
	 * ArrayList<Card>.
	 */
	public Hand () {
		hand = new ArrayList<Card>();
		size = 0;
		total = 0;
	}
	
	/**
	 * Adds a card to the current hand.
	 * @param card the card that is to be added.
	 */
	public void addCard(Card card) {
		if(card.equals(ACEH) || card.equals(ACED) 
				|| card.equals(ACEC) || card.equals(ACES)) {
			numAces++;
		}
		hand.add(card);
		size++;
		
		total += card.getFaceValue().getFaceValue();
		aceValueCalc();
	}
	
	/**
	 * 
	 * @return the size of the current hand.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 
	 * @return the total of the current hand.
	 */
	public int getHandTotal() {
		return total;
	}
	
	/**
	 * 
	 * @return an Iterator<Card>
	 */
	public Iterator<Card> iterator() {
		return hand.iterator();
	}
	
	/**
	 * Checks if the hand is soft or not (has an Ace in).
	 * @return true if hand is soft, false if the hand is not.
	 */
	public boolean softHand() {
		return numAces > 0;
	}
	
	/**
	 * Private class only to be used inside of this class. Automatically 
	 * recalculates the hand value if the hand is soft (softHand()), and 
	 * the hand is greater that 21. 
	 */
	private void aceValueCalc() { 
		if(numAces > 0 && total > 21) {
			total -= 10;
			numAces--;
		}
	}
}
