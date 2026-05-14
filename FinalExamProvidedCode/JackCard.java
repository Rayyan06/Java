// Compile Error 13: JackCard should really be extending Card
public class JackCard extends Card {
	// Compile Error 14: add JackCard's constructor
	public JackCard(String suit) {
		super(suit);
	}

	// Compile Error 29: Implement doWinRoundEffect() for jackCard (rename winRound)

	@Override
	public String doWinRoundEffect(CardGameGui gui) {
		gui.reDrawHand();
		return "Suits match! Discard hand and re-draw";
	}

	// Compile Error 12: This is a method, not a variable, it needs to use method syntax like KingCard and QueenCard classes.
	@Override
	public int getLossPenalty() {
		return 15;
	};

	

	@Override
	public String toString() {
		return "Jack of " + getSuit();
	}
}
