import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * All cards in the hand should be visible on the lower center of the
 * screen in a row.
 */
public class Hand {
	public final int MAX = 5; //Amount of cards to be able to hold
	private ArrayList<Card> hand; //alla kort i handen ligger här.
	private int xPos, yPos;
	private double screenWidth, screenHeight;
	private Board board;
	double handYPosition;

	private HBox mainBox; //the box where hand is held !!!NEVER USED!!!

	public Hand(double screenWidth, double screenHeight, double handYPos, Group root) {

		handYPosition = handYPos;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		hand = new ArrayList<Card>();
		mainBox = new HBox();
		mainBox.setTranslateX(0);
		mainBox.setTranslateY(0);

		board = new Board(screenWidth, screenHeight, this, root);
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
	 * Adds desired card from hand to the board
	 */
	//BASICALLY THE REMOVE FUNCTION
	//should be put in the game loop.
	public void addToBoard(Card card) {
		board.add(card); //adds the card to board
		/*
		Kör generateCard i Board classen
		 */
		hand.remove(card); //remove card from hand

		sort(); //sort again

		for(Card d : hand) {
			VBox box = d.getVBoxCard();
			box.toFront();
		}
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
	 * Returns the board associated with the Hand.
	 */
	public Board getBoard() {
		return board;
	}


	/**
	 * The method "draws" the picked card on the screen.
	 * But that's all it does, use the sort method to
	 * sort it beautifully in the hand.
	 */
	/*
		GLÖM EJ ATT BYTA KORTETS X OCH Y POSITION TILL BOXEN
	*/
	public void drawVisuals() {
		for(Card c : hand) {
			c.flipCard();
			c.generateCard(c.getRoot());
			VBox card = c.getVBoxCard(); //ger dendär bakgrunden
			//experiment with these variables to put in hand.
			//write a good algorithm.

			sort();
			mainBox.getChildren().add(card); //THIS IS NEVER USED
			c.show(); //adds card to the root
		}
	}

	/**
	 * The method will define cards x & y position
	 * in your hand and adjust these based on how
	 * many cards there are in you hand.
	 */
	//should be called whenever you add or remove a card from the hand.
	/*
	Write like an algorithm that will decide the card position based on
	card width and screen width.
	 */
	public void sort() {

		int cardAmount = hand.size(); //current cards in hand
		int cardGap = 130; //cardGap

		int value = (cardAmount*cardGap)/2; //change this value to change position of hand

		double handXPosition = screenWidth/2 + 90; //perfect for sorting hand in middle.
		handXPosition -= cardGap + value;

		for(Card c : hand) {
			VBox card = c.getVBoxCard();
			card.setTranslateY(handYPosition); //ändra på denna
			card.setTranslateX(handXPosition);
			handXPosition += cardGap;
		}

	}

	/**
	 * The handEvent should be called inside the game loop.
	 * This method makes it so that whenever you hold the mouse on
	 * a card, that card should visually move up a little bit so you can
	 * see all its spells etc.
	 */
	//put this in game loop
	public void handEvent() {
		double newPosition = handYPosition - 99; //the card position when mouse is helt on it
		double oldPosition = handYPosition;

		for(Card c : hand) {

			VBox card = c.getVBoxCard();

			//mouseEvent här
			card.setOnMouseEntered(e -> {
				if(hand.contains(c)) {
					card.setTranslateY(newPosition); //ändrar kortets position när du håller på kortet.
					card.toFront();
				}
			});

			card.setOnMouseExited(e -> {
				if(hand.contains(c)) {
					card.setTranslateY(oldPosition); //drar tillbaka kortets position när du tar bort.
				/*
				This part puts everything back to normal once mouse is exited.
				*/
					for (Card d : hand) {
						VBox box = d.getVBoxCard();
						box.toFront();
					}
				}
			});

			//when card is clicked
			card.setOnMouseClicked(e -> {
				if(board.check() && hand.contains(c)) {
					addToBoard(c);
				}
			});
		}
	}
}