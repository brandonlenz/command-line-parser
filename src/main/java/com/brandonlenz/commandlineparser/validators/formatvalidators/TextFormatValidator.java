package com.brandonlenz.commandlineparser.validators.formatvalidators;

import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.TextArgumentDefinition;

public class TextFormatValidator extends ArgumentFormatValidator implements FormatValidator {

    private final TextArgumentDefinition argumentDefinition;

    public TextFormatValidator(ArgumentDefinition argumentDefinition) {
        if (argumentDefinition instanceof TextArgumentDefinition) {
            this.argumentDefinition = (TextArgumentDefinition) argumentDefinition;
        } else {
            throw new IllegalArgumentException(
                    "Attempted to instantiate a TextFormatValidator with and invalid ArgumentDefinition: " + argumentDefinition);
        }
    }

    @Override
    public boolean formatIsValid(String text) {
        return lengthIsValid(text);
    }

    private boolean lengthIsValid(String name) {
        return name.length() >= argumentDefinition.getMinLength() && name.length() <= argumentDefinition.getMaxLength();
    }
}
