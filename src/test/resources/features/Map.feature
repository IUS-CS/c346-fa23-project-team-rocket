Feature: Map
    I need to make sure Map works properly

    Scenario: A 5 by 10 map should have a width of 5 and a height of 10
        Given a 5 by 10 map is created
        Then the map should be 5 by 10
        And the grid should not be null

    Scenario: A 1 by 1 map should have a width of 1 and a height of 1
        Given a 1 by 1 map is created
        Then the map should be 1 by 1
        And the grid should not be null

    Scenario: A 0 by 1 map should not exist
        Given a 0 by 1 map is created
        Then the grid should be null

    Scenario: A 1 by 0 map should not exist
        Given a 1 by 0 map is created
        Then the grid should be null

    Scenario: A map created with an empty grid should be empty
        Given an empty map is created
        Then the map should be empty

    Scenario: A map created with a nonempty grid should not be empty
        Given a nonempty map is created
        Then the map should not be empty

    Scenario: A map of all rabbits should be full
        Given a map of all rabbits is created
        Then the map should be full

    Scenario: A map created with a nonfull grid should not be full
        Given a nonfull map is created
        Then the map should not be full

    Scenario: A map created with a grid should have the same dimensions and same contents
        Given a map is created with a grid
        Then the map should have the given dimensions and given contents

    Scenario: An added organism should overwrite an empty space
        Given a space has no organism
        When an organism is added to the space
        Then the space should have the given organism

    Scenario: An added organism should overwrite another organism
        Given a space has an organism
        When an organism is added to the space
        Then the space should have the given organism

    Scenario: A removed organism should no longer be on the map
        Given a space has an organism
        When an organism is removed from the space
        Then the space should be empty

    Scenario: A space should still be empty after attempting to remove an organism from the empty space
        Given a space has an organism
        When an organism is removed from the space
        Then the space should be empty

    Scenario: An organism in a 1x1 grid should have no neighbors
        Given a 1 by 1 map is created
        When an organism is added to space (0 , 0)
        Then Space (0 , 0) should have 0 neighbors
        And Space (0 , 0) should have 0 neighbor characters

    Scenario: An organism in the middle of a 3x3 grid should have 4 null neighbors
        Given a 3 by 3 map is created
        When an organism is added to space (1 , 1)
        Then Space (1 , 1) should have 4 neighbors
        And All of space (1 , 1) neighbors are null
        And All of space (1 , 1) neighbors are " "

    Scenario: An organism in the middle of a 3x3 grid with one Rabbit at (1, 0)
        Given a 3 by 3 map is created
        When an organism is added to space (1 , 1)
        And a Rabbit is added to space (0 , 1)
        Then Space (1 , 1) should have 4 neighbors
        And The organisms "Up" neighbor is "Rabbit"
        And The organisms "Up" character neighbor is "R"
        And The organisms "Down" neighbor is "Null"
        And The organisms "Down" character neighbor is " "
        And The organisms "Left" neighbor is "Null"
        And The organisms "Left" character neighbor is " "
        And The organisms "Right" neighbor is "Null"
        And The organisms "Right" character neighbor is " "

    Scenario: An organism in the middle of a 3x3 grid with one Rabbit at (1, 2)
        Given a 3 by 3 map is created
        When an organism is added to space (1 , 1)
        And a Rabbit is added to space (1 , 2)
        Then Space (1 , 1) should have 4 neighbors
        And The organisms "Up" neighbor is "Null"
        And The organisms "Up" character neighbor is " "
        And The organisms "Down" neighbor is "Rabbit"
        And The organisms "Down" character neighbor is "R"
        And The organisms "Left" neighbor is "Null"
        And The organisms "Left" character neighbor is " "
        And The organisms "Right" neighbor is "Null"
        And The organisms "Right" character neighbor is " "

    Scenario: An organism in the middle of a 3x3 grid with one Rabbit at (0, 1)
        Given a 3 by 3 map is created
        When an organism is added to space (1 , 1)
        And a Rabbit is added to space (0 , 1)
        Then Space (1 , 1) should have 4 neighbors
        And The organisms "Up" neighbor is "Null"
        And The organisms "Up" character neighbor is " "
        And The organisms "Down" neighbor is "Null"
        And The organisms "Down" character neighbor is " "
        And The organisms "Left" neighbor is "Rabbit"
        And The organisms "Left" character neighbor is "R"
        And The organisms "Right" neighbor is "Null"
        And The organisms "Right" character neighbor is " "

    Scenario: An organism in the middle of a 3x3 grid with one Rabbit at (2, 1)
        Given a 3 by 3 map is created
        When an organism is added to space (1 , 1)
        And a Rabbit is added to space (2 , 1)
        Then Space (1 , 1) should have 4 neighbors
        And The organisms "Up" neighbor is "Null"
        And The organisms "Up" character neighbor is " "
        And The organisms "Down" neighbor is "Null"
        And The organisms "Down" character neighbor is " "
        And The organisms "Left" neighbor is "Null"
        And The organisms "Left" character neighbor is " "
        And The organisms "Right" neighbor is "Rabbit"
        And The organisms "Right" character neighbor is " "
