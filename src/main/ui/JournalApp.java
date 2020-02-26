package ui;


import model.EntriesCollection;
import model.JournalEntry;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static javafx.application.Platform.exit;

// Journal application
public class JournalApp {
    private String journalFile = "./data/JOURNAL.txt";
    private EntriesCollection journal;
    private Scanner input;

    // EFFECTS: runs the journal application
    public JournalApp() {
        runJournal();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runJournal() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        loadJournal();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                System.out.println("See you next time!");
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: load entries from journalFile if that file exists,
    // otherwise initializes empty journal
    //TODO: add loading existing journal implementation
    private void loadJournal() {
        try {
            journal = new EntriesCollection();
            List<JournalEntry> entries = Reader.readEntries(new File(journalFile));
            for (JournalEntry j : entries) {
                journal.addEntry(j);
            }
        } catch (IOException e) {
            initialize();
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case ("write"):
                doAddEntry();
                break;
            case ("delete"):
                doDeleteEntry();
                break;
            case ("view"):
                doViewEntry();
                break;
            case ("all"):
                doListAllEntries();
                break;
            case ("save"):
                saveJournal();
                break;
        }
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat would you like to do today?\n");
        System.out.println("Enter 'write' to write a new entry");
        System.out.println("Enter 'delete' to delete an entry");
        System.out.println("Enter 'view' to view an entry");
        System.out.println("Enter 'all' to view a list of all existing entries");
        System.out.println("Enter 'save' to save new entries to your journal");
        System.out.println("Enter 'quit' to exit the program");
    }

    //MODIFIES: this
    //EFFECTS: initializes new journal
    private void initialize() {
        journal = new EntriesCollection();
    }

    //MODIFIES: this
    //EFFECTS: saves data of new entries to journalFile
    private void saveJournal() {
        try {
            Writer writer = new Writer(new File(journalFile));

            for (JournalEntry entry : journal.entries) {
                writer.write(entry);
            }

            writer.close();
            System.out.println("Journal entries saved to journal: " + journalFile);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save journal entries to: " + journalFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts creation of new journal entry
    private void doAddEntry() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("What would you like to name this entry?");
        input.nextLine();
        String title = input.nextLine();

        while (isTaken(title)) {
            System.out.println("This title already exists, please enter another.");
            title = input.nextLine();
            isTaken(title);
        }

        createEntry(title, dateTime);
    }

    private boolean isTaken(String t) {
        boolean taken = false;
        for (JournalEntry j : journal.entries) {
            if (j.title.equals(t)) {
                taken = true;
                break;
            }
        }
        return taken;
    }

    private void createEntry(String t, LocalDateTime d) {
        JournalEntry newEntry = new JournalEntry(t, d);
        journal.addEntry(newEntry);
        addText(newEntry);
    }

    private void addText(JournalEntry entry) {
        System.out.println("Write your thoughts below:");
        String entryText = input.nextLine();
        entry.setText(entryText);
    }

    private void doViewEntry() {
        System.out.println("Here are all your entries.\nWhat was the title of the entry you're looking for?");
        System.out.println(journal.listAllEntries());
        input.nextLine();
        String title = input.nextLine();
        System.out.println(journal.viewEntry(title));
    }

    //MODIFIES: this
    //EFFECTS: conducts deletion of an entry
    private void doDeleteEntry() {
        System.out.println(journal.listAllEntries());
        System.out.println("What was the title of the entry?");
        input.nextLine();
        String title = input.nextLine();

        for (JournalEntry next : journal.entries) {
            if (next.title.equals(title)) {
                journal.deleteEntry(next);
                System.out.println("This entry has been deleted.");
                return;
            }
        }
        System.out.println("Entry cannot be found.");
    }

    //MODIFIES: this
    //EFFECTS: conducts listing of all entries
    private void doListAllEntries() {
        System.out.println("Here are all the entries you've written from oldest to newest:\n");
        System.out.println(journal.listAllEntries());
    }
}