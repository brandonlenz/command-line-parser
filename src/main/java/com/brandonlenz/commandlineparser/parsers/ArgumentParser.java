package com.brandonlenz.commandlineparser.parsers;

import com.brandonlenz.commandlineparser.Configuration;
import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.validators.formatvalidators.FormatValidator;
import com.brandonlenz.commandlineparser.validators.formatvalidators.LineFormatValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ArgumentParser {

    private Configuration configuration;

    public ArgumentParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<Argument> parseArguments(String line) {
        FormatValidator lineFormatValidator = new LineFormatValidator(configuration);
        if (!lineFormatValidator.formatIsValid(line)) {
            throw new IllegalArgumentException(
                    "line is improperly formatted: " + line);
        }

        String[] splitLine = splitLineByConfiguredPrefixes(line); //splitLine[0] would be any potential invocation before prefixed arguments
        String[] potentialArguments = Arrays.copyOfRange(splitLine, 1, splitLine.length);

        List<Argument> arguments = new ArrayList<>();
        for (String potentialArgument : potentialArguments) {
            arguments.add(parseArgument(potentialArgument));
        }

        return arguments;
    }

    private String[] splitLineByConfiguredPrefixes(String line) {
        Pattern pattern = getPrefixPattern();
        return pattern.split(line);
    }

    private Pattern getPrefixPattern() {
        String regex = configuration.getAllowedPrefixes().stream()
                .map(p -> wrapAndEscape(p.toString()))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.joining("|"));
        return Pattern.compile(regex);
    }

    private String wrapAndEscape(String prefixString) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (char character : prefixString.toCharArray()) {
            sb.append("\\").append(character);
        }
        sb.append(")");

        return sb.toString();
    }

    private Argument parseArgument(String potentialArgument) {
        ArgumentDefinition argumentDefinition = getArgumentDefinition(potentialArgument);
        String value = sanitizeValue(parseValue(argumentDefinition, potentialArgument));
        return new Argument(argumentDefinition, value);
    }

    private ArgumentDefinition getArgumentDefinition(String potentialArgument) {
        return configuration.getArgumentDefinitions().stream()
                .filter(ad -> potentialArgument.toLowerCase().startsWith(ad.getCommand().getText()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No definition found for potential argument: " + potentialArgument));
    }

    private String parseValue(ArgumentDefinition definition, String potentialArgument) {
        return potentialArgument.substring(definition.getCommand().getText().length(), potentialArgument.length());
    }

    private String sanitizeValue(String rawValue) {
        //remove leading and trailing spaces only for now.
        return rawValue.trim();
    }
}
