package stepdefinitions.TerminalFlags;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.IO.Terminal.TerminalFlagRequest;
import team.rocket.Map;
import team.rocket.Entities.OrganismFactory;
import team.rocket.Entities.Rabbit;

public class InitialOrganismCountFlagHandler {
	team.rocket.IO.Terminal.TerminalFlagRequest request;

	@Given("Theres a TerminalFlagRequest Created with a {int} by {int} map and a {string} command")
	public void theresATerminalFlagRequestCreatedWithAByMapAndACommand(int grid_width, int grid_height, String cmd) {
 		request = new TerminalFlagRequest(cmd, new Map(grid_width, grid_height));
	}


	@When("The TerminalFlagRequest is handled by the InitialOrganismCountFlagHandler")
	public void theTerminalFlagRequestIsHandledByTheInitialOrganismCountFlagHandler() {
		team.rocket.IO.Terminal.InitialOrganismCountFlagHandler handler = new team.rocket.IO.Terminal.InitialOrganismCountFlagHandler();
		handler.handleRequest(request);
	}

	@Then("The TerminalFlagRequest has a {int} by {int} map, a {string} command")
	public void theTerminalFlagRequestHasAByMapACommand(int grid_width, int grid_height, String cmd) {
		Assertions.assertEquals(grid_width, request.getMap().getWidth());
		Assertions.assertEquals(grid_height, request.getMap().getHeight());
		Assertions.assertEquals(cmd, request.getTerminalCommand());
	}

	@And("There are {int} Rabbits in the TerminalFlagRequest")
	public void thereAreRabbitsInTheTerminalFlagRequest(int numRabbits) {
		Assertions.assertEquals(numRabbits, Rabbit.getCount());
	}

	@Given("Theres a TerminalFlagRequest Created with a <int{int}> by <int{int}> map and a {} command")
	public void theresATerminalFlagRequestCreatedWithAByMapAndACommand(String grid_width, String grid_height, String cmd) {
		request = new TerminalFlagRequest(cmd, new Map(Integer.parseInt(grid_width), Integer.parseInt(grid_height)));
	}

	@Then("The TerminalFlagRequest has a <int{int}> by <int{int}> map, a {} command")
	public void theTerminalFlagRequestHasAByMapACommand(String grid_width, String grid_height, String cmd) {
		Assertions.assertEquals(Integer.parseInt(grid_width), request.getMap().getWidth());
		Assertions.assertEquals(Integer.parseInt(grid_height), request.getMap().getHeight());
		Assertions.assertEquals(cmd, request.getTerminalCommand());
	}

	@And("There are {} Rabbits in the TerminalFlagRequest.")
	public void thereAreRabbitsInTheTerminalFlagRequest(String numRabbits) {
		Assertions.assertEquals(Integer.parseInt(numRabbits), Rabbit.getCount());

	}

	@Given("We register the Rabbit class into the OrganismFactory")
	public void weRegisterTheRabbitClassIntoTheOrganismFactory() {
		Rabbit rabbit = new Rabbit();
		rabbit.reduceCount();
		OrganismFactory.getInstance().registerOrganism("Rabbit", rabbit);
	}

	@And("We ensure there is zero rabbits initially")
	public void weEnsureThereIsZeroRabbitsInitially() {
		Rabbit rabbit = new Rabbit();
		rabbit.setCount(0);
	}

}
