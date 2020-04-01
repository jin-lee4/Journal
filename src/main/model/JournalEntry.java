package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.time.LocalDateTime;

// Represents a journal entry with the date it is created, text and photos added to entry
public class JournalEntry extends Date implements Saveable {

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

    //EFFECTS: returns text of entry
    //         if entry is empty, return "Nothing yet. What's on your mind?"
    public String getText() {
        if (this.text.equals("")) {
            return "Nothing yet. What's on your mind?";
        }
        return this.text;
    }

    //MODIFIES: this
    //EFFECTS: saves title, time, and text of entry
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(title);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(time);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(text);
    }

}
