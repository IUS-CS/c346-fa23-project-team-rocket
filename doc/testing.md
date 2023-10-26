# Testing Methodology

We are currently utilizing two different forms of testing to ensure that both the parts and the whole of our program are working as expected. We perform unit testing using Cucumber and JUnit and manually perform Integration testing. We also run our testing code on merges to ensure that Pull Requests properly pass all of our Unit Tests.

## Unit Testing

For our Unit Testing, we use the [Cucumber](https://cucumber.io/docs/installation/) testing platform, which separates the test writing from the internal test definitions. A person can write the feature files in a near-plain text language called Gherkin, and then a developer can implement the background code needed for the 
tests to run and pass properly. Using this platform for our Unit Testing makes ensuring that our testing checks normal and edge cases simple. We store our tests in a separate directory under src with subdirectories containing our feature files and step definitions.

## Integration Testing

Our integration testing is a more lengthy process, slightly less defined than our unit testing methodology, although we are working on the process this sprint. Generally, when we perform integration testing, we run the program with specific variables and watch the program's behavior to see if anything unexpected occurs. If the program acts accordingly and reacts well to any edge cases we throw at it, then it passes the integration test and is good to be merged.

## Possible Future Testing  Ventures

We currently have plans to implement a few different things into our program; they are as follows:

- Create an integration testing process diagram
- Automatically restrict merges with code coverage below some arbitrary value
- Work with an outside party to test for possibly unconsidered edge cases
