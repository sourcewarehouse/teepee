package com.threeheadedmonkey.teepee.parser;

/**
 * Creates a parser based on the provided line format
 */
public class ParserFactory {

    private static ParserFactory instance;

    private final Parser project;
    private final Parser task;
    private final Parser note;

    protected ParserFactory() {
        this.project = new ProjectParser();
        this.task = new TaskParser();
        this.note = new NoteParser();
    }

    protected static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }
        return instance;
    }

    /**
     * Based on the provided line create a Parser
     *
     * @param line The line that will be parsed and therefore determines the Parser returned
     * @return the Parser
     */
    public static Parser getParser(String line) {

        return getInstance().determineParser(line);
    }

    private Parser determineParser(String line) {
        if (isProject(line)) {
            return project;
        } else if (isTask(line)) {
            return task;
        } else if (isNote(line)) {
            return note;
        }

        return null;
    }

    private boolean isProject(String line) {
        return line.matches(project.getPattern().pattern());
    }

    private boolean isTask(String line) {
        return line.matches(task.getPattern().pattern());
    }

    private boolean isNote(String line) {
        return line.matches(note.getPattern().pattern());
    }
}
