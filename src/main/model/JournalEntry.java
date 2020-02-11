package model;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;

public class JournalEntry {
    // Represents a journal entry with the date it is created, text and photos added to entry

    LocalDateTime time;
    String text;
    File image;

    //EFFECTS: creates an empty entry
    public JournalEntry(LocalDateTime time) {
        this.time = time;
    }

    //MODIFIES: this
    //EFFECTS: adds text to entry
    public void addText(String text) {
        this.text = text;
    }

    //MODIFIES: this
    //EFFECTS: add or edit text of an existing entry
    public void editText(String text) {
    }

    //MODIFIES: this
    //EFFECTS: adds an image to the entry
    public void addImage(File img) {
        this.image = img;
    }

    //MODIFIES: this
    //EFFECTS: removes an image from the entry
    public void deleteImage(File img) {

    }

    //MODIFIES: date
    //EFFECTS: returns date as a string in format "yyyy/mm/dd hr:sec"
    public String toString(LocalDateTime date) {
        return null;
    }

    //EFFECTS: returns text of entry
    //         if entry is empty, return "Nothing yet. What's on your mind?"
    public String getText() {
        return this.text;
    }

    public File getImage() {
        return this.image;
    }

}
