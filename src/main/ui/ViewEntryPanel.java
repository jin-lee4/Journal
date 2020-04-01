package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.JournalEntry;

import static javafx.geometry.Pos.BOTTOM_RIGHT;
import static javafx.geometry.Pos.CENTER_LEFT;
import static ui.Main.*;

//Application's viewing selected journal entry panel UI
public class ViewEntryPanel extends AppPanel {
    JournalEntry entry;

    public ViewEntryPanel(JournalEntry entry) {
        this.entry = entry;
    }

    //returns scene for viewing a selected entry
    @Override
    protected Scene display(Stage window) {
        String time = entry.dateToString(entry.time);

        VBox main = new VBox(PADDING);

        VBox contents = new VBox(PADDING);
        contents.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        contents.setAlignment(CENTER_LEFT);

        Label title = new Label(time + "\n" + entry.title);
        Text text = new Text(entry.getText());

        HBox options = new HBox(PADDING);
        options.setPadding(new Insets(250, PADDING, PADDING, PADDING));
        options.setAlignment(BOTTOM_RIGHT);

        Button edit = new Button("Edit");
        edit.setOnAction(e -> editEntryScene(entry));
        Button delete = new Button("Delete");
        delete.setOnAction(e -> deleteButtonClickEvent(entry));

        Button back = new Button("Back");
        back.setOnAction(e -> viewAllEntriesScene());

        options.getChildren().addAll(edit, delete, back);
        contents.getChildren().addAll(title, text);

        main.getChildren().addAll(contents, options);

        return new Scene(main, WIDTH, HEIGHT);
    }
}
