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
    private VBox board;

    /*
    Position Card[3] kommer tillhöra event korten.
     */
    private ArrayList<Card> cards; //board can hold a maximum of 3 cards

    public Board(double w, double h, Hand hand) {
        cards = new ArrayList<Card>();
        board = new VBox();
        screenHeight = h;
        screenWidth = w;
        this.hand = hand;
    }

    public void add(Card card) {

        cards.add(card);

    }

    public void drawBoard() {

    }


    /*
    IDÉ: Varje board objekt har en x,y position. Boardet är där dina kort från handen
    placeras. Du har två rutor för dina vanliga attack kort och sedan en ruta för ability kort.
    Ability korten kan antingen vara passive saker eller event kort.
     */




}
