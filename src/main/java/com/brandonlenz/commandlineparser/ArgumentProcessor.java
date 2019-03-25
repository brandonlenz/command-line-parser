package com.brandonlenz.commandlineparser;

import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.parsers.ArgumentParser;
import com.brandonlenz.commandlineparser.validators.ArgumentValidator;
import java.util.List;

class ArgumentProcessor {
    private ArgumentParser argumentParser;
    private ArgumentValidator argumentValidator;

    ArgumentProcessor(Configuration configuration) {
        argumentParser = new ArgumentParser(configuration);
        argumentValidator = new ArgumentValidator(configuration);
    }

    Result parseAndValidate(String line) {
        List<Argument> arguments;
        try {
            arguments = argumentParser.parseArguments(line);
            return argumentValidator.validateArguments(arguments);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Result.SYNTAX_INVALID;
        }
    }
}

