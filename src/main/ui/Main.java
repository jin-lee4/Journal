package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.EntriesCollection;
import model.JournalEntry;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import static javafx.geometry.Pos.*;

public class Main extends Application {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int PADDING = 15;
    Stage window;
    ListView<String> entries;

    String startup = "./data/StartUp.wav";
    String error = "./data/Error.wav";
    String successful = "./data/Successful.wav";
    Media startUpSound = new Media(new File(startup).toURI().toString());
    Media errorSound = new Media(new File(error).toURI().toString());
    Media successfulSound = new Media(new File(successful).toURI().toString());

    EntriesCollection journal;
    private String journalFile = "./data/JOURNAL.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            journal = new EntriesCollection();
            List<JournalEntry> entries = Reader.readEntries(new File(journalFile));
            for (JournalEntry j : entries) {
                journal.addEntry(j);
            }
        } catch (IOException e) {
            initialize();
        }
        window = primaryStage;

        window.setScene(createMainScene());
        window.show();
        playSound(startUpSound);
    }

    //MODIFIES: this
    //EFFECTS: initializes new journal
    private void initialize() {
        journal = new EntriesCollection();
    }

    private Scene createMainScene() {
        VBox main = new VBox(PADDING);
        HBox options = new HBox(PADDING);
        main.setAlignment(CENTER);
        options.setAlignment(CENTER);

        Label header = new Label("What would you like to do today?");
        main.getChildren().add(header);

        Button write = new Button("New Entry");
        write.setOnAction(e -> window.setScene(createWriteEntryScene()));

        Button view = new Button("View Entries");
        view.setOnAction(e -> window.setScene(createViewEntriesScene()));

        Button quit = new Button("Quit");
        quit.setOnAction((e -> confirmClose()));
        options.getChildren().addAll(write, view, quit);

        main.getChildren().add(options);

        return new Scene(main, WIDTH, HEIGHT);
    }

    private Scene createWriteEntryScene() {
        Pane main = createMainVBox();
        Pane options = createOptionsHBox();

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

    private Scene createViewEntriesScene() {
        Pane main = createMainVBox();
        Pane options = createOptionsHBox();

        addEntriesToList();

        Button view = new Button("View");
        view.setOnAction(e -> viewButtonClickEvent());

        Button delete = new Button("Delete");
        delete.setOnAction(e -> selectedEntryDeleteButtonClickEvent());

        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(createMainScene()));

        options.getChildren().addAll(view, delete, back);
        main.getChildren().addAll(entries, options);

        return new Scene(main, WIDTH, HEIGHT);
    }

    private void viewButtonClickEvent() {
        String selected = entries.getSelectionModel().getSelectedItem();
        if (selected == null) {
            playSound(errorSound);
            AlertBox.display("Error", "No entry has been selected.");
        } else {
            window.setScene(viewEntryScene(searchEntry(selected)));
        }
    }

    private void editButtonClickEvent(JournalEntry entry) {

        window.setScene(createEditEntryScene(entry));

    }

    ///TODO: fix the save feature (overwrite old entry)
    private Scene createEditEntryScene(JournalEntry entry) {
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

    private void saveEditedEntry(JournalEntry entry, String t, String txt) {
        entry.setText(txt);
        entry.title = t;
    }

    private ListView addEntriesToList() {
        entries = new ListView<>();
        for (JournalEntry next : journal.entries) {
            String header = next.dateToString(next.time) + " " + next.title;
            entries.getItems().add(header);
        }
        return entries;
    }

    private JournalEntry searchEntry(String selected) {

        JournalEntry found = null;
        for (JournalEntry j : journal.entries) {
            String header = j.dateToString(j.time) + " " + j.title;
            if (header.equals(selected)) {
                found = j;
            }
        }
        return found;
    }

    private void selectedEntryDeleteButtonClickEvent() {
        String selected = entries.getSelectionModel().getSelectedItem();

        if (selected == null) {
            playSound(errorSound);
            AlertBox.display("Error", "No entry has been selected.");
        } else {
            deleteButtonClickEvent(searchEntry(selected));
        }
    }

    private Scene viewEntryScene(JournalEntry entry) {
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
        edit.setOnAction(e -> editButtonClickEvent(entry));
        Button delete = new Button("Delete");
        delete.setOnAction(e -> deleteButtonClickEvent(entry));

        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(createViewEntriesScene()));

        options.getChildren().addAll(edit, delete, back);
        contents.getChildren().addAll(title, text);

        main.getChildren().addAll(contents, options);

        return new Scene(main, WIDTH, HEIGHT);
    }

    private void deleteButtonClickEvent(JournalEntry entry) {
        boolean answer = ConfirmBox.display("Delete Entry", "Are you sure you'd like to delete?");
        if (answer) {
            journal.deleteEntry(entry);
            window.setScene(createViewEntriesScene());
            playSound(successfulSound);
            AlertBox.display("Successful", "Your entry has been deleted.");
        }
    }

    private Pane createOptionsHBox() {
        HBox options = new HBox(PADDING);
        options.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        options.setAlignment(BOTTOM_RIGHT);
        return options;
    }

    private Pane createMainVBox() {
        VBox main = new VBox(PADDING);
        main.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        return main;
    }

    private void playSound(Media sound) {
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    private void confirmClose() {

        boolean answer = ConfirmBox.display("Close Program", "Are you sure you want to exit?");
        if (answer) {
            answer = ConfirmBox.display("Save", "Would you like to save changes?");
            if (answer) {
                saveJournal();
            }
            window.close();
        }
    }

    private void confirmBack(String t) {

        boolean found = false;

        for (JournalEntry j : journal.entries) {
            if (j.title.equals(t)) {
                found = true;
                break;
            }
        }

        if (!found) {
            boolean answer = ConfirmBox.display("Confirm", "Are you sure you want to exit?"
                    + " Your entry will not be saved.");
            if (answer) {
                window.setScene(createMainScene());
            }
        } else {
            window.setScene(createMainScene());
        }
    }

    //Code partially taken from TellerApp
    //MODIFIES: this
    //EFFECTS: saves data of new entries to journalFile
    public void saveJournal() {
        try {
            Writer writer = new Writer(new File(journalFile));

            for (JournalEntry entry : journal.entries) {
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

    private boolean isTaken(String t) {
        boolean taken = false;
        for (JournalEntry j : journal.entries) {
            if (j.title.equals(t)) {
                taken = true;
                break;
            }
        }
        return taken;
    }
}