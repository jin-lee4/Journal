package model;

import exceptions.NoEntryFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        testFirstEntry = new JournalEntry("Birthday", firstDate);
        testSecondEntry = new JournalEntry("First Day of School", secondDate);
        testThirdEntry = new JournalEntry("Rainy Day", thirdDate);
    }

    @Test
    void testConstructor() {
        assertEquals(0, collection.getNumEntries());
    }

    @Test
    void testAddOneEntry() {
        collection.addEntry(testFirstEntry);
        assertEquals(1, collection.getNumEntries());
        assertEquals("2020/02/01 09:18 AM Birthday\n", collection.listAllEntries());
    }

    @Test
    void testAddMultipleEntries() {
        collection.addEntry(testFirstEntry);
        collection.addEntry(testSecondEntry);
        assertEquals(2, collection.getNumEntries());
        assertEquals( "2020/02/01 09:18 AM Birthday\n" + "2020/02/18 06:07 PM First Day of School\n", collection.listAllEntries());
    }

    @Test
    void testDeleteEntry() {
        collection.addEntry(testFirstEntry);
        collection.addEntry(testSecondEntry);
        assertEquals(2, collection.getNumEntries());
        collection.deleteEntry(testSecondEntry);
        assertEquals(1, collection.getNumEntries());
        assertEquals("2020/02/01 09:18 AM Birthday\n", collection.listAllEntries());
    }

    @Test
    void testViewEntryFound() {
        collection.addEntry(testFirstEntry);
        testFirstEntry.setText("Today is my birthday, hooray!");
        try {
            collection.viewEntry(testFirstEntry.title);
        } catch (NoEntryFoundException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void testViewEntryBiggerListFound() {
        collection.addEntry(testFirstEntry);
        collection.addEntry(testThirdEntry);
        testFirstEntry.setText("Today is my birthday, hooray!");
        testThirdEntry.setText("I broke my leg today.");
        try {
            collection.viewEntry(testThirdEntry.title);
        } catch (NoEntryFoundException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void testViewEntryNoEntryFoundException() {
        try {
            collection.viewEntry("Sunday");
        } catch (NoEntryFoundException e) {
            // nothing happens
        }
    }

    @Test
    void testListSingleEntry() {
        collection.addEntry(testSecondEntry);
        assertEquals("2020/02/18 06:07 PM First Day of School\n", collection.listAllEntries());
    }

    @Test
    void testListAllEntries() {
        collection.addEntry(testFirstEntry);
        collection.addEntry(testSecondEntry);
        assertEquals( "2020/02/01 09:18 AM Birthday\n" + "2020/02/18 06:07 PM First Day of School\n",
                collection.listAllEntries());
    }

    @Test
     void testListAllEntriesSameDay() {
        collection.addEntry(testSecondEntry);
        collection.addEntry(testThirdEntry);
        assertEquals("2020/02/18 06:07 PM First Day of School" + "\n" + "2020/02/18 01:48 PM Rainy Day\n",
                collection.listAllEntries());
    }

}
