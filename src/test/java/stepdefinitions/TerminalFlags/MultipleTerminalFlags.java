package stepdefinitions.TerminalFlags;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.Handlers.Terminal.FlagHandler;
import team.rocket.Handlers.Terminal.GridSizeFlagHandler;
import team.rocket.Handlers.Terminal.InitialOrganismCountFlagHandler;
import team.rocket.Handlers.Terminal.TerminalFlagRequest;
import team.rocket.Map;
import team.rocket.Entities.OrganismFactory;
import team.rocket.Entities.Rabbit;

public class MultipleTerminalFlags {
	FlagHandler handler;
	TerminalFlagRequest request;


	@And("We set the Successor of the GridSizeFlagHandler to the InitialOrganismCountFlagHandler")
	public void weSetTheSuccessorOfTheGridSizeFlagHandlerToTheInitialOrganismCountFlagHandler() {
		handler.setSuccessor(new InitialOrganismCountFlagHandler());
	}

	@And("We assign a handler value to the InitialOrganismCountFlagHandler") //Must be run before the successor one
	public void weAssignAHandlerValueToTheInitialOrganismCountFlagHandler() {
		handler = new GridSizeFlagHandler();
	}

	@Given("We Create a TFRequest with a {} by {} grid and a {} command")
	public void weCreateATFRequestWithAByGridAndACommand(String width, String height, String cmd) {
		request = new TerminalFlagRequest(cmd, new Map(Integer.parseInt(width), Integer.parseInt(height)));
	}

	@When("We pass the TFRequest to the handler")
	public void wePassTheTFRequestToTheHandler() {
		handler.handleRequest(request);
	}

	@Then("We should have a TFRequest with a {} by {} grid, {} Rabbits, and a {} command")
	public void weShouldHaveATFRequestWithAByGridRabbitsAndACommand(String newWidth, String newHeight, String numRabbits, String cmd) {
		Assertions.assertEquals(Integer.parseInt(newWidth), request.getMap().getWidth());
		Assertions.assertEquals(Integer.parseInt(newHeight), request.getMap().getHeight());
		Assertions.assertEquals(Integer.parseInt(numRabbits), Rabbit.getCount());
		Assertions.assertEquals(cmd, request.getTerminalCommand());
	}

	@Given("We register the Rabbit class into the Factory")
	public void weRegisterTheRabbitClassIntoTheFactory() {
		Rabbit rabbit = new Rabbit();
		rabbit.reduceCount();
		OrganismFactory.getInstance().registerOrganism("Rabbit", rabbit);
	}

	@And("We ensure there are zero rabbits initially")
	public void weEnsureThereAreZeroRabbitsInitially() {
		Rabbit rabbit = new Rabbit();
		rabbit.setCount(0);
	}
}
