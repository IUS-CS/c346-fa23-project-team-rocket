Feature: Cucumber Test
      I need to validate that Cucumber is working properly

      Scenario: Is Cucumber working
            Given Cucumber is working
            When Cucumber is installed
            Then I should see cucumber run tests it should return True


      Scenario: This test should fail
            Given Cucumber is working
            When Cucumber is installed
            Then this test should fail