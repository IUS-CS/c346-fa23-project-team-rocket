Feature: Simulation
  I need to make sure that Simulation properly works

  Scenario: The program exits whenever the map is full
    Given simulation is given a full map
    When The run method is run for the simulation
    Then The getCurrentDay method returns a value less than 3

    # Testing getCurrentDay method
  Scenario: getCurrentDay method returns number of days passed properly
    Given There is an empty simulation
    When The run method is run for the simulation
    Then The getCurrentDay method returns a value equal to DEFAULT_DAYS_PER_RUN

  Scenario: getCurrentDay method returns null before the run method is run
    Given There is an empty simulation
    Then The getCurrentDay method returns a value equal to 0

    # Testing that the program appropriately sleeps for timesteps
  Scenario: Program duration should wait some ms per TimeStep at minimum on an empty simulation
    Given There is an empty simulation
    And The current time is recorded
    When The run method is run for the simulation
    Then The current time is greater than the expected offset

    # Testing setDaysPerRun method
  Scenario: Changing the DaysPerRun cause the number of days to have passed to change
    Given There is an empty simulation
    When the DaysPerRun is set too 100
    And The run method is run for the simulation
    Then The getCurrentDay method returns a value equal to 100

    # Testing setTimeStepsPerDay method
  Scenario: Changing the TimeStepsPerDay causes the amount of time passed to increase but the number of days to not
    Given There is an empty simulation
    And The current time is recorded
    When We set the TimeStepsPerDay to 25
    And The run method is run for the simulation
    Then The current time is greater than the expected offset
    And The getCurrentDay method returns a value equal to DEFAULT_DAYS_PER_RUN

    # Testing setMillisecondsPerTimeStep method
  Scenario: Changing the MillisecondsPerTimeStep causes the amount of time passed to increase but the total timesteps passed to not
    Given There is an empty simulation
    And The current time is recorded
    When We set the MillisecondsPerTimeStep to 200
    And The run method is run for the simulation
    Then The current time is greater than the expected offset
    And The number of timesteps passed is equal to the default value

    # Test that a full Simulation contains the expected amount of Rabbits
  Scenario: Make sure that when going through the simulation that the rabbit count doesn't exceed the number of rabbits on the map
    Given There is a default simulation
    When the DaysPerRun is set too 20
    And The run method is run for the simulation
    Then The number of rabbits in the map is equal to the Rabbit count





