package sample;

import javafx.collections.ObservableList;
import users.admin.Admin;
import users.admin.AdminUserInterface;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import users.student.Student;
import users.student.StudentUserInterface;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*TODO

      ->Zrobić dodawanie nowych kursów w klasie Admin
       ->Może zrobić bazę danych serializable i zapamiętywać zmiany, ale w sumie to nie ma ciśnienia
       ->Wygenerować losowe grupy zajęciowe do kursów
       -> merge z tym co maciek zrobił, żeby zobaczyć czy działa zapisywanie do grup itd
       ->zrobić, żeby wiersze w tabeli kursów i grup mieściły się w poziomie
       ->PRZETESTOWAĆ CZY WSZYSTKO HULA
       ->dodawanie/usuwanie kursów i grup przez admina

*/
public class Main extends Application {

    private static Stage mainWindow;
    private static Scene mainScene;
    public static final String adminDataFilename = "admin.txt";
    public static final String studentDataFilename = "student.txt";
    public static final String groupDataFilename = "grupy.txt";

    private enum ValidationOutput {
        admin, student, wrongInput, noDatabase
    }

    ObservableList<Student> studentsList = DataBase.INSTANCE.getStudentsList();

    @Override
    public void start(Stage primaryStage) throws Exception{

        mainWindow = primaryStage;
        mainWindow.setTitle("Logowanie do systemu");
        mainWindow.setOnCloseRequest(e -> {
            e.consume(); //Konsumujemy zdarzenie - program nie zamknie się już automatycznie
            boolean closeProgram = ConfirmationBox.Display("Zamykanie programu", "Czy na pewno chcesz zamknąć program?");
            if(closeProgram)
                mainWindow.close();
        });

       Label messageLabel = new Label("Podaj swój login oraz hasło aby zalogować się do systemu zapisów:");
       messageLabel.setPadding(new Insets(10,0,10,0));
       Label errorLabel = new Label();
       errorLabel.setTextFill(Color.RED);
       errorLabel.setPadding(new Insets(0,0,10,0));

       Label loginLabel = new Label("Login:");
       Label passLabel = new Label("Hasło:");

       TextField loginField = new TextField();
       loginField.setPromptText("twój login");

       PasswordField passField = new PasswordField();
       passField.setPromptText("twoje hasło");

       Button loginButton = new Button("Zaloguj");
       loginButton.setPadding(new Insets(5,5,5,5));

       GridPane layout = new GridPane();
       layout.setHgap(10);
       layout.setVgap(10);
       layout.setPadding(new Insets(10,50,10,50));

       layout.add(messageLabel, 0,0,2,1);
       layout.add(loginLabel, 0,1);
       layout.add(loginField, 1,1);
       layout.add(passLabel, 0,2);
       layout.add(passField, 1,2);
       layout.add(loginButton, 1,3);
       layout.add(errorLabel, 1,4);

       layout.setAlignment(Pos.CENTER);
       mainScene = new Scene(layout);

       loginButton.setOnAction(e -> {
           errorLabel.setText("");
           String login = loginField.getText();
           String password = passField.getText();

           ValidationOutput whoIsIt = validateLoginData(login, password);
           if(whoIsIt.equals(ValidationOutput.wrongInput))
               errorLabel.setText("Błędny login lub hasło!");

           if(whoIsIt.equals(ValidationOutput.admin)){
               /*ADMIN*/
               AdminUserInterface admin = new AdminUserInterface(this.mainWindow, this.mainScene, login);
               /*Zamiana sceny w głównym oknie - wyświetlenie menu admina*/
               admin.displayMainMenu();
               loginField.clear();
               passField.clear();
           }

           if(whoIsIt.equals(ValidationOutput.student)){
               /*STUDENT*/
               StudentUserInterface student = new StudentUserInterface(this.mainWindow, this.mainScene, login);
               /*Zamiana sceny w głównym oknie - wyświetlenie menu admina*/
               student.displayMainMenu();
               loginField.clear();
               passField.clear();
           }
           System.out.println(whoIsIt);
       });



       mainWindow.resizableProperty().setValue(false);
       mainWindow.setScene(mainScene);
       mainWindow.show();

    }

    private ValidationOutput validateLoginData(String login, String password){


        for(Student s : DataBase.INSTANCE.getStudentsList())
            if(s.login.equals(login))
                return ValidationOutput.student;

        for(Admin a : DataBase.INSTANCE.getAdminList())
            if(a.login.equals(login))
                return  ValidationOutput.admin;

        return ValidationOutput.wrongInput;
    }

    /*Funkcja pozwalająca na zamknięcie programu przy wystąpieniu nieobsługiwalnego błędu w innej klasie*/
    public static void closeOnError(String errorMessage){
        System.err.println(errorMessage);
        mainWindow.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
