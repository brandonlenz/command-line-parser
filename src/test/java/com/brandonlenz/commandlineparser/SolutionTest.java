package com.brandonlenz.commandlineparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {

    private Solution solution = new Solution();

    @Test
    void noArgumentsPresent() {
        Assertions.assertEquals(-1, solution.solution("noArgumentsPresent "));
    }

    @Test
    void allParametersAreValidWithHelp() {
        Assertions.assertEquals(1, solution.solution("--help --name Brandon --count 11"));
    }

    @Test
    void allParametersAreValidWithHelpCaseInsensitive() {
        Assertions.assertEquals(1, solution.solution("--hElP --NaMe Brandon --COUNT 11"));
    }

    @Test
    void allParametersAreValidWithHelpAndMessyWhitespace() {
        Assertions.assertEquals(1, solution.solution("--help         --nameBrandon--count   11   "));
    }

    @Test
    void allParametersAreValidWithoutHelp() {
        Assertions.assertEquals(0, solution.solution("--name Brandon --count 11"));
    }

    @Test
    void allParametersAreInvalid() {
        Assertions.assertEquals(-1, solution.solution("--name b --count 0"));
    }

    @Test
    void duplicateParametersOtherwiseValid() {
        Assertions.assertEquals(-1, solution.solution("--name Brandon --name Lenz"));
    }

    @Test
    void duplicateParametersSomeInvalid() {
        Assertions.assertEquals(-1, solution.solution("--name --name Lenz"));
    }

    @Test
    void invalidCommandUsed() {
        Assertions.assertEquals(-1, solution.solution("--fakeCommand fakeValue"));
    }


}