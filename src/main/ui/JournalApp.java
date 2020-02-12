package ui;


import model.EntriesCollection;
import model.JournalEntry;

import java.util.Scanner;

// Journal application
public class JournalApp {
    private EntriesCollection journal;
    //private JournalEntry entry;
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
            doViewAllEntries();
        }
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat would you like to do today?\n");
        System.out.println("Enter 'write' to write a new entry");
        System.out.println("Enter 'delete' to delete an entry");
        System.out.println("Enter 'view' to view an entry");
        System.out.println("Enter 'all' to view a list of all existing entries");

    }

    //MODIFIES: this
    //EFFECTS: conducts creation of new journal entry
    private void doAddEntry() {

    }

    //MODIFIES: this
    //EFFECTS: conducts deletion of existing journal entry
    private void doDeleteEntry() {

    }

    //MODIFIES: this
    //EFFECTS: conducts viewing an entry
    private void doViewEntry() {

    }

    //MODIFIES: this
    //EFFECTS: conducts listing of all entries
    private void doViewAllEntries() {

    }
}
