import javafx.scene.image.Image;

import java.util.Random;

public class AnimatedImage
{
    public Image[] frames;
    public double duration;
    private Random rnd;
    private int diceNumber; //set this variable to number on dice when it stops.

    public AnimatedImage() {
        rnd = new Random();
    }
     
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * The method is used for when you press the dice to
     * stop it on a random number. That random number should
     * be saved in a variable so it can be returned when needed.
     * @return
     */
    public Image getRandomFrame() {
        int index = rnd.nextInt(6);
        diceNumber = index + 1;
        return frames[index];
    }
    public int getDiceNumber() {
        return diceNumber;
    }

    public Image[] getImageArray() {
    	return frames;
    }
}