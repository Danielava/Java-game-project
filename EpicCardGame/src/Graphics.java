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
    private GameLoopUser game;

    /*
        These two app_w & h will be important to keep
        track of when changing window size.
        The program graphic will be set with
        these in mind.
     */
    private int app_w = 1000; //witdh
    private int app_h = 700;  //height

    private Spell spell1, spell2, spell3;
    private Card aori, inkling;
    private ArrayList<Spell> spellSet1, spellSet2, spellSet3, spellSet4;
    private Deck myDeck, opponentDeck;
    private Hand myHand, opponentHand;
    private ArrayList<Card> allCards;

    //constructor
    public Graphics() {

        root = new Group();

        myHand = new Hand();
        opponentHand = new Hand();

        myDeck = new Deck(400, 400, myHand, root);
        opponentDeck = new Deck(20, 20, opponentHand, root);
        allCards = new ArrayList<Card>();

        spellSet1 = new ArrayList<>();
        spellSet2 = new ArrayList<>();
        spellSet3 = new ArrayList<>();
        spellSet4 = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        scene = new Scene(root, app_w, app_h);

        //initiate the game loop
        game = new GameLoopUser(myDeck, scene);


        createCards();
        createSpells();
        generateSpellSets();
        generateDeck();
        drawDeck();

        scene.getStylesheets().add("StyleSheet.css");
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
        game.startGame();

    }

    public int getWidth() {
        return app_w;
    }

    public int getHeight() {
        return app_h;
    }

    private void createSpells() {
        spell1 = new Spell("Calamari Song", 30, 3);
        spell2 = new Spell("Inkpow", 30, 1);
        spell3 = new Spell("Zapper", 20, 2);
    }

    private void generateSpellSets() {
        addSpell(spellSet1, spell1);

        addSpell(spellSet2, spell2);
        addSpell(spellSet2, spell3);
    }
    private void addSpell(ArrayList<Spell> list,Spell spell) {
        list.add(new Spell(spell.getName(), spell.getPower(), spell.getDiceNumber()));
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
        aori = new Card(aoriPng, "Aori", 70, spellSet1, Type.CUTE);
        //aori.generateCard(rootNode);

        Image inklingPng = new Image("images/squidGirl.png");
        inkling = new Card(inklingPng, "Squid-chan", 60, spellSet2, Type.BAD);
        //inkling.generateCard(rootNode);

        //just put all cards into the allCard arrayList.
        allCards.add(aori);
        allCards.add(inkling);
    }

    /*
    PROBLEM SOLVED!
     */
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
        myDeck.drawDeck();
        opponentDeck.drawDeck();
    }
}