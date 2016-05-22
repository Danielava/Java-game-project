import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mariostarr on 04/05/16.
 */
public class Graphics extends Application{

    private Stage window;
    private Scene scene;
    private Group root;
    private GameLoopUser game;
    private OpponentAI AI;

    /*
        These two app_w & h will be important to keep
        track of when changing window size.
        The program graphic will be set with
        these in mind.
     */

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenWidth = screenSize.getWidth();
    double screenHeight = screenSize.getHeight();

    private int app_w = 1100; //witdh
    private int app_h = 750;  //height

    private Spell spell1, spell2, spell3, camSpell1, camSpell2, octoSpell1, octoSpell2, octoSpell3, shellSpell1, trueStreamEdge, kraken, jellyDance;
    private Card aori, inkling, camilla, octoling, octoling2, inkling2, shellfie;
    private ArrayList<Spell> spellSet1, spellSet2, spellSet3, spellSet4, octo2Spell, inkling2Spell, shellfSpell;
    private Deck myDeck, opponentDeck;
    private Hand myHand, opponentHand;
    private Dice myDice, opponentDice;
    private Chat myChat;
    private ArrayList<Card> allCards;
    private boolean yourTurn; //when this is true it means it's currently your turn. The variable will be fundamental to our program.

    private Scale scale;

    //constructor
    public Graphics() {

        System.out.println(screenWidth);
        System.out.println(screenHeight);

        root = new Group();
        myDice = new Dice(root, screenWidth, screenHeight, false);
        opponentDice = new Dice(root, screenWidth, screenHeight, true);

        myHand = new Hand(screenWidth, screenHeight, screenHeight - 201, root, myDice, false);
        opponentHand = new Hand(screenWidth, screenHeight, -240, root, opponentDice, true); //change the third paramether

        myDeck = new Deck(screenWidth * 0.852, screenHeight * 0.635, myHand, root, false); //sets your deck position based on pc screenSize.
        opponentDeck = new Deck(20, 20, opponentHand, root, true); //hand object goes in here
        allCards = new ArrayList<Card>();

        myChat = new Chat(root,screenWidth, screenHeight);
        
        spellSet1 = new ArrayList<>();
        spellSet2 = new ArrayList<>();
        spellSet3 = new ArrayList<>();
        spellSet4 = new ArrayList<>();
        octo2Spell = new ArrayList<>();
        inkling2Spell = new ArrayList<>();
        shellfSpell = new ArrayList<>();

        scale = new Scale();
        yourTurn = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        scene = new Scene(root, app_w, app_h);

        scene.getRoot().getTransforms().setAll(scale);

        //initiate the game loop
        game = new GameLoopUser(myDeck, scene, root, myDice, myChat, true);
        AI = new OpponentAI(opponentDeck, scene, root, opponentDice, false);

        //this way these two can communicate
        game.setNemesis(AI);
        AI.setNemesis(game);

        createCards();
        createSpells();
        generateSpellSets();
        generateDeck();
        drawDeck();

        scene.getStylesheets().add("StyleSheet.css");
        window.setScene(scene);
        window.setFullScreen(true); //fundamental
        window.show();
        game.startGame(); //starts your game
        AI.start(); //starts the AI
    }

    public Group getRoot() {
        return root;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }

    private void createSpells() {
        spell1 = new Spell("Calamari Song", 30, 3);

        spell2 = new Spell("Inkpow", 30, 1);
        spell3 = new Spell("Zapper", 20, 2);

        camSpell1 = new Spell("Galeforce", 40, 4);

        octoSpell1 = new Spell("Stream edge", 20, 1);
        octoSpell2 = new Spell("Sea slash", 20, 5);
        octoSpell3 = new Spell("Splattack", 30, 6);

        shellSpell1 = new Spell("Mic drop", 30, 4);
        jellyDance = new Spell("Jelly dance", 20, 3);

        trueStreamEdge = new Spell("True Stream edge", 50, 6);

        kraken = new Spell("Kraken", 20, 5);
    }

    private void generateSpellSets() {
        addSpell(spellSet1, spell1);

        addSpell(spellSet2, spell2);
        addSpell(spellSet2, spell3);

        addSpell(spellSet3, camSpell1);

        addSpell(spellSet4, octoSpell1);
        addSpell(spellSet4, octoSpell2);
        addSpell(spellSet4, octoSpell3);

        addSpell(inkling2Spell, spell2);
        addSpell(inkling2Spell, kraken);

        addSpell(octo2Spell, trueStreamEdge);

        addSpell(shellfSpell, shellSpell1);
        addSpell(shellfSpell, jellyDance);
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
        aori = new Card(aoriPng, "Aori", 50, spellSet1, Type.CUTE);
        //aori.generateCard(rootNode);

        Image inklingPng = new Image("images/squidGirl.png");
        inkling = new Card(inklingPng, "Squid-chan", 50, spellSet2, Type.ACTOR);
        //inkling.generateCard(rootNode);

        Image camillaPng = new Image("images/camilla.png");
        camilla = new Card(camillaPng, "Camilla", 70, spellSet3, Type.BRAVE);

        Image octolingPng = new Image("images/octoling.png");
        octoling = new Card(octolingPng, "Rookie octoling", 40, spellSet4, Type.TALENT);

        Image inkl2Png = new Image("images/inkl3.png");
        inkling2 = new Card(inkl2Png, "Inkling", 30, inkling2Spell, Type.RELAXING);

        Image shellfiPng = new Image("images/shellfie.png");
        shellfie = new Card(shellfiPng, "Shellfie", 40, shellfSpell, Type.HAPPY);

        Image octo2Png = new Image("images/octo2.png");
        octoling2 = new Card(octo2Png, "Elite octoling", 60, octo2Spell, Type.TALENT);

        //just put all cards into the allCard arrayList. not very necessary
        allCards.add(aori);
        allCards.add(inkling);
        allCards.add(camilla);
        allCards.add(octoling);
        allCards.add(inkling2);
        allCards.add(shellfie);
        allCards.add(octoling2);
    }

    /*
    PROBLEM SOLVED!
     */
    /**
     * Generate the two decks here
     */
    private void generateDeck() {
        myDeck.add(aori);
        myDeck.add(inkling);
        myDeck.add(camilla);
        myDeck.add(octoling);
        myDeck.add(inkling2);
        myDeck.add(shellfie);
        myDeck.add(octoling2);

        opponentDeck.add(aori);
        opponentDeck.add(shellfie);
        opponentDeck.add(inkling2);
        opponentDeck.add(shellfie);
        opponentDeck.add(inkling2);
        opponentDeck.add(shellfie);
        opponentDeck.add(inkling2);
        opponentDeck.add(shellfie);
        opponentDeck.add(inkling2);
        opponentDeck.add(shellfie);
        opponentDeck.add(inkling2);
        opponentDeck.add(shellfie);
        opponentDeck.add(inkling2);
        opponentDeck.add(shellfie);
        opponentDeck.add(inkling2);
        opponentDeck.add(shellfie);
        opponentDeck.add(inkling2);
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

    public boolean getYourTurn() {
        return yourTurn;
    }
}