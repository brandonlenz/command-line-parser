package com.brandonlenz.commandlineparser.validators.formatvalidators;

import com.brandonlenz.commandlineparser.Configuration;
import com.brandonlenz.commandlineparser.argument.Prefix;
import java.util.Set;

public class LineFormatValidator implements FormatValidator {

    private Set<Prefix> allowedPrefixes;

    public LineFormatValidator(Configuration configuration) {
        this.allowedPrefixes = configuration.getAllowedPrefixes();
    }

    @Override
    public boolean formatIsValid(String statement) {
        //Validate prefix placement
        return prefixPlacementIsValid(statement);
    }

    private boolean prefixPlacementIsValid(String statement) {
        for (Prefix prefix : allowedPrefixes) {
            if (containsPrefixFollowedByWhiteSpace(statement, prefix)){
                return false;
            }

            if (endsWithPrefix(statement, prefix)) {
                return false;
            }
        }

        return true;
    }

    private boolean containsPrefixFollowedByWhiteSpace(String statement, Prefix prefix) {
        return statement.contains(prefix.toString() + " ");
    }

    private boolean endsWithPrefix(String statement, Prefix prefix) {
        return statement.endsWith(prefix.toString());
    }
}
