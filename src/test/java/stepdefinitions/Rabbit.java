package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team.rocket.Entities.AbstractAnimal;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Map;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Rabbit {
    team.rocket.Entities.Rabbit rabbit;
    List<team.rocket.Entities.Rabbit> rabbitList;
    team.rocket.Map map = new Map();
    AbstractOrganism[][] grid = map.getGrid();
    int y;
    int x;

    @Given("{int} rabbits are created")
    public void rabbitsAreCreated(int arg0) {
        rabbitList = new LinkedList<>();

        for (int i = 0; i < arg0; i++) {
            rabbitList.add(new team.rocket.Entities.Rabbit());
        }
    }

    @Then("there should be {int} rabbits")
    public void thereShouldBeRabbits(int arg0) {
        assertEquals(arg0, team.rocket.Entities.Rabbit.getCount());
    }

    @Then("toIcon should return R")
    public void toiconShouldReturnR() {
        assertEquals('R', team.rocket.Entities.Rabbit.toIcon());
    }

    @Given("a new rabbit is born")
    public void aNewRabbitIsBorn() {
        rabbit = new team.rocket.Entities.Rabbit();
    }

    @Then("the rabbit can't move")
    public void theRabbitCanTMove() {
        grid[3][2] = rabbit;
        map.setGrid(grid);
        rabbit.move(map, y, x);
        assertEquals(grid[3][2], rabbit);
    }

    @When("the rabbit's movement is reset")
    public void theRabbitSMovementIsReset() {
        rabbit.resetMove();
    }

    @Then("the rabbit can move")
    public void theRabbitCanMove() {
        team.rocket.Entities.Rabbit rabbit2 = new team.rocket.Entities.Rabbit();
        grid[1][2] = rabbit2;
        grid[3][2] = rabbit2;
        grid[2][1] = rabbit2;
        y = 2;
        x = 2;
        rabbit.move(map,y,x);
        assertEquals(grid[2][3], rabbit);
    }

    @Then("the rabbit can eat")
    public void theRabbitCanEat() {
        for (int i=3; i>0; i--) {
            rabbit.reduceHunger();
        }
        team.rocket.Entities.Carrot carrot = new team.rocket.Entities.Carrot();
        grid[1][2] = carrot;
        grid[3][2] = carrot;
        grid[2][1] = carrot;
        grid[2][3] = carrot;
        y = 2;
        x = 2;
        rabbit.move(map,y,x);
        assertNull(grid[2][2]);
        assertEquals(95, rabbit.getHunger());
    }
}
