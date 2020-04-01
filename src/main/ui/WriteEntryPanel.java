package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.JournalEntry;

import java.time.LocalDateTime;

//Application's writing a new journal entry panel UI
public class WriteEntryPanel extends AppPanel {

    //returns scene for writing a new entry
    @Override
    protected Scene display(Stage window) {
        Pane options = createOptionsHBox();
        Pane main = createMainVBox();

        Label title = new Label("Title:");
        TextField titleField = new TextField();

        Label textPrompt = new Label("Write below:");
        TextArea textField = new TextArea();
        textField.setPrefHeight(350);

        Button save = new Button("Save");
        save.setOnAction(e -> {
            createEntry(titleField.getText(), textField.getText());
            playSound(successfulSound);
            Label saveSuccessful = new Label("Saved successfully.");
            options.getChildren().add(saveSuccessful);
        });
        Button back = new Button("Back");
        back.setOnAction(e -> confirmBack(titleField.getText()));
        options.getChildren().addAll(save, back);

        main.getChildren().addAll(title, titleField, textPrompt, textField, options);

        return new Scene(main, WIDTH, HEIGHT);
    }

    //MODIFIES: journal
    //EFFECTS: if isTaken is false, creates a new entry with current date/time, t as title and txt as entry text
    //         if isTaken is true, AlertBox is displayed
    private void createEntry(String t, String txt) {

        if (isTaken(t)) {
            MediaPlayer errorMediaPlayer = new MediaPlayer(errorSound);
            errorMediaPlayer.play();
            AlertBox.display("Error", "Title already exists, please enter another.");
        } else {
            LocalDateTime dateTime = LocalDateTime.now();
            JournalEntry newEntry = new JournalEntry(t, dateTime);
            newEntry.setText(txt);
            journal.addEntry(newEntry);
        }
    }

    //EFFECTS: returns true if t is equal to another entry's title
    private boolean isTaken(String t) {
        boolean taken = false;
        for (JournalEntry j : journal) {
            if (j.title.equals(t)) {
                taken = true;
                break;
            }
        }
        return taken;
    }
}
