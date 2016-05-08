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

    /*
        These two app_w & h will be important to keep
        track of when changing window size.
        The program graphic will be set with
        these in mind.
     */
    private int app_w = 1000; //witdh
    private int app_h = 700;  //height

    private Card aori, inkling;
    private ArrayList<Spell> spellSet1, spellSet2, spellSet3, spellSet4;
    private Deck myDeck, opponentDeck;
    private ArrayList<Card> allCards;

    //constructor
    public Graphics() {
        myDeck = new Deck(400, 400);
        opponentDeck = new Deck(20, 20);
        allCards = new ArrayList<Card>();

        spellSet1 = new ArrayList<>();
        spellSet2 = new ArrayList<>();
        spellSet3 = new ArrayList<>();
        spellSet4 = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        root = new Group();
        scene = new Scene(root, app_w, app_h);

        generateSpellSets();
        createCards();
        generateDeck();
        drawDeck();

        scene.getStylesheets().add("StyleSheet.css");
        window.setScene(scene);
        //window.setFullScreen(true);
        window.show();
    }

    public int getWidth() {
        return app_w;
    }

    public int getHeight() {
        return app_h;
    }

    private void generateSpellSets() {
        spellSet1.add(new Spell("Calamari Song", 30, 3));

        spellSet2.add(new Spell("Inkpow", 30, 1));
        spellSet2.add(new Spell("Zapper", 20, 2));
    }

    /*
    Här lägger du till Spell objektet inkpow i arrayListan.
    varje Spell objekt har en metod du kan kalla på getInfo()
    som returnerar ett Label objekt med info om spellen.
    anropa denna i Card klassen för att lägga in alla Labels i
    en separat arraylist. Denna arrayList som innehåller alla labels
    kommer sedan kallas för att sätta in Labels i VBox
     */
    private void createCards() {
        Image aoriPng = new Image("images/splat.png");
        aori = new Card(aoriPng, "Aori", 70, spellSet1);
        //aori.generateCard(rootNode);

        Image inklingPng = new Image("images/squidGirl.png");
        inkling = new Card(inklingPng, "Inkling", 60, spellSet2);
        //inkling.generateCard(rootNode);

        //just put all cards into the allCard arrayList.
        allCards.add(aori);
        allCards.add(inkling);
    }

    private void generateDeck() {
        myDeck.add(aori);
        myDeck.add(inkling);

        opponentDeck.add(aori);
    }

    /*
    Make it so that the Deck class drawDeck method takes
    care of the drawing on canvas,
    by making a call to the generateCard method in Card class
     */
    private void drawDeck() {
        myDeck.drawDeck(root);
        opponentDeck.drawDeck(root);
    }
}