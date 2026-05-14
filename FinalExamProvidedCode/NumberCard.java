public class NumberCard extends Card {
	private int number;

	public NumberCard(int number, String suit) {
		super(suit);

		// Compile Error 27: private member variable number was never defined
		this.number = number;
	}
	public int getNumber() {
		return number;
	}

	// Compile Error 28: We can remove getSuit() here, now that we moved it to Card
	
	@Override
	public String doWinRoundEffect(CardGameGui gui) {
		gui.changeScore(number);
		return "Suits match! Gain " + number + " points";
	}

	@Override
	public int getLossPenalty() {
		return getNumber();
	}

	@Override
	public String toString() {
		return number + " of " + getSuit();
	}
}
