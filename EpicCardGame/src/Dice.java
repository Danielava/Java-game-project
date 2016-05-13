import java.util.Random;
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
	
	public Dice(){
		r = new Random();
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
	/*
	public void displayDiceRolling() {
		AnimatedImage dice = new AnimatedImage();
		Image[] imageArray = new Image[diceRolls];
		for (int i = 0; i < diceRolls; i++) {
		    imageArray[i] = new Image( "dice" + i + ".png" );
		    
		}
		dice.frames = imageArray;
		dice.duration = 0.500;
	}
	*/
}
