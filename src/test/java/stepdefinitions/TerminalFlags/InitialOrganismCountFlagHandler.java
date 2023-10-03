package stepdefinitions.TerminalFlags;

import io.cucumber.java.en.And;
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

	@Given("Theres a TerminalFlagRequest Created with a {int} by {int} map and a {string} command")
	public void theresATerminalFlagRequestCreatedWithAByMapAndACommand(int arg0, int arg1, String arg2) {

	}


	@When("The TerminalFlagRequest is handled by the InitialOrganismCountFlagHandler")
	public void theTerminalFlagRequestIsHandledByTheInitialOrganismCountFlagHandler() {

	}

	@Then("The TerminalFlagRequest has a {int} by {int} map, a {string} command")
	public void theTerminalFlagRequestHasAByMapACommand(int arg0, int arg1, String arg2) {

	}

	@And("There are {int} Rabbits in the TerminalFlagRequest")
	public void thereAreRabbitsInTheTerminalFlagRequest(int arg0) {
	}
}
