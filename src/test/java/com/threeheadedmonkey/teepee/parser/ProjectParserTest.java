package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.entity.Project;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Unit test for the NoteParser class
 */
public class ProjectParserTest {

    private ProjectParser parser;

    @Before
    public void setup() {
        this.parser = new ProjectParser();
    }

    @Test
    public void testGetPattern() throws Exception {
        Pattern pattern = parser.getPattern();
        assertNotNull(pattern);

        assertTrue(pattern.matcher("Project One:").matches());
        assertFalse(pattern.matcher("Project One").matches());
        assertTrue(pattern.matcher("Project: One:").matches());
        assertFalse(pattern.matcher("Project: One").matches());
        assertTrue(pattern.matcher("\tJava:").matches());
        assertTrue(pattern.matcher("  Java:").matches());
    }

    @Test
    public void testParseLine() throws Exception {
        String line = "This is a project:";
        Project project = parser.parseLine(line);
        assertEquals("This is a project", project.getContent());
        assertEquals(0, project.getLevel());

        line = "  This is a project:";
        project = parser.parseLine(line);
        assertEquals("This is a project", project.getContent());
        assertEquals(1, project.getLevel());

        line = "\t\t  This is a project:";
        project = parser.parseLine(line);
        assertEquals("This is a project", project.getContent());
        assertEquals(3, project.getLevel());
    }
}
