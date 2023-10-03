Feature: Flag Handler for initial Organism Count
    Handles the testing for the InitialOrganismCountFlagHandler class

    Scenario: Test whether the InitialOrganismCountFlagHandler appropriately spawns the appropriate amount of rabbits
        Given Theres a TerminalFlagRequest Created with a 6 by 6 map and a "--Rabbit_count 32" command
        When The TerminalFlagRequest is handled by the InitialOrganismCountFlagHandler
        Then The TerminalFlagRequest has a 6 by 6 map, a "--Rabbit_count 32" command
        And There are 32 Rabbits in the TerminalFlagRequest
