import java.util.Random;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.transform.Scale;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Thread;
/**
 * This class will create a dice on the screen.
 * Click on the dice to roll it and then you will be able to perform
 * your move.
 */
/**
 * This class will create a dice on the screen.
 * Click on the dice to roll it and then you will be able to perform
 * your move.
 */
public class Dice {

	private Random r;
	private final int diceRolls = 30;
	private Canvas canvas;
	private GraphicsContext gc;
	private AnimatedImage aniImage;
	
	public Dice(Group root){
		r = new Random();
		canvas = new Canvas( 512, 512 );
		gc = canvas.getGraphicsContext2D();
		aniImage = createDice();
		root.getChildren().add( canvas );
	}
	
	/*
	 * generates an array pattern of how
	 * the random dice roll will look like.
	 */
	public int[] generateRandomPattern() {
		int[] array = new int[diceRolls];
		for(int i=0; i<diceRolls; i++) {
			array[i] = r.nextInt(6)+1;
		}
		return array;
	}
	public AnimatedImage createDice() {
		AnimatedImage dice = new AnimatedImage();
		Image[] imageArray = new Image[diceRolls];
		for (int i = 1; i < 7; i++) {
			System.out.println("images/dice" + i + ".png");
		    imageArray[i-1] = new Image( "images/dice" + i + ".png" );
		    
		}
		dice.frames = imageArray;
		dice.duration = 0.500;
		return dice;
	}
	public void displayDiceRoll(double time,Group root) {	    
	    //for (int i = 1; i<aniImage.getImageArray().length; i++) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	    	gc.drawImage( aniImage.getFrame(time), 350, 100 );
	    	//try{Thread.sleep(200);}
	    	//catch(Exception e){/*Do Nothing*/}
	    //}
	}
}