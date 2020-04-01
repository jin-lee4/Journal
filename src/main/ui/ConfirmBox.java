package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//Application's confirmation box
public class ConfirmBox extends PopUpBox {
    static Stage window;
    private static boolean answer;

    public static boolean display(String title, String message) {
        window = createWindow(title);
        Label confirmationMessage = new Label(message);

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        layout = createBoxScene();
        layout.getChildren().addAll(confirmationMessage, yesButton, noButton);
        layout.setPadding(new Insets(10, 10, 10, 10));

        scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
