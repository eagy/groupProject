package cards;

import java.util.*;
import javax.swing.*;


/**
 * 
 * @author Daniel Eagy
 * @version 1.0
 *
 */
public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();
	private int numDecks;
	
	/**
	 * The primary constructor. Calls on the additional construction that would 
	 * enable the use of multiple decks in a game. 
	 */
	public Deck() {
		this(1);
	}
	
	/**
	 * The secondary constructor. This one at the moment is strictly being used
	 * by the primary constructor to set up the game as a single deck. 
	 * @param numDecks the number of decks to be used in a game.
	 */
	public Deck(int numDecks) {
		this.numDecks = numDecks;

		try {
			for (int i = 0; i < numDecks; i++) {
				for (FaceValue card : FaceValue.values()) {
					for (Suit suit : Suit.values()) {
						this.deck.add(new Card(card, suit, 
								new ImageIcon(getClass().getResource("/cardpics/" 
								+ card 
								+ suit 
								+ ".png"))));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * A copy constructor. Not currently being implemented. 
	 * @param other another that is to be copied to this one. 
	 * @deprecated Not in current use; not tested.
	 */

	public Deck(Deck other) {
		this(other.numDecks);
	}
	
	/**
	 * Adds a card to the current deck.
	 * @param theCard the card to be added.
	 */
	public void addCard(Card theCard) {
		deck.add(theCard);
	}
	
	/**
	 * Shuffles the current deck.
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	/**
	 * Removes a card from the current deck.
	 * @return the card that is being removed.
	 */
	public Card removeCard() {
		if (isEmpty())
			throw new IndexOutOfBoundsException("Deck is empty.");
		return deck.remove(0);
	}
	
	/**
	 * Returns the remaining cards left in the deck.
	 * @return the number of remaining cards.
	 */
	public int remainingCards() {
		return deck.size();
	}
	
	/**
	 * Checks if the deck is empty.
	 * @return true if the deck is empty, false if not.
	 */
	public boolean isEmpty() {
		return remainingCards() == 0;
	}
	
	/**
	 * Returns an iterator of cards for the deck. 
	 * @return an Iterator<Card> 
	 */
	public Iterator<Card> Iterator() {
		return deck.iterator();
	}

}
