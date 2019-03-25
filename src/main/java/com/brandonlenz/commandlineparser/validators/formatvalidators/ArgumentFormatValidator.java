package com.brandonlenz.commandlineparser.validators.formatvalidators;

import com.brandonlenz.commandlineparser.argument.Argument;

public abstract class ArgumentFormatValidator implements FormatValidator {

    public boolean formatIsValid(Argument argument) {
        return formatIsValid(argument.getValue());
    }
}
