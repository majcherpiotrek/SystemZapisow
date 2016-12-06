package users.student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import users.GeneralUser;

/**
 * Created by piotrek on 06.12.16.
 */
public class Student extends GeneralUser {

    public Student(Stage _parentWindow, Scene _parentScene){
        this.parentWindow = _parentWindow;
        this.parentScene = _parentScene;
    }

    @Override
    public void displayMainMenu() {
        /*Inicjalizacja szkieletu GUI*/
        initInterfaceFrame("Panel studenta");

        VBox mainMenuLayout = new VBox();
        mainMenuLayout.setPadding(new Insets(50,50,50,50));
        mainMenuLayout.setSpacing(40);
        mainMenuLayout.setAlignment(Pos.CENTER);

        mainMenuLayout.getChildren().addAll(buttonProfile, buttonCourses, buttonLogout);
        Scene mainMenuScene = new Scene(mainMenuLayout);
        this.parentWindow.setScene(mainMenuScene);
    }

    @Override
    public void showProfile() {

    }

    @Override
    public void manageCourses() {

    }

    @Override
    public void manageGroups() {

    }
}
