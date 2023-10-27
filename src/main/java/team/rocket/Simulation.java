package team.rocket;

import java.lang.Runnable;

import team.rocket.Entities.AbstractAnimal;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Entities.OrganismFactory;
import team.rocket.Entities.Rabbit;
import team.rocket.Enums.Direction;
/*
import team.rocket.Handlers.Terminal.FlagHandler;
import team.rocket.Handlers.Terminal.GridSizeFlagHandler;
import team.rocket.Handlers.Terminal.InitialOrganismCountFlagHandler;
import team.rocket.Handlers.Terminal.TerminalFlagRequest;
 */

/**
 * team.rocket.Simulation is the class that controls the backend of the simulation. It contains a grid of animals. It also runs
 * multiple time steps and days worth of simulated time during which animals can breed.
 *
 * @author Dale Morris, Jon Roberts
 * @version 0.4.0
 * @since 0.1.0
 */
public class Simulation implements Runnable {
    private Map map; // Grid that organisms can exist in
    public static final int DEFAULT_DAYS_PER_RUN = 10; // The default number of days in each run
    public static final int DEFAULT_TIME_STEPS_PER_DAY = 10; // The default number of time steps in each day
    public static final int DEFAULT_MILLISECONDS_PER_TIME_STEP = 100; // The default number of real-world milliseconds in each time step
    private int currentDay; // The current day of the simulation
    private int currentTimeStep; // The current time step within the current day of the simulation
    private int daysPerRun; // The number of days that make up each run of the simulation
    private int timeStepsPerDay; // The number of time steps that make up each day
    private int millisecondsPerTimeStep; // The number of real-world milliseconds that make up each time step
    // private FlagHandler flagHandler = new GridSizeFlagHandler();
    private boolean mapIsFull = false;

    /**
     * Returns a new team.rocket.Simulation object with the given constraints.
     *
     * @param mapWidth      the number of columns of the simulated grid
     * @param mapHeight     the number of rows of the simulated grid
     */
    public Simulation(int mapWidth, int mapHeight) {
        map = new Map(mapWidth, mapHeight);
    }


    /**
     * Returns a new team.rocket.Simulation object with default constraints.
     * Contains one rabbit in the corner by default
     */
    Simulation() {
        map = new Map();
        //adds one rabbit in the top left corner by default
        map.addOrganism(OrganismFactory.getInstance().createOrganism("Rabbit"), 0,0 );
    }

    public Simulation(Map m){
        map = m;
    }

    /**
     * Simulates how the environment changes over time based on initial conditions and interactions among animals.
     */
    @Override
    public void run() {

        currentDay = 1;
        UI.outputGrid(currentDay, map);

        for (currentDay = 2; currentDay <= daysPerRun; currentDay++) { // Iterates through each day
            for (currentTimeStep = 1; currentTimeStep <= timeStepsPerDay; currentTimeStep++) { // Iterates through each time step in the current day
                try {
                    Thread.sleep(millisecondsPerTimeStep);
                } catch (InterruptedException e) {
                    System.out.println("There was an unexpected issue with the simulation.");
                    return;
                }
                moveAnimal();
            } // End of current day

            breed();
            if (mapIsFull) {
                return;
            }
            UI.outputGrid(currentDay, map);
        } // End of simulation
    }

    /**
     * Simulates breeding among the animals and creates a new entity when breeding occurs
     */
    private void breed() {
        int rabbitsBred = 0;
        int expectedBreeds = (int) Math.pow(2, currentDay-1);
        Map oldMap = map;    // A copy of the current map
        boolean hasBred;     // Indicates if the animal in the current grid space has bred yet

        /* Looks for an organism to breed */
        for (int i = 0; i < oldMap.getHeight(); i++) { // Iterates through each row of a copy of the grid
            hasBred = false;
            for (int j = 0; j < oldMap.getWidth(); j++) { // Iterates through each column of a copy of the grid
                if (map.getOrganism(i, j) != null) { // Found an animal

                    /* Looks for an empty space to put a new organism */
                    for (int k = 0; k < map.getHeight(); k++) {     // Iterates through each row of the grid
                        for (int l = 0; l < map.getWidth(); l++) {  // Iterates through each column of the grid
                            if (map.getOrganism(k, l) == null) {    // If the current grid space is empty
                                map.addOrganism(new Rabbit(), k, l);
                                rabbitsBred++;
                                hasBred = true;
                            }

                            if (rabbitsBred > expectedBreeds || hasBred) {
                                break;
                            }
                        }

                        if (rabbitsBred > expectedBreeds || hasBred) {
                            break;
                        }
                    }
                }

                if (map.isFull()) {
                    mapIsFull = true;
                    UI.outputGrid(currentDay, map);

                    System.out.println("The program has ended prematurely because there is no more space for animals.");
                    return;
                }
            }
        } // End of grid iteration
    }

    /**
     * Handles animal movement as the days progress
     */
    private void moveAnimal() {
        for (int i = 0; i < map.getHeight(); i++) { // Iterates through each row of the grid
            for (int j = 0; j < map.getWidth(); j++) { // Iterates through each column of the grid
                if (map.getOrganism(i, j) instanceof AbstractAnimal) { // Check if the object is an instance of AbstractAnimal
                    AbstractOrganism[] neighbors = new AbstractOrganism[4];
                    if (i == 0) {
                        neighbors[0] = new Rabbit();
                    } else {
                        neighbors[0] = map.getOrganism(i - 1, j);
                    }

                    if (i == map.getHeight() - 1) {
                        neighbors[1] = new Rabbit();
                    } else {
                        neighbors[1] = map.getOrganism(i + 1, j);
                    }

                    if (j == 0) {
                        neighbors[2] = new Rabbit();
                    } else {
                        neighbors[2] = map.getOrganism(i, j - 1);
                    }

                    if (j == map.getWidth() - 1) {
                        neighbors[3] = new Rabbit();
                    } else {
                        neighbors[3] = map.getOrganism(i, j + 1);
                    }

                    moveDirection((AbstractAnimal) map.getOrganism(i, j), neighbors, i, j);
                } else {
                    break; // Break from the loop if the object is not an instance of AbstractAnimal
                }
            }
        }
    }

    /**
     * Determines the movement positions of animals up or dowm and left or right
     *
     * @param animal    The animal being moved
     * @param neighbors The surrounding animals
     * @param y         the vertical movement of the grid
     * @param x         the horizontal movement of the grid
     */
    private void moveDirection(AbstractAnimal animal, AbstractOrganism[] neighbors, int y, int x) {
        Direction direction = animal.availableMovementSpace(neighbors);

        if (direction == null) {
            return;
        }

        if (direction == Direction.UP) {
            map.removeOrganism(y, x);
            map.addOrganism(animal, y-1, x);
        }

        if (direction == Direction.DOWN) {
            map.removeOrganism(y, x);
            map.addOrganism(animal, y+1, x);
        }

        if (direction == Direction.LEFT) {
            map.removeOrganism(y, x);
            map.addOrganism(animal, y, x-1);
        }

        if (direction == Direction.RIGHT) {
            map.removeOrganism(y, x);
            map.addOrganism(animal, y, x+1);
        }
    }

    public void setDaysPerRun(int daysPerRun) {
        this.daysPerRun = daysPerRun;
    }

    public void setTimeStepsPerDay(int timeStepsPerDay) {
        this.timeStepsPerDay = timeStepsPerDay;
    }

    public void setMillisecondsPerTimeStep(int millisecondsPerTimeStep) {
        this.millisecondsPerTimeStep = millisecondsPerTimeStep;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentTimeStep() {
        return currentTimeStep;
    }
}
