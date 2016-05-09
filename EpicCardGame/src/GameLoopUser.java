import javafx.animation.AnimationTimer;

/**
 * This is the main GameLoop
 * In here there are some stuff to keep track of.
 *
 * 1. Make it so that when you click on the deck, you will get a new card in your hand.
 * Also the card should then be flipped and visible, and you can't have more than 5 cards
 * in your hand.
 *
 */
public class GameLoopUser {

    //constructor
    public GameLoop() {

    }


    public void startGame() {

        new AnimationTimer() {

            @Override
            public void handle(long time) {

            }
        }.start();
    }


}
