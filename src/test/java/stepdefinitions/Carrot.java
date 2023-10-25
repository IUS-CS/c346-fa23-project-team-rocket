package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import team.rocket.Entities.AbstractOrganism;

public class Carrot {
    team.rocket.Entities.Carrot c;
    team.rocket.Entities.Carrot c2;
    AbstractOrganism grid[][];
    AbstractOrganism[] neighbors;
    int y;
    int x;

    @Given("A new Carrot is grown")
    public void aNewCarrotIsGrown(){c = new team.rocket.Entities.Carrot();}

    @Then("The Carrot should be unable to grow")
    public void theCarrotShouldBeUnableToGrow(){Assertions.assertEquals(true, c.growthStatus());}

    @Given("A Carrot's growth is reset")
    public void aCarrotsGrowthIsReset(){c.resetGrown();}

    @Then("The Carrot should be able to grow")
    public void theCarrotShouldBeAbleToGrow(){Assertions.assertEquals(c.growthStatus(),false);}

    @Then("The Carrot should have 25 nutrition")
    public void theCarrotShouldHave25Nutrition(){Assertions.assertEquals(c.getNutrition(),25);}

    @Then("A new Carrot should be grown")
    public void aNewCarrotShouldBeGrown(){
        neighbors[1]=c;
        neighbors[2]=c;
        neighbors[3]=c;
        y=3;
        x=2;
        c.grow(grid,neighbors,y,x);
        Assertions.assertEquals(grid[2][2],c);
        }

}
