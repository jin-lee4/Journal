package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    private static Stage window;

    public static void display(String title, String message) {
        window = new Stage();

        //Blocks other windows from being used until current window is closed
//        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label1 = new Label();
        label1.setText(message);
//        Button closeButton = new Button("You need to close this first");
//        closeButton.setOnAction((e -> window.close()));

        VBox layout = new VBox(10);
        layout.getChildren().add(label1);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}

