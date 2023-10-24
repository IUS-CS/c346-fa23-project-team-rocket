Feature:
    Handles the testing for the DaysPerRunFlagHandler class

    Scenario Outline:
        Given Theres a TerminalFlagRequest Created with some size and a <string> command
        When The TerminalFlagRequest is handled by the DaysPerRunFlagHandler
        Then The TerminalFlagRequest has <N> numOfDays

        Examples:
            | string            | N |
            | "--days_amount 3" | 3 |
            | "--days_amount 6" | 6 |
            | ""                | 0 |