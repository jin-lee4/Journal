package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.JournalEntry;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static javafx.geometry.Pos.CENTER;
import static ui.Main.viewAllEntriesScene;
import static ui.Main.writeEntryScene;

//Application's main panel UI
public class MainPanel extends AppPanel {
    private String journalFile = "./data/JOURNAL.txt";

    //EFFECTS: returns a new scene for main panel
    @Override
    protected Scene display(Stage window) {
        VBox main = new VBox(PADDING);
        HBox options = new HBox(PADDING);
        main.setAlignment(CENTER);
        options.setAlignment(CENTER);

        Label header = new Label("What would you like to do today?");
        main.getChildren().add(header);

        Button write = new Button("New Entry");
        write.setOnAction(e -> writeEntryScene());

        Button view = new Button("View Entries");
        view.setOnAction(e -> viewAllEntriesScene());

        Button quit = new Button("Quit");
        quit.setOnAction((e -> confirmClose(window)));

        options.getChildren().addAll(write, view, quit);

        main.getChildren().add(options);

        return new Scene(main, WIDTH, HEIGHT);
    }

    //MODIFIES: this
    //EFFECTS: displays ConfirmBox; if answer is true, window is closed
    //         if answer is false, nothing is changed
    private void confirmClose(Stage window) {
        boolean answer = ConfirmBox.display("Close Program", "Are you sure you want to exit?");
        if (answer) {
            answer = ConfirmBox.display("Save", "Would you like to save changes?");
            if (answer) {
                saveJournal();
            }
            window.close();
        }
    }

    //Code partially taken from TellerApp
    //MODIFIES: this, journal
    //EFFECTS: saves data of new entries to journalFile
    public void saveJournal() {
        try {
            Writer writer = new Writer(new File(journalFile));

            for (JournalEntry entry : journal) {
                writer.write(entry);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            MediaPlayer errorMediaPlayer = new MediaPlayer(errorSound);
            errorMediaPlayer.play();
            AlertBox.display("Error", "Entry could not be saved.");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
