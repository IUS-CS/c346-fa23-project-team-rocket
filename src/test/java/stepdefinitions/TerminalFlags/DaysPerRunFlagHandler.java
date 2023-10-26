package stepdefinitions.TerminalFlags;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.Handlers.Terminal.FlagHandler;
import team.rocket.Handlers.Terminal.TerminalFlagRequest;
import team.rocket.Map;

import java.util.Objects;

public class DaysPerRunFlagHandler {
	TerminalFlagRequest request;

	@When("The TerminalFlagRequest is handled by the DaysPerRunFlagHandler")
	public void theTerminalFlagRequestIsHandledByTheDaysPerRunFlagHandler() {
		FlagHandler handler = new team.rocket.Handlers.Terminal.DaysPerRunFlagHandler();
		handler.handleRequest(request);
	}

	@And("The TerminalFlagRequest has {} numOfDays")
	public void theTerminalFlagRequestHasNumOfDays(String numOfDays) {
		if(Objects.equals(numOfDays, "0")){
			Assertions.assertEquals(0, request.getNumOfDays());
		} else {
			Assertions.assertEquals(Integer.parseInt(numOfDays), request.getNumOfDays());
		}
	}

	@Given("Theres a TerminalFlagRequest Created with some size and a {} command")
	public void theresATerminalFlagRequestCreatedWithSomeSizeAndACommand(String cmd) {
		request = new TerminalFlagRequest(cmd, new Map(3, 3));
	}
}
