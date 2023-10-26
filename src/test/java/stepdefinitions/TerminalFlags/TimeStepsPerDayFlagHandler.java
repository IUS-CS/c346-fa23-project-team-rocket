package stepdefinitions.TerminalFlags;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.Handlers.Terminal.FlagHandler;
import team.rocket.Handlers.Terminal.TerminalFlagRequest;
import team.rocket.Map;

public class TimeStepsPerDayFlagHandler {
	TerminalFlagRequest request;
	@Given("Theres a TerminalFlagRequest Created with some size and a {} command two")
	public void theresATerminalFlagRequestCreatedWithSomeSizeAndACommand(String cmd) {
		request = new TerminalFlagRequest(cmd, new Map(5, 5));
	}

	@When("The TerminalFlagRequest is handled by the TimeStepsPerDayFlagHandler")
	public void theTerminalFlagRequestIsHandledByTheTimeStepsPerDayFlagHandler() {
		FlagHandler handler = new team.rocket.Handlers.Terminal.TimeStepsPerDayFlagHandler();
		handler.handleRequest(request);
	}

	@Then("The TerminalFlagRequest has {} stepsPerDay")
	public void theTerminalFlagRequestHasStepsPerDay(String expectedStepsPerDay) {
		Assertions.assertEquals(Integer.parseInt(expectedStepsPerDay), request.getStepsPerDay());
	}
}
