import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.util.ArrayList;

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
public class GameLoopUser {

    private Button pressEndTurn; //the button will appear on screen when your turn is done. press it to end your turn.
    private boolean endTurn;

    private Deck myDeck;
    private Scene scene;
    private Hand myHand;
    private Group root;
    private Dice dice;
    private Board board;
    private boolean yourTurn; //your turn if this is true which it is at the beginning
    final long startNanoTime = System.nanoTime(); //only used for animation
    private ArrayList<Card> cardsOnBoard;

    private boolean firstTurn; //only true during the first turn

    private OpponentAI nemesis;

    //constructor
    public GameLoopUser(Deck deck, Scene scene, Group root, Dice dice, boolean turn) {
        pressEndTurn = new Button("END TURN");
        pressEndTurn.setTranslateX(0);
        pressEndTurn.setTranslateY(0);

        myDeck = deck;
        this.scene = scene;
        myHand = myDeck.getHand();
        this.root = root;
        this.dice = dice;
        board = myHand.getBoard();
        yourTurn = turn;
        this.cardsOnBoard = board.cardsOnBoard();
        endTurn = board.getEndTurn();
        firstTurn = true;
    }

    public void startGame() {

        //scene.getOnKeyPressed();
    	
        new AnimationTimer() {
        	/*
        	BUG: SÅ FORT DU LÄGGER KORT PÅ BOARD, DVS SÅ FORT GETBOARDACCESS VARIABELN BLIR FALSE SÅ BUGGAR TÄRNINGEN
        	 */
            @Override
            public void handle(long time) {
            	double t = ((time - startNanoTime) / 1000000000.0)*2;

                myDeck.deckEvent(); //the deck event draws a card when you click on deck.
                myHand.handEvent(); //events for player hand.
                //endTurn = board.getEndTurn();

                if(yourTurn == true && !firstTurn) {
                    if (myHand.getBoardAccess() == false || cardsOnBoard.size() == 2) { //when you are done with putting card on board.
                        dice.diceEvent(t); //animates the dice and make it interactive.
                    }
                    if (dice.getDiceThrown()) {
                        board.boardEventDice(); //create board events
                    }
                }

                /*
                OK så ifall vi inte har någon match efter att tärningen kastats så dyker knappen endTurn upp och du kan avsluta
                din runda. och om det är din tur. Men nu är denna fixad så att du i första rundan endast kan placera ett kort.
                 */
                if(!board.checkBoardMatch() && !root.getChildren().contains(pressEndTurn) && dice.getDiceThrown() && yourTurn
                        || firstTurn && !root.getChildren().contains(pressEndTurn) && yourTurn && !myHand.getBoardAccess()) {
                    root.getChildren().addAll(pressEndTurn);
                }

                //det som händer när du trycker på knappen.
                pressEndTurn.setOnAction(e -> {
                    nemesis.setYourTurn(true); //fundamental
                    nemesis.setBoardAccess(true); //fundamental

                    yourTurn = false;
                    root.getChildren().remove(pressEndTurn);
                    dice.removeDice();
                    firstTurn = false;
                });

            }

        }.start();
    }

    public void setNemesis(OpponentAI ai) {
        nemesis = ai;
    }

    public void setYourTurn(boolean v) {
        yourTurn = v;
    }
}