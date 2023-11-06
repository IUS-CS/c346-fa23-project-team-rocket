package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import team.rocket.Entities.Fox;
import team.rocket.Entities.Rabbit;

import static org.junit.Assert.assertEquals;

public class Vision {
    team.rocket.Entities.AbstractAnimal animal;

    @Given("an animal is a rabbit")
    public void anAnimalIsARabbit() {
        animal = new team.rocket.Entities.Rabbit();
    }

    @Then("the animal should see {int} tiles away")
    public void theAnimalShouldSeeTilesAway(int arg0) {
        int vision; // Value is -1 when no applicable value can be found

        if (animal.getClass().equals(team.rocket.Entities.Rabbit.class)) {
            vision = team.rocket.Entities.Rabbit.getVision();
        } else if (animal.getClass().equals(team.rocket.Entities.Fox.class)) {
            vision = team.rocket.Entities.Fox.getVision();
        } else {
            vision = -1;
        }

        assertEquals(arg0, vision);
    }

    @Given("an animal is a fox")
    public void anAnimalIsAFox() {
        animal = new team.rocket.Entities.Fox();
    }
}
