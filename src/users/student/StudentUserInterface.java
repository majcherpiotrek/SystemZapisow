package users.student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Group;
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
        GridPane layout = new GridPane();
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        v1.setSpacing(20);
        v2.setSpacing(20);
        layout.setAlignment(Pos.CENTER_LEFT);

        Button powrot = new Button("Wróć");

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });


        Label login             = new Label("Login : ");
        Label email             = new Label("E-mail : ");
        Label name              = new Label("Imię : ");
        Label surr              = new Label("Nazwisko : ");
        Label id                = new Label("Indeks : ");
        Label department        = new Label("Wydział : ");
        Label fieldOfStudy      = new Label("Kierunek : ");
        Label specialization    = new Label("Specjalizacja : ");
        Label term              = new Label("Semestr : ");
        Label signUpRight       = new Label("Prawo do zapisów : ");
        Label ects              = new Label("ECTS : ");


        Label loginV2             = new Label(student.login);
        Label emailV2             = new Label(student.email);
        Label nameV2              = new Label(student.name);
        Label surrV2              = new Label(student.surname);
        Label idV2                = new Label(student.getID());
        Label departmentV2        = new Label(student.getDepartment().getName());
        Label fieldOfStudyV2      = new Label(student.getFieldOfStudy());
        Label specializationV2    = new Label(student.getSpecialization());
        Label termV2              = new Label(String.valueOf(student.getTerm()));
        Label signUpRightV2       = new Label(student.getSpecialization());
        Label ectsV2              = new Label(String .valueOf(student.getECTS()));


        v1.getChildren().addAll(login,name,surr,email,id,department,fieldOfStudy,specialization,term,signUpRight,ects,powrot);
        v2.getChildren().addAll(loginV2,nameV2,surrV2,emailV2,idV2,departmentV2,fieldOfStudyV2,specializationV2,termV2,signUpRightV2,ectsV2);

        layout.setHgap(10);
        layout.setVgap(10);


    layout.setConstraints(v1,0,0);
    layout.setConstraints(v2,1,0);

    layout.getChildren().addAll(v1,v2);

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
