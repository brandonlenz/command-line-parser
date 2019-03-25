package com.brandonlenz.commandlineparser;

import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.argument.Command;
import com.brandonlenz.commandlineparser.argument.Prefix;
import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.BlankArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.NumericArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.TextArgumentDefinition;
import com.brandonlenz.commandlineparser.parsers.ArgumentParser;
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
class ArgumentParserTest {

    private ArgumentParser argumentParser;
    private static final Prefix DOUBLE_DASH_PREFIX = new Prefix("--");
    private static final Prefix SINGLE_DASH_PREFIX = new Prefix("-");

    private static final Command SAMPLE_NUMERIC_COMMAND = new Command(DOUBLE_DASH_PREFIX, "count");
    private static final Command SAMPLE_TEXT_COMMAND = new Command(DOUBLE_DASH_PREFIX, "name");
    private static final Command SAMPLE_BLANK_COMMAND = new Command(SINGLE_DASH_PREFIX, "help");

    private static final ArgumentDefinition SAMPLE_NUMERIC_ARGUMENT_DEFINITION =
            new NumericArgumentDefinition(SAMPLE_NUMERIC_COMMAND, 10, 100);
    private final ArgumentDefinition SAMPLE_TEXT_ARGUMENT_DEFINITION =
            new TextArgumentDefinition(SAMPLE_TEXT_COMMAND, 3, 10);
    private final ArgumentDefinition SAMPLE_BLANK_ARGUMENT_DEFINITION =
            new BlankArgumentDefinition(SAMPLE_BLANK_COMMAND);

    @BeforeAll
    void setUp() {
        Set<ArgumentDefinition> argumentDefinitions = new HashSet<>();
        argumentDefinitions.add(SAMPLE_NUMERIC_ARGUMENT_DEFINITION);
        argumentDefinitions.add(SAMPLE_TEXT_ARGUMENT_DEFINITION);
        argumentDefinitions.add(SAMPLE_BLANK_ARGUMENT_DEFINITION);
        Configuration configuration = new Configuration(argumentDefinitions);

        argumentParser = new ArgumentParser(configuration);
    }

    @Test
    void getArgumentsInvalidCommand() {
        String statement = "--fakeCommand" + "fakeValue";

        Assertions.assertThrows(IllegalArgumentException.class, () -> argumentParser.parseArguments(statement));
    }

    @Test
    void getArgumentsOnlyHelp() {
        String statement = SAMPLE_BLANK_COMMAND.toString();
        List<Argument> expected = new ArrayList<>();
        expected.add(new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, ""));

        List<Argument> actual = argumentParser.parseArguments(statement);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getArgumentsMultipleAllValid() {
        StringBuilder sb = new StringBuilder();
        sb.append(SAMPLE_BLANK_COMMAND);
        sb.append(SAMPLE_NUMERIC_COMMAND).append(" 11");
        sb.append(SAMPLE_TEXT_COMMAND).append(" Brandon");

        List<Argument> expected = new ArrayList<>();
        expected.add(new Argument(SAMPLE_BLANK_ARGUMENT_DEFINITION, ""));
        expected.add(new Argument(SAMPLE_NUMERIC_ARGUMENT_DEFINITION, "11"));
        expected.add(new Argument(SAMPLE_TEXT_ARGUMENT_DEFINITION, "Brandon"));

        List<Argument> actual = argumentParser.parseArguments(sb.toString());

        Assertions.assertEquals(expected, actual);
    }
}