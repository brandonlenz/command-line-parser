package com.brandonlenz.commandlineparser.argument;

import com.brandonlenz.commandlineparser.argument.definitions.Precedence;

public class Command implements Comparable<Command> {

    private final Prefix prefix;
    private final String text;

    private final Precedence precedence;

    public Command(Prefix prefix, String text, Precedence precedence) {
        this.prefix = prefix;
        this.text = text.toLowerCase();
        this.precedence = Precedence.HIGH;
    }

    public Command(Prefix prefix, String text) {
        this.prefix = prefix;
        this.text = text.toLowerCase();
        this.precedence = Precedence.STANDARD;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public String getText() {
        return text;
    }

    public Precedence getPrecedence() {
        return precedence;
    }

    @Override
    public String toString() {
        return prefix + text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof Command) {
            Command c = (Command) o;
            return prefix.compareTo(c.prefix) == 0
                    && text.compareTo(c.text) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 11;

        result = 31 * result + prefix.hashCode();
        result = 31 * result + text.hashCode();

        return result;
    }

    public int compareTo(Command command) {
        return text.compareTo(command.text);
    }
}
