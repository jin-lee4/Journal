package persistence;

import model.JournalEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Code taken from TellerApp application, Reader class
//A reader that can read journal data from a file
public class Reader {
    public static final String DELIMITER = "::";

    //EFFECTS: returns a list of entries parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<JournalEntry> readEntries(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    //EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    //EFFECTS: returns a list of entries parsed from list of strings
    // where each string contains data for one entry
    private static List<JournalEntry> parseContent(List<String> fileContent) {
        List<JournalEntry> entries = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            entries.add(parseEntry(lineComponents));
        }
        return entries;
    }

    //EFFECTS: returns a list of string obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: components has size 3 where element 0 represents the
    //title of the entry, element 1 represents the time, and element 2
    //represents the text of the journal entry
    //EFFECTS: returns a journal entry constructed from components
    private static JournalEntry parseEntry(List<String> components) {
        String title = components.get(0);
        LocalDateTime date = LocalDateTime.parse(components.get(1));
        String text = components.get(2);
        JournalEntry entry = new JournalEntry(title, date);
        entry.setText(text);
        return entry;
    }
}
