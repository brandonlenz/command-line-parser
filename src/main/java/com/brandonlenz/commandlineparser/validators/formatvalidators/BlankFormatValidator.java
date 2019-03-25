package com.brandonlenz.commandlineparser.validators.formatvalidators;

import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.BlankArgumentDefinition;

public class BlankFormatValidator extends ArgumentFormatValidator implements FormatValidator {

    private final BlankArgumentDefinition argumentDefinition;


    public BlankFormatValidator(ArgumentDefinition argumentDefinition) {
        if (argumentDefinition instanceof BlankArgumentDefinition) {
            this.argumentDefinition = (BlankArgumentDefinition) argumentDefinition;
        } else {
            throw new IllegalArgumentException(
                    "Attempted to instantiate a BlankFormatValidator with and invalid ArgumentDefinition: " + argumentDefinition);
        }
    }

    @Override
    public boolean formatIsValid(String argumentValue) {
        return argumentValue.isEmpty();
    }
}
