/*
Final

Three types of errors will be categorized, enumerated, and labelled:
1. Compile Errors (e.g. Syntax Errors)
2. Runtime Errors
3. Logic Errors

Each compile and runtime error will be pointed out and corrected.
For logic errors, modifications will need to be made to code that was already syntactically correct, 
so additional elaboration will be required explaining why the previous logic is invalid, justifying the changes,
and the fix will be clearly labelled.

Errors will be numbered in the order that they are encountered.
Explanations will be provided wherever the code logic is unclear.
*/


// Compile Error 1: Missing semicolon after import statement, missing import statement for java.util.Collections
import java.util.ArrayList;	
import java.util.Collections; 

// Layout
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

// Event Handling
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Components
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

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
		setLayout(new BorderLayout());	// Compile Error 5: BorderLayout was never imported from java awt.

		JPanel handPanel = new JPanel();
		handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.X_AXIS));
		// Runtime Error 2: Missing instantiation of handCards
		handCards = new JComboBox<Card>(); 
		handCards.addItem(null); // allow player to deselect card
		playButton = new JButton("Play this card");
		playButton.addActionListener(new PlayCardButtonListener());

		// Runtime Error 3: Missing playerScoreLabel instantiation
		playerScoreLabel = new JLabel();
		dealerCardLabel = new JLabel("Dealer played: ");
		dealerCardLabel.setAlignmentX(Component.RIGHT_ALIGNMENT); // Compile Error 7: Component import missing from java awt

		handPanel.add(playerScoreLabel);
		handPanel.add(handCards);

		add(dealerCardLabel, BorderLayout.NORTH);
		add(handPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initializeDeck();
	}

	// Compile Error 8: Static reference not required for initializeDeck();
	public void initializeDeck() {
		deck.clear();

		/*
		Apparently, this nested for-loop intends to fill the deck with a standard card deck
		A standard card deck has 52 cards split into four suits.
		The outer for-loop is rotating between the suits (e.g. Clubs, Spades, Hearts, Diamonds)
		The inner for-loop, presumably, is for adding the 9 numeric ranks (2-10) out of the 13 ranks in each suit
		Presumably, this program does not have an Ace Card, so lets leave it out. 		
		*/

		// Compile Error 9: suitIndex should be an int, not a String
		// Compile Error 10: Card.SUITS should be a static variable in order to use it in this context
		for(int suitIndex=0; suitIndex < Card.SUITS.length; suitIndex++) {
			String suit = Card.SUITS[suitIndex];
			for(int i=2; i<=10; i++) {
				// Compile Error 11: The NumberCard constructor requires: int number, String suit, fix the instantiation call
				
				deck.add(new NumberCard(i, suit));
			}

			// Compile Error 12: The deck.add() calls for Jack, Queen, and King card should be inside the outer for loop since they need the suit
			deck.add(new JackCard(suit));
			deck.add(new QueenCard(suit));
			deck.add(new KingCard(suit));
		}
		
		Collections.shuffle(deck);
	}

	public void changeScore(int change) {
		this.score += change;
	}

	private Card drawCard() {
		return deck.remove(deck.size()-1);
	}

	// Compile Error 6: An event handling class must implement ActionListener, which must be imported from java awt
	private class PlayCardButtonListener implements ActionListener {
		// Compile Error 14: We MUST override the abstract method actionPerformed(ActionEvent e) properly
		@Override
		public void actionPerformed(ActionEvent e) {
			Card selected = handCards.getItemAt(handCards.getSelectedIndex());
			handCards.removeItemAt(handCards.getSelectedIndex());
			String message;

			// if the card played matches the dealer card's suit, one of two things can happen:
			// 1. If it's a number card, they score points equal to the value of the card
			// 2. If it's a face card, they don't score points, isntead they get to use a special ability:
			// A king puts the dealer's card into the player's hand, 
			// a queen allows the player to draw an additional card, 
			// and a jack allows the player to discard their hand and draw that many cards.

			// Compile Error 15: We should use a getter for suit, because suit is a private variable
			// Compile Error 16: The variable name for the selected card is "selected", not "card"
			if (selected.getSuit().equals(dealerCard.getSuit())) {
				// Compile Error 17: The variable name for the selected card is "selected", not "selectedCard"
				message = selected.doWinRoundEffect(CardGameGui.this);
				// Compile/Logic Error 18: the doWinRoundEffect function should handle the logic, move the change score there for polymorphic style
						
			} else {
				int penalty = dealerCard.getLossPenalty();
				message = "Suits don't match. Lose " + penalty + " points";
				changeScore(penalty);
				drawPlayerCards(1);
			}
			// Compile Error 19: message is not defined
			// Compile Error 20: JOptionPane is not imported
			// Compile Error 21: showMessageDialog is not defined, it belongs to the JOptionPane class
			JOptionPane.showMessageDialog(CardGameGui.this, message, "Card result", JOptionPane.INFORMATION_MESSAGE);
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
			// Compile Error 22: card should be the return value of drawCard() here:
			handCards.addItem(drawCard());
		}
		handCards.repaint();
	}

	public void reDrawHand() {
		handCards.removeAllItems();
		drawPlayerCards(handCards.getItemCount());
	}

	// Compile Error 23: stealDealerCard() does not need to return anything.
	public void stealDealerCard() {
		// note that we don't need to remove dealerCard from the deck, since it was already removed when we called drawCard() at the start of the turn
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

	// Runtime Error 1: main() method improperly defined
	public static void main(String[] args) {
		CardGameGui window = new CardGameGui();
		window.beginGame();
	}
}
