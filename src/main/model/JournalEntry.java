package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Represents a journal entry with the date it is created, text and photos added to entry
public class JournalEntry {

    public LocalDateTime time;
    public String title;
    String text;

    //EFFECTS: creates an empty entry
    public JournalEntry(String title, LocalDateTime time) {
        this.title = title;
        this.time = time;
        this.text = "";
    }

    //MODIFIES: this
    //EFFECTS: adds or replaces text to entry
    public void setText(String text) {
        this.text = text;
    }

    //MODIFIES: time
    //EFFECTS: returns date as a string in format "yyyy/mm/dd hr:sec"
    public String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
        return time.format(formatter);
    }

    //EFFECTS: returns text of entry
    //         if entry is empty, return "Nothing yet. What's on your mind?"
    public String getText() {
        if (this.text.equals("")) {
            return "Nothing yet. What's on your mind?";
        }
        return this.text;
    }

}
