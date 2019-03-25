package com.brandonlenz.commandlineparser;

import com.brandonlenz.commandlineparser.argument.Command;
import com.brandonlenz.commandlineparser.argument.Prefix;
import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.BlankArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.NumericArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.Precedence;
import com.brandonlenz.commandlineparser.argument.definitions.TextArgumentDefinition;
import java.util.HashSet;
import java.util.Set;

/**
 * Design a method that parses an input String, s, such that it validates the arguments passed in.
 * The possible commands are as follows:
 *
 * --count  An Integer between 10 and 100 (inclusive)
 * --name   A String consisting of 3 to 10 characters (inclusive)
 * --help   A command indicating the user is requesting help, but has no value following the command
 *
 *  The method should return an int as follows:
 *  1 if the help command is used and all other arguments are valid
 *  0 if all arguments are valid
 *  -1 if there are invalid arguments
 *
 * Rules:
 * - Arguments are optional, but at least one valid command must be present
 * - Arguments are evaluated in order
 * - The help argument takes precedence
 * - Arguments are case insensitive
 * - "Extra" whitespace is to be ignored
 *
 */
class Solution {
    int solution (String line) {
        Configuration solutionConfiguration = getSolutionConfiguration();
        ArgumentProcessor processor = new ArgumentProcessor(solutionConfiguration);
        return processor.parseAndValidate(line).intValue();
    }

    private Configuration getSolutionConfiguration() {
        int minimumNumberOfArguments = 1;
        Set<ArgumentDefinition> argumentDefinitions = getSolutionArgumentDefinitions();
        return new Configuration(argumentDefinitions, minimumNumberOfArguments);
    }

    private Set<ArgumentDefinition> getSolutionArgumentDefinitions() {
        Prefix doubleDashPrefix = new Prefix("--");
        Set<ArgumentDefinition> argumentDefinitions = new HashSet<>();

        argumentDefinitions.add(
                new NumericArgumentDefinition(new Command(doubleDashPrefix, "count"), 10, 100));
        argumentDefinitions.add(
                new TextArgumentDefinition(new Command(doubleDashPrefix, "name"), 3, 10));
        argumentDefinitions.add(
                new BlankArgumentDefinition(new Command(doubleDashPrefix, "help", Precedence.HIGH)));

        return argumentDefinitions;
    }
}
