package ui;


import model.EntriesCollection;
import model.JournalEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;

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
        JournalEntry newEntry = new JournalEntry(dateTime);
        journal.addEntry(newEntry);
        input.nextLine();
        String entryText = input.nextLine();
        newEntry.setText(entryText);
    }

    //MODIFIES: this
    //EFFECTS: conducts viewing of existing journal entry
    private void doViewEntry() {
        System.out.println("What date was the entry written?");
        input.nextLine();
        input.findInLine("(\\d\\d\\d\\d)\\/(\\d\\d)\\/(\\d\\d)");
        MatchResult mr = input.match();
        int year = Integer.parseInt(mr.group(1));
        int month = Integer.parseInt(mr.group(2));
        int day = Integer.parseInt(mr.group(3));
        LocalDate inputDate = LocalDate.of(year, month, day);

        ArrayList<JournalEntry> found = dateCount(inputDate);

        if (dateCount(inputDate).size() > 1) {
            System.out.println("There are multiple entries on this date. Here's what you wrote:");
        }
        System.out.println(journal.viewEntry(inputDate));
    }

    //MODIFIES: this
    //EFFECTS: conducts deletion of an entry
    private void doDeleteEntry() {
        doListAllEntries();
        System.out.println("What date was the entry written?");
        input.nextLine();
        input.findInLine("(\\d\\d\\d\\d)\\/(\\d\\d)\\/(\\d\\d)");
        MatchResult mr = input.match();
        int year = Integer.parseInt(mr.group(1));
        int month = Integer.parseInt(mr.group(2));
        int day = Integer.parseInt(mr.group(3));
        LocalDate inputDate = LocalDate.of(year, month, day);

        int numDateFound = dateCount(inputDate).size();

        if (numDateFound > 1) {
            narrowDeleteEntrySearch();
        } else if (numDateFound == 1) {
            journal.deleteEntry(dateCount(inputDate).get(0));
            System.out.println("Your entry has been deleted!");
        } else {
            System.out.println("Entry cannot be found.");
        }
    }

    private ArrayList<JournalEntry> dateCount(LocalDate d) {
        ArrayList<JournalEntry> entriesFound = new ArrayList<>();

        for (JournalEntry next : journal.entries) {
            if (next.time.toLocalDate().equals(d)) {
                entriesFound.add(next);
            }
        }
        return entriesFound;
    }

     //EFFECTS: searches for entry based on time
    private void narrowDeleteEntrySearch() {
        System.out.println("We found more than one entry on this date. What time was it written?");
        input.nextLine();
        input.findInLine("(\\d\\d)\\:(\\d\\d)\\ [A, a, p, P][m, M]");
        MatchResult mrTime = input.match();

        int hour = Integer.parseInt(mrTime.group(1));
        int minute = Integer.parseInt(mrTime.group(2));
        LocalTime inputTime = LocalTime.of(hour, minute);

        for (JournalEntry next : journal.entries) {
            if (next.time.toLocalTime().equals(inputTime)) {
                journal.deleteEntry(next);
                System.out.println("This entry has been deleted!");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts listing of all entries
    private void doListAllEntries() {
        System.out.println("Here are all the entries you've written from newest to oldest:\n");
        System.out.println(journal.listAllEntries());
    }
}