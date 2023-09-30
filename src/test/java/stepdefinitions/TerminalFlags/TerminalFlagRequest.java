package stepdefinitions.TerminalFlags;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import team.rocket.Map;

public class TerminalFlagRequest {
	team.rocket.Handlers.Terminal.TerminalFlagRequest request;

	@Then("A TerminalFlagRequest exists")
	public void aTerminalFlagRequestExists() {
		Assertions.assertNotNull(request);
	}

	@Given("A TerminalFlagRequest is Created")
	public void aTerminalFlagRequestIsCreated() {
		request = new team.rocket.Handlers.Terminal.TerminalFlagRequest("", new Map(3, 3));
	}

	@Given("there is a TerminalFlagRequest Created with an {} by {} map and a {string} command")
	public void thereIsATerminalFlagRequestCreatedWithAnByMapAndACommand(String arg0, String arg1, String arg2, String arg3) {
	}

	@Then("I should have a TerminalFlagRequest which gives me an {} by {} map with a {string} command")
	public void iShouldHaveATerminalFlagRequestWhichGivesMeAnByMapWithACommand(String arg0, String arg1, String arg2, String arg3) {
	}
}
