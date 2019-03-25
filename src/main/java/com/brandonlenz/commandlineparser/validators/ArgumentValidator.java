package com.brandonlenz.commandlineparser.validators;

import com.brandonlenz.commandlineparser.Configuration;
import com.brandonlenz.commandlineparser.Result;
import com.brandonlenz.commandlineparser.argument.Argument;
import com.brandonlenz.commandlineparser.argument.definitions.Precedence;
import com.brandonlenz.commandlineparser.validators.formatvalidators.ArgumentFormatValidator;
import java.util.List;

public class ArgumentValidator {
    private Configuration configuration;

    public ArgumentValidator(Configuration configuration) {
        this.configuration = configuration;
    }

    public Result validateArguments(List<Argument> arguments) {
        if (minimumNumberOfArgumentsNotPresent(arguments)
                || multipleArgumentsWithSameCommandPresent(arguments)
                || multipleArgumentsWithHighPrecedenceCommandPresent(arguments)) {
            return Result.SYNTAX_INVALID;
        } else {
            return validate(arguments);
        }
    }

    private Result validate(List<Argument> arguments) {
        ArgumentValidatorFactory validatorFactory = new ArgumentValidatorFactory();
        for (Argument argument : arguments) {
            ArgumentFormatValidator validator = validatorFactory.getValidator(argument);
            boolean validationResult = validator.formatIsValid(argument);
            argument.setIsValid(validationResult);
        }

        boolean allArgumentsValid = allArgumentsValid(arguments);
        boolean precedenceCommandPresent = singlePrecedenceCommandPresent(arguments);

        if (precedenceCommandPresent && allArgumentsValid) {
            return Result.PRECEDENCE_COMMAND_PRESENT;
        } else if (allArgumentsValid) {
            return Result.SYNTAX_VALID;
        } else {
            return Result.SYNTAX_INVALID;
        }
    }

    private boolean minimumNumberOfArgumentsNotPresent(List<Argument> arguments) {
        return arguments.size() < configuration.getMinimumNumberOfArguments();
    }

    private boolean multipleArgumentsWithSameCommandPresent(List<Argument> arguments) {
        return arguments.stream().map(Argument::getCommand)
                                 .distinct()
                                 .count() < arguments.size();
    }

    private boolean multipleArgumentsWithHighPrecedenceCommandPresent(List<Argument> arguments) {
        return arguments.stream().map(argument -> argument.getCommand().getPrecedence())
                                 .filter(precedence -> precedence == Precedence.HIGH)
                                 .count() > 1;
    }

    private boolean allArgumentsValid(List<Argument> arguments) {
        return arguments.stream().map(Argument::isValid)
                                 .filter(isValid -> isValid)
                                 .count() == arguments.size();
    }

    private boolean singlePrecedenceCommandPresent(List<Argument> arguments) {
        return arguments.stream().map(argument -> argument.getCommand().getPrecedence())
                                 .filter(precedence -> precedence == Precedence.HIGH)
                                 .count() == 1;
    }
}
