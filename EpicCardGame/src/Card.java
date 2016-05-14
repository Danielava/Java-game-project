import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * The card class. It is responsible for every card that the
 * player can put on the board.
 * The card class contains:
 * 1. One or more spells to use against enemies.
 * 2. Health points, which if reduced to or below 0 results in the card
 * being destroyed.
 * 3. A picture of the card.
 * 4. A name
 *
 * This class is supposed to create a card and then generate it to a visible thing
 * which will be drawn on the canvas.
 */
public class Card {

    private VBox card;

    private String nameString;
    private ArrayList<Spell> spells;
    private final int HEALTH;
    private int currentHp;
    private Image image; //loads the image
    private ImageView iv; //creates the visble object
    private ArrayList<Label> spellInfo;
    private Label name;
    private double posX, posY;

    private final double CARDHEIGHT = 300;
    private final double CARDWIDTH = 180;



    Group root;

    private final Type type;

    /*
        when each card is created it's flip = true, which means that
        the card is flipped. card being flipped means that you only see the
        back of it which is identical to each card. When the card is put into your hand
        flip is set to false meaning that you will be able to see the cards.

        use this variable in your game loop to check if the card is flipped or not.
    */
    private boolean flip;
    private Image dummyImage;
    private ImageView dummyIv;

    public Card(Image png, String name, int hp, ArrayList<Spell> spells, Type t) {

        root = new Group();
        type = t;

        posX = 0;
        posY = 0;

        flip = false;

        dummyImage = new Image("images/dummy.png");
        dummyIv = new ImageView();
        dummyIv.setImage(dummyImage);

        image = png;
        iv = new ImageView();
        nameString = name;
        this.name = new Label(name);
        this.spells = spells;
        HEALTH = hp;
        currentHp = hp;
        iv.setImage(image);

        //getSpellInfo(); //puts the spell info into the ArrayList smoothly so it can be taken in generateCard method.
    }

    public Image getImage() {
        return image;
    }

    public Type getType() {
        return type;
    }

    //förstör kortet
    private void destroy() {

    }

    public void setPosX(double x) {
        posX = x;
    }

    public void setPosY(double y) {
        posY = y;
    }

    public void setPos(double x, double y) {
        posX = x;
        posY = y;
    }

    public void draw() {

    }

    public ArrayList<Spell> getSpells() {
        ArrayList<Spell> list = new ArrayList<>();

        for(Spell s : spells) {
            list.add(new Spell(s.getName(), s.getPower(), s.getDiceNumber()));
        }
        return list;
    }

    public boolean getFlip() {
        return flip;
    }

    public void flipCard() {
        flip = true;
    }

    /**
     * Another help method used to put the spellInfo in
     * the form of Strings, into the VBox.
     * @param card The card you wish to add the info to.
     */
    private void generateSpell(VBox card) {

        spellInfo = new ArrayList<Label>();
        /*
        Nu hamnar all spellinfo i Vboxen.
         */
        for (Spell s : spells) {
            //spellInfo.add(s.getInfo());
            card.getChildren().add(s.getInfo());

        }

    }


    public String getName() {
        return nameString;
    }

    public int getHealth() {
        return HEALTH;
    }

