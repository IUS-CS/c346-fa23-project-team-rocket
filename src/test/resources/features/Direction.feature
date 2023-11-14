Feature: Direction Enum
    Tests the direction Enum

    Scenario Outline: Test directionFromInt method and ensure that integer values are returning the appropriate Directions
        Given The <int> is saved.
        When <int> is passed into directionFromInt
        Then the return direction from directionFromInt is <direction>

        Examples:
            | int | direction |
            | 0   | UP        |
            | 1   | DOWN      |
            |2    |LEFT       |
            |3    |RIGHT      |

    Scenario: left is passed into directionFromString
        Given "left" is passed into directionFromString
        Then the returned direction should be left

    Scenario: right is passed into directionFromString
        Given "right" is passed into directionFromString
        Then the returned direction should be right

    Scenario: up is passed into directionFromString
        Given "up" is passed into directionFromString
        Then the returned direction should be up

    Scenario: down is passed into directionFromString
        Given "down" is passed into directionFromString
        Then the returned direction should be down

    Scenario: 5 is passed into directionFromInt and throws IllegalArgumentException
        Then directionFromInt should throw illegalArgumentException when five is passed into it

    Scenario: a non-direction is passed into directionFromString
        Then directionFromString should throw illegalArgumentException when a non-direction is passed into it

    Scenario: passing a 5-length boolean array into randomDirectionFromBooleanArray
        Then randomDirectionFromBooleanArray should throw IllegalArgumentException when a too long boolean array is passed in

    Scenario: passing a full false array into randomDirectionFromBooleanArray returns null
        Given a boolean array with values (0 , 0 , 0 , 0) is made
        Then randomDirectionFromBooleanArray returns null when passed the array

    Scenario: passing (1,0,0,0) into randomDirectionFromBooleanArray returns the one direction
        Given a boolean array with values (1 , 0 , 0 , 0) is made
        Then randomDirectionFromBooleanArray returns UP

    Scenario: passing (0,0,1,0) into randomDirectionFromBooleanArray returns the one direction
        Given a boolean array with values (0 , 0 , 1 , 0) is made
        Then randomDirectionFromBooleanArray returns LEFT