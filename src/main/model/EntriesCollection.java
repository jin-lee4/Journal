package model;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import model.JournalEntry;

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
        entries.add(entry);
        numEntries++;
    }

    //REQUIRES: entry must exist within the collection
    //MODIFIES: this
    //EFFECTS: removes an entry from collection
    public void deleteEntry(JournalEntry entry) {
        entries.remove(entry);
        numEntries--;
    }

    //EFFECTS: returns entry with given date,
    //         if no entry with such date exists, return "Entry cannot be found."
    public String viewEntry(LocalDateTime date) {
        for (JournalEntry next : entries) {
            if (next.time == date) {
                return next.dateToString(next.time) + "\n" + next.text;
            }
        }
        return "Entry cannot be found.";
    }

    //EFFECTS: lists all entries with timestamps in chronological order of newest to oldest
    public String listAllEntries() {
        StringBuilder listOfEntries = new StringBuilder();
        ArrayList<String> dates = new ArrayList<>();

        for (JournalEntry next : entries) {
            dates.add(next.dateToString(next.time));
        }

        Collections.sort(dates, Collections.reverseOrder());

        for (String d : dates) {
            listOfEntries.append(d).append("\n");
        }
        return listOfEntries.toString();
    }

    public int getNumEntries() {
        return numEntries;
    }

}
