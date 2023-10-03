Feature: Flag Handler for initial Organism Count
    Handles the testing for the InitialOrganismCountFlagHandler class

    Background: Register the appropriate OrganismFactory classes
        Given We register the Rabbit class into the OrganismFactory
        And We ensure there is zero rabbits initially

    Scenario: Test whether the InitialOrganismCountFlagHandler appropriately spawns the appropriate amount of rabbits
        Given Theres a TerminalFlagRequest Created with a 6 by 6 map and a "--Rabbit_count 32" command
        When The TerminalFlagRequest is handled by the InitialOrganismCountFlagHandler
        Then The TerminalFlagRequest has a 6 by 6 map, a "--Rabbit_count 32" command
        And There are 32 Rabbits in the TerminalFlagRequest

    Scenario Outline: Run through multiple tests that have different grid sizes and spawn different amount of rabbits with different commands
        Given Theres a TerminalFlagRequest Created with a <int1> by <int2> map and a <string> command
        When The TerminalFlagRequest is handled by the InitialOrganismCountFlagHandler
        Then The TerminalFlagRequest has a <int1> by <int2> map, a <string> command
        And There are <rabbit_count> Rabbits in the TerminalFlagRequest.

        Examples:
