package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.JournalEntry;

//Application's edit selected journal entry panel UI
public class EditEntryPanel extends AppPanel {
    JournalEntry entry;

    public EditEntryPanel(JournalEntry entry) {
        this.entry = entry;
    }

    @Override
    protected Scene display(Stage window) {
        Pane main = createMainVBox();
        Pane options = createOptionsHBox();

        Label title = new Label("Title:");
        TextField titleField = new TextField();
        titleField.setText(entry.title);

        Label textPrompt = new Label("Write below:");
        TextArea textField = new TextArea();
        textField.setText(entry.getText());

        Button save = new Button("Save");
        save.setOnAction(e -> {
            saveEditedEntry(entry, titleField.getText(), textField.getText());
            Label saveSuccessful = new Label("Saved successfully.");
            playSound(successfulSound);
            options.getChildren().add(saveSuccessful);
        });
        Button back = new Button("Back");
        back.setOnAction(e -> confirmBack(titleField.getText()));
        options.getChildren().addAll(save, back);

        main.getChildren().addAll(title, titleField, textPrompt, textField, options);

        return new Scene(main, WIDTH, HEIGHT);
    }

    //MODIFIES: entry
    //EFFECTS: sets entry text to txt, entry title to t
    private void saveEditedEntry(JournalEntry entry, String t, String txt) {
        entry.setText(txt);
        entry.title = t;
    }
}
