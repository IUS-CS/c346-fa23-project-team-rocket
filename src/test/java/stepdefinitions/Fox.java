package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import team.rocket.Entities.AbstractAnimal;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Map;

public class Fox {
    team.rocket.Entities.Fox f;
    team.rocket.Entities.Fox f2;
    Map map = new Map();
    AbstractAnimal[] neighbors = new AbstractAnimal[4];
    int y;
    int x;

    @Given("A new fox is born")
    public void aNewFoxIsBorn(){f = new team.rocket.Entities.Fox();}

    @Then("The fox should be full")
    public void theFoxShouldBeFull(){Assertions.assertEquals(f.getHunger(),100);}

    @Then("A fox can't move")
    public void aFoxCantMove(){
        map.getGrid()[3][2]=f;
        f.move(map,neighbors,y,x);
        Assertions.assertEquals(map.getGrid()[3][2],f);
    }

    @And("A fox loses hunger")
    public void aFoxLosesHunger(){f.reduceHunger();}

    @And("A fox's movement is reset")
    public void aFoxsMovementIsReset(){f.resetMove();}

    @Then("A fox should have ninety hunger")
    public void aFoxShouldHaveNinetyHunger(){Assertions.assertEquals(f.getHunger(),90);}

    @Then("A fox can move")
    public void aFoxCanMove(){
        f2 = new team.rocket.Entities.Fox();
        neighbors[0]=null;
        neighbors[1]=f2;
        neighbors[2]=f2;
        neighbors[3]=f2;
        y=3;
        x=2;
        f.move(map,neighbors,y,x);
        Assertions.assertEquals(map.getGrid()[2][2],f);
    }

}
