import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.ArrayList;

/**
 * Let's try and create an AI for this game.
 * Remember that when AI draws cards from the deck AI
 * won't flip its card so they are visible to us. Only
 * when AI puts them in board will they be visible to us.
 *
 * Also don't forget that AI cards should be rotated.
 */
public class OpponentAI {

    private Deck opponentDeck;
    private Scene scene;
    private Hand opponentHand;
    private Group root;
    private Dice dice;
    private Board opponentBoard;
    private boolean yourTurn; //AI turn if this is set to true
    private ArrayList<Card> cardsInDeck, cardsInHand;
    private final long startNanoTime = System.nanoTime(); //only used for animation

    private GameLoopUser nemesis;


    public OpponentAI(Deck deck, Scene scene, Group root, Dice dice, boolean turn) {
        opponentDeck = deck;
        this.scene = scene;
        this.root = root;
        this.dice = dice;
        yourTurn = turn; //how to access this from outside?
        opponentHand = deck.getHand();
        opponentBoard = opponentHand.getBoard();
        cardsInHand = opponentHand.getHand();

        setAttributes(); //set all deck, board and hand poisitions for the AI.
    }

    private void setAttributes() {

    }


    //GAME LOOP FOR AI
    public void start() {

        new AnimationTimer() {

            @Override
            public void handle(long time) {
                if(cardsInHand.size() < 5) {
                    opponentDeck.draw();
                    opponentDeck.drawDeck();
                }

                //opponents turn to put card on board and roll dice.
                if(yourTurn == true) { //put the variable to false when your turn is over.
                    int x = cardsInHand.size();
                    opponentHand.addToBoard(cardsInHand.get(x - 1));
                }


            }

        }.start();
    }

    public void setNemesis(GameLoopUser user) {
        nemesis = user;
    }

    public void setYourTurn(boolean v) {
        yourTurn = v;
    }

}
