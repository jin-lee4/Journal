package model;

import exceptions.NoEntryFoundException;

import java.util.ArrayList;
import java.util.Iterator;

// Represents the total collection of journal entries user has created thus far, organized in chronological order
public class EntriesCollection implements Iterable<JournalEntry> {

    private static EntriesCollection entriesCollection = new EntriesCollection();
    public ArrayList<JournalEntry> entries;
    public int numEntries;

    public EntriesCollection() {
        entries = new ArrayList<>();
        numEntries = 0;
    }

    public static EntriesCollection getInstance() {
        return entriesCollection;
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

    //EFFECTS: finds journal entry with title "title" and returns its time, title and text
    //         if journal entry is not found, NoEntryFoundException is thrown
    public String viewEntry(String title) throws NoEntryFoundException {
        StringBuilder entryText = new StringBuilder();

        for (JournalEntry next : entries) {
            if (next.title.equals(title)) {
                String header = next.dateToString(next.time) + " " + next.title;
                entryText.append("\n\n").append(header).append("\n").append(next.getText());
            }
        }

        if (entryText.toString().equals("")) {
            throw new NoEntryFoundException("This entry cannot be found.");
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

    //EFFECTS: returns number of entries
    public int getNumEntries() {
        return numEntries;
    }

    @Override
    public Iterator<JournalEntry> iterator() {
        return entries.iterator();
    }
}
