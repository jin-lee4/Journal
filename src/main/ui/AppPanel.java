package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.EntriesCollection;
import model.JournalEntry;

import java.io.File;

import static javafx.geometry.Pos.BOTTOM_RIGHT;
import static ui.Main.mainScene;
import static ui.Main.viewAllEntriesScene;

//Abstract class for all of application's panels
public abstract class AppPanel {
    EntriesCollection journal = EntriesCollection.getInstance();

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int PADDING = 15;

    String error = "./data/Error.wav";
    String successful = "./data/Successful.wav";
    Media errorSound = new Media(new File(error).toURI().toString());
    Media successfulSound = new Media(new File(successful).toURI().toString());


    protected abstract Scene display(Stage window);

    //MODIFIES: this
    //EFFECTS: returns a new HBox
    protected HBox createOptionsHBox() {
        HBox options = new HBox(PADDING);
        options.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        options.setAlignment(BOTTOM_RIGHT);
        return options;
    }

    //MODIFIES: this
    //EFFECTS: returns a new VBox
    protected VBox createMainVBox() {
        VBox main = new VBox(PADDING);
        main.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        return main;
    }

    //EFFECTS: creates new MediaPlayer and plays sound
    protected void playSound(Media sound) {
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    //MODIFIES: this, journal
    //EFFECTS: displays ConfirmBox; if answer is true, entry is deleted
    //         if answer is false, nothing is changed
    protected void deleteButtonClickEvent(JournalEntry entry) {
        boolean answer = ConfirmBox.display("Delete Entry", "Are you sure you'd like to delete?");
        if (answer) {
            journal.deleteEntry(entry);
            viewAllEntriesScene();
            playSound(successfulSound);
            AlertBox.display("Successful", "Your entry has been deleted.");
        }
    }

    //MODIFIES: this
    //EFFECTS: displays ConfirmBox; if answer and found is true, window changes to createMainScene()
    //         if answer is false, nothing is changed
    protected void confirmBack(String t) {
        boolean found = false;
        for (JournalEntry j : journal) {
            if (j.title.equals(t)) {
                found = true;
                break;
            }
        }
        if (!found) {
            boolean answer = ConfirmBox.display("Confirm", "Are you sure you want to exit?"
                    + " Your entry will not be saved.");
            if (answer) {
                mainScene();
            }
        } else {
            mainScene();
        }
    }
}
