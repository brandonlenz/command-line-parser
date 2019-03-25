package com.brandonlenz.commandlineparser.argument.definitions;

import com.brandonlenz.commandlineparser.argument.Command;

public abstract class ArgumentDefinition {

    private final Command command;
    private final Format format;

    ArgumentDefinition(Command command, Format format) {
        this.command = command;
        this.format = format;
    }

    public Command getCommand() {
        return command;
    }

    public Format getFormat() {
        return format;
    }
}
