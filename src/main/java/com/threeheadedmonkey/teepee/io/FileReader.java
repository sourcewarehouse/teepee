package com.threeheadedmonkey.teepee.io;

import com.threeheadedmonkey.teepee.entity.Item;
import com.threeheadedmonkey.teepee.parser.Parser;
import com.threeheadedmonkey.teepee.parser.ParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Read a TaskPaper file into an entity
 */
public class FileReader {

    private final static Logger log = LoggerFactory.getLogger(FileReader.class);


    /**
     * Read the content from the Reader into a Collection of Items
     *
     * @param content The content to read in, parse and return
     * @return the parsed Collection of Items
     */
    public Collection<Item> read(Reader content) {
        log.debug("Reading content from Reader");
        Scanner lines = new Scanner(content);
        return parseLines(lines);
    }

    /**
     * Read the content from the String into a Collection of Items
     *
     * @param content The content to read in, parse and return
     * @return the parsed Collection of Items
     */
    public Collection<Item> read(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content to parse must have value");
        }
        // Split the file into single lines which can then be parsed
        log.debug("Splitting content of size {} characters", content.length());
        Scanner scanner = new Scanner(content);
        // Parse the lines into a single Collection and return it
        return parseLines(scanner);
    }

    /**
     * Parse each line into a Collection of items
     *
     * @param lines The lines to parse
     * @return the collection of items
     */
    private Collection<Item> parseLines(Scanner lines) {
        log.debug("Parsing lines of content into Items");
        // Split the lines on carriage returns
        List<Item> items = new ArrayList<Item>();
        while (lines.hasNext()) {
            String line = lines.nextLine();
            Item item = parseLine(line);
            log.debug("Parsed line <<{}>> as Item: <<{}>>", line, item);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }

    private Item parseLine(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Line to parse was empty");
        }

        Parser parser = ParserFactory.getParser(line);
        if (parser != null) {
            return parser.parseLine(line);
        }
        return null;
    }

}
