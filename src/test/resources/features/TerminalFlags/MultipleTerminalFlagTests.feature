Feature: MultipleTerminal Flags being used at once
    Testing to make sure that using multiple terminal flags at once works appropriately

    Background: We need to register Rabbit to the factory and Set up the successor flags
        Given We register the Rabbit class into the Factory
        And We ensure there are zero rabbits initially
        And We assign a handler value to the InitialOrganismCountFlagHandler
        And We set the Successor of the GridSizeFlagHandler to the InitialOrganismCountFlagHandler


    Scenario Outline: Create a tFRequest and handle it normally
        Given We Create a TFRequest with a <width> by <height> grid and a <string> command
        When We pass the TFRequest to the handler
        Then We should have a TFRequest with a <newWidth> by <newHeight> grid, <numRabbits> Rabbits, and a <string> command

        Examples:
            | width | height | string                                               | newWidth | newHeight | numRabbits |
            | 3     | 3      | --grid_width 5 --grid_height 5 --rabbit_count 5      | 5        | 5         | 5          |
            | 10    | 10     | --grid_width 1 --grid_height 1 --rabbit_count 3      | 1        | 1         | 1          |
            | 30    | 30     | --grid_width 40 --grid_height 40 --rabbit_count 1000 | 40       | 40        | 1000       |
            | 1     | 1      | --rabbit_count 1                                     | 1        | 1         | 1          |
            | 32    | 32     | --grid_width 40 --grid_height 40                     | 40       | 40        | 0          |