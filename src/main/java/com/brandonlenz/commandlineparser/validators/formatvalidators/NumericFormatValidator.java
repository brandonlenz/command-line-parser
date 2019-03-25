package com.brandonlenz.commandlineparser.validators.formatvalidators;

import com.brandonlenz.commandlineparser.argument.definitions.ArgumentDefinition;
import com.brandonlenz.commandlineparser.argument.definitions.NumericArgumentDefinition;

public class NumericFormatValidator extends ArgumentFormatValidator implements FormatValidator {

    private final NumericArgumentDefinition argumentDefinition;

    public NumericFormatValidator(ArgumentDefinition argumentDefinition) {
        if (argumentDefinition instanceof NumericArgumentDefinition) {
            this.argumentDefinition = (NumericArgumentDefinition) argumentDefinition;
        } else {
            throw new IllegalArgumentException(
                    "Attempted to instantiate a NumericFormatValidator with and invalid ArgumentDefinition: " + argumentDefinition);
        }
    }

    @Override
    public boolean formatIsValid(String number) {
        if (number.isEmpty()) return false;

        try {
            int intValue = Integer.parseInt(number);
            return valueIsWithinValidRange(intValue);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean valueIsWithinValidRange(int value) {
        return value >= argumentDefinition.getMinValue() && value <= argumentDefinition.getMaxValue();
    }
}