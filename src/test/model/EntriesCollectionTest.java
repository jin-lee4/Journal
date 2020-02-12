package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntriesCollectionTest {
    private EntriesCollection collection;
    private JournalEntry testFirstEntry;
    private JournalEntry testSecondEntry;
    private JournalEntry testThirdEntry;
    private LocalDateTime firstDate;
    private LocalDateTime secondDate;
    private LocalDateTime thirdDate;

    @BeforeEach
    void RunBefore() {
        collection = new EntriesCollection();
        firstDate = LocalDateTime.of(2020, 2, 1, 9, 18);
        secondDate = LocalDateTime.of(2020, 2, 18, 18, 7);
        thirdDate = LocalDateTime.of(2020, 2, 18, 13, 48);
        testFirstEntry = new JournalEntry(firstDate);
        testSecondEntry = new JournalEntry(secondDate);
        testThirdEntry = new JournalEntry(thirdDate);
    }

    @Test
    void testConstructor() {
        assertEquals(0, collection.getNumEntries());
    }

    @Test
    void testAddOneEntry() {
        collection.addEntry(testFirstEntry);
        assertEquals(1, collection.getNumEntries());
        assertEquals("2020/02/01 09:18 AM" + "\n", collection.listAllEntries());
    }

    @Test
    void testAddMultipleEntries() {
        collection.addEntry(testFirstEntry);
        collection.addEntry(testSecondEntry);
        assertEquals(2, collection.getNumEntries());
        assertEquals("2020/02/18 06:07 PM" + "\n" + "2020/02/01 09:18 AM" + "\n", collection.listAllEntries());
    }

    @Test
    void testDeleteEntry() {
        collection.addEntry(testFirstEntry);
        collection.addEntry(testSecondEntry);
        assertEquals(2, collection.getNumEntries());
        collection.deleteEntry(testSecondEntry);
        assertEquals(1, collection.getNumEntries());
        assertEquals("2020/02/01 09:18 AM" + "\n", collection.listAllEntries());
    }

    @Test
    void testViewEntry() {
        collection.addEntry(testFirstEntry);
        testFirstEntry.setText("Today is my birthday, hooray!");
        assertEquals("\n\n2020/02/01 09:18 AM" + "\n" + "Today is my birthday, hooray!",
                collection.viewEntry(firstDate.toLocalDate()));
    }

    @Test
    void testViewEntryBiggerList() {
        collection.addEntry(testFirstEntry);
        collection.addEntry(testThirdEntry);
        testFirstEntry.setText("Today is my birthday, hooray!");
        testThirdEntry.setText("I broke my leg today.");
        assertEquals("\n\n2020/02/18 01:48 PM" + "\n" + "I broke my leg today.",
                collection.viewEntry(thirdDate.toLocalDate()));
    }

    @Test
    void testViewEntryDNE() {
        assertEquals("Entry cannot be found.",
                collection.viewEntry(LocalDate.of(2020, 2, 3)));
    }

    @Test
    void testListSingleEntry() {
        collection.addEntry(testSecondEntry);
        assertEquals("2020/02/18 06:07 PM" + "\n", collection.listAllEntries());
    }

    @Test
    void testListAllEntries() {
        collection.addEntry(testFirstEntry);
        collection.addEntry(testSecondEntry);
        assertEquals("2020/02/18 06:07 PM" + "\n" + "2020/02/01 09:18 AM" + "\n", collection.listAllEntries());
    }

    @Test
    void testListAllEntriesSameDay() {
        collection.addEntry(testSecondEntry);
        collection.addEntry(testThirdEntry);
        assertEquals("2020/02/18 06:07 PM" + "\n" + "2020/02/18 01:48 PM" + "\n", collection.listAllEntries());
    }

}
