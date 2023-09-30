Feature: Terminal Flag Request Class
    Handles the testing for the TerminalFlag

    Scenario: A TerminalFlagRequest is Created and it exists
        Given A TerminalFlagRequest is Created
        Then A TerminalFlagRequest exists

    Scenario Outline: Creating TFR's
        Given there is a TerminalFlagRequest Created with an <X> by <Y> map and a "<string>" command
        Then I should have a TerminalFlagRequest which gives me an <X> by <Y> map with a "<string>" command

        Examples:
            | X | Y | string  |
            | 3 | 4 | "hello" |
