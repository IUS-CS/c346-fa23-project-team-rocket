package stepdefinitions.TerminalFlags;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.jupiter.api.Assertions;
import team.rocket.AbstractOrganism;
import team.rocket.Handlers.Terminal.TerminalFlagRequest;
import team.rocket.Map;

public class GridSizeFlagHandler {

	team.rocket.Handlers.Terminal.TerminalFlagRequest request;
	@Given("A TerminalFlagRequest is Created with a {int} by {int} grid and {string} command")
	public void aTerminalFlagRequestIsCreatedWithAByGridAndCommand(int width, int height, String cmd) {
		request = new TerminalFlagRequest(cmd, new Map(width, height));
	}

	@When("The TerminalFlagRequest is handled by the GridSizeFlagHandler")
	public void theTerminalFlagRequestIsHandledByTheGridSizeFlagHandler() {
		team.rocket.Handlers.Terminal.GridSizeFlagHandler handler = new team.rocket.Handlers.Terminal.GridSizeFlagHandler();
		handler.handleRequest(request);
	}

	@Then("A TerminalFlagRequest exists with a {int} by {int} grid and {string} command")
	public void aTerminalFlagRequestExistsWithAByGridAndCommand(int width, int height, String cmd) {
		Assertions.assertEquals(width, request.getMap().getWidth());
		Assertions.assertEquals(height, request.getMap().getHeight());
		Assertions.assertEquals(cmd, request.getTerminalCommand());
	}

	@Given("ATerminalFlagRequest is Created with a {} by {} grid and a command to change it to {} by {}")
	public void aterminalflagrequestIsCreatedWithAByGridAndACommandToChangeItToBy(String width, String height, String new_width, String new_height) {
		String cmd = "--grid_width "+ new_width+ " --grid_height " + new_height;
		aTerminalFlagRequestIsCreatedWithAByGridAndCommand(Integer.parseInt(width), Integer.parseInt(height), cmd);
	}

	@Then("A TerminalFlagRequest exists with a {} by {} grid")
	public void aTerminalFlagRequestExistsWithAByGrid(String expected_width, String expected_height) {
		Assertions.assertEquals(Integer.parseInt(expected_width), request.getMap().getWidth());
		Assertions.assertEquals(Integer.parseInt(expected_height), request.getMap().getHeight());
	}
}
