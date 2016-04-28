package mainGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import cards.*;
import players.Hand;

/**
 * This class establishes the main attributes of the frame and all of its 
 * contents. It also adds the funcionality of the game with the buttons hit, 
 * stand, play again, and deal. 
 * 
 * @author Daniel Eagy
 * @version 1.0
 *
 */

public class PlayBoard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel bottom = new JPanel();
	private JPanel playerPanel = new JPanel();
	private JPanel dealerPanel = new JPanel();

	private JTextPane gameResult = new JTextPane();

	private JButton deal = new JButton("Deal");
	private JButton hit = new JButton("Hit");
	private JButton stand = new JButton("Stand");
	private JButton playAgain = new JButton("Play Again");

	private JLabel playerLabel = new JLabel();
	private JLabel dealerLabel = new JLabel();

	private Hand dealer = new Hand();
	private Hand player = new Hand();
	private Blackjack game = new Blackjack(dealer, player);

	private JLabel dHiddenCard;
	private JLabel dCard1;
	private JLabel dCard2;
	private JLabel dHitCard;

	private JLabel pCard1;
	private JLabel pCard2;
	private JLabel pHitCard;

	/**
	 * Playboard is the constructor for this JFrame application. It sets up the
	 * window, size, all the buttons, and adds their listeners.
	 */
	public PlayBoard() {
		bottom.setBackground(new Color(39, 119, 20));
		playerPanel.setBackground(new Color(39, 119, 20));
		dealerPanel.setBackground(new Color(39, 119, 20));

		bottom.setLayout(new FlowLayout());
		gameResult.setText(" ");
		gameResult.setEditable(false);

		deal.addActionListener(new deal());
		hit.addActionListener(new hit());
		hit.setEnabled(false);
		stand.addActionListener(new stand());
		stand.setEnabled(false);
		playAgain.addActionListener(new playAgain());
		playAgain.setEnabled(false);

		playerLabel.setText("Player: ");
		dealerLabel.setText("Dealer: ");

		bottom.add(gameResult);
		bottom.add(deal);
		bottom.add(hit);
		bottom.add(stand);
		bottom.add(playAgain);
		playerPanel.add(playerLabel);
		dealerPanel.add(dealerLabel);

		setLayout(new BorderLayout());
		add(dealerPanel, BorderLayout.NORTH);
		add(playerPanel, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);

	}

	class deal implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			playerPanel.add(playerLabel);
			dealerPanel.add(dealerLabel);

			dHiddenCard = new JLabel(new ImageIcon("BACK.png"));

			game.deal();

			Iterator<Card> iterator = dealer.iterator();
			Card dealerShowCard = null;
			int i = 0;

			while (iterator.hasNext()) {
				dealerShowCard = iterator.next();
				if (i == 0)
					dCard1 = new JLabel(dealerShowCard.getCardPic());
				else
					dCard2 = new JLabel(dealerShowCard.getCardPic());
				i++;
			}

			iterator = player.iterator();
			i = 0;

			while (iterator.hasNext()) {
				Card card = iterator.next();
				if (i == 0)
					pCard1 = new JLabel(card.getCardPic());
				else
					pCard2 = new JLabel(card.getCardPic());
				i++;
			}

			dealerPanel.add(dHiddenCard);
			dealerPanel.add(dCard2);

			playerPanel.add(pCard1);
			playerPanel.add(pCard2);

			dealerLabel.setText("Dealer: " + dealerShowCard.getFaceValue().getFaceValue());
			playerLabel.setText("Player: " + player.getHandTotal());

			if (game.blackjack()) {
				deal.setEnabled(false);
				hit.setEnabled(false);
				stand.setEnabled(false);
				playAgain.setEnabled(true);

				gameResult.setText("BLACKJACK");
			} else {
				deal.setEnabled(false);
				hit.setEnabled(true);
				stand.setEnabled(true);
			}

			add(dealerPanel, BorderLayout.NORTH);
			add(playerPanel, BorderLayout.CENTER);

		}
	}

	class hit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pHitCard = new JLabel(game.hit(player).getCardPic());
			playerPanel.add(pHitCard);
			playerPanel.repaint();

			if (game.bust(player)) {
				deal.setEnabled(false);
				hit.setEnabled(false);
				stand.setEnabled(false);
				playAgain.setEnabled(true);

				gameResult.setText(game.gameResult());
			}

			playerLabel.setText("Player: " + player.getHandTotal());

		}
	}

	class stand implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dealerPanel.remove(dHiddenCard);
			dealerPanel.add(dCard1);

			while (dealer.getHandTotal() < 17 || (dealer.softHand()) && dealer.getHandTotal() >= 17) {
				dHitCard = new JLabel(game.hit(dealer).getCardPic());
				dealerPanel.add(dHitCard);
				dealerPanel.repaint();
			}

			deal.setEnabled(false);
			hit.setEnabled(false);
			stand.setEnabled(false);
			playAgain.setEnabled(true);

			gameResult.setText(game.gameResult());

			dealerLabel.setText("Dealer: " + dealer.getHandTotal());
		}
	}

	class playAgain implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			player = new Hand();
			dealer = new Hand();
			game = new Blackjack(dealer, player);

			playerLabel.setText("Player: ");
			dealerLabel.setText("Dealer: ");
			gameResult.setText(" ");

			playerPanel.removeAll();
			dealerPanel.removeAll();

			hit.setEnabled(false);
			stand.setEnabled(false);
			playAgain.setEnabled(false);
			deal.setEnabled(true);
		}
	}

}
