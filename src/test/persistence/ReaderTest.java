package persistence;

import model.JournalEntry;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    void testParseJournalFile1() {
        try {
            List<JournalEntry> entries = Reader.readEntries(new File("./data/testJournal1.txt"));
            JournalEntry firstEntry = entries.get(0);
            assertEquals("Monday", firstEntry.title);
            assertEquals("2020/02/09 11:54 AM", firstEntry.dateToString(firstEntry.time));
            assertEquals("Mondays are my least favourite day! They are so tiring and boring. " +
                    "All of the classes I hate are on Mondays.", firstEntry.getText());

            JournalEntry secondEntry = entries.get(1);
            assertEquals("Mom’s Birthday", secondEntry.title);
            assertEquals("2020/02/11 08:33 PM", secondEntry.dateToString(secondEntry.time));
            assertEquals("For mom’s birthday we threw her a surprise party. She was so surprised and " +
                    "I think her favourite part was the chocolate cake.", secondEntry.getText());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    void testIOException() {
        try {
            Reader.readEntries(new File("./path/doesnt/exist/testEntry.txt"));
        } catch (IOException e) {

        }
        // expected
    }
}
