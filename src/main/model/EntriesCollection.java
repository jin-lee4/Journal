package model;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EntriesCollection {
    // Represents the total collection of journal entries user has created thus far, organized in chronological order

    public ArrayList<JournalEntry> entries;
    public int numEntries;

    public EntriesCollection() {
        entries = new ArrayList<>();
        numEntries = 0;
    }

    //MODIFIES: this
    //EFFECTS: adds new entry
    public void addEntry(JournalEntry entry) {
    }

    //REQUIRES: entry must exist within the collection
    //MODIFIES: this
    //EFFECTS: removes an entry from collection
    public void deleteEntry(JournalEntry entry) {
    }

    //EFFECTS: returns entry with given date,
    //         if no entry with such date exists, return "Entry cannot be found."
    public String viewEntry(LocalDateTime date) {
        return null; //stub
    }

    //EFFECTS: lists all entries with timestamps in chronological order of newest to oldest
    public String listAllEntries() {
        return null; //stub
    }

    public int getNumEntries() {
        return numEntries; //stub
    }




}
