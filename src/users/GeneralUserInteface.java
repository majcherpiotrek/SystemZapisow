package users;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.ConfirmationBox;

/**
 * Created by piotrek on 06.12.16.
 */
public abstract class GeneralUserInteface {

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

    /*Metody abstrakcyjne do przeładowania w klasach admina i studenta*/
    public abstract void displayMainMenu();
    public abstract void showProfile();
    public abstract void manageCourses();
    public abstract void manageGroups();

    /*Funkcja inicjalizująca elementy wspólne interfejsów admina i studenta*/
    public void initInterfaceFrame(String windowTitle){
        parentWindow.setTitle(windowTitle);

        /*Utworzenie przycisków*/
        buttonLogout = new Button();
        buttonProfile = new Button();
        buttonCourses = new Button();

        /*Przypisanie tekstu przycisków*/
        buttonLogout.setText("Wyloguj");
        buttonProfile.setText("Pokaż profil");
        buttonCourses.setText("Zarządzaj kursami");

        /*Ustawienie atrybutów przycisków*/
        buttonLogout.setPadding(new Insets(5,10,5,10));
        buttonLogout.setAlignment(Pos.CENTER);
        buttonProfile.setPadding(new Insets(5,10,5,10));
        buttonProfile.setAlignment(Pos.CENTER);
        buttonCourses.setPadding(new Insets(5,10,5,10));
        buttonCourses.setAlignment(Pos.CENTER);

        /*Obsługa zdarzeń przycisków*/
        buttonLogout.setOnAction(e -> logout());
        buttonProfile.setOnAction(e -> showProfile());
        buttonCourses.setOnAction(e -> manageCourses());
    }
    public void logout() {
        boolean confirmed = ConfirmationBox.Display("Wylogowywanie", "Czy na pewno chcesz się wylogować?");
        if(confirmed)
            this.parentWindow.setScene(this.parentScene);
    }
}
