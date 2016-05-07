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

    private String name;
    private ArrayList<Spell> spells;
    private int health;
    private Image image; //loads the image
    private ImageView iv; //creates the visble object
    private ArrayList<Label> spellInfo;

    /*
        when each card is created it's flip = true, which means that
        the card is flipped. card being flipped means that you only see the
        back of it which is identical to each card. When the card is put into your hand
        flip is set to false meaning that you will be able to see the cards.
    */
    private boolean flip;

    public Card(Image png, String name, int hp, ArrayList<Spell> spells) {

        flip = true;

        image = png;
        iv = new ImageView();
        this.name = name;
        this.spells = spells;
        health = hp;
        iv.setImage(image);
        //getSpellInfo(); //puts the spell info into the ArrayList smoothly so it can be taken in generateCard method.
    }

    //förstör kortet
    private void destroy() {

    }

    public boolean getFlip() {
        return flip;
    }

    /**
     * Another help method used to put the spellInfo in
     * the form of labels, into the VBox.
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
        return name;
    }

    public int getHealth() {
        return health;
    }

    /*
	How to use this function.. Create a Card object and define its fields etc.
	This method will generate the cards, set each things to pieces like name on top,
	image under, and spells under that etc.
	To then draw this on the canvas you will write in code: root.getChildren.add(card1.generateCard());
	 */
    public VBox generateCard(Group root) {
        VBox card = new VBox();

        card.getStyleClass().addAll("vbox");

        iv.prefHeight(100);
        iv.prefWidth(100);

        iv.setFitHeight(100 * 1.6);
        iv.setFitWidth(110 * 1.6);

        /*
        The card background color is set based on its Type
         */

        card.getChildren().addAll(iv); //add the card name first then its image
        generateSpell(card); //adds the spells to this VBox


        /*
        Fix this so that the cards are put into the deck and not in the root directly
         */
        root.getChildren().addAll(card);

        return card;
    }
}
