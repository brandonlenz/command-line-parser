package com.brandonlenz.commandlineparser;

public enum Result {
    PRECEDENCE_COMMAND_PRESENT(1),
    SYNTAX_VALID(0),
    SYNTAX_INVALID(-1);

    private int intValue;

    Result(int intValue) {
        this.intValue = intValue;
    }

    public int intValue() {
        return intValue;
    }
}
