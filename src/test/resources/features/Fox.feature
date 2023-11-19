Feature: Fox
  Testing the Fox

  Background:
    Given A new fox is born
  Scenario:
    Then The fox should be full
    Then A fox can't move

  Scenario:
    Given A new fox is born
    And A fox's movement is reset
    And A fox loses hunger
    Then A fox should have ninety hunger
    Then A fox can move

    Scenario:
      Given A new fox is born
      And A fox's movement is reset
      And A fox loses hunger
      Then A fox should have ninety hunger
      Then The fox will eat nearby rabbits

      Scenario:
        Given A new fox is born
        Then The Fox can eat rabbits