Feature: Simulation
  I need to make sure that Simulation properly works

  Scenario: The program exits whenever the map is full
    Given simulation has a map
    When the map is full
    Then the simulation stops
