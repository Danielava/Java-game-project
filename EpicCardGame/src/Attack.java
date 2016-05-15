import javafx.scene.layout.VBox;

import java.util.ArrayList;

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
    private ArrayList<Card> userCardsOnBoard, opponentCardsOnBoard;

    private Card pickedCard;

    private boolean check;

    public Attack(GameLoopUser you, OpponentAI ai) {
        user =  you;
        this.ai = ai;

        press = true;

        dice = you.getDice();

        userBoard = you.getBoard();
        opponentBoard = ai.getOpponentBoard();

        //remember that the cards on board will change when cards are added/removed etc
        setCardsOnBoard();
        check = true;
    }

    private void setCardsOnBoard() {
        userCardsOnBoard = userBoard.cardsOnBoard();
        opponentCardsOnBoard = opponentBoard.cardsOnBoard();
    }

    //put in loop after diceThrow and if BOARD.CHECK_BOARD_MATCH is true.
    public void pickAttackCardEvent() {
        if(press) { //good position?
            for (Card c : userCardsOnBoard) {
                VBox card = c.getVBoxCard();
                if (c.getAttackStatus()) {
                    card.setOnMouseClicked(e -> {
                        //du trycker på ditt kort en gång
                        if (c.getAttackStatus() && press) { //&&press //attack status is only true if card is matching dice number and then we enter this if-statemen
                            System.out.println("HEJ");
                            card.getStyleClass().add("vboxPicked");
                            pickedCard = c;
                            press = false;
                        }
                    });
                } else {
                    card.getStyleClass().add("vbox"); //doesn't work
                }
            }
        }
    }

    /**
     * An event that let's you pick an opponent to attack.
     */
    public void attackOpponentEvent() {
        if(!press && pickedCard.getAttackStatus()) {
            for(Card c : opponentCardsOnBoard) {
                VBox card = c.getVBoxCard();

                card.setOnMouseClicked(e -> {
                    //attack opponent
                    press = true;
                    attack(pickedCard, c, dice.getDiceNumber()); //why is this still being called when we sell variables to false?
                    //pickedCard.setAttackStatus(false);
                    //try and call the attack method inside the GameLoopUser loop.
                });
            }
        }
    }

    /**
     * Helper method attack for attackOpponentEvent()
     */
    public void attack(Card attacker, Card victim, int diceNr) {
        ArrayList<Spell> spells = attacker.getSpells(); //all spells in this list
        for(Spell s : spells) {
            if(s.getDiceNumber() == diceNr && check) {
                victim.setCurrentHp(victim.getCurrentHealth() - s.getPower());
                victim.updateHpOnCard();
                //System.out.println(victim.getCurrentHealth());
                if(victim.getCurrentHealth() <= 0) {
                    opponentBoard.removeFromBoard(victim);
                    opponentBoard.drawBoard();
                    victim.remove();
                    //System.out.println(attacker.getAttackStatus()); //why
                }
                check = false; //don't forget to change this to true at some point
                attacker.setAttackStatus(false);
                attacker.setDefatulCardStyle();
            }
        }
    }

    public boolean getPress() {
        return press;
    }


}
