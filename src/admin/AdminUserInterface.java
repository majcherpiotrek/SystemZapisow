package admin;
import javafx.geometry.Pos;
import user.UserInterface;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.ConfirmationBox;

/**
 * Klasa wyświetla główny interfejs dla administratora.
 * Metody klasy wyświetlają interfejs dla metod z klasy Admin.
 * Created by piotrek on 06.12.16.
 */
public class AdminUserInterface extends UserInterface{


    @Override
    public Scene createUserInterfaceScene(Stage lastWindow, Scene lastScene){
        window = lastWindow;
        window.setTitle("Panel administracyjny");

        //Przycisk pokazania profilu
        Button buttonProfile = new Button("Pokaż profil");
        buttonProfile.setPadding(new Insets(5,5,5,5));
        buttonProfile.setAlignment(Pos.CENTER);
        buttonProfile.setOnAction(e ->{
            /*pokazanie profilu*/
            showProfile();
        });

        //Przycisk wylogowywania
        Button buttonLogout = new Button("Wyloguj");
        buttonLogout.setPadding(new Insets(5,5,5,5));
        buttonLogout.setAlignment(Pos.CENTER);
        buttonLogout.setOnAction(e -> {
            window.setScene(lastScene);
        });

        VBox layout = new VBox();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(buttonProfile,buttonLogout);
        scene = new Scene(layout);
        return scene;
    }

    @Override
    public void showProfile(){

    }
}














