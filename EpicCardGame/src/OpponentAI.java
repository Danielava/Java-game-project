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
    private Dice opponentDice;
    private Board opponentBoard;
    private boolean yourTurn; //AI turn if this is set to true
    private ArrayList<Card> cardsInDeck, cardsInHand;
    private final long startNanoTime = System.nanoTime(); //only used for animation
    double i;
    private boolean pause, unpause; //use these for timer

    private GameLoopUser nemesis;


    public OpponentAI(Deck deck, Scene scene, Group root, Dice dice, boolean turn) {
        opponentDeck = deck;
        this.scene = scene;
        this.root = root;
        this.opponentDice = dice;
        yourTurn = turn; //how to access this from outside?
        opponentHand = deck.getHand();
        opponentBoard = opponentHand.getBoard();
        cardsInHand = opponentHand.getHand();
        setAttributes(); //set all deck, board and hand poisitions for the AI.
        i = 0.0;
        unPause();
    }

    private void setAttributes() {

    }


    //GAME LOOP FOR AI
    public void start() {
        new AnimationTimer() {

            @Override
            public void handle(long time) {

                if(pause) {
                    i = 0;
                }

                double t = ((time - startNanoTime) / 1000000000.0)*2;

                if(cardsInHand.size() < 5) {
                    opponentDeck.draw();
                    opponentDeck.drawDeck();
                }

                //opponents turn to put card on board and roll dice.
                if(yourTurn == true) { //put the variable to false when your turn is over.
                    int x = cardsInHand.size();
                    opponentHand.addToBoard(cardsInHand.get(x - 1));
                    //make dice appear.
                    opponentDice.diceEvent(t);

                    if(!opponentHand.getBoardAccess() && timerWait(0.8)) {
                        //throw the dice after tiny delay.
                        opponentDice.stopDice();
                        pause(); //now our timer stops ANROPA UNPAUSE INNAN DU STARTAR EN NY TIMER.
                    }

                    //allt som ska hända efter tärningen är kastad.
                    /*
                    boardEventDice gör så att korten börjar lysa om de matchar tärningen.
                    Sedan anropar vi en attackEvent så vår motståndare kan attackera.
                     */
                    if(opponentDice.getDiceThrown()) {
                        opponentBoard.boardEventDice();

                        /*
                        FIXA ATTACKER I BOARD EVENT
                         */

                        opponentDice.removeDice(); //REMOVE LATER
                        nemesis.setYourTurn(true);
                        yourTurn = false;
                    }
                }
            }

            /**
             * A small timer which will return
             * boolean when timer is done
             */
            public double timerHelper() {
                i += 0.01;
                //System.out.println(i);
                return i;
            }
            public boolean timerWait(double wait) {
                //if timer is paused this won't execute
                if(pause) {
                    return false;
                }

                double x = timerHelper();
                if(x > wait) {
                    x = 0;
                    i = 0;
                    return true;
                } return false;
            }

        }.start();
    }

    private void pause() {
        pause = true;
        unpause = false;
    }

    /**
     * Se till att unpause efter AI har kört klart sin omgång.
     */
    private void unPause() {
        pause = false;
        unpause = true;
    }

    /**
     * Sets the board access
     * This should be called by the user when his turn is done.
     */
    public void setBoardAccess(boolean v) {
        opponentHand.setBoardAccess(v);
    }

    public void setNemesis(GameLoopUser user) {
        nemesis = user;
    }

    public void setYourTurn(boolean v) {
        opponentHand.setBoardAccess(true);
        opponentDice.resetDice();
        unPause();
        yourTurn = v;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

}
