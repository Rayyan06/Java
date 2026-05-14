public class Card {

	public final String[] SUITS = { "spades", "hearts", "clubs", "diamonds" }

	public static void validSuit(String suit) {
		for(int i=0; i<=SUITS.length; i++) {
			if(SUITS[i].equals(suit)) {
				return true;
			} else {
				return false;
			}
		}
	} 

	private String suit;

	public Card(String suit) {
		if(!validSuit(suit)) {
			throw new IllegalArgumentException("Invalid suiit!");
		}
		suit = suit;
	}

	public abstract String doWinRoundEffect(CardGameGui gui) {
	
	}

	public abstract int getLossPenalty;
}
