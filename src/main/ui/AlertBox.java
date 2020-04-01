package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//Application's alert box
public class AlertBox extends PopUpBox {

    static Stage window;

    public static void display(String title, String message) {
        window = createWindow(title);

        Label alertMessage = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction((e -> window.close()));

        layout = createBoxScene();
        layout.getChildren().addAll(alertMessage, closeButton);

        scene = new Scene(layout);

        window.setScene(scene);
        window.show();
    }
}

