import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

/**
 * This is the main GameLoop
 * In here there are some stuff to keep track of.
 *
 * 1. Make it so that when you click on the deck, you will get a new card in your hand.
 * Also the card should then be flipped and visible, and you can't have more than 5 cards
 * in your hand.
 *
 * THIS GAME LOOP CLASS IS MEANT FOR THE USER (YOU) ONLY.
 * THIS IS NOT THE AI. SEE THIS AS THE USER INTERFACE
 */
public class GameLoopUser{

    private Deck myDeck;
    private Scene scene;

    //constructor
    public GameLoopUser(Deck deck, Scene scene) {
        myDeck = deck;
        this.scene = scene;
    }




    public void startGame() {

        scene.getOnKeyPressed();

        new AnimationTimer() {

            @Override
            public void handle(long time) {




            }


        }.start();
    }
}
