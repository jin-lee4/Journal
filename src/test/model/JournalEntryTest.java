package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JournalEntryTest {
    private JournalEntry testEntry;
    private LocalDateTime date;
    private File testImage;
    private File testImage2;

    @BeforeEach
    void RunBefore() {
        date = LocalDateTime.of(2020,2,9,11,54);
        testEntry = new JournalEntry(date);
        testImage = new File("data/tobs.jpg");
        testImage2 = new File("data/jerms.jpg");
    }

    @Test
    void testConstructor() {
        assertEquals("Nothing yet. What's on your mind?", testEntry.getText());
        assertEquals(date, testEntry.time);
    }

    @Test
    void testAddText() {
        testEntry.setText("Today was my birthday! It was the best day ever. We had chocolate cake.");
        assertEquals("Today was my birthday! It was the best day ever. We had chocolate cake.",
                testEntry.getText());
    }

//    @Test
//    void testChangeText() {
//        testEntry.setText("Today was my birthday! It was the best day ever. We had chocolate cake.");
//        testEntry.editText("Today was my birthday! It was the worst day ever. We had chocolate cake.");
//        assertEquals("Today was my birthday! It was the worst day ever. We had chocolate cake.",
//                testEntry.getText());
//    }
//
//    @Test
//    void testEditAddText() {
//        testEntry.setText("Today was my birthday! It was the best day ever. We had chocolate cake.");
//        testEntry.editText("Today was my birthday! It was the best day ever. We had chocolate cake and milkshakes.");
//        assertEquals("Today was my birthday! It was the best day ever. We had chocolate cake and milkshakes.",
//                testEntry.getText());
//    }
//
//    @Test
//    void testEditDeleteText() {
//        testEntry.setText("Today was my birthday! It was the best day ever. We had chocolate cake.");
//        testEntry.editText("Today was my birthday! It was the best day ever.");
//        assertEquals("Today was my birthday! It was the best day ever.", testEntry.getText());
//    }

    @Test
    void testAddImageSingle() {
        testEntry.addImage(testImage);
        assertEquals(testImage, testEntry.images.get(0));
    }

    @Test
    void testAddImageMultiple() {
        testEntry.addImage(testImage);
        testEntry.addImage(testImage2);
        assertEquals(2, testEntry.images.size());
        assertEquals(testImage, testEntry.images.get(0));
        assertEquals(testImage2, testEntry.images.get(1));
    }

    @Test
    void testRemoveImage() {
        testEntry.addImage(testImage);
        testEntry.deleteImage(testImage);
        assertEquals(0, testEntry.images.size());
    }

    @Test
    void testRemoveImageFromMultipleImages() {
        testEntry.addImage(testImage);
        testEntry.addImage(testImage2);
        testEntry.deleteImage(testImage);
        assertEquals(testImage2, testEntry.images.get(0));
        assertEquals(1, testEntry.images.size());
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