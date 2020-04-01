package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Creates a pop up window
public class PopUpBox {

    static Stage window;
    static VBox layout;
    static Scene scene;

    protected static Stage createWindow(String title) {
        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        return window;
    }

    protected static VBox createBoxScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }
}
