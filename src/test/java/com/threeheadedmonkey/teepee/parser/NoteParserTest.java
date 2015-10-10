package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.entity.Note;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Unit test for the NoteParser class
 */
public class NoteParserTest {

    private NoteParser parser;

    @Before
    public void setup() {
        this.parser = new NoteParser();
    }

    @Test
    public void testGetPattern() throws Exception {
        Pattern pattern = parser.getPattern();
        assertNotNull(pattern);

        assertTrue(pattern.matcher("This is a note").matches());
        assertTrue(pattern.matcher("  This is a note").matches());
        assertTrue(pattern.matcher("\t\t  This is a note").matches());
        assertTrue(pattern.matcher("\tThis is a note").matches());
        assertFalse(pattern.matcher("This is not a note:").matches());
        assertFalse(pattern.matcher("  This is not a note:").matches());
        assertFalse(pattern.matcher("\tThis is not a note:").matches());
    }

    @Test
    public void testParseLine() throws Exception {
        String line = "This is a note";
        Note note = parser.parseLine(line);
        assertEquals(line, note.getContent());
        assertEquals(0, note.getLevel());

        line = "  This is a note";
        note = parser.parseLine(line);
        assertEquals("This is a note", note.getContent());
        assertEquals(1, note.getLevel());

        line = "\t\t  This is a note";
        note = parser.parseLine(line);
        assertEquals("This is a note", note.getContent());
        assertEquals(3, note.getLevel());

    }
}
