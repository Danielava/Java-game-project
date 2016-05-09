import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * All cards in the hand should be visible on the lower center of the
 * screen in a row.
 */
public class Hand {
	private final int MAX = 5; //Amount of cards to be able to hold
	private ArrayList<Card> hand;

	private HBox mainBox;

	public Hand() {
		hand = new ArrayList<Card>();
		mainBox = new HBox();
	}

	/**
	 * Add a card to hand
	 */
	public void add(Card card) {
		if(hand.size() >= MAX) {
			return;
		}
		hand.add(card);
		drawVisuals();
	}

	/*
	You don't need the root. All you need to do is switch the position
	of the cards from the deck to the HBox.
	 */

	/**
	 * Makes so all cards in hand is sorted in a row at the bottom of the screen.
	 * The trick is to put them in a Hbox.
	 */
	/*

	 */
	//this method should be called in the gameLoop.
	public void drawVisuals() {
		for(Card c : hand) {
			mainBox.getChildren().add(c.getVBoxCard());
			c.show();
		}
	}



}