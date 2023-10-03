Feature: OrganismFactory
    Testing the Organism factory

    Background:
        Given there is a previous number of rabbits we should reset it
        And We retrieve an OrganismFactory instances

    Scenario: OrganismFactory is a singleton
        Given We retrieve another OrganismFactory instance
        Then The OrganismFactory instances should be the same instance
        And the OrganismFactory instances shouldnt be null

    Scenario: Creating a nonexistent organism
        Then We should get null when when creating a nonexistent organism

    Scenario: Registering Rabbit
        When We register the Rabbit class
        Then The number of Rabbits should be 0

    Scenario: Creating 4 Rabbits
        When We register the Rabbit class
        And We create 4 Rabbits with the OrganismFactory
        Then The number of Rabbits should be 4

    Scenario: Creating 1 Rabbits
        When We register the Rabbit class
        And We create 1 Rabbits with the OrganismFactory
        Then The number of Rabbits should be 1

    Scenario Outline: Creating N Rabbits
        When We register the Rabbit class
        And We create <Num> Rabbits with the OrganismFactory
        Then The number of Rabbits should be <Num>

        Examples:
            | Num |
            | 3   |
            | 10  |
            | 0   |




