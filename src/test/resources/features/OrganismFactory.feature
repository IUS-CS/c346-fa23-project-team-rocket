Feature: OrganismFactory
    Testing the Organism factory

    Background:
        Given there is a previous number of rabbits we should reset it

    Scenario: OrganismFactory is a singleton
        Given We retrieve 2 OrganismFactory instances
        Then The OrganismFactory instances should be the same instance
        And the OrganismFactory instances shouldnt be null

    Scenario: Registering Rabbit
        Given We retrieve 1 OrganismFactory instances
        When We register the Rabbit class
        Then The Rabbit count should be 0

    Scenario: Creating a Rabbit
        Given We retrieve 1 OrganismFactory instances
        When We register the Rabbit class
        And We create 1 Rabbits with the OrganismFactory
        Then The number of Rabbits should be 1

    Scenario: Creating a nonexistent organism
        Given We retrieve 1 OrganismFactory instances
        Then We should get null when when creating a nonexistent organism

    Scenario Outline: Creating Rabbits
        Given We retrieve 1 OrganismFactory instances
        And We register the Rabbit class
        When We create <int> Rabbits with the OrganismFactory
        Then There should be <n> Rabbits

        Examples:
            | int | n   |
            | 3   | 3   |
            | 4   | 4   |
            | 0   | 0   |
            | 6   | 6   |
            | 100 | 100 |

