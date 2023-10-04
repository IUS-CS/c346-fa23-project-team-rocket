package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import team.rocket.AbstractOrganism;

public class Grass {
    team.rocket.Grass g;
    team.rocket.Grass g2;
    team.rocket.AbstractOrganism grid[][];
    AbstractOrganism[] neighbors;
    int y;
    int x;

    @Given("A new grass is grown")
    public void aNewGrassIsGrown(){g = new team.rocket.Grass();}

    @Then("The grass should be unable to grow")
    public void theGrassShouldBeUnableToGrow(){Assertions.assertEquals(true, g.growthStatus());}

    @Given("A grass's growth is reset")
    public void aGrasssGrowthIsReset(){g.resetGrown();}

    @Then("The grass should be able to grow")
    public void theGrassShouldBeAbleToGrow(){Assertions.assertEquals(g.growthStatus(),false);}

    @Then("A new grass should be grown")
    public void aNewGrassShouldBeGrown(){
        neighbors[1]=g;
        neighbors[2]=g;
        neighbors[3]=g;
        y=3;
        x=2;
        g.grow(grid,neighbors,y,x);
        Assertions.assertEquals(grid[2][2],g);
        }

}
