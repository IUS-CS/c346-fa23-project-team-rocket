package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Entities.Carrot;
import team.rocket.Entities.Fox;
import team.rocket.Entities.Grass;
import team.rocket.Entities.OrganismFactory;
import team.rocket.Entities.Rabbit;
import team.rocket.Enums.Direction;
import team.rocket.IO.UI;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.*;

public class Map {
    /* Constructor-related tests*/
    team.rocket.Map map;
    HashMap<Direction, AbstractOrganism> neighbors;
    HashMap<Direction, Character> charNeighbors;

    @BeforeAll
    public static void beforeAll(){
        OrganismFactory.getInstance().registerOrganism("Rabbit", new Rabbit());
        OrganismFactory.getInstance().registerOrganism("Carrot", new Carrot());
        OrganismFactory.getInstance().registerOrganism("Fox", new Fox());
        OrganismFactory.getInstance().registerOrganism("Grass", new Grass());
    }

    @Before
    public void before(){
        map = null;
        neighbors = null;
        charNeighbors = null;
    }

    @Given("a {int} by {int} map is created")
    public void aByMapIsCreated(int arg0, int arg1) {
        map = new team.rocket.Map(arg0, arg1);

        assertNotNull(map);
    }

    @Then("the map should be {int} by {int}")
    public void theMapShouldBeBy(int arg0, int arg1) {
        map = new team.rocket.Map(arg0, arg1);

        assertEquals(map.getWidth(), arg0);
        assertEquals(map.getHeight(), arg1);
    }

    @Then("the grid should be null")
    public void theGridShouldBeNull() {
        map = new team.rocket.Map(0, 1);

        assertNull(map.getGrid());
    }

    @And("the grid should not be null")
    public void theGridShouldNotBeNull() {
        map = new team.rocket.Map(1, 1);
        assertNotNull(map.getGrid());
    }

    @Given("a map is created with a grid")
    public void aMapIsCreatedWithAGrid() {
        AbstractOrganism[][] grid = new AbstractOrganism[2][2];
        map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should have the given dimensions and given contents")
    public void theMapShouldHaveTheGivenDimensionsAndGivenContents() {
        AbstractOrganism[][] grid = new AbstractOrganism[2][2];
        map = new team.rocket.Map(grid);

        assertTrue(Arrays.deepEquals(grid, map.getGrid()));
        assertEquals(grid[0].length, map.getWidth());
        assertEquals(grid.length, map.getHeight());
    }

    /* Empty/full-related tests */

    @Given("an empty map is created")
    public void anEmptyMapIsCreated() {
        AbstractOrganism[][] grid = new AbstractOrganism[1][1];
        map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should be empty")
    public void theMapShouldBeEmpty() {
        AbstractOrganism[][] grid = new AbstractOrganism[1][1];
        map = new team.rocket.Map(grid);

        assertTrue(map.isEmpty());
    }

    @Given("a map of all rabbits is created")
    public void aMapOfAllRabbitsIsCreated() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{new Rabbit()}};
        map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should be full")
    public void theMapShouldBeFull() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{new Rabbit()}};
        map = new team.rocket.Map(grid);

