package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.Rabbit;

import java.util.Arrays;

public class OrganismFactory {
    team.rocket.OrganismFactory[] factories;

    @Then("The OrganismFactory instances should be the same instance")
    public void theOrganismFactoryInstancesShouldBeTheSameInstance() {
        if(factories.length > 1 && factories != null){
            int i = 0;
            int j = 1;
            boolean SameInstance = true;
            while(i < factories.length && j < factories.length){
                if (factories[0] != factories[1]) {
                    SameInstance = false;
                    break;
                }
                i++;
                j++;
            }
            Assertions.assertTrue(SameInstance);
        } else {
            Assertions.assertTrue(true);
        }
    }

    @When("We register the Rabbit class")
    public void weRegisterTheRabbitClass() {
        if(factories.length < 1){
            Assertions.fail("factory array is 0 length");
        }
        if(factories[0] == null){
            Assertions.fail("factory is null");
        }
        factories[0].registerOrganism("Rabbit", new Rabbit());
    }

    @Then("The Rabbit count should be {int}")
    public void theRabbitCountShouldBe(int rabbit_count) {
        Assertions.assertEquals(Rabbit.getCount(), rabbit_count);
    }



    @When("We create {} Rabbits with the OrganismFactory")
    public void weCreateRabbitsWithTheOrganismFactory(String num_rabbits) {
        if(factories.length < 1){
            Assertions.fail("Factory array length is 0");
        }
        if(factories[0].createOrganism("Rabbit") == null){
            Assertions.fail("Rabbit creation returned null");
        }
        for(int i = 0; i < Integer.parseInt(num_rabbits); i++) {
            factories[0].createOrganism("Rabbit");
        }
    }

    @Given("We retrieve {int} OrganismFactory instances")
    public void weRetrieveOrganismFactoryInstances(int num_instances) {
        factories = new team.rocket.OrganismFactory[num_instances];
        for(int i = 0; i < factories.length; i++){
            factories[i] = team.rocket.OrganismFactory.getInstance();
        }
    }

    @And("the OrganismFactory instances shouldnt be null")
    public void theOrganismFactoryInstancesShouldntBeNull() {
        for(team.rocket.OrganismFactory factory1: factories){
            if (factory1 == null){
                Assertions.fail();
            }
        }
    }


    @Then("There should be {} Rabbits")
    public void thereShouldBeRabbits(String arg0) {
        Assertions.assertEquals(Rabbit.getCount(), Integer.parseInt(arg0));
    }

    @Then("The number of Rabbits should be {int}")
    public void theNumberOfRabbitsShouldBe(int arg0) {
        Assertions.assertEquals(Rabbit.getCount(), arg0);
    }

    @Given("there is a previous number of rabbits we should reset it")
    public void thereIsAPreviousNumberOfRabbitsWeShouldResetIt() {
        Rabbit rabbit = new Rabbit();
        rabbit.setCount(0);
    }

    @Then("We should get null when when creating a nonexistent organism")
    public void weShouldGetNullWhenWhenCreatingANonexistentOrganism() {
        Assertions.assertNull(factories[0].createOrganism("nonexistent organism"));
    }
}
