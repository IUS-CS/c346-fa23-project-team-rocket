Feature: Rabbit
    Testing rabbits

    Scenario: Five rabbits are created
        Given 5 rabbits are created
        Then there should be 5 rabbits

    Scenario: A rabbit is shown on screen
        Then toIcon should return R

    Scenario: A new rabbit is born
        Given a new rabbit is born
        Then the rabbit can't move

    Scenario: A newborn rabbit's movement is reset
        Given a new rabbit is born
        When the rabbit's movement is reset
        Then the rabbit can move

    Scenario: A newborn rabbit's movement is reset
        Given a new rabbit is born
        When the rabbit's movement is reset
        Then the rabbit can eat