        assertTrue(map.isFull());
    }

    @Given("a nonempty map is created")
    public void aNonemptyMapIsCreated() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{null, null}, {new Rabbit(), null}};
        map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should not be empty")
    public void theMapShouldNotBeEmpty() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{null, null}, {new Rabbit(), null}};
        map = new team.rocket.Map(grid);

        assertFalse(map.isEmpty());
    }

    @Given("a nonfull map is created")
    public void aNonfullMapIsCreated() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{new Rabbit(), null}, {new Rabbit(), new Rabbit()}};
        map = new team.rocket.Map(grid);

        assertNotNull(map);
    }

    @Then("the map should not be full")
    public void theMapShouldNotBeFull() {
        AbstractOrganism[][] grid = new AbstractOrganism[][] {{new Rabbit(), null}, {new Rabbit(), new Rabbit()}};
        map = new team.rocket.Map(grid);

        assertFalse(map.isFull());
    }

    /* Add/remove organism-related tests */

    @Given("a space has no organism")
    public void aSpaceHasNoOrganism() {
        map = new team.rocket.Map(2, 2);

        assertNull(map.getGrid()[1][1]);
    }

    @When("an organism is added to the space")
    public void anOrganismIsAddedToTheSpace() {
        map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);
    }

    @Then("the space should have the given organism")
    public void theSpaceShouldHaveTheGivenOrganism() {
        map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);

        assertEquals(rabbit, map.getGrid()[1][1]);
    }

    @Given("a space has an organism")
    public void aSpaceHasAnOrganism() {
        map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);
    }

    @When("an organism is removed from the space")
    public void anOrganismIsRemovedFromTheSpace() {
        map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);
        map.removeOrganism(1, 1);
    }

    @Then("the space should be empty")
    public void theSpaceShouldBeEmpty() {
        map = new team.rocket.Map(2, 2);
        AbstractOrganism rabbit = new Rabbit();
        map.addOrganism(rabbit, 1, 1);
        map.removeOrganism(1, 1);

        assertNull(map.getGrid()[1][1]);
    }

    @When("an organism is added to space \\({int} , {int})")
    public void anOrganismIsAddedToSpace(int row, int col) {
        map.addOrganism(OrganismFactory.getInstance().createOrganism("Rabbit"), row, col);
    }

    @Then("Space \\({int} , {int}) should have {int} neighbors")
    public void spaceShouldHaveNeighbors(int row, int col, int numNeighbors) {

        Assertions.assertEquals(numNeighbors, map.getNeighbors(row, col).size());
    }

    @And("a Rabbit is added to space \\({int} , {int})")
    public void aRabbitIsAddedToSpace(int row, int col) {
        map.addOrganism(OrganismFactory.getInstance().createOrganism("Rabbit"), row, col);
    }

    @And("The organisms {string} neighbor is {string}")
    public void theOrganismsNeighborIs(String direction, String organism) {
        AbstractOrganism abstractOrganism = neighbors.get(Direction.directionFromString(direction));
        if(organism.equalsIgnoreCase("null")){
            Assertions.assertNull(abstractOrganism);
        } else {
            Assertions.assertSame(OrganismFactory.getInstance().createOrganism(organism).getClass(), abstractOrganism.getClass());
        }
    }

    @And("The organisms {string} character neighbor is {string}")
    public void theOrganismsCharacterNeighborIs(String direction, String character) {
        char c = charNeighbors.get(Direction.directionFromString(direction));
        if(Objects.equals(character, " ")) {
            Assertions.assertEquals(' ', c);
        } else {
            Assertions.assertEquals(character, String.valueOf(c));
        }

    }

    @And("Space \\({int} , {int}) should have {int} neighbor characters")
    public void spaceShouldHaveNeighborCharacters(int row, int col, int numNeighbors) {
        Assertions.assertEquals(numNeighbors, map.getNeighborsAsCharacter(row, col).size());
    }

    @And("All of space \\({int} , {int}) neighbors are null")
    public void allOfSpaceNeighborsAreNull(int row, int col) {
        for(java.util.Map.Entry<Direction, AbstractOrganism> entry: map.getNeighbors(row,col).entrySet()){
            Assertions.assertNull(entry.getValue());
        }
    }

    @And("All of space \\({int} , {int}) character neighbors are {string}")
    public void allOfSpaceNeighborsAre(int row, int col, String character) {
        for(java.util.Map.Entry<Direction, Character> entry: map.getNeighborsAsCharacter(row,col).entrySet()){
            Assertions.assertEquals(character, String.valueOf(entry.getValue()));
        }
    }

    @And("\\({int} , {int}) neighbors are calculated")
    public void neighborsAreCalculated(int row, int col) {
        neighbors = map.getNeighbors(row, col);
        charNeighbors = map.getNeighborsAsCharacter(row, col);
    }
}
