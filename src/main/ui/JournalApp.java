package ui;


import model.EntriesCollection;
import model.JournalEntry;

import java.time.LocalDateTime;
import java.util.Scanner;

// Journal application
public class JournalApp {
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
    //EFFECTS: initializes journal with no journal entries
    private void loadJournal() {
        journal = new EntriesCollection();
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("write")) {
            doAddEntry();
        } else if (command.equals("delete")) {
            doDeleteEntry();
        } else if (command.equals("view")) {
            doViewEntry();
        } else if (command.equals("all")) {
            doListAllEntries();
        }
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat would you like to do today?\n");
        System.out.println("Enter 'write' to write a new entry");
        System.out.println("Enter 'delete' to delete an entry");
        System.out.println("Enter 'view' to view an entry");
        System.out.println("Enter 'all' to view a list of all existing entries");
        System.out.println("Enter 'quit' to exit the program");
    }

    //MODIFIES: this
    //EFFECTS: conducts creation of new journal entry
    private void doAddEntry() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("What would you like to name this entry?");
        input.nextLine();
        String title = input.nextLine();
        JournalEntry newEntry = new JournalEntry(title, dateTime);
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
    //TODO: fix this to use title instead of date
    private void doDeleteEntry() {
        System.out.println(journal.listAllEntries());
        System.out.println("What was the title of the entry?");
        input.nextLine();
        String title = input.nextLine();
        for (JournalEntry next : journal.entries) {
            if (next.title.equals(title)) {
                journal.deleteEntry(next);
                System.out.println("This entry has been deleted.");
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