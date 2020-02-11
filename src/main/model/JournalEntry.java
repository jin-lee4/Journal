package model;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JournalEntry {
    // Represents a journal entry with the date it is created, text and photos added to entry

    LocalDateTime time;
    String text;
    ArrayList<File> images;

    //EFFECTS: creates an empty entry
    public JournalEntry(LocalDateTime time) {
        this.time = time;
        this.images = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds text to entry
    public void addText(String text) {
        this.text = text;
    }

    //TODO: implement this method!
    //MODIFIES: this
    //EFFECTS: add or edit text of an existing entry
    public void editText(String text) {

    }

    //MODIFIES: this
    //EFFECTS: adds an image to the entry
    public void addImage(File img) {
        this.images.add(img);
    }

    //MODIFIES: this
    //EFFECTS: removes an image from the entry
    public void deleteImage(File img) {
        this.images.remove(img);
    }

    //MODIFIES: time
    //EFFECTS: returns date as a string in format "yyyy/mm/dd hr:sec"
    public String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm a");
        return time.format(formatter);
    }

    //EFFECTS: returns text of entry
    //         if entry is empty, return "Nothing yet. What's on your mind?"
    public String getText() {
        if (this.text == null) {
            return "Nothing yet. What's on your mind?";
        }
        return this.text;
    }

}
