public class NumberCard extends Card {
	public NumberCard(int number, String suit) {
		super(suit);
		this.number = number;
	}
	public int getNumber() {
		return number;
	}
	public String getSuit() {
		return suit;
	}
	
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
