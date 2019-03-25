package com.brandonlenz.commandlineparser.argument.definitions;

import com.brandonlenz.commandlineparser.argument.Command;

public class NumericArgumentDefinition extends ArgumentDefinition {

    private final int minValue;
    private final int maxValue;

    public NumericArgumentDefinition(Command command, int minValue, int maxValue) {
        super(command, Format.NUMERIC);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
