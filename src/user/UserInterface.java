package user;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by piotrek on 06.12.16.
 */
public abstract class UserInterface {
    public Stage window;
    public Scene scene;
    public abstract void showProfile();
    public abstract Scene createUserInterfaceScene(Stage lastWindow, Scene lastScene);
}
