import java.util.ArrayList, java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardGameGui extends JFrame {
	private JComboBox<Card> handCards;
	private JButton playButton;
	private JLabel playerScoreLabel;
	private JLabel dealerCardLabel;

	private ArrayList<Card> deck;
	private Card dealerCard;
	private int score;

	public CardGameGui() {
		super("Card Game");
		setLayout(new BorderLayout());

		JPanel handPanel = new JPanel();
		handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.X_AXIS));

		handCards.addItem(null); // allow player to deselect card
		playButton = new JButton("Play this card");
		playButton.addActionListener(new PlayCardButtonListener());
		dealerCardLabel = new JLabel("Dealer played: ");
		dealerCardLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		handPanel.add(playerScoreLabel);
		handPanel.add(handCards);

		add(dealerCardLabel, BorderLayout.NORTH);
		add(handPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initializeDeck();
	}

	public static void initializeDeck() {
		deck.clear();
		for(String suitIndex=0; suitIndex < Card.SUITS.length; suitIndex++) {
			String suit = Card.SUITS[suitIndex];
			for(int i=2; i<=10; i++) {
				deck.add(new NumberCard(suitIndex, i));
			}
		}
		deck.add(new JackCard(suit));
		deck.add(new QueenCard(suit));
		deck.add(new KingCard(suit));
		Collections.shuffle(deck);
	}

	public void changeScore(int change) {
		this.score += change;
	}

	private Card drawCard() {
		return deck.remove(deck.size()-1);
	}

	private class PlayCardButtonListener {
		public void actionPerformed() {
			Card selected = handCards.getItemAt(handCards.getSelectedIndex());
			handCards.removeItemAt(handCards.getSelectedIndex());
			if (card.suit.equals(dealerCard.suit)) {
				String message = selectedCard.doWinRoundEffect(CardGameGui.this);
				changeScore(card.getNumber());
			} else {
				int penalty = dealerCard.getLossPenalty();
				String message = "Suits don't match. Lose " + penalty + " points";
				changeScore(penalty);
				drawPlayerCards(1);
			}
			showMessageDialog(CardGameGui.this, message, "Card result", JOptionPane.INFORMATION_MESSAGE);
			if(deck.isEmpty()) {
				endGame();
			}
		}
	}

	public void dealCard() {
		dealerCard = drawCard();
		dealerCardLabel.setText("Dealer played: " + dealerCard.toString());
		dealerCardLabel.repaint();
	}

	public void drawPlayerCards(int count) {
		for(int i=0; i<5; i++) {
			drawCard();
			handCards.addItem(card);
		}
		handCards.repaint();
	}

	public void reDrawHand() {
		handCards.removeAllItems();
		drawPlayerCards(handCards.getItemCount());
	}

	public Card stealDealerCard() {
		handCards.addItem(dealerCard);
	}

	private void beginGame() {
		initializeDeck();
		drawPlayerCards(5);
		dealCard();
		pack();
	}

	private void endGame() {
		if(score > 0) {
			JOptionPane.showMessageDialog(CardGameGui.this, "Congratulations! Your score was " + score, "You win!", JOptionPane.INFORMATION_MESSAGE);
		} else if(score < 0) {
			JOptionPane.showMessageDialog(CardGameGui.this, "Better luck next time. Dealer score was " + -score, "You lose...", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(CardGameGui.this, "The game was a draw.", "Nobody won?", JOptionPane.INFORMATION_MESSAGE);
		}
		setVisible(false);
		dispose();
	}

	public void main() {
		CardGameGui window = new CardGameGui();
		window.beginGame();
	}
}
