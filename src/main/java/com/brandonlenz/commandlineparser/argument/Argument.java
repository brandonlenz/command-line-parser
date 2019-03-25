package com.brandonlenz.commandlineparser.argument;

import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;

/**
 * An Argument consists of a command, and value.
 */
public class Argument {
    private final Command command;
    private final String value;
    private final ArgumentDefinition definition;
    private boolean isValid;


    public Argument(ArgumentDefinition definition, String value) {
        this.definition = definition;
        this.command = definition.getCommand();
        this.value = value;
    }

    public Command getCommand() {
        return command;
    }

    public String getValue() {
        return value;
    }

    public ArgumentDefinition getDefinition() {
        return definition;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return this.command.getPrefix().toString() + this.command.getText() + " " + this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof Argument) {
            Argument a = (Argument) o;
            return command.compareTo(a.command) == 0
                    && value.compareTo(a.value) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 11;

        result = 31 * result + command.hashCode();
        result = 31 * result + value.hashCode();

        return result;
    }
}
