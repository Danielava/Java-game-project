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
    private int health;
    private Image image; //loads the image
    private ImageView iv; //creates the visble object
    private ArrayList<Label> spellInfo;

    public Card(Image png, String name, int hp) {
        iv = new ImageView();
        image = png;
        this.name = name;
        health = hp;
        iv.setImage(image);
    }

    //förstör kortet
    private void destroy() {

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

        iv.prefHeight(100);
        iv.prefWidth(100);

        iv.setFitHeight(100 * 1.6);
        iv.setFitWidth(110 * 1.6);

        card.getChildren().addAll(iv);
        root.getChildren().addAll(card);

        return card;
    }
}
