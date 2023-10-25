package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Entities.Rabbit;
import team.rocket.Entities.AbstractAnimal;


import java.util.Arrays;

import static org.junit.Assert.*;

public class Map {
    /* Constructor-related tests*/

    @Given("a {int} by {int} map is created")
    public void aByMapIsCreated(int arg0, int arg1) {
        team.rocket.Map map = new team.rocket.Map(arg0, arg1);

        assertNotNull(map);
    }

    @Then("the map should be {int} by {int}")
    public void theMapShouldBeBy(int arg0, int arg1) {
        team.rocket.Map map = new team.rocket.Map(arg0, arg1);

        assertEquals(map.getWidth(), arg0);
        assertEquals(map.getHeight(), arg1);
    }

    @Then("the grid should be null")
    public void theGridShouldBeNull() {
        team.rocket.Map map = new team.rocket.Map(0, 1);

        assertNull(map.getGrid());
    }

    @And("the grid should not be null")
    public void theGridShouldNotBeNull() {
        team.rocket.Map map = new team.rocket.Map(1, 1);
        assertNotNull(map.getGrid());
    }

    @Given("a map is created with a grid")
    public void aMapIsCreatedWithAGrid() {
        AbstractOrganism[][] grid = new AbstractOrganism[2][2];
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should have the given dimensions and given contents")
    public void theMapShouldHaveTheGivenDimensionsAndGivenContents() {
        AbstractOrganism[][] grid = new AbstractOrganism[2][2];
        team.rocket.Map map = new team.rocket.Map(grid);

        assertTrue(Arrays.deepEquals(grid, map.getGrid()));
        assertEquals(grid[0].length, map.getWidth());
        assertEquals(grid.length, map.getHeight());
    }

    /* Empty/full-related tests */

    @Given("an empty map is created")
    public void anEmptyMapIsCreated() {
        AbstractOrganism[][] grid = new AbstractOrganism[1][1];
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should be empty")
    public void theMapShouldBeEmpty() {
        AbstractOrganism[][] grid = new AbstractOrganism[1][1];
        team.rocket.Map map = new team.rocket.Map(grid);

        assertTrue(map.isEmpty());
    }

    @Given("a map of all rabbits is created")
    public void aMapOfAllRabbitsIsCreated() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{new Rabbit()}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should be full")
    public void theMapShouldBeFull() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{new Rabbit()}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertTrue(map.isFull());
    }

    @Given("a nonempty map is created")
    public void aNonemptyMapIsCreated() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{null, null}, {new Rabbit(), null}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should not be empty")
    public void theMapShouldNotBeEmpty() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{null, null}, {new Rabbit(), null}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertFalse(map.isEmpty());
    }

    @Given("a nonfull map is created")
    public void aNonfullMapIsCreated() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{new Rabbit(), null}, {new Rabbit(), new Rabbit()}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should not be full")
    public void theMapShouldNotBeFull() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{new Rabbit(), null}, {new Rabbit(), new Rabbit()}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertFalse(map.isFull());
    }

    /* Add/remove organism-related tests */

    @Given("a space has no organism")
    public void aSpaceHasNoOrganism() {
        team.rocket.Map map = new team.rocket.Map(2, 2);

        assertNull(map.getGrid()[1][1]);
    }

    @When("an organism is added to the space")
    public void anOrganismIsAddedToTheSpace() {
        team.rocket.Map map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);
    }

    @Then("the space should have the given organism")
    public void theSpaceShouldHaveTheGivenOrganism() {
        team.rocket.Map map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);

        assertEquals(rabbit, map.getGrid()[1][1]);
    }

    @Given("a space has an organism")
    public void aSpaceHasAnOrganism() {
        team.rocket.Map map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);
    }

    @When("an organism is removed from the space")
    public void anOrganismIsRemovedFromTheSpace() {
        team.rocket.Map map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);
        map.removeOrganism(1, 1);
    }

    @Then("the space should be empty")
    public void theSpaceShouldBeEmpty() {
        team.rocket.Map map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);
        map.removeOrganism(1, 1);

        assertNull(map.getGrid()[1][1]);
    }
}
