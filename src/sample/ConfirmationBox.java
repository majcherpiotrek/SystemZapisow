package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


class ConfirmationBox {

    private static boolean decision;

    static boolean Display(String windowTitle, String message){

        Stage confirmationBoxWindow = new Stage();
        confirmationBoxWindow.initModality(Modality.APPLICATION_MODAL); //razem z showAndWait() powoduje, że nie można edytować innego okna, zanim to nie zostanie zamknięte
        confirmationBoxWindow.setTitle(windowTitle);
        confirmationBoxWindow.resizableProperty().setValue(false);

        Label label = new Label(message); //Wiadomość wyświetlana w oknie

        Button buttonYes = new Button();
        buttonYes.setText("Tak");
        buttonYes.setPrefSize(120, 20);
        buttonYes.setPadding(new Insets(5, 5, 5, 5));
        buttonYes.setOnAction(e -> {
            decision = true;
            confirmationBoxWindow.close();
        });

        Button buttonNo = new Button();
        buttonNo.setText("Nie");
        buttonNo.setPrefSize(120, 20);
        buttonNo.setPadding(new Insets(5, 5, 5, 5));
        buttonNo.setOnAction(e -> {
            decision = false;
            confirmationBoxWindow.close();
        });

        VBox backgroundLayout = new VBox();
        backgroundLayout.setPadding(new Insets(10,10,10,10));
        backgroundLayout.setSpacing(20);

        HBox frontLayout = new HBox();
        frontLayout.setPadding(new Insets(10, 10, 10, 10));
        frontLayout.setSpacing(10);

        frontLayout.getChildren().addAll(buttonYes, buttonNo);
        backgroundLayout.getChildren().addAll(label, frontLayout);

        Scene scene = new Scene(backgroundLayout);

        confirmationBoxWindow.setScene(scene);
        confirmationBoxWindow.showAndWait();

        return decision;
    }
}
