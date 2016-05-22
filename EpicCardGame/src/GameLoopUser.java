import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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

	public static int killCount;
	
    private Button pressEndTurn; //the button will appear on screen when your turn is done. press it to end your turn.
    private boolean endTurn;

    private Deck myDeck;
    private Scene scene;
    private Hand myHand;
    private Group root;
    private Dice myDice;
    private Chat myChat;
    private Board board;
    private boolean yourTurn; //your turn if this is true which it is at the beginning
    final long startNanoTime = System.nanoTime(); //only used for animation
    private ArrayList<Card> cardsOnBoard;

    private Attack myAttack;

    private boolean firstTurn; //only true during the first turn
    private boolean gameWin;

    private OpponentAI nemesis;

    //constructor
    public GameLoopUser(Deck deck, Scene scene, Group root, Dice dice, Chat chat, boolean turn) {
        pressEndTurn = new Button("END TURN");
        pressEndTurn.setTranslateX(0);
        pressEndTurn.setTranslateY(0);
        killCount = 0;

        myDeck = deck;
        this.scene = scene;
        myHand = myDeck.getHand();
        this.root = root;
        myDice = dice;
        myChat = chat;
        board = myHand.getBoard();
        yourTurn = turn;
        this.cardsOnBoard = board.cardsOnBoard();
        endTurn = board.getEndTurn();
        firstTurn = true;
        gameWin = false;
    }

    public void startGame() {

        //scene.getOnKeyPressed();
    	
        new AnimationTimer() {
        	/*
        	BUG: SÅ FORT DU LÄGGER KORT PÅ BOARD, DVS SÅ FORT GETBOARDACCESS VARIABELN BLIR FALSE SÅ BUGGAR TÄRNINGEN
        	 */
            @Override
            public void handle(long time) {
            	if(gameWin == true) {
            		for(Card c : board.cardsOnBoard()) {
            			c.remove();
            		}
            		Image gameWin = new Image("images/wonthegame.png");
            		VBox box = new VBox();
            		ImageView iv = new ImageView();
            		iv.setImage(gameWin);
            		box.getChildren().addAll(iv);
            		root.getChildren().add(box);
            		while(true) {
            			System.out.println("WON!");
            		}
            	}
            	double t = ((time - startNanoTime) / 1000000000.0)*2;

                myDeck.deckEvent(); //the deck event draws a card when you click on deck.
                myHand.handEvent(); //events for player hand.
                myChat.chatEvent(); //endTurn = board.getEndTurn();

                if(yourTurn == true && !firstTurn) {
                    if (!myHand.getBoardAccess() || cardsOnBoard.size() == 2) { //when you are done with putting card on board.
                        myDice.diceEvent(t); //animates the dice and make it interactive.
                    }
                    if (myDice.getDiceThrown()) {
                        board.boardEventDice(); //create board events. gör så korten lyser
                        if(board.checkBoardMatch()) {
                            myAttack.pickAttackCardEvent(); //gör så du kan välja attack kort.
                            myAttack.attackOpponentEvent(); //gör så du kan attackera motståndaren.
                        }
                    }
                }

                /*
                OK så ifall vi inte har någon match efter att tärningen kastats så dyker knappen endTurn upp och du kan avsluta
                din runda, och om det är din tur. Men nu är denna fixad så att du i första rundan endast kan placera ett kort.
                 */
                if(!root.getChildren().contains(pressEndTurn) && myDice.getDiceThrown() && yourTurn
                        || firstTurn && !root.getChildren().contains(pressEndTurn) && yourTurn && !myHand.getBoardAccess()) {
                    root.getChildren().addAll(pressEndTurn);
                }

                //det som händer när du trycker på END_TURN knappen.
                pressEndTurn.setOnAction(e -> {
                    nemesis.setYourTurn(true); //fundamental

                    yourTurn = false;
                    root.getChildren().remove(pressEndTurn);
                    myDice.removeDice();
                    firstTurn = false;
                    Chat.storeText("The turn was ended!");
                    for(Card c : getBoard().cardsOnBoard()) {
                    	c.setHasAttacked(false);
                    	c.setDefaultCardStyle();
                    }
                    if(killCount == 5) {
                    	gameWin = true;
                    }
                });

            }

        }.start();
    }

    public Board getBoard() {
        return board;
    }


    public void setNemesis(OpponentAI ai) {
        nemesis = ai;
        myAttack = new Attack(this, nemesis);
    }


    public OpponentAI getNemesis() {
        return nemesis;
    }

    public Dice getDice() {
        return myDice;
    }

    public void setYourTurn(boolean v) {
        myHand.setBoardAccess(true); //makes it so you can place on the board again
        myDice.resetDice(); //fundamental so you can press on dice again.
        yourTurn = v;
    }
}