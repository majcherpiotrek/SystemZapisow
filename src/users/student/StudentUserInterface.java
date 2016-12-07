package users.student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Main;
import users.GeneralUserInteface;

/**
 * Created by piotrek on 06.12.16.
 */
public class StudentUserInterface extends GeneralUserInteface {

    private Student student;

    public StudentUserInterface(Stage _parentWindow, Scene _parentScene, String studentLogin){
        this.parentWindow = _parentWindow;
        this.parentScene = _parentScene;
        student = new Student(studentLogin);
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

         /*Obsługa zdarzeń przycisków*/
        buttonLogout.setOnAction(e -> logout());
        buttonProfile.setOnAction(e -> showProfile(mainMenuScene));
        buttonCourses.setOnAction(e -> manageCourses());

        this.parentWindow.setScene(mainMenuScene);
    }

    @Override
    public void showProfile(Scene lastScene) {
        System.out.println(student);
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Button powrot = new Button("Wróć");

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });

        Label login = new Label(student.login);
        Label email = new Label(student.email);
        Label name  = new Label(student.name);
        Label surr  = new Label(student.surname);

        layout.getChildren().addAll(login,name,surr,email,powrot);
        Scene scene = new Scene(layout);

        parentWindow.setScene(scene);

    }

    @Override
    public void manageCourses() {

    }

    @Override
    public void manageGroups() {

    }
}
