Feature: Map
    I need to make sure Map works properly

    Scenario: A 5 by 10 map should have a width of 5 and a height of 10
        Given a 5 by 5 map is created
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