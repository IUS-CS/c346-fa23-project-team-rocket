package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import team.rocket.Entities.AbstractOrganism;

public class Grass {
    team.rocket.Entities.Grass g;
    team.rocket.Entities.Grass g2;
    AbstractOrganism grid[][];
    AbstractOrganism[] neighbors;
    int y;
    int x;

    @Given("A new grass is grown")
    public void aNewGrassIsGrown(){g = new team.rocket.Entities.Grass();}

    @Then("The grass should be unable to grow")
    public void theGrassShouldBeUnableToGrow(){Assertions.assertEquals(true, g.growthStatus());}

    @Given("A grass's growth is reset")
    public void aGrasssGrowthIsReset(){g.resetGrown();}

    @Then("The grass should be able to grow")
    public void theGrassShouldBeAbleToGrow(){Assertions.assertEquals(g.growthStatus(),false);}



}
