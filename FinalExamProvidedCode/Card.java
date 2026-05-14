public abstract class Card {
	// Compile Error 2: Missing semicolon
	public static final String[] SUITS = { "spades", "hearts", "clubs", "diamonds" };

	// Compile Error 24: validSuit() should return boolean
	public static boolean validSuit(String suit) {
		for(int i=0; i<=SUITS.length; i++) {
			if(SUITS[i].equals(suit)) {
				return true;
			}
		}

		// Compile Error 26: Reached end of code with no return statement by default, we must default to false
		return false;
	} 

	private String suit;

	public Card(String suit) {
		if(!validSuit(suit)) {
			throw new IllegalArgumentException("Invalid suiit!");
		}
		suit = suit;
	}

	public String getSuit() {
		return suit;
	}

	// Compile Error 25: Abstract methods cannot have a body
	public abstract String doWinRoundEffect(CardGameGui gui);

	// Compile Error 3: Abstract methods must be placed in an abtract base class. Add abstract modifier to Card.
	// Compile Error 4: getLossPenalty should be a class method, not a variable. Add ()
	public abstract int getLossPenalty();
}
