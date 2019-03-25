package com.brandonlenz.commandlineparser.validators.formatvalidators;

import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.argument.Command;
import com.brandonlenz.commandlineparser.argument.Prefix;
import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.BlankArgumentDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BlankFormatValidatorTest {
    private static final Prefix DOUBLE_DASH_PREFIX = new Prefix("--");
    private static final Command SAMPLE_BLANK_COMMAND = new Command(DOUBLE_DASH_PREFIX, "help");
    private static final ArgumentDefinition SAMPLE_BLANK_ARGUMENT_DEFINITION =
            new BlankArgumentDefinition(SAMPLE_BLANK_COMMAND);

    @Test
    void formatIsValid() {
        Argument argument = new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, "");
        ArgumentFormatValidator validator = new BlankFormatValidator(SAMPLE_BLANK_ARGUMENT_DEFINITION);
        Assertions.assertTrue(validator.formatIsValid(argument));
    }

    @Test
    void formatIsInvalid() {
        Argument argument = new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, "not empty");
        ArgumentFormatValidator validator = new BlankFormatValidator(SAMPLE_BLANK_ARGUMENT_DEFINITION);
        Assertions.assertFalse(validator.formatIsValid(argument));
    }
}