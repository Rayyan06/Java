public class QueenCard extends Card {

	public QueenCard(String suit) {

	}

	@Override
	public String doWinRoundEffect(CardGameGui gui) {
		gui.drawPlayerCards(1);
		return "Suits match! Draw two cards";
	}

	@Override
	public int getLossPenalty() {
		return 15;
	}
}
