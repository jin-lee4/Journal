package model;

import persistence.Saveable;
import persistence.Writer;

import java.io.PrintWriter;
import java.util.ArrayList;

// Represents the total collection of journal entries user has created thus far, organized in chronological order
public class EntriesCollection {

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

    public String viewEntry(String title) {
        StringBuilder entryText = new StringBuilder();
        for (JournalEntry next : entries) {
            if (next.title.equals(title)) {
                String header = next.dateToString(next.time) + " " + next.title;
                entryText.append("\n\n").append(header).append("\n").append(next.getText());
            }
        }
        if (entryText.toString().equals("")) {
            return "Entry cannot be found.";
        } else {
            return entryText.toString();
        }
    }

    //EFFECTS: lists all entries with timestamps in chronological order of oldest to newest
    public String listAllEntries() {
        StringBuilder listOfEntries = new StringBuilder();

        for (JournalEntry next : entries) {
            String header = next.dateToString(next.time) + " " + next.title;
            listOfEntries.append(header).append("\n");
        }
        return listOfEntries.toString();
    }

    public int getNumEntries() {
        return numEntries;
    }
}
