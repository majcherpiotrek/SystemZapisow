package users.admin;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import users.GeneralUserInteface;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Klasa administrator w systemie. Posiada metody do wyświetlnia
 * interfejsu użytkownika oraz do edycji danych w systemie.
 * Created by piotrek on 06.12.16.
 */
public class AdminUserInterface extends GeneralUserInteface {

    private Admin admin;

    public AdminUserInterface(Stage _parentWindow, Scene _previousScene, String adminLogin){
        /*Przypisanie okna i sceny rodzica*/
        this.parentWindow = _parentWindow;
        this.parentScene = _previousScene;
        admin = new Admin(adminLogin);
    }

    @Override
    public void displayMainMenu(){
        /*Inicjalizacja szkieletu GUI*/
        initInterfaceFrame("Panel administracyjny");

        VBox mainMenuLayout = new VBox();
        mainMenuLayout.setPadding(new Insets(50,50,50,50));
        mainMenuLayout.setSpacing(40);
        mainMenuLayout.setAlignment(Pos.CENTER);

        mainMenuLayout.getChildren().addAll(buttonProfile, buttonCourses, buttonLogout);
        Scene mainMenuScene = new Scene(mainMenuLayout);

         /*Obsługa zdarzeń przycisków*/
        buttonLogout.setOnAction(e -> logout());
        buttonProfile.setOnAction(e -> showProfile(mainMenuScene));
        buttonCourses.setOnAction(e -> manageCourses());

        this.parentWindow.setScene(mainMenuScene);

    }

    @Override
    public void showProfile(Scene lastScene){
        System.out.println(admin);
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Button powrot = new Button("Wróć");

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });

        Label login = new Label(admin.login);
        Label name  = new Label(admin.name);
        Label surr  = new Label(admin.surname);
        Label email = new Label(admin.email);

        layout.getChildren().addAll(login,name,surr,email,powrot);
        Scene scene = new Scene(layout);

        parentWindow.setScene(scene);
    }

    @Override
    public void manageCourses() {
        System.out.println("manage courses");
    }

    @Override
    public void manageGroups() {
        System.out.println("manage courses");
    }


}














