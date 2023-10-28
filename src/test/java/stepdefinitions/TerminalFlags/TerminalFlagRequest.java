package stepdefinitions.TerminalFlags;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.Map;

import java.util.Objects;

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

	@Given("there is a TerminalFlagRequest Created with an {} by {} map and a {} command")
	public void thereIsATerminalFlagRequestCreatedWithAnByMapAndACommand(String arg0, String arg1, String arg2) {
		request = new team.rocket.Handlers.Terminal.TerminalFlagRequest(arg2, new Map(Integer.parseInt(arg0), Integer.parseInt(arg1)));
	}

	@Then("I should have a TerminalFlagRequest which gives me an {} by {} map with a {} command")
	public void iShouldHaveATerminalFlagRequestWhichGivesMeAnByMapWithACommand(String arg0, String arg1, String arg2) {
		Assertions.assertTrue(Objects.equals(request.getTerminalCommand(), arg2) && request.getMap().getWidth() == Integer.parseInt(arg0) && request.getMap().getHeight() == Integer.parseInt(arg1));
	}


	@When("I set the map to Size {}, {}")
	public void iSetTheMapToSize(String arg0, String arg1) {
		request.setMap(new Map(Integer.parseInt(arg0), Integer.parseInt(arg1)));
	}

	@And("I set the numOfDays to {}")
	public void iSetTheNumOfDaysTo(String numOfDays) {
		request.setNumOfDays(Integer.parseInt(numOfDays));
	}


	@And("the TerminalFlagRequest should return {} when getting the numOfDays")
	public void theTerminalFlagRequestShouldReturnWhenGettingTheNumOfDays(String numOfDays) {
		Assertions.assertEquals(Integer.parseInt(numOfDays), request.getNumOfDays());
	}

	@When("The TerminalFlagRequests stepsPerDay value is changed to {int}")
	public void theTerminalFlagRequestsStepsPerDayValueIsChangedTo(int stepsPerDay) {
		request.setStepsPerDay(stepsPerDay);
	}

	@Then("the TerminalFlagRequests stepsPerDay value is {int}")
	public void theTerminalFlagRequestsStepsPerDayValueIs(int stepsPerDay) {
		Assertions.assertEquals(stepsPerDay, request.getStepsPerDay());
	}
}
