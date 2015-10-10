package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.entity.Item;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract parser which operates on lines to determine their indent level
 */
public abstract class ItemParser<T extends Item> implements Parser {

    /**
     * Parse the indent level from the first group of the Matcher
     *
     * @param indent contains the text to get a level from
     * @return the indent level or 0 if none could be parsed
     */
    protected int parseLevel(String indent) {
        int level = 0;
        if (indent.length() > 0) {
            indent = indent.replace("\t", "  ");
            level = indent.length() / 2;
        }
        return level;
    }

    /**
     * Get the starting whitespace from the line and determine the level from it
     *
     * @param line the line to grab whitespace and level from
     * @return the index level or 0 if none could be parsed
     */
    protected int parseLevelFromStartingWhitespace(String line) {
        int level = 0;
        Pattern whitespacePattern = Pattern.compile("^(\\s+)[^\\s]");
        Matcher whitespaceMatch = whitespacePattern.matcher(line);
        if (whitespaceMatch.find()) {
            String indent = whitespaceMatch.group(1);
            level = parseLevel(indent);
        }
        return level;
    }

}
