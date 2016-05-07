import javafx.scene.control.Label;

/**
 * This class contains all the card spells and attacks etc.
 * Created by Mariostarr on 25/04/16.
 */
public class Spell {

    private String name; //Spell name
    private String description; //Spell description, hold on the spell to show this. Only put description if spell does something effect.. otherwise leave blanc
    //private Ability ability; //some spells will inflict status stuff when executed. These are kept in Abilities class
    private int attackPower;
    private int diceNumber;
    private Label label;

    public Spell(String name, int power, int diceNr) {
        //ability = new Ability();
        this.name = name;
        attackPower = power;
        diceNumber = diceNr;
        //label = new Label(); //skapa label Objekt i generateInfo istället och i dess konstruktors parameter skriver du en string som du genererat i förväg.
        generateInfo();
    }

    /**
     * The description of spells are set in the Ability class if these
     * have an ability. Otherwise no description is needed.
     * @return
     */
     /*
        public String getDescription() {
        return ability.getDescription();
        }
     */
    public String getName() {
        return name;
    }

    public int getPower() {
        return attackPower;
    }

    /**
     * Genererar info om attacken och sätter denna info
     * i ett label objekt
     */
    private void generateInfo() {
        //vår string med info
        String attackInfo = diceNumber + " - " + name;
        label = new Label(attackInfo);
    }

    /**
     * Returnerar ett label objekt som innehåller info om spell
     */
    public Label getInfo() {
        return label;
    }

    /**
     * @param card The card you want to apply spell to
     */
     /*
        Create a card and a spell object.
        Then apply the spell to the card by writing spell.setOnCard(card)
     */
    public void setOnCard(Card card) {

    }

    public int getDiceNumber() {
        return diceNumber;
    }
}