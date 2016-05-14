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
}
