import javafx.scene.Group;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * This is the class that puts each card on the board.
 * There are 2 types of board. One where you put your
 * regular attack cards where max = 2. and one where
 * you put your ability cards which holds 1.
 */
public class Board implements Announcer{

    private final int MAX = 3; //maxSize on arrayList.
    private double screenWidth, screenHeight;
    private Hand hand;
    //our box where the cards are put
    private double posX, posY;
    private Group root;
    private boolean AI;
    private boolean match, endTurn;
    private Dice dice;
    private boolean boardEventCheck;

    /*
    Position Card[3] kommer tillhöra event korten.
     */
    private ArrayList<Card> regularCards; //board can hold a maximum of 2 regular cards
    private Card eventCard; //board can hold only one event card.

    public Board(double w, double h, Hand hand, Group root, Dice dice, boolean AI) {
        regularCards = new ArrayList<Card>();
        screenHeight = h;
        screenWidth = w;
        this.hand = hand;
        this.root = root;
        this.AI = AI;
        match = false;
        this.dice = dice;
        endTurn = false;
        boardEventCheck = true;
    }

    /**
     * Add cards to the board.
     * When a card is added to the board, the dice will appear so you can click on it
     * @param card
     */
    public void add(Card card) {
        //if selected card is event card then set it to the variable eventCard.
        if((card.getType() == Type.EVENT) && eventCard == null) {
            setEventCard(card);
            return;
        }
        regularCards.add(card);

        //sätt kortets rotation här:
        VBox box = card.getVBoxCard();
        if(!AI) {
           //box.setRotate(hand.getLightRandom()); //LÄGG TILL DENNA OM DU VILL ATT DINA KORT ROTERAR LITE. TYCKER INTE OM DET PERSONLIGEN
        }
        if(AI) {
            card.flipCard();
            card.generateCard(root, AI);
        }
        //Chat.storeText(card.toString() + "was added to the board.");
        drawBoard();
    }

    /**
     * To draw the board we simply set the position of the cards
     * defined in the variables regularCards and eventCard.
     */
    public void drawBoard() {
        //set these two based on screenWidth & Height properties
        double x, y;
        if(!AI) {
            x = screenWidth * 0.3;
            y = screenHeight * 0.44;
        } else {
            x = screenWidth * 0.3;
            y = screenHeight * 0.09;
        }

        for(Card c : regularCards) {
            VBox card = c.getVBoxCard();
            card.setTranslateX(x);
            card.setTranslateY(y);
            if(AI) {
                card.setRotate(180 + hand.getLightRandom()); //ROTATES OPPONENTS CARD. LOOKS KIND OF COOL
            }
            if(!root.getChildren().contains(card)) {
                root.getChildren().addAll(card);
            }
            x += 190;
        }

        if(eventCard != null) {
            //rita eventkortet
        }
    }

    /**
     * Use this method to set eventCard.
     * Set to null if you wanna remove it.
     * @param card
     */
    public void setEventCard(Card card) {
        eventCard = card;
    }




    /**
     * Use this method to check if you can put the chosen card in Board
     * or not. For example if you already have 1 Event card on board, you
     * can't put another one in there. If you have 2 regular cards on board
     * you can't put anymore etc..
     * @return true if you are allowed to place card on board
     */
    public boolean check() {
        if(regularCards.size() >= 2) {
            return false;
        }
        if(eventCard != null) {
            return false;
        }
        return true;
    }

    /**
     * Events used for board. when dice is thrown,
     * cards with matched nr on the board will glow
     * and you can press on them to attack.
     *
     * You can also throw the cards away if you don't want them
     * on your deck. (??)
     */
    public void boardEventDice() {
        int diceNr = dice.getDiceNumber(); //gives rolled dice nr.
        boardEventCheck = false;
        for (Card c : regularCards) {
            ArrayList<Integer> numbers = c.getSpellsDiceNumber();
            if (numbers.contains(diceNr) && !c.getHasAttacked()) {
                match = true; //dvs vi har en matchning.
                c.getVBoxCard().getStyleClass().add("vboxGlow");
                c.setAttackStatus(true); //use this variable inside the Attack Class.
                //boardAttackEvent(c, opponentBoard);
            } else {
                c.setDefaultCardStyle(); //call this when attack is done.
            }
        }
    }

    /**
     * Använd denna metod för att se om det nu är tillåtet att rita END TURN knappen.
     * Denna knapp ritas då alla kort på board attackStatus = false;
     * @return false eller true beroende på boardens attack status.
     */
    public boolean attacksDone() {
        boolean x = false;
        for(Card c : regularCards) {
            if(c.getAttackStatus() == true) {
                x = true;
            }
        }
        return x;
    }

    /*
    //important method used in gameLoop.
    public boolean checkBoardMatch() {
        match = false;
        int x = dice.getDiceNumber();
        for (Card c : regularCards) {
            ArrayList<Integer> nr = c.getSpellsDiceNumber();
            if (nr.contains(x)) {
                match = true;
                //c.getVBoxCard().getStyleClass().add("vboxGlow");
            }
            //c.setDefaultCardStyle(); call this when attack is done.
        }
        return match;
    }
*/

    public boolean getMatch() {
        return match;
    }

    /**
     * anropa denna i slutet av OpponentAI turn
     */
    public void resetMatch() {
        match = false;
    }

    public boolean getEndTurn() {
        return endTurn;
    }

    public ArrayList<Card> cardsOnBoard() {
        return regularCards;
    }

    /**
     * Remove desired card from board. For example
     * when the card hp is below 0.
     * @param card
     */
    public void removeFromBoard(Card card) {
        if(regularCards.contains(card)) {
            regularCards.remove(card);
        }
    }
    /*
    IDÉ: Varje board objekt har en x,y position. Boardet är där dina kort från handen
    placeras. Du har två rutor för dina vanliga attack kort och sedan en ruta för ability kort.
    Ability korten kan antingen vara passive saker eller event kort.
     */

    public int getSize() {
        return regularCards.size();
    }

    public Card getCard(int index) {
        return regularCards.get(index);
    }

    /**
     * The variable is used so that the boardEventDice method
     * is only called once after dice is thrown.
     * @return
     */
    public boolean getBoardEventCheck() {
        return boardEventCheck;
    }

    /**
     * Call this method after your turn is done.
     */
    public void resetBoardEventCheck() {
        boardEventCheck = true;
    }

    public void announceEvents() {
    	
    }


}
