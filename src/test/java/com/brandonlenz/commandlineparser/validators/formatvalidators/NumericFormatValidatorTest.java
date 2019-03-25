package com.brandonlenz.commandlineparser.validators.formatvalidators;

import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.argument.Command;
import com.brandonlenz.commandlineparser.argument.Prefix;
import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.NumericArgumentDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumericFormatValidatorTest {
    private static final Prefix DOUBLE_DASH_PREFIX = new Prefix("--");
    private static final Command SAMPLE_NUMERIC_COMMAND = new Command(DOUBLE_DASH_PREFIX, "count");
    private static final ArgumentDefinition SAMPLE_NUMERIC_ARGUMENT_DEFINITION =
            new NumericArgumentDefinition(SAMPLE_NUMERIC_COMMAND, 10, 100);

    @Test
    void formatIsValid() {
        Argument argument = new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "50");
        ArgumentFormatValidator validator = new NumericFormatValidator(SAMPLE_NUMERIC_ARGUMENT_DEFINITION);
        Assertions.assertTrue(validator.formatIsValid(argument));
    }

    @Test
    void formatIsValidLowerBound() {
        Argument argument = new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "10");
        ArgumentFormatValidator validator = new NumericFormatValidator(SAMPLE_NUMERIC_ARGUMENT_DEFINITION);
        Assertions.assertTrue(validator.formatIsValid(argument));
    }

    @Test
    void formatIsValidUpperBound() {
        Argument argument = new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "100");
        ArgumentFormatValidator validator = new NumericFormatValidator(SAMPLE_NUMERIC_ARGUMENT_DEFINITION);
        Assertions.assertTrue(validator.formatIsValid(argument));
    }

    @Test
    void formatIsInvalidTooLow() {
        Argument argument = new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "9");
        ArgumentFormatValidator validator = new NumericFormatValidator(SAMPLE_NUMERIC_ARGUMENT_DEFINITION);
        Assertions.assertFalse(validator.formatIsValid(argument));
    }

    @Test
    void formatIsInvalidTooHigh() {
        Argument argument = new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "101");
        ArgumentFormatValidator validator = new NumericFormatValidator(SAMPLE_NUMERIC_ARGUMENT_DEFINITION);
        Assertions.assertFalse(validator.formatIsValid(argument));
    }
}