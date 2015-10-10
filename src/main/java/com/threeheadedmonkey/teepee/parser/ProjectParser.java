package com.threeheadedmonkey.teepee.parser;

import com.threeheadedmonkey.teepee.entity.Project;

import java.util.regex.Pattern;

/**
 * Parse a Project line
 */
public class ProjectParser extends ItemParser<Project> {

    @Override
    public Pattern getPattern() {
        return Pattern.compile("^.+:$");
    }

    @Override
    public Project parseLine(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }

        // Get the indent level from the first whitespace
        int level = parseLevelFromStartingWhitespace(line);

        Project project = new Project(line.substring(0, line.length() - 1).trim());
        project.setLevel(level);

        return project;
    }
}
