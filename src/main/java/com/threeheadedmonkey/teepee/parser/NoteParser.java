package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.entity.Note;

import java.util.regex.Pattern;

/**
 * Parse a note line
 */
public class NoteParser extends ItemParser<Note> {

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^.*[^:]$");
    }

    @Override
    public Note parseLine(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }

        // Get the indent level from the first whitespace
        int level = parseLevelFromStartingWhitespace(line);

        // Get the content from the line
        String content = line.trim();
        Note note = new Note(content);
        note.setLevel(level);

        return note;
    }
}
