package mainGame;

import cards.*;
import players.Hand;

/**
 * 
 * @author Daniel Eagy
 * @version 1.0
 */
public class Blackjack {
	private Hand dealer;
	private Hand player;
	private Deck deck;

	public Blackjack(Hand dealer, Hand player) {
		this.dealer = dealer;
		this.player = player;

		deck = new Deck();

	}
	
	/**
	 * Deals the initials cards to the player and to the dealers. Deals in the 
	 * order of player, dealer, player, dealer.
	 */
	public void deal() {
		deck.shuffle();
		player.addCard(deck.removeCard());
		dealer.addCard(deck.removeCard());
		player.addCard(deck.removeCard());
		dealer.addCard(deck.removeCard());
	}
	
	/**
	 * Adds a card to the player's hand. 
	 * @param playerOrDealer the player in which to add a card to.
	 * @return the card that is added to the hand.
	 */

	public Card hit(Hand playerOrDealer) {
		Card temp = deck.removeCard();
		playerOrDealer.addCard(temp);
		return temp;
	}
	
	/**
	 * Checks if the players and is blackjack. 
	 * @return true if the player has blackjack, and false if the player does
	 * not.
	 */
	public boolean blackjack() {
		if (player.size() == 2 && player.getHandTotal() == 21) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks whether or not the hand is bust or not. 
	 * @param playerOrDealer the hand that needs to be checked.
	 * @return true if the hand is busted, and false if the hand is not.
	 */
	public boolean bust(Hand playerOrDealer) {
		if (playerOrDealer.getHandTotal() > 21) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public Hand split(Hand player) {
		Hand returnHand = new Hand();
		returnHand.addCard(player.discard());
		return returnHand;
	}
	
	/**
	 * The end game result of the hand that was dealt.
	 * @return a string that represents the final result of the game. 
	 */
	public String gameResult() {
		String returnString;

		if (player.getHandTotal() > dealer.getHandTotal() && !bust(player)
				|| (!bust(player) && bust(dealer)))
			returnString = "PLAYER WINS";
		else if (dealer.getHandTotal() == player.getHandTotal())
			returnString = "PUSH";
		else if (dealer.getHandTotal() > player.getHandTotal() && !bust(dealer))
			returnString = "DEALER WINS";
		else
			returnString = "BUST";

		return returnString;
	}

}
