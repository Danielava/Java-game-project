public class Chat {
	/* DENHÄR KLASSEN FUCKAR SPELET

	private final double maxTextWidth;
	private double canvasPosX, canvasPosY;
	private double screenWidth, screenHeight;
	private Canvas canvas;
	private GraphicsContext gc;
	private static String[] texts = {"","","","","","","","","",""};
	
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

		gc = canvas.getGraphicsContext2D();
	}
	
	public void chatEvent() {
		for (int i = 0; i < texts.length; i++) {
			System.out.println(texts[i]);
			gc.setFill(javafx.scene.paint.Color.BLACK);
			gc.fillText(texts[i],screenWidth,screenHeight - i*15,maxTextWidth);
			gc.fillRect(screenWidth, screenHeight, 100, 100);
		}
	}
	
	/**
	 * Puts a given text into the first place
	 * of the texts array, and pushes all of the
	 * remaining texts one step forward.
	 * The last text is removed.
	 * @param text 		a short text
	 */
	/*
	public static void storeText(String text) {
		for (int i = 0; i < texts.length-1; i++) {
			texts[i+1] = texts[i];
		}
		texts[0] = text;
	}
	*/
}