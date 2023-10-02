Feature: Organism Enum
    I need to validate that the Organism Enum. is working appropriately

    Scenario: The Enum is passed "Rabbit"
        Given "Rabbit" is passed to the Enum Class
        Then The Rabbit class instance should be returned


    Scenario: The Enum is passed "Unexistium"
        Given "Unexistium" is passed to the Enum Class
        Then Class Reference "Unexistium" returns null

    Scenario: "Rabbit" count should be 1 #One instance is created from the passed instance
        Given "Rabbit" is passed to the Enum Class
        Then The Rabbit class count should be 1