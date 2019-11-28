import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class has a fundamental method used in a gameloop
 * to keep track of card attacks etc. And also adds attacks
 * to the gameplay.
 */
public class Attack {

    private GameLoopUser user;
    private OpponentAI ai;
    private boolean press;
    private Dice dice;

    private Board userBoard, opponentBoard;

    private Card pickedCard;
    private Card card1, card2;
    private VBox c1, c2;

    private boolean check, wait;
    private Random rnd;

    private Animation animation;

    public Attack(GameLoopUser you, OpponentAI ai) {
        rnd = new Random();
        user =  you;
        this.ai = ai;
        wait = false;
        press = true;

        dice = you.getDice();

        userBoard = you.getBoard();
        opponentBoard = ai.getOpponentBoard();

        animation = new Animation();

        //remember that the cards on board will change when cards are added/removed etc
        check = true;
    }

    /**
     * se till att anropa denna metod för att uppdatera infon.
     */
    /*
    public void setCardsOnBoard() {
        userCardsOnBoard = userBoard.cardsOnBoard();
        opponentCardsOnBoard = opponentBoard.cardsOnBoard();
    }
    */

    /*
    BUGGEN HAR ATT GÖRA MED DENNA METOD ENDAST!!!


     */
    //put in loop after diceThrow and if BOARD.CHECK_BOARD_MATCH is true.
    public void pickAttackCardEvent() {

        if(!user.getTurn()) {
            return;
        }
        if (press && !wait && userBoard.getMatch() && user.getTurn()) { //good position?
            wait = true;

            for (Card c : userBoard.cardsOnBoard()) {
                VBox card = c.getVBoxCard();
                if (c.getAttackStatus()) {
                    card.setOnMouseClicked(e -> {
                        //du trycker på ditt kort en gång
                        if (c.getAttackStatus() && press) { //&&press //attack status is only true if card is matching dice number and then we enter this if-statemen
                            card.getStyleClass().add("vboxPicked");
                            pickedCard = c;
                            wait = false;

                            //if(!userBoard.getMatch()) {
                                press = false;
                            //}
                        }
                    });
                } else {
                    card.getStyleClass().add("vbox"); //doesn't work
                }
                wait = false;
            }
        }
    }

    /**
     * An event that let's you pick an opponent to attack.
     */
    public void attackOpponentEvent() {
        if(!user.getTurn()) {
            return;
        }

        if(!press && pickedCard.getAttackStatus()) {
            for(Card c : opponentBoard.cardsOnBoard()) {
                VBox card = c.getVBoxCard();

                card.setOnMouseClicked(e -> {
                    //attack opponent
                    attack(pickedCard, c, dice.getDiceNumber(), false); //why is this still being called when we sell variables to false?

                    userBoard.getBoardEventCheck();

                    if(userBoard.getMatch()) {
                        press = true;
                    }

                    pickedCard.setAttackStatus(false);
                    pickedCard.setHasAttacked(true);
                    //try and call the attack method inside the GameLoopUser loop.
                });
            }
        }
    }

    /**
     * Call this method in the AI for attacking.
     * So far it's built very simple, the AI will just
     * attack one random card from your board.
     */
    public void aiAttack(Card attacker, int diceNr) {

        int rndCard = rnd.nextInt(userBoard.getSize());
        attack(attacker, userBoard.getCard(rndCard), diceNr, true);
    }

    /**
     * Helper method attack for attackOpponentEvent()
     */
    public void attack(Card attacker, Card victim, int diceNr, boolean AI) {
        ArrayList<Spell> spells = attacker.getSpells(); //all spells in this list

        //animation.moveTo(attacker, victim.getVBoxCard().getTranslateX(), victim.getVBoxCard().getTranslateY(), 1);

        for(Spell s : spells) {
            if(s.getDiceNumber() == diceNr && !attacker.getHasAttacked()) {
                victim.setCurrentHp(victim.getCurrentHealth() - s.getPower());
                victim.updateHpOnCard();
                //System.out.println(victim.getCurrentHealth());
                if(victim.getCurrentHealth() <= 0) {
                    if(!AI) {
                        opponentBoard.removeFromBoard(victim);
                        opponentBoard.drawBoard();
                    } else {
                        userBoard.removeFromBoard(victim);
                        userBoard.drawBoard();
                    }
                    victim.killCard(); //sets its dead variable to true. not needed I think..
                    victim.remove();
                    victim = null;
                }
                attacker.setAttackStatus(false); //när attack status är false ska kortet ej kunna attackera mer under omgången.
                attacker.setHasAttacked(true);
            }
        }
    }

    public boolean getPress() {
        return press;
    }

    public void resetPress() {
        press = true;
    }

}
