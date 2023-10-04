Feature: Grass
  Testing the Grass

  Background:
    Given A new grass is grown
  Scenario:
    Then The grass should be unable to grow

  Scenario:
    Given A grass's growth is reset
    Then The grass should be able to grow
    Then A new grass is grown