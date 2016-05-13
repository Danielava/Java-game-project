import javafx.scene.Group;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * This is the class that puts each card on the board.
 * There are 2 types of board. One where you put your
 * regular attack cards where max = 2. and one where
 * you put your ability cards which holds 1.
 */
public class Board {

    private final int MAX = 3; //maxSize on arrayList.
    private double screenWidth, screenHeight;
    private Hand hand;
    //our box where the cards are put
    private double posX, posY;
    private Group root;

    /*
    Position Card[3] kommer tillhöra event korten.
     */
    private ArrayList<Card> regularCards; //board can hold a maximum of 2 regular cards
    private Card eventCard; //board can hold only one event card.

    public Board(double w, double h, Hand hand, Group root) {
        regularCards = new ArrayList<Card>();
        screenHeight = h;
        screenWidth = w;
        this.hand = hand;
        this.root = root;
    }

    /**
     * Add cards to the board
     * @param card
     */
    public void add(Card card) {
        //if selected card is event card then set it to the variable eventCard.
        if((card.getType() == Type.EVENT) && eventCard == null) {
            setEventCard(card);
            return;
        }
        regularCards.add(card);
        drawBoard();
    }

    /**
     * To draw the board we simply set the position of the cards
     * defined in the variables regularCards and eventCard.
     */
    public void drawBoard() {
        //set these two based on screenWidth & Height properties
        double x = screenWidth * 0.3;
        double y = screenHeight * 0.44;

        for(Card c : regularCards) {
            VBox card = c.getVBoxCard();
            card.setTranslateX(x);
            card.setTranslateY(y);
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
     * //You can also throw the cards away if you don't want them
     * //on your deck. (??)
     * @param dice
     */
    public void boardEvent(Dice dice) {
        int diceNr = dice.getDiceNumber();

        for(Card c : regularCards)  {
            ArrayList<Integer> numbers = c.getSpellsDiceNumber();
            if(numbers.contains(diceNr)) {
                c.getVBoxCard().getStyleClass().add("vboxGlow");
            }
            //c.setDefatulCardStyle(); call this when attack is done.
        }
    }

    /*
    IDÉ: Varje board objekt har en x,y position. Boardet är där dina kort från handen
    placeras. Du har två rutor för dina vanliga attack kort och sedan en ruta för ability kort.
    Ability korten kan antingen vara passive saker eller event kort.
     */




}
