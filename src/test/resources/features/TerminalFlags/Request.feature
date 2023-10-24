Feature: Terminal Flag Request Class
    Handles the testing for the TerminalFlag

    Scenario: A TerminalFlagRequest is Created and it exists
        Given A TerminalFlagRequest is Created
        Then A TerminalFlagRequest exists

    Scenario Outline: Creating TFR's #(Terminal Flag Request)
        Given there is a TerminalFlagRequest Created with an <X> by <Y> map and a <string> command
        And A TerminalFlagRequest exists
        Then I should have a TerminalFlagRequest which gives me an <X> by <Y> map with a <string> command

        Examples:
            | X      | Y   | string                                           |
            | 3      | 4   | "hello"                                          |
            | 6      | 6   | "--rabbit 6"                                     |
            | 190000 | 1   | ""                                               |
            | 300    | 300 | "--rabbit 4 --grid_width 300  --grid_height 300" |

    Scenario Outline: Changing the map within a TFR
        Given there is a TerminalFlagRequest Created with an <X> by <Y> map and a <string> command
        And A TerminalFlagRequest exists
        When I set the map to Size <A>, <B>
        And I set the numOfDays to <N>
        Then I should have a TerminalFlagRequest which gives me an <A> by <B> map with a <string> command
        And the TerminalFlagRequest should return <N> when getting the numOfDays

        Examples:
            | X  | Y  | A | B | string           | N  |
            | 3  | 3  | 4 | 4 | "--rabbit 6      | 5  |
            | 2  | 2  | 2 | 2 | "--grid_width 30 | 3  |
            | 1  | 1  | 4 | 5 | ""               | 10 |
            | 10 | 10 | 3 | 4 | "x "             | 16 |
            | 9  | 1  | 1 | 9 | "rigorous"       | 25 |





