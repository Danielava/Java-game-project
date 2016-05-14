import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.awt.Dimension;
import java.util.ArrayList;

public class Chat extends GameLoopChat{
	
	private double canvasPosX, canvasPosY;
	private double screenWidth, screenHeight;
	private Canvas canvas;
	private GraphicsContext gc;
	private String[] texts;
	
	public Chat(Group root, double w, double h) {
		screenWidth = w;
		screenHeight = h;

		canvasPosX = screenWidth * 0.016; //rita från denna position
		canvasPosY = screenHeight * 0.2;
		
		canvas = new Canvas(canvasPosX + 102, canvasPosY + 112); //canvas storlek i parametrarna (till denna position)
		root.getChildren().add(canvas);

		canvas.setTranslateX(canvasPosX);
		canvas.setTranslateY(canvasPosY);

		gc = canvas.getGraphicsContext2D();
	}
	
	public void chatEvent(String text, Dimension screen) {
		for (int i = 0; i < texts.length; i++) {
			gc.fillText(texts[i],screen.getWidth()/2,screen.getHeight()/2 - i*10);
		}
	}
	
	/**
	 * Puts a given text into the first place
	 * of the texts array, and pushes all of the
	 * remaining texts one step forward.
	 * The last text is removed.
	 * @param 		a short text
	 */
	public void storeText(String text) {
		for (int i = 0; i < texts.length; i++) {
			texts[i] = texts[i+1];
		}
		texts[0] = text;
	}
}
