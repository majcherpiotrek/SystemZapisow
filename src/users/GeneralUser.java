package users;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by piotrek on 06.12.16.
 */
public abstract class GeneralUser {

    /*
     *Okno w którym program będzie wyświetlany
     *oraz scena, w której został utworzony obiekt klasy
     */
    public Stage parentWindow;
    public Scene parentScene;

    /*Przyciski wspólne dla interfejsów studenta i administratora*/
    public Button buttonLogout;
    public Button buttonProfile;
    public Button buttonCourses;


    public abstract void displayMainMenu();
    public abstract void logout();
    public abstract void showProfile();
    public abstract void manageCourses();
    public abstract void manageGroups();
}
