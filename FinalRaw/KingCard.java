public class KingCard extends Card {

	public KingCard(String suit) {

	}

	@Override
	public String doWinRoundEffect(CardGameGui gui) {
		gui.stealDealerCard();
		return "Suits match! Steal dealer's card";
	}

	@Override
	public int getLossPenalty() {
		return 15;
	}

	@Override
	public String toString() {
		return "King of " + getSuit();
	}
}
