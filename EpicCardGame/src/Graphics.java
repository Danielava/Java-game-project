import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Mariostarr on 04/05/16.
 */
public class Graphics extends Application{

        private Stage window;
        private Scene scene;
        private Group root;
        private final int APP_W = 1000;
        private final int APP_H = 700;


    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        root = new Group();
        scene = new Scene(root, APP_W, APP_H);

        window.setScene(scene);
        //window.setFullScreen(true);
        window.show();
    }
}
