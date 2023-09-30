package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import team.rocket.AbstractOrganism;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Map {
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

    @Given("an empty map is created")
    public void anEmptyMapIsCreated() {
        team.rocket.AbstractOrganism[][] grid = new AbstractOrganism[1][1];
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should be empty")
    public void theMapShouldBeEmpty() {
        team.rocket.AbstractOrganism[][] grid = new AbstractOrganism[1][1];
        team.rocket.Map map = new team.rocket.Map(grid);

        assertTrue(map.isEmpty());
    }

    @Given("a map of all rabbits is created")
    public void aMapOfAllRabbitsIsCreated() {
        team.rocket.AbstractOrganism[][] grid = new team.rocket.AbstractOrganism[][] {{new team.rocket.Rabbit()}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should be full")
    public void theMapShouldBeFull() {
        team.rocket.AbstractOrganism[][] grid = new team.rocket.AbstractOrganism[][] {{new team.rocket.Rabbit()}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertTrue(map.isFull());
    }

    @Given("a nonempty map is created")
    public void aNonemptyMapIsCreated() {
        team.rocket.AbstractOrganism[][] grid = new team.rocket.AbstractOrganism[][] {{null, null}, {new team.rocket.Rabbit(), null}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should not be empty")
    public void theMapShouldNotBeEmpty() {
        team.rocket.AbstractOrganism[][] grid = new team.rocket.AbstractOrganism[][] {{null, null}, {new team.rocket.Rabbit(), null}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertFalse(map.isEmpty());
    }

    @Given("a nonfull map is created")
    public void aNonfullMapIsCreated() {
        team.rocket.AbstractOrganism[][] grid = new team.rocket.AbstractOrganism[][] {{new team.rocket.Rabbit(), null}, {new team.rocket.Rabbit(), new team.rocket.Rabbit()}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should not be full")
    public void theMapShouldNotBeFull() {
        team.rocket.AbstractOrganism[][] grid = new team.rocket.AbstractOrganism[][] {{new team.rocket.Rabbit(), null}, {new team.rocket.Rabbit(), new team.rocket.Rabbit()}};
        team.rocket.Map map = new team.rocket.Map(grid);

        assertFalse(map.isFull());
    }

    @Given("a map is created with a grid")
    public void aMapIsCreatedWithAGrid() {
        team.rocket.AbstractOrganism[][] grid = new team.rocket.AbstractOrganism[2][2];
        team.rocket.Map map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should have the given dimensions and given contents")
    public void theMapShouldHaveTheGivenDimensionsAndGivenContents() {
        team.rocket.AbstractOrganism[][] grid = new team.rocket.AbstractOrganism[2][2];
        team.rocket.Map map = new team.rocket.Map(grid);

        assertTrue(Arrays.deepEquals(grid, map.getGrid()));
        assertEquals(grid[0].length, map.getWidth());
        assertEquals(grid.length, map.getHeight());
    }
}
