public class JackCard {

	@Override
	public String winRound(CardGameGui gui) {
		gui.reDrawHand();
		return "Suits match! Discard hand and re-draw";
	}

	@Override
	public int getLossPenalty = 15;

	@Override
	public String toString() {
		return "Jack of " + getSuit();
	}
}
