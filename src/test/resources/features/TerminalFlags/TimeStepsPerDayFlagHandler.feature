Feature: Flag Handler for the number of Time Steps per day
    Handles the testing of the TimeStepsPerDayFlagHandler class

    Scenario Outline:
        Given Theres a TerminalFlagRequest Created with some size and a <string> command two
        When The TerminalFlagRequest is handled by the TimeStepsPerDayFlagHandler
        Then The TerminalFlagRequest has <N> stepsPerDay

        Examples:
            | string              | N |
            | "--steps_per_day 4" | 4 |
            | "--steps_per_day 9" | 9 |
            | "" | 10 |