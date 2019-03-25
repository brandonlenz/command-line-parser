package com.brandonlenz.commandlineparser.validators.formatvalidators;

import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.argument.Command;
import com.brandonlenz.commandlineparser.argument.Prefix;
import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.TextArgumentDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextFormatValidatorTest {
    private static final Prefix DOUBLE_DASH_PREFIX = new Prefix("--");
    private static final Command SAMPLE_TEXT_COMMAND = new Command(DOUBLE_DASH_PREFIX, "name");
    private static final ArgumentDefinition SAMPLE_TEXT_ARGUMENT_DEFINITION =
            new TextArgumentDefinition(SAMPLE_TEXT_COMMAND, 3, 10);

    @Test
    void formatIsValid() {
        Argument argument = new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "validValue");
        ArgumentFormatValidator validator = new TextFormatValidator(SAMPLE_TEXT_ARGUMENT_DEFINITION);
        Assertions.assertTrue(validator.formatIsValid(argument));
    }

    @Test
    void formatIsValidLowerBound() {
        Argument argument = new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "123");
        ArgumentFormatValidator validator = new TextFormatValidator(SAMPLE_TEXT_ARGUMENT_DEFINITION);
        Assertions.assertTrue(validator.formatIsValid(argument));
    }

    @Test
    void formatIsValidUpperBound() {
        Argument argument = new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "1234567890");
        ArgumentFormatValidator validator = new TextFormatValidator(SAMPLE_TEXT_ARGUMENT_DEFINITION);
        Assertions.assertTrue(validator.formatIsValid(argument));
    }

    @Test
    void formatIsInvalidTooShort() {
        Argument argument = new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "12");
        ArgumentFormatValidator validator = new TextFormatValidator(SAMPLE_TEXT_ARGUMENT_DEFINITION);
        Assertions.assertFalse(validator.formatIsValid(argument));
    }

    @Test
    void formatIsInvalidTooLong() {
        Argument argument = new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "12345678901");
        ArgumentFormatValidator validator = new TextFormatValidator(SAMPLE_TEXT_ARGUMENT_DEFINITION);
        Assertions.assertFalse(validator.formatIsValid(argument));
    }

}