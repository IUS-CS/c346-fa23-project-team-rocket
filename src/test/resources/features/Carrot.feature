Feature: Carrot
  Testing the Carrot

  Background:
    Given A new Carrot is grown
  Scenario:
    Then The Carrot should be unable to grow

  Scenario:
    Given A Carrot's growth is reset
    Then The Carrot should be able to grow
    Then The Carrot should have 25 nutrition
    Then A new Carrot is grown