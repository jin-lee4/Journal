package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JournalEntryTest {
    private JournalEntry testEntry;
    private LocalDateTime date;
    private File image;

    @BeforeEach
    void RunBefore() {
        date = LocalDateTime.of(2020,2,9,11,54);
        testEntry = new JournalEntry(date);
        image = new File("data/tobs.jpg");
    }

    @Test
    void testConstructor() {
        assertEquals("Nothing yet. What's on your mind?", testEntry.getText());
        assertEquals(date, testEntry.time);
    }

    @Test
    void testAddText() {
        testEntry.addText("Today was my birthday! It was the best day ever. We had chocolate cake.");
        assertEquals("Today was my birthday! It was the best day ever. We had chocolate cake.",
                testEntry.getText());
    }

    @Test
    void testChangeText() {
        testEntry.addText("Today was my birthday! It was the best day ever. We had chocolate cake.");
        testEntry.editText("Today was my birthday! It was the worst day ever. We had chocolate cake.");
        assertEquals("Today was my birthday! It was the worst day ever. We had chocolate cake.",
                testEntry.getText());
    }

    @Test
    void testEditAddText() {
        testEntry.addText("Today was my birthday! It was the best day ever. We had chocolate cake.");
        testEntry.editText("Today was my birthday! It was the best day ever. We had chocolate cake and milkshakes.");
        assertEquals("Today was my birthday! It was the best day ever. We had chocolate cake and milkshakes.",
                testEntry.getText());
    }

    @Test
    void testEditDeleteText() {
        testEntry.addText("Today was my birthday! It was the best day ever. We had chocolate cake.");
        testEntry.editText("Today was my birthday! It was the best day ever.");
        assertEquals("Today was my birthday! It was the best day ever.", testEntry.getText());
    }

    @Test
    void testAddImage() {
        testEntry.addImage(image);
        assertEquals(image, testEntry.getImage());
    }

    @Test
    void testRemoveImage() {
        testEntry.addImage(image);
        testEntry.deleteImage(image);
        assertNull(testEntry.getImage());
    }

    @Test
    void testToString() {
        assertEquals("2020-02-9 9:11:54", testEntry.toString(date));
    }

    @Test
    void testGetText() {
        testEntry.addText("My first entry! What an exciting day.");
        assertEquals("My first entry! What an exciting day.", testEntry.getText());
    }

    @Test
    void testGetTextEntryDNE() {
        assertEquals("Nothing yet. What's on your mind?", testEntry.getText());
    }
}