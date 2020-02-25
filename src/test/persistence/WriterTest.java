package persistence;

import model.JournalEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final File TEST_FILE = new File("./data/testJournal.txt");
    private Writer testWriter;
    private JournalEntry FIRST_ENTRY;
    private JournalEntry SECOND_ENTRY;
    private LocalDateTime FIRST_DATE;
    private LocalDateTime SECOND_DATE;

    @BeforeEach
    void RunBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(TEST_FILE);
        FIRST_DATE = LocalDateTime.of(2020,2,22,19,23);
        SECOND_DATE = LocalDateTime.of(2020,2,23,17,44);
        FIRST_ENTRY = new JournalEntry("Monday", FIRST_DATE);
        SECOND_ENTRY = new JournalEntry("Tuesday", SECOND_DATE);

        FIRST_ENTRY.setText("It was Joe's birthday today.");
        SECOND_ENTRY.setText("I got a haircut!");
    }

    @Test
    void testWriteEntries() {
        //save entries to file
        testWriter.write(FIRST_ENTRY);
        testWriter.write(SECOND_ENTRY);
        testWriter.close();

        try {
            List<JournalEntry> entries = Reader.readEntries(TEST_FILE);
            JournalEntry firstEntry = entries.get(0);
            assertEquals("Monday", firstEntry.title);
            assertEquals("2020/02/22 07:23 PM", firstEntry.dateToString(firstEntry.time));
            assertEquals("It was Joe's birthday today.", firstEntry.getText());

            JournalEntry secondEntry = entries.get(1);
            assertEquals("Tuesday", secondEntry.title);
            assertEquals("2020/02/23 05:44 PM", secondEntry.dateToString(secondEntry.time));
            assertEquals("I got a haircut!", secondEntry.getText());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
