Feature: Vision
    Testing organism vision

    Scenario: Rabbits should be able to see 4 tiles away
        Given an animal is a rabbit
        Then the animal should see 4 tiles away

    Scenario: Foxes should be able to see 5 tiles away
        Given an animal is a fox
        Then the animal should see 5 tiles away