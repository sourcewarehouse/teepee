package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.entity.Item;

import java.util.regex.Pattern;

/**
 * Parse a line from a TaskPaper file
 */
public interface Parser<T extends Item> {

    /**
     * Get the pattern which this parser uses
     *
     * @return the pattern
     */
    public Pattern getPattern();

    /**
     * Parse a line into its constituent parts as an Item
     *
     * @param line to be parsed
     * @return the populated Item
     */
    public T parseLine(String line);
}
