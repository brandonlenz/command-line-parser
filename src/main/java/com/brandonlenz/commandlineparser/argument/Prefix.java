package com.brandonlenz.commandlineparser.argument;

public class Prefix implements Comparable<Prefix>{
    private String text;

    public Prefix(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof Prefix) {
            Prefix p = (Prefix) o;
            return text.compareTo(p.text) == 0;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    public int compareTo(Prefix prefix) {
        return text.compareTo(prefix.text);
    }
}
