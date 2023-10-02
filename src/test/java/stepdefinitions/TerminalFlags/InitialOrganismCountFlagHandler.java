package stepdefinitions.TerminalFlags;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.AbstractOrganism;
import team.rocket.Handlers.Terminal.FlagHandler;
import team.rocket.Map;
import team.rocket.Rabbit;

import java.util.Objects;

public class InitialOrganismCountFlagHandler {
	team.rocket.Handlers.Terminal.TerminalFlagRequest request;
	@Given("theres a TerminalFlagRequest Created with an {} by {} map and a {} command")
	public void thereIsATerminalFlagRequestCreatedWithAnByMapAndACommand(String arg0, String arg1, String arg2) {
		request = new team.rocket.Handlers.Terminal.TerminalFlagRequest(arg2, new Map(Integer.parseInt(arg0), Integer.parseInt(arg1)));
	}
	@When("the TerminalFlagRequest is handled by the InitialOrganismCountFlagHandler")
	public void theTerminalFlagRequestIsHandledByTheInitialOrganismCountFlagHandler() {
		FlagHandler handler1 = new team.rocket.Handlers.Terminal.InitialOrganismCountFlagHandler();
		handler1.handleRequest(request);
	}

	@Then("there should be a TerminalFlagRequest with an {} by {} map, {} command, and {} rabbits")
	public void thereShouldBeATerminalFlagRequestWithAnByMapCommandAndRabbits(String grid_width, String grid_height, String terminal_command, String num_rabbits) {
		//Number of Rabbits created should match num_rabbits
		int realNumberOfRabbits = Rabbit.getCount();

		Assertions.assertEquals(Integer.parseInt(grid_width), request.getMap().getWidth());
		Assertions.assertEquals(Integer.parseInt(grid_height), request.getMap().getHeight());
		Assertions.assertEquals(terminal_command, request.getTerminalCommand());
		Assertions.assertEquals(Integer.parseInt(num_rabbits),  realNumberOfRabbits);

	}
}
