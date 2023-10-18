package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.Entities.Rabbit;

public class OrganismFactory {
    team.rocket.Entities.OrganismFactory factory;
    team.rocket.Entities.OrganismFactory factoryTwo;

    @Given("there is a previous number of rabbits we should reset it")
    public void thereIsAPreviousNumberOfRabbitsWeShouldResetIt() {
        new Rabbit().setCount(0);
    }

    @And("We retrieve an OrganismFactory instances")
    public void weRetrieveOrganismFactoryInstances() {
        factory = team.rocket.Entities.OrganismFactory.getInstance();
    }


    @Given("We retrieve another OrganismFactory instance")
    public void weRetrieveAnotherOrganismFactoryInstance() {
        factoryTwo = team.rocket.Entities.OrganismFactory.getInstance();
    }


    @Then("The OrganismFactory instances should be the same instance")
    public void theOrganismFactoryInstancesShouldBeTheSameInstance() {
        Assertions.assertEquals(factory, factoryTwo);
    }


    @And("the OrganismFactory instances shouldnt be null")
    public void theOrganismFactoryInstancesShouldntBeNull() {
        Assertions.assertTrue(factory != null && factoryTwo != null);
    }


    @Then("We should get null when when creating a nonexistent organism")
    public void weShouldGetNullWhenWhenCreatingANonexistentOrganism() {
        Assertions.assertNull(factory.createOrganism(""));
    }


    @When("We register the Rabbit class")
    public void weRegisterTheRabbitClass() {
        Rabbit rabbit = new Rabbit();
        rabbit.reduceCount();
        factory.registerOrganism("Rabbit", rabbit);
    }


    @Then("The number of Rabbits should be {int}")
    public void theNumberOfRabbitsShouldBe(int expectedRabbitCount) {
        Assertions.assertEquals(expectedRabbitCount, Rabbit.getCount());
    }


    @And("We create {int} Rabbits with the OrganismFactory")
    public void weCreateRabbitsWithTheOrganismFactory(int rabbitsToCreate) {
        for(int i = 0; i < rabbitsToCreate; i++){
            factory.createOrganism("Rabbit");
        }
    }

}
