package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team.rocket.Entities.AbstractAnimal;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Rabbit {
    team.rocket.Entities.Rabbit rabbit;
    List<team.rocket.Entities.Rabbit> rabbitList;
    AbstractAnimal[][] grid = new AbstractAnimal[10][10];
    AbstractAnimal[] neighbors = new AbstractAnimal[4];
    int y;
    int x;

//    @Given("{int} rabbits are created")
//    public void rabbitsAreCreated(int arg0) {
//        rabbitList = new LinkedList<>();
//
//        for (int i = 0; i < arg0; i++) {
//            rabbitList.add(new team.rocket.Entities.Rabbit());
//        }
//    }
//
//    @Then("there should be {int} rabbits")
//    public void thereShouldBeRabbits(int arg0) {
//        assertEquals(arg0, team.rocket.Entities.Rabbit.getCount());
//    }
//
//    @Then("toIcon should return R")
//    public void toiconShouldReturnR() {
//        assertEquals('R', team.rocket.Entities.Rabbit.toIcon());
//    }
//
//    @Given("a new rabbit is born")
//    public void aNewRabbitIsBorn() {
//        rabbit = new team.rocket.Entities.Rabbit();
//    }
//
//    @Then("the rabbit can't move")
//    public void theRabbitCanTMove() {
//        grid[3][2] = rabbit;
//        rabbit.move(grid, y, x);
//        assertEquals(grid[3][2], rabbit);
//    }
//
//    @When("the rabbit's movement is reset")
//    public void theRabbitSMovementIsReset() {
//        rabbit.resetMove();
//    }
//
//    @Then("the rabbit can move")
//    public void theRabbitCanMove() {
//        team.rocket.Entities.Rabbit rabbit2 = new team.rocket.Entities.Rabbit();
//        neighbors[0] = null;
//        neighbors[1] = rabbit2;
//        neighbors[2] = rabbit2;
//        neighbors[3] = rabbit2;
//        y = 3;
//        x = 2;
//        rabbit.move(grid,y,x);
//        assertEquals(grid[2][2], rabbit);
//    }
}
