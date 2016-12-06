package admin;
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
    public void DisplayMainInterface(){
        Stage windowAdmin = new Stage();
        windowAdmin.setTitle("Panel administracyjny systemu");
        windowAdmin.setOnCloseRequest(e -> {
            e.consume(); //Konsumujemy zdarzenie - program nie zamknie się już automatycznie
            boolean closeProgram = ConfirmationBox.Display("Zamykanie programu", "Czy na pewno chcesz zamknąć program?");
            if(closeProgram)
                windowAdmin.close();
        });
    }

    @Override
    public void showProfile(){

    }
}
