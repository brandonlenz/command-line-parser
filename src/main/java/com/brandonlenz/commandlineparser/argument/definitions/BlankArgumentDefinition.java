package com.brandonlenz.commandlineparser.argument.definitions;

import com.brandonlenz.commandlineparser.argument.Command;

public class BlankArgumentDefinition extends ArgumentDefinition {

    public BlankArgumentDefinition(Command command) {
        super(command, Format.BLANK);
    }
}