    /*
	How to use this function.. Create a Card object and define its fields etc.
	This method will generate the cards, set each things to pieces like name on top,
	image under, and spells under that etc.
	To then draw this on the canvas you will write in code: root.getChildren.add(card1.generateCard());
	 */
    public void generateCard(Group root, boolean rotate) {

        this.root = root;
        root.getChildren().remove(card);


        card = new VBox();
        //These are not perfect sizes for the card imo.
        /*
        GOOD TO REMEMBER: LATER WHEN YOU HAVE NO CARDS IN DECK YOU CAN SET THESE
        VARIABELS TO 0 TO MAKE THE HBOX DECK DISAPPEAR!!!
         */
        card.setMinHeight(CARDHEIGHT);
        card.setMinWidth(CARDWIDTH);

        //if flip variable is true we are gonna draw something else on it (a dummy image)
        //making it look like the card is flipped, occurs when it's in deck
        /*
        CURRENTLY BUGGY!!
         */
        if(flip == false) {
            card.getStyleClass().add("vboxDummy");

            dummyIv.setFitHeight(card.getMinHeight());
            dummyIv.setFitWidth(card.getMinWidth());

            card.getChildren().add(dummyIv);

            card.setTranslateX(posX);
            card.setTranslateY(posY);

            //rotate both our and opponent deck
            /*
            BRA IDE!! VARJE GÅNG PROGRAMMET STARTAR SÅ SÄTTER VI DECKENS ROTATIONER
            TILL RANDOM VÄRDEN BLIR MER REALISTISKT DÅ. fixa senare om du har tid
             */
            if(rotate) {
                card.setRotate(200); //56 bra, 224 bra, 200 bra
            } else {
                card.setRotate(-18); //36 bra, 26 bra, -18 bra
            }

            return;
        }

        VBox cardName = new VBox();
        VBox cardImage = new VBox();
        VBox cardSpells = new VBox();

        cardName.getChildren().addAll(name);
        cardName.getStyleClass().addAll("vboxName");

        card.getStyleClass().addAll("vbox");

        iv.prefHeight(100);
        iv.prefWidth(100);

        iv.setFitHeight(100 * 1.6);
        iv.setFitWidth(100 * 1.6);

        cardImage.getChildren().addAll(iv);
        cardImage.getStyleClass().addAll("vboxImage");

        generateSpell(cardSpells); //adds the spells to this vbox
        cardSpells.getStyleClass().addAll("vboxSpell");

        //sätter position på VBox
        card.setTranslateX(posX);
        card.setTranslateY(posY);

        //här vrider vi på motståndarens hand så den blir spegelvänd
        if(rotate) {
            card.setRotate(180);
        }
        /*
        The card background color is set based on its Type
         */
        setCardStyle();

        card.getChildren().addAll(cardName, cardImage, cardSpells); //add the card name first then its image

        /*
        Fix this so that the cards are put into the deck and not in the root directly
         */
    }

    /**
     * Set the cards style back to default.
     */
    public void setCardStyle() {
        //sets card Color based on type
        if(type.equals(Type.BAD)) {
            card.setStyle("-fx-background-color: linear-gradient(#707070, #e9e9e9)");
        }

        if(type.equals(Type.HAPPY)) {
            card.setStyle("-fx-background-color: linear-gradient(#0f7f31, #50d5c7)");
        }

        if(type.equals(Type.BRAVE)) {
            card.setStyle("-fx-background-color: linear-gradient(rgb(209, 209, 209), #2b6fd5)");
        }

        if(type.equals(Type.CUTE)) {
            card.setStyle("-fx-background-color: linear-gradient(#d55a79, #d595a9, #d53e6f)");
        }

        if(type == Type.TALENT) {
            card.setStyle("-fx-background-color: linear-gradient(#d50717, #d55917)");
        }

        if(type == Type.RELAXING) {
            card.setStyle("-fx-background-color: linear-gradient(#f9ff21, #f4ff8b)");
        }

        if(type == Type.ACTOR) {
            card.setStyle("-fx-background-color: linear-gradient(#67f2ff, #a3ecff)");
        }
    }

    public void setDefatulCardStyle() {
        card.getStyleClass().add("vbox");
    }

    public double getCardHeigth() {
        return CARDHEIGHT;
    }

    public double getCardWidth() {
        return CARDWIDTH;
    }

    /**
     * This method returns the VBox containing the card
     */
    public VBox getVBoxCard() {
        return card;
    }

    /**
     * Call this method to draw the card on the screen.
     * make it visible
     */
    public void show() {
        root.getChildren().add(card);
    }

    public Group getRoot() {
        return root;
    }
	
	public int getCurrentHealth() {
    	return currentHp;
    }
	
	public void incrementHealth(int hp) {
    	currentHp = currentHp+hp;
    }

	public void decrementHealth(int hp) {
    	currentHp = currentHp-hp;
    }
    /**
     * This method will handle the event when clicking on the card
     */

    public void rotateCard() {
        card.setRotate(180);
    }

    /**
     * gives you a list of spells diceNr.
     * Use this to highlight cards that match
     * dice thrown.
     * @return list with integers
     */
    public ArrayList<Integer> getSpellsDiceNumber(){
        ArrayList<Integer> list = new ArrayList<>();
        for(Spell s:spells) {
            list.add(s.getDiceNumber());
        }
        return list;
    }
}
