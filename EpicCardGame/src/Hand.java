import java.util.ArrayList;

/**
 * All cards in the hand should be visible on the lower center of the
 * screen in a row.
 */
public class Hand {
	private final int MAX = 5; //Amount of cards to be able to hold
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}

	/**
	 * Makes so all cards in hand is sorted in a row at the bottom of the screen.
	 * The trick is to but them in a Hbox.
	 */
	public void sort() {

	}
}
