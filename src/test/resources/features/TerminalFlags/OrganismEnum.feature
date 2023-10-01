Feature: Organism Enum
    I need to validate that the Organism Enum. is working appropriately

    Scenario: The Enum is passed "Rabbit"
        Given "Rabbit" is passed to the Enum Class
        Then The Rabbit class instance should be returned

    Scenario: The Enum is passed "Unexistium"
        Given "Unexistium" is passed to the Enum Class
        Then Class Reference "Unexistium" returns null