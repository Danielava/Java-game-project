import javafx.animation.AnimationTimer;
import javafx.scene.layout.VBox;

/**
 * Created by Mariostarr on 06/07/16.
 */
/*
The class has methods that makes it so the cards will animate when
drawn from deck, or put on board etc, attack animations etc..
 */
public class Animation {

    public boolean stopX;
    public boolean stopY;

    public Animation() {

    }

    /**
     * moves the card to specified location with animation
     * @param c The card you want to move.
     * @param x the x position you wanna move it to.
     * @param y the y position you wanna move it to.
     * @param speed the speed you wanna move it with. Put it to something low between 0 - 1
     */
    public void moveTo(Card c, double x, double y, double speed) {
        VBox card = c.getVBoxCard();
        //stopX = false;
        stopY = false;


        new AnimationTimer() {

            @Override
            public void handle(long time) {
                /*
                if(card.getTranslateX() >= x && !stopX) {
                    card.setTranslateX(card.getTranslateX() + speed);
                } else {
                    card.setTranslateX(x);
                    stopX = true;
                }*/

                if(card.getTranslateY() >= y && !stopY) {
                    card.setTranslateY(card.getTranslateY() + speed);
                } else {
                    card.setTranslateY(y);
                    stopY = true;
                }

                if(stopY) {// && stopX) {
                    System.out.println("hej");
                    stop();
                }
            }

        }.start();
    }

}
