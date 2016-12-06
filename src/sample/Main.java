package sample;

import users.admin.Admin;
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

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    private Stage mainWindow;
    private Scene mainScene;
    private static final String adminDataFilename = "admin.txt";
    private static final String studentDataFilename = "student.txt";

    private enum ValidationOutput {
        admin, student, wrongInput, noDatabase
    }

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
               Admin admin = new Admin(this.mainWindow, this.mainScene);

               /*Zamiana sceny w głównym oknie - wyświetlenie menu admina*/
               admin.displayMainMenu();
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

        Scanner input;
        String loginLine = login + " " + password;

        try{
            File adminDataFile = new File(adminDataFilename);
            input = new Scanner(adminDataFile);
        }catch(IOException ex){
            return ValidationOutput.noDatabase;
        }

        String line;

        while(input.hasNextLine()) {
            line = input.nextLine();
            if(line.equals(loginLine))
                return ValidationOutput.admin;
        }



        try{
            File studentDataFile = new File(studentDataFilename);
            input = new Scanner(studentDataFile);
        }catch(IOException ex){
            return ValidationOutput.noDatabase;
        }

        while(input.hasNextLine()){
            line = input.nextLine();
            if(line.equals(loginLine))
                return ValidationOutput.student;
        }

        return ValidationOutput.wrongInput;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
