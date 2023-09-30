package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.AbstractOrganism;
import team.rocket.Enums.Organism;
import team.rocket.Rabbit;

public class OrganismEnum {
	AbstractOrganism test_value;
	@Given("{string} is passed to the Enum Class")
	public void isPassedToTheEnumClass(String arg0) {
		try {
			test_value = Organism.getOrganism(arg0);
			Assertions.assertNotNull(test_value);
		}
		catch(IllegalArgumentException e){
			throwsIllegalArgumentException(arg0);
		}
	}

	@Then("The Rabbit class instance should be returned")
	public void theClassInstanceShouldBeReturned() {
		if(test_value instanceof Rabbit){
			Assertions.assertTrue(true);
		} else {
			Assertions.assertFalse(false);
		}
	}

	@Then("Class Reference {string} throws IllegalArgumentException")
	public void throwsIllegalArgumentException(String arg0) {
		Assertions.assertThrows(IllegalArgumentException.class,
			() -> Organism.getOrganism(arg0)
			);
	}
}
