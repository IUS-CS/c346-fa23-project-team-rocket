Feature: Flag Handler for initial Organism Count
    Handles the testing for the InitialOrganismCountFlagHandler class

    Scenario Outline: Testing whether the InitialOrganismCountFlagHandler appropriately handles the command and spawns the appropriate amount of rabbits
        Given theres a TerminalFlagRequest Created with an <X> by <Y> map and a <string> command
        When the TerminalFlagRequest is handled by the InitialOrganismCountFlagHandler
        Then there should be a TerminalFlagRequest with an <X> by <Y> map, <string> command, and <Z> rabbits
        Examples:
            | X    | Y    | string               | Z   |
            | 3    | 4    | "--Wolf_count 4"     | 0   |
            | 4    | 3    | "--Rabbit_count 6"   | 6   |
            | 10   | 10   | "--Rabbit_count 55"  | 55  |
            | 100  | 100  | "--rabbit_count 100" | 100 |
            | 1000 | 1000 | "--RABBIT_count 100" | 100 |
            | 1    | 1    | "--rabbIT_count 10"  | 1   |