package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import team.rocket.Entities.AbstractAnimal;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Entities.Rabbit;
import team.rocket.Enums.Direction;
import team.rocket.Map;

public class Fox {
    team.rocket.Entities.Fox f;
    team.rocket.Entities.Fox f2;
    Map map = new Map();
    AbstractOrganism[][] grid = map.getGrid();
    AbstractAnimal[] neighbors = new AbstractAnimal[4];
    int y;
    int x;

    @Given("A new fox is born")
    public void aNewFoxIsBorn() {
        f = new team.rocket.Entities.Fox();
    }

    @Then("The fox should be full")
    public void theFoxShouldBeFull(){Assertions.assertEquals(100,f.getHunger());}

    @Then("A fox can't move")
    public void aFoxCantMove(){
        map.getGrid()[3][2]=f;
        f.move(map,y,x);
        Assertions.assertEquals(map.getGrid()[3][2],f);
    }

    @And("A fox loses hunger")
    public void aFoxLosesHunger() {
        f.reduceHunger();
    }

    @And("A fox's movement is reset")
    public void aFoxsMovementIsReset() {
        f.resetMove();
    }

    @Then("A fox should have ninety hunger")
    public void aFoxShouldHaveNinetyHunger() {
        Assertions.assertEquals(f.getHunger(),90);
    }

    @Then("A fox can move")
    public void aFoxCanMove(){
        f2 = new team.rocket.Entities.Fox();
        grid[1][2] = f2;
        grid[3][2] = f2;
        grid[2][1] = f2;
        y=2;
        x=2;
        f.move(map,y,x);
        Assertions.assertEquals(map.getGrid()[2][3],f);
    }

    @And("There are rabbits")
    public void thereAreRabbits(){
        grid[1][2] = new Rabbit();
        grid[3][2] = new Rabbit();
        grid[2][1] = new Rabbit();
        grid[2][3] = new Rabbit();
    }

    @Then("The fox will eat nearby rabbits")
    public void theFoxWillEatNearbyRabbits(){
        Rabbit r = new Rabbit();
        grid[1][2] = r;
        grid[3][2] = r;
        grid[2][1] = r;
        grid[2][3] = r;
        map.setGrid(grid);
        f.move(map,2,2);
        Assertions.assertNull(grid[2][2]);
        Assertions.assertEquals(100, f.getHunger());
    }

    @Then("The Fox can eat rabbits")
    public void theFoxCanEatRabbits(){
        grid[2][2] = new Rabbit();
        f.eat(map,2,2);
    }

}
