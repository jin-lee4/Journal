package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.JournalEntry;

import static ui.Main.mainScene;
import static ui.Main.viewEntryScene;

//Application's viewing all journal entries panel UI
public class ViewEntriesPanel extends AppPanel {
    ListView<String> entries;

    @Override
    protected Scene display(Stage window) {
        Pane main = createMainVBox();
        Pane options = createOptionsHBox();

        addEntriesToList();

        Button view = new Button("View");
        view.setOnAction(e -> viewButtonClickEvent());

        Button delete = new Button("Delete");
        delete.setOnAction(e -> selectedEntryDeleteButtonClickEvent());

        Button back = new Button("Back");
        back.setOnAction(e -> mainScene());

        options.getChildren().addAll(view, delete, back);
        main.getChildren().addAll(entries, options);

        return new Scene(main, WIDTH, HEIGHT);
    }

    //MODIFIES: this
    //EFFECTS: button click event to view entry from createViewEntriesScene()
    //         if no item in ListView is selected, AlertBox is displayed
    public void viewButtonClickEvent() {
        String selected = entries.getSelectionModel().getSelectedItem();
        if (selected == null) {
            playSound(errorSound);
            AlertBox.display("Error", "No entry has been selected.");
        } else {
            viewEntryScene(searchEntry(selected));
        }
    }

    //MODIFIES: this
    //EFFECTS: initiates deleteButtonClickEvent if selected entry has been found
    //         if no selected entry has been found, AlertBox is displayed
    private void selectedEntryDeleteButtonClickEvent() {
        String selected = entries.getSelectionModel().getSelectedItem();

        if (selected == null) {
            playSound(errorSound);
            AlertBox.display("Error", "No entry has been selected.");
        } else {
            deleteButtonClickEvent(searchEntry(selected));
        }
    }

    //EFFECTS: returns a ListView of date + time of each entry in journal
    private void addEntriesToList() {
        entries = new ListView<>();
        for (JournalEntry next : journal) {
            String header = next.dateToString(next.time) + " " + next.title;
            entries.getItems().add(header);
        }
    }

    private JournalEntry searchEntry(String selected) {

        JournalEntry found = null;
        for (JournalEntry j : journal) {
            String header = j.dateToString(j.time) + " " + j.title;
            if (header.equals(selected)) {
                found = j;
            }
        }
        return found;
    }
}
