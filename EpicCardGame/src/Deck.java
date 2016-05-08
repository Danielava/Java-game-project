import java.util.*;

/**
 * The deck class is a object that's
 * meant to hold a lot of cards.
 * This class will communicate with the Hand class.
 * Drawing cards from deck will put them in you Hand.
 *
 * The way this class will work is that the cards will all
 * be inside a deck in a set order but when you draw one card,
 * it will randomly pick one from the deck, making it look like
 * the deck is shuffled
 */

public class Deck {
	private ArrayList<Card> deck;
	private final int deckSize = 20;
	/*
	These variables sets the X and Y position of the deck.
	The X and Y position set is needed to distinquish your
	and opponent deck.
	 */
	private final int DECKPOSX, DECKPOSY;


	public Deck(int posX, int posY) {
		deck = new ArrayList<Card>();
		DECKPOSX = posX;
		DECKPOSY = posY;
	}

	/**
	 * draws a card randomly from the deck
	 * and puts it in your hand. It's basicly like a
	 * remove function.
	 */
	public void draw() {
		int amount = deck.size();
	}

	/**
	 * add cards to the deck
	 * @param card you wanna add
     */
	public void add(Card card) {
		deck.add(card);
	}

	/**
	 * return the deck size
	 */
	public int getSize() {
		return deck.size();
	}

	/**
	 * Draws the deck on the screen in a cool way
	 * so that you can visually see how many cards there are
	 * in the deck etc
	 */
	//should not be in the game loop. only draw deck once
	public void drawDeck() {
		int x = DECKPOSX;
		int y = DECKPOSY;

		for(Card c : deck) {
			c.setPos(x, y);
			c.draw();
			x += 3;
			y += 3;
		}
	}
}
