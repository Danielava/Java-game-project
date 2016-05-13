import java.util.Random;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AnimatedImage
{
    public Image[] frames;
    public double duration;
     
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
    public Image[] getImageArray() {
    	return frames;
    }
}