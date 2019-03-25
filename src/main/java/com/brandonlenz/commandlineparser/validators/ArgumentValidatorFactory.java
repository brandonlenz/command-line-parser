package com.brandonlenz.commandlineparser.validators;

import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.validators.formatvalidators.ArgumentFormatValidator;
import com.brandonlenz.commandlineparser.validators.formatvalidators.BlankFormatValidator;
import com.brandonlenz.commandlineparser.validators.formatvalidators.NumericFormatValidator;
import com.brandonlenz.commandlineparser.validators.formatvalidators.TextFormatValidator;

class ArgumentValidatorFactory {

    ArgumentFormatValidator getValidator(Argument argument) {
        switch (argument.getDefinition().getFormat()) {
            case BLANK:
                return new BlankFormatValidator(argument.getDefinition());

            case NUMERIC:
                return new NumericFormatValidator(argument.getDefinition());

            case TEXT:
                return new TextFormatValidator(argument.getDefinition());

            default:
                throw new IllegalArgumentException(
                        "No validator for Format: " + argument.getDefinition().getFormat() + " exists.");
        }
    }
}