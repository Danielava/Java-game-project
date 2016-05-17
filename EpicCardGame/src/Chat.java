import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Chat {
	
	private final double maxTextWidth;
	private double canvasPosX, canvasPosY;
	private double screenWidth, screenHeight;
	private Canvas canvas;
	private GraphicsContext gc;
	private String[] texts;
	
	public Chat(Group root, double w, double h) {
		screenWidth = w;
		screenHeight = h;
		maxTextWidth = 15;

		canvasPosX = screenWidth * 0.016; //rita frÃ¥n denna position
		canvasPosY = screenHeight * 0.2;
		
		canvas = new Canvas(canvasPosX + 102, canvasPosY + 112); //canvas storlek i parametrarna (till denna position)
		root.getChildren().add(canvas);

		canvas.setTranslateX(canvasPosX);
		canvas.setTranslateY(canvasPosY);
		
		texts = new String[10];

		gc = canvas.getGraphicsContext2D();
	}
	
	public void chatEvent() {
		for (int i = 0; i < texts.length; i++) {
			gc.fillText(texts[i],screenWidth/2,screenHeight/2 - i*15,maxTextWidth);
		}
	}
	
	/**
	 * Puts a given text into the first place
	 * of the texts array, and pushes all of the
	 * remaining texts one step forward.
	 * The last text is removed.
	 * @param text 		a short text
	 */
	public void storeText(String text) {
		for (int i = 0; i < texts.length; i++) {
			texts[i] = texts[i+1];
		}
		texts[0] = text;
	}
}
