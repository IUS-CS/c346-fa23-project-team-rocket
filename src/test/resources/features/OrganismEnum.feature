Feature: Organism Enum Test
    I need to validate that the Organism Enum. is working appropriately

    Scenario: The Enum is passed "Rabbit"
        Given "Rabbit" is passed to the Enum Class
        When "Rabbit" is a valid class
        Then The "Rabbit" class instance should be returned