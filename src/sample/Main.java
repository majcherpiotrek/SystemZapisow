package sample;

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

    private Stage windowLogin;
    private static final String adminDataFilename = "admin.txt";
    private static final String studentDataFilename = "student.txt";

    private enum ValidationOutput {
        admin, student, wrongInput, noDatabase
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        windowLogin = primaryStage;
        windowLogin.setTitle("Logowanie do systemu");
        windowLogin.setOnCloseRequest(e -> {
            e.consume(); //Konsumujemy zdarzenie - program nie zamknie się już automatycznie
            boolean closeProgram = ConfirmationBox.Display("Zamykanie programu", "Czy na pewno chcesz zamknąć program?");
            if(closeProgram)
                windowLogin.close();
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
       loginButton.setOnAction(e -> {
           errorLabel.setText("");
           String login = loginField.getText();
           String password = passField.getText();

           ValidationOutput whoIsIt = validateLoginData(login, password);
           if(whoIsIt.equals(ValidationOutput.wrongInput))
               errorLabel.setText("Błędny login lub hasło!");

           System.out.println(whoIsIt);
       });

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
       Scene sceneLogin = new Scene(layout);

       windowLogin.resizableProperty().setValue(false);
       windowLogin.setScene(sceneLogin);
       windowLogin.show();

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
            System.out.println("bla");
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
