# command-line-parser
A (hopefully) flexibly starting point for a library capable of parsing and validating command line commands with custom arguments, prefixes, commands, etc. each with their own custom validation rules.

Solution.java contains the original problem statement (also below) along with the configuration of the code base to solve tests passed in via the original rule set.

Note that the code base is designed to potentially accomodate custom rules flexibly.

 > Design a method that parses an input String, s, such that it validates the arguments passed in.
 > The possible commands are as follows:
 >
 > - **--count**  An Integer between 10 and 100 (inclusive)
 > - **--name**   A String consisting of 3 to 10 characters (inclusive)
 > - **--help**   A command indicating the user is requesting help, but has no value following the command
 >
 >  The method should return an int as follows:
 >  - **1** if the help command is used and all other arguments are valid
 >  - **0** if all arguments are valid
 >  - **-1** if there are invalid arguments
 >
 > **Rules:**
 > - Arguments are optional, but at least one valid command must be present
 > - Arguments are evaluated in order
 > - The help argument takes precedence
 > - Arguments are case insensitive
 > - "Extra" whitespace is to be ignored
 
