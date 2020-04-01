package ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.EntriesCollection;
import model.JournalEntry;
import persistence.Reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

//Runs journal application GUI
public class Main extends Application {

    private static Scene currentScene;
    private static Stage window;

    private static EntriesCollection journal;
    private String journalFile = "./data/JOURNAL.txt";

    //EFFECTS: runs the journal application
    public static void main(String[] args) {
        launch(args);
    }

    //MODIFIES: this
    //EFFECTS: load entries from journalFile if that file exists, otherwise initializes empty journal
    //         sets window scene to createMainScene()
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

        mainScene();

        window.show();
    }

    //MODIFIES: this
    //EFFECTS: initializes new journal
    private void initialize() {
        journal = new EntriesCollection();
    }

    //MODIFIES: this
    //EFFECTS: sets stage to mainPanel scene
    public static void mainScene() {
        MainPanel mainPanel = new MainPanel();
        currentScene = mainPanel.display(window);

        window.setScene(currentScene);
    }

    //MODIFIES: this
    //EFFECTS: creates a new scene for writing a new entry
    public static void writeEntryScene() {
        WriteEntryPanel writeEntryPanel = new WriteEntryPanel();
        currentScene = writeEntryPanel.display(window);

        window.setScene(currentScene);
    }

    //MODIFIES: this
    //EFFECTS: creates a new scene to view all entries
    public static void viewAllEntriesScene() {
        ViewEntriesPanel viewEntriesPanel = new ViewEntriesPanel();
        currentScene = viewEntriesPanel.display(window);

        window.setScene(currentScene);
    }


    //MODIFIES: this
    //EFFECTS: creates a new scene for editing an entry
    public static void editEntryScene(JournalEntry entry) {

        EditEntryPanel editEntryPanel = new EditEntryPanel(entry);
        currentScene = editEntryPanel.display(window);

        window.setScene(currentScene);
    }


    //MODIFIES: this
    //EFFECTS: creates a new scene to view a journal entry
    public static void viewEntryScene(JournalEntry entry) {
        ViewEntryPanel viewEntryPanel = new ViewEntryPanel(entry);
        currentScene = viewEntryPanel.display(window);

        window.setScene(currentScene);
    }
}