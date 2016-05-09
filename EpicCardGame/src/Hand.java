import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * All cards in the hand should be visible on the lower center of the
 * screen in a row.
 */
public class Hand {
	public final int MAX = 5; //Amount of cards to be able to hold
	private ArrayList<Card> hand;
	private int xPos, yPos;

	private HBox mainBox; //the box where hand is held

	public Hand() {
		hand = new ArrayList<Card>();
		mainBox = new HBox();
		mainBox.setTranslateX(0);
		mainBox.setTranslateY(0);
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

	/**
	 * returns the nr of cards currently held in hand.
     */
	public int getCardsInHand() {
		return hand.size();
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
		GLÖM EJ ATT BYTA KORTETS X OCH Y POSITION TILL BOXEN
	*/
	//this method should be called in the gameLoop.
	public void drawVisuals() {
		for(Card c : hand) {
			c.flipCard();
			c.generateCard(c.getRoot());
			VBox card = c.getVBoxCard(); //ger dendär bakgrunden
			//experiment with these variables to put in hand.
			//write a good algorithm.
			card.setTranslateY(100);
			card.setTranslateX(100);

			mainBox.getChildren().add(card);
			c.show();
		}
	}
}