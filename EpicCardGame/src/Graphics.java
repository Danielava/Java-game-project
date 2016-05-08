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
    private Group rootNode;
    private Spell inkPow, monadoPurge, jab;
    private final int APP_W = 1000;
    private final int APP_H = 700;
    private Card aori, inkling;
    private ArrayList<Spell> spellSet1, spellSet2, spellSet3, spellSet4;
    private Deck myDeck, opponentDeck;
    private ArrayList<Card> allCards;

    //constructor
    public Graphics() {
        myDeck = new Deck();
        opponentDeck = new Deck();
        allCards = new ArrayList<Card>();

        spellSet1 = new ArrayList<>();
        spellSet2 = new ArrayList<>();
        spellSet3 = new ArrayList<>();
        spellSet4 = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        rootNode = new Group();
        scene = new Scene(rootNode, APP_W, APP_H);


        generateSpells();
        generateSpellSets();
        createCards();
        generateDeck();

        scene.getStylesheets().add("StyleSheet.css");
        window.setScene(scene);
        //window.setFullScreen(true);
        window.show();
    }

    private void generateSpells() {
        inkPow = new Spell("Inkpow", 30, 3); //Inkpow does 30 damage
        jab = new Spell("Jab", 20, 2);
        monadoPurge = new Spell("Monado Purge", 30, 1);
    }

    private void generateSpellSets() {
        spellSet1.add(inkPow);

        spellSet2.add(jab);
        spellSet2.add(inkPow);
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
        aori.generateCard(rootNode);

        Image inklingPng = new Image("images/squidGirl.png");
        inkling = new Card(inklingPng, "Inkling", 60, spellSet2);
        inkling.generateCard(rootNode);

        //just put all cards into the allCard arrayList.
        allCards.add(aori);
        allCards.add(inkling);
    }

    private void generateDeck() {
        myDeck.add(aori);
    }
}