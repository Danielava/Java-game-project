import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Mariostarr on 04/05/16.
 */
public class Graphics extends Application{

    private Stage window;
    private Scene scene;
    private Group root;
    private Spell inkPow, monadoPurge, jab;
    private final int APP_W = 1000;
    private final int APP_H = 700;
    private Card squid;


    @Override
    public void start(Stage primaryStage) throws Exception {
        generateSpells();
        createCards();

        window = primaryStage;
        root = new Group();
        scene = new Scene(root, APP_W, APP_H);

        squid.generateCard(root);
        window.setScene(scene);
        //window.setFullScreen(true);
        window.show();
    }

    private void generateSpells() {
        inkPow = new Spell("Inkpow", 30, 3); //Inkpow does 30 damage
        jab = new Spell("Jab", 20, 2);
        monadoPurge = new Spell("Monado Purge", 30, 1);
    }

    private void createCards() {
        ArrayList<Spell> squidSpells = new ArrayList<Spell>();
        squidSpells.add(inkPow);
        Image squidPng = new Image("images/splat.png");
        squid = new Card(squidPng, "Aori", 70, squidSpells);
    }
}
