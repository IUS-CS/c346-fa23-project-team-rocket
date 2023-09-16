package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class test_1 {
	@io.cucumber.java.en.Given("Cucumber is working")
	public void cucumberIsWorking() {

	}

	@When("Cucumber is installed")
	public void cucumberIsInstalled() {

	}

	@Then("I should see cucumber run tests it should return True")
	public void iShouldSeeCucumberRunTestsItShouldReturnTrue() {
		Assertions.assertTrue(true);
	}

	@Then("this test should fail")
	public void thisTestShouldFail() {
		Assertions.assertTrue(false);
	}
}
