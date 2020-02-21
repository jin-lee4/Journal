package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JournalEntryTest {
    private JournalEntry testEntry;
    private LocalDateTime date;
    private String title;

    @BeforeEach
    void RunBefore() {
        date = LocalDateTime.of(2020,2,9,11,54);
        title = "My Birthday";
        testEntry = new JournalEntry(title, date);
    }

    @Test
    void testConstructor() {
        assertEquals("Nothing yet. What's on your mind?", testEntry.getText());
        assertEquals(title, testEntry.title);
        assertEquals(date, testEntry.time);
    }

    @Test
    void testAddText() {
        testEntry.setText("Today was my birthday! It was the best day ever. We had chocolate cake.");
        assertEquals("Today was my birthday! It was the best day ever. We had chocolate cake.",
                testEntry.getText());
    }

    @Test
    void testToString() {
        assertEquals("2020/02/09 11:54 AM", testEntry.dateToString(date));
    }

    @Test
    void testGetText() {
        testEntry.setText("My first entry! What an exciting day.");
        assertEquals("My first entry! What an exciting day.", testEntry.getText());
    }

    @Test
    void testGetTextEntryDNE() {
        assertEquals("Nothing yet. What's on your mind?", testEntry.getText());
    }
}