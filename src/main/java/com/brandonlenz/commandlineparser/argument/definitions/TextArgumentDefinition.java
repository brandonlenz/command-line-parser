package com.brandonlenz.commandlineparser.argument.definitions;

import com.brandonlenz.commandlineparser.argument.Command;

public class TextArgumentDefinition extends ArgumentDefinition {

    private final int minLength;
    private final int maxLength;

    public TextArgumentDefinition(Command command, int minLength, int maxLength) {
        super(command, Format.TEXT);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
