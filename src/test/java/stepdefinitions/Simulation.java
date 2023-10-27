package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import team.rocket.Entities.Carrot;
import team.rocket.Entities.Fox;
import team.rocket.Entities.Grass;
import team.rocket.Entities.OrganismFactory;
import team.rocket.Entities.Rabbit;
import team.rocket.Map;

import java.time.Instant;
import java.time.InstantSource;


public class Simulation {
    team.rocket.Simulation simulation;
    team.rocket.Map map;

    static OrganismFactory factory = OrganismFactory.getInstance();
    /**
     * java time library Instant instance
     */
    Instant instant;
    /**
     * start time used for some tests
     */
    long startTime;


    @BeforeAll
    public static void beforeAll(){
        factory.registerOrganism("Rabbit", new Rabbit());
        factory.registerOrganism("Grass", new Grass());
        factory.registerOrganism("Carrot", new Carrot());
        factory.registerOrganism("Fox", new Fox());
    }
    @Before
    public void before(){
        simulation = null;
        map = null;
    }


    @Given("simulation is given a full map")
    public void simulationIsGivenAFullMap() {
        //Create full map
        map = new Map(1, 1);
        map.addOrganism(factory.createOrganism("Rabbit"), 0, 0);
        //Pass the map to simulation
        simulation = new team.rocket.Simulation(map);
    }

    @When("The run method is run for the simulation")
    public void theRunMethodIsRunForTheSimulation() {
        simulation.run();
    }

    @Then("The getCurrentDay method returns a value less than {int}")
    public void theTheGetCurrentDayMethodReturnsAValueLessThan(int dayComparison) {
        Assertions.assertTrue(simulation.getCurrentDay() < dayComparison);
    }

    @Given("There is an empty simulation")
    public void thereIsAnEmptySimulation() {
        //create an empty map and pass it to simulation
        map = new Map(1, 1);
        simulation = new team.rocket.Simulation(map);
    }

    @Then("The getCurrentDay method returns a value equal to DEFAULT_DAYS_PER_RUN")
    public void theGetCurrentDayMethodReturnsAValueEqualToDEFAULT_DAYS_PER_RUN() {
        Assertions.assertEquals(team.rocket.Simulation.DEFAULT_DAYS_PER_RUN, simulation.getCurrentDay());
    }

    @Then("The getCurrentDay method returns a value equal to {int}")
    public void theGetCurrentDayMethodReturnsAValueEqualTo(int expectedCurrentDay) {
        Assertions.assertEquals(expectedCurrentDay, simulation.getCurrentDay());
    }

    @And("The current time is recorded")
    public void theCurrentTimeIsRecorded() {
        instant = Instant.now();
        startTime = instant.toEpochMilli();
    }

    @Then("The current time is greater than the expected offset")
    public void theCurrentTimeIsGreaterThanTheExpectedOffset() {
        Assertions.assertTrue(instant.toEpochMilli() > startTime
                + team.rocket.Simulation.DEFAULT_DAYS_PER_RUN
                * team.rocket.Simulation.DEFAULT_TIME_STEPS_PER_DAY
                * team.rocket.Simulation.DEFAULT_MILLISECONDS_PER_TIME_STEP);
    }

    @When("the DaysPerRun is set too {int}")
    public void theDaysPerRunIsSetToo(int newDaysPerRun) {
        simulation.setDaysPerRun(newDaysPerRun);
    }

    @When("We set the TimeStepsPerDay to {int}")
    public void weSetTheTimeStepsPerDayTo(int newTimeStepsPerDay) {
        simulation.setTimeStepsPerDay(newTimeStepsPerDay);
    }

    @When("We set the MillisecondsPerTimeStep to {int}")
    public void weSetTheMillisecondsPerTimeStepTo(int newMillisecondsPerTimeStep) {
        simulation.setMillisecondsPerTimeStep(newMillisecondsPerTimeStep);
    }

    @And("The number of timesteps passed is equal to the default value")
    public void theNumberOfTimestepsPassedIsEqualToTheExpectedValue() {
        Assertions.assertEquals(team.rocket.Simulation.DEFAULT_DAYS_PER_RUN*team.rocket.Simulation.DEFAULT_TIME_STEPS_PER_DAY, simulation.getCurrentDay()*simulation.getCurrentTimeStep());
    }

    @Given("There is a default simulation")
    public void thereIsADefaultSimulation() {
        simulation = new team.rocket.Simulation();
    }

    @Then("The number of rabbits in the map is equal to the Rabbit count")
    public void theNumberOfRabbitsInTheMapIsEqualToTheRabbitCount() {
        map = simulation.getMap();
        int rabbitCount = 0;
        for(int i = 0; i < map.getHeight(); i++){
            for(int j = 0; j < map.getWidth(); j++){
                if (map.getOrganism(i, j) != null){ rabbitCount++;}
            }
        }
        Assertions.assertEquals(rabbitCount, Rabbit.getCount());
    }
}
