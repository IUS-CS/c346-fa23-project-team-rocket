Feature:
    Handles the testing for the DaysPerRunFlagHandler class

    Scenario Outline:
        Given Theres a TerminalFlagRequest Created with some size and a <string> command
        When The TerminalFlagRequest is handled by the DaysPerRunFlagHandler
        Then The TerminalFlagRequest has <N> numOfDays

        Examples:
            | string            | N |
            | "--Days_Amount 3" | 3 |
            | "--Days_Amount 6" | 6 |
            | ""                | 0 |