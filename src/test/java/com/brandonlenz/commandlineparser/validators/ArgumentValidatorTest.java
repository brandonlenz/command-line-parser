package com.brandonlenz.commandlineparser.validators;

import com.brandonlenz.commandlineparser.Configuration;
import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.argument.Command;
import com.brandonlenz.commandlineparser.argument.Prefix;
import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.BlankArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.NumericArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.Precedence;
import com.brandonlenz.commandlineparser.argument.definitions.TextArgumentDefinition;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class ArgumentValidatorTest {

    private ArgumentValidator argumentValidator;
    private static final Prefix DOUBLE_DASH_PREFIX = new Prefix("--");
    private static final Prefix SINGLE_DASH_PREFIX = new Prefix("-");

    private static final Command SAMPLE_NUMERIC_COMMAND = new Command(DOUBLE_DASH_PREFIX, "count");
    private static final Command SAMPLE_TEXT_COMMAND = new Command(DOUBLE_DASH_PREFIX, "name");
    private static final Command SAMPLE_BLANK_COMMAND = new Command(SINGLE_DASH_PREFIX, "help");

    private static final Command SAMPLE_NUMERIC_COMMAND_WITH_HIGH_PRECEDENCE =
            new Command(DOUBLE_DASH_PREFIX, "count", Precedence.HIGH);
    private static final Command SAMPLE_TEXT_COMMAND_WITH_HIGH_PRECEDENCE =
            new Command(DOUBLE_DASH_PREFIX, "name", Precedence.HIGH);
    private static final Command SAMPLE_BLANK_COMMAND_WITH_HIGH_PRECEDENCE =
            new Command(SINGLE_DASH_PREFIX, "help", Precedence.HIGH);

    private static final ArgumentDefinition SAMPLE_NUMERIC_ARGUMENT_DEFINITION =
            new NumericArgumentDefinition(SAMPLE_NUMERIC_COMMAND, 10, 100);
    private final ArgumentDefinition SAMPLE_TEXT_ARGUMENT_DEFINITION =
            new TextArgumentDefinition(SAMPLE_TEXT_COMMAND, 3, 10);
    private final ArgumentDefinition SAMPLE_BLANK_ARGUMENT_DEFINITION =
            new BlankArgumentDefinition(SAMPLE_BLANK_COMMAND);

    private static final ArgumentDefinition SAMPLE_NUMERIC_ARGUMENT_WITH_HIGH_PRECEDENCE_DEFINITION =
            new NumericArgumentDefinition(SAMPLE_NUMERIC_COMMAND_WITH_HIGH_PRECEDENCE, 10, 100);
    private final ArgumentDefinition SAMPLE_TEXT_ARGUMENT_WITH_HIGH_PRECEDENCE_DEFINITION =
            new TextArgumentDefinition(SAMPLE_TEXT_COMMAND_WITH_HIGH_PRECEDENCE, 3, 10);
    private final ArgumentDefinition SAMPLE_BLANK_ARGUMENT_WITH_HIGH_PRECEDENCE_DEFINITION =
            new BlankArgumentDefinition(SAMPLE_BLANK_COMMAND_WITH_HIGH_PRECEDENCE);

    @BeforeAll
    void setUp() {
        Set<ArgumentDefinition> argumentDefinitions = new HashSet<>();
        argumentDefinitions.add(SAMPLE_NUMERIC_ARGUMENT_DEFINITION);
        argumentDefinitions.add(SAMPLE_TEXT_ARGUMENT_DEFINITION);
        argumentDefinitions.add(SAMPLE_BLANK_ARGUMENT_DEFINITION);
        Configuration configuration = new Configuration(argumentDefinitions);

        argumentValidator = new ArgumentValidator(configuration);
    }

    @Test
    void validateArgumentsAllValid() {
            List<Argument> arguments = new ArrayList<>();
            arguments.add(new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, ""));
            arguments.add(new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "Brandon"));
            arguments.add(new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "11"));

            Assertions.assertEquals(0, argumentValidator.validateArguments(arguments).intValue());
    }

    @Test
    void validateArgumentsAllValidWithOneHighPrecedence() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument(SAMPLE_BLANK_ARGUMENT_WITH_HIGH_PRECEDENCE_DEFINITION, ""));
        arguments.add(new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "Brandon"));
        arguments.add(new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "11"));

        Assertions.assertEquals(1, argumentValidator.validateArguments(arguments).intValue());
    }

    @Test
    void validateArgumentsAllValidWithMultipleHighPrecedence() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument(SAMPLE_BLANK_ARGUMENT_WITH_HIGH_PRECEDENCE_DEFINITION, ""));
        arguments.add(new Argument(SAMPLE_TEXT_ARGUMENT_WITH_HIGH_PRECEDENCE_DEFINITION, "Brandon"));
        arguments.add(new Argument(SAMPLE_NUMERIC_ARGUMENT_WITH_HIGH_PRECEDENCE_DEFINITION, "11"));

        Assertions.assertEquals(-1, argumentValidator.validateArguments(arguments).intValue());
    }

    @Test
    void validateArgumentsAllInvalid() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, "notEmpty"));
        arguments.add(new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "BrandonLongName"));
        arguments.add(new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "1"));

        Assertions.assertEquals(-1, argumentValidator.validateArguments(arguments).intValue());
    }

    @Test
    void validateArgumentsOneInvalid() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, "notEmpty"));
        arguments.add(new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "Brandon"));
        arguments.add(new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "11"));

        Assertions.assertEquals(-1, argumentValidator.validateArguments(arguments).intValue());
    }

    @Test
    void validateArgumentsRepeatedCommands() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, ""));
        arguments.add(new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, ""));
        arguments.add(new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "11"));

        Assertions.assertEquals(-1, argumentValidator.validateArguments(arguments).intValue());
    }
}