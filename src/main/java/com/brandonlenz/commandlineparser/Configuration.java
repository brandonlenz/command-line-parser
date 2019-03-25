package com.brandonlenz.commandlineparser;

import com.brandonlenz.commandlineparser.argument.Prefix;
import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import java.util.HashSet;
import java.util.Set;

public class Configuration {

    private int minimumNumberOfArguments;
    private Set<ArgumentDefinition> argumentDefinitions;

    public Configuration(Set<ArgumentDefinition> argumentDefinitions) {
        this.argumentDefinitions = argumentDefinitions;
        this.minimumNumberOfArguments = 0;
    }

    public Configuration(Set<ArgumentDefinition> argumentDefinitions, int minimumNumberOfArguments) {
        this.argumentDefinitions = argumentDefinitions;
        this.minimumNumberOfArguments = minimumNumberOfArguments;
    }

    public Set<Prefix> getAllowedPrefixes() {
        Set<Prefix> allowedPrefixes = new HashSet<>();

        for (ArgumentDefinition argumentDefinition : argumentDefinitions) {
            allowedPrefixes.add(argumentDefinition.getCommand().getPrefix());
        }

        return allowedPrefixes;
    }

    public Set<ArgumentDefinition> getArgumentDefinitions() {
        return argumentDefinitions;
    }

    public int getMinimumNumberOfArguments() {
        return minimumNumberOfArguments;
    }
}
