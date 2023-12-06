package team.rocket;

import java.lang.Runnable;

import team.rocket.Entities.AbstractAnimal;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Entities.AbstractPlant;
import team.rocket.Entities.OrganismFactory;
import team.rocket.Enums.Direction;
import team.rocket.IO.UI;
import team.rocket.util.RandomManager;
import team.rocket.util.TimeManager;

import java.util.Arrays;
/*
import team.rocket.IO.Terminal.FlagHandler;
import team.rocket.IO.Terminal.GridSizeFlagHandler;
import team.rocket.IO.Terminal.InitialOrganismCountFlagHandler;
import team.rocket.IO.Terminal.TerminalFlagRequest;
 */

/**
 * team.rocket.Simulation is the class that controls the backend of the simulation. It contains a grid of animals. It also runs
 * multiple time steps and days worth of simulated time during which animals can breed.
 *
 * @author Dale Morris, Jon Roberts
 * @version 0.6.0
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
    private int breedChance = 25; //the % chance that two animals will breed

    private boolean printOutput = true;


    //Holds all of the useful one-off offsets
    private static final int[][] offsetArray = {
            {1, 1},
            {1, 0},
            {1, -1},
            {0, 1},
            {0, -1},
            {-1, 1},
            {-1, 0},
            {-1, -1}
    };

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
    public Simulation() {
        map = new Map();
        //adds two rabbits to the top left corner when the simulation starts running
        map.addOrganism(OrganismFactory.getInstance().createOrganism("Rabbit"), 0,0 );
        map.addOrganism(OrganismFactory.getInstance().createOrganism("Rabbit"),0,1 );
    }

    /**
     * Creates a simulation from a map
     * @param m the map to create a simulation from
     */
    public Simulation(Map m){
        map = m;
    }

    /**
     * Simulates how the environment changes over time based on initial conditions and interactions among animals.
     */
    @Override
    public void run() {
        if(printOutput) UI.outputGridViaMainThread(0, map);
        for (currentDay = 1; currentDay <= daysPerRun; currentDay++) { // Iterates through each day
            long startTime = TimeManager.getCurrentTime();
            int millisecondsPerDay = millisecondsPerTimeStep*timeStepsPerDay;

            for (currentTimeStep = 1; currentTimeStep <= timeStepsPerDay; currentTimeStep++) { // Iterates through each time step in the current day
                moveAnimal();
            } // End of current day

            currentTimeStep--; //For loop increments past the stopping step, this fixes that error

            breed();

            if (mapIsFull) {
                return;
            }
            //print out map
            if(printOutput) UI.outputGrid(currentDay, map);

            long currentTime = TimeManager.getCurrentTime();
            //Only sleep if computation time didn't take long enough
            if(currentTime < startTime + millisecondsPerDay){
                try {
                    Thread.sleep(startTime + millisecondsPerDay-currentTime);

                } catch (InterruptedException e) {
                    System.out.println("There was an unexpected issue with the simulation.");
                    return;
                }
            }

        } // End of simulation
        currentDay--; //For loop increments past the stopping date, this fixes that error
        //Decrements ensure that if the simulations step is checked that it isn't on day 11 or timestep 11 since those haven't occurred
    }

    /**
     * Simulates breeding among the animals and creates a new entity when breeding occurs
     * There is a chance that the animals breed based on the breedChance variable
     */
    private void breed() {
        int maxDistance = 4; // Maximum distance from parent for new animal to spawn
        Map oldMap = map; // A copy of the current map


        int randomValue;

        /* Looks for an organism to breed */
        for (int i = 0; i < oldMap.getHeight(); i++) { // Iterates through each row of a copy of the grid

            for (int j = 0; j < oldMap.getWidth(); j++) { // Iterates through each column of a copy of the grid
                if (map.getOrganism(i, j) != null && !map.getOrganism(i,j).getBreeding()) { // Found an animal and check breeding limit
                    char species = map.getOrganism(i, j).instancedToIcon();
                    // Check if there is an organism on the tile next to them (left, right, up, or down)
                    boolean breedingPartner = false;
                    if(i > 0 &&
                            map.getOrganism(i - 1, j) != null &&
                            !map.getOrganism(i - 1, j).getBreeding() &&
                            (map.getOrganism(i - 1, j).instancedToIcon() == species)){
                        breedingPartner = true;
                        map.getOrganism(i - 1, j).breed();
                    }
                    else if(i < map.getHeight() - 1 &&
                            map.getOrganism(i + 1, j) != null &&
                            !map.getOrganism(i + 1, j).getBreeding() &&
                            (map.getOrganism(i + 1, j).instancedToIcon() == species)){
                        breedingPartner = true;
                        map.getOrganism(i + 1, j).breed();
                    }
                    else if(j > 0 &&
                            map.getOrganism(i, j - 1) != null &&
                            !map.getOrganism(i, j - 1).getBreeding() &&
                            (map.getOrganism(i, j - 1).instancedToIcon() == species)){
                        breedingPartner = true;
                        map.getOrganism(i, j - 1).breed();
                    }
                    else if(j < map.getWidth() - 1 &&
                            map.getOrganism(i, j + 1) != null &&
                            !map.getOrganism(i, j + 1).getBreeding() &&
                            (map.getOrganism(i, j + 1).instancedToIcon() == species)){
                        breedingPartner = true;
                        map.getOrganism(i, j + 1).breed();
                    }

                    if(breedingPartner){
                        // Generate a random value between 0 and 99
                        randomValue = RandomManager.getRandom().nextInt(100);

                        // Check if the random value is less than the breed chance
                        if (randomValue < breedChance) {
                            // Breed the animals in the closest available tile
                            int[] closestEmptyTile = findClosestEmptyTile(oldMap, i, j, maxDistance);
                            if (closestEmptyTile != null) {
                                map.getOrganism(i,j).breed();
                                if (species == 'R') {
                                    map.addOrganism(OrganismFactory.getInstance().createOrganism("Rabbit"), closestEmptyTile[0], closestEmptyTile[1]);
                                }
                                if (species == 'F') {
                                    map.addOrganism(OrganismFactory.getInstance().createOrganism("Fox"), closestEmptyTile[0], closestEmptyTile[1]);
                                }
                            }
                        }
                    }


                    if (map.isFull()) {
                        mapIsFull = true;
                        if (printOutput) {
                            UI.outputGrid(currentDay, map);
                            System.out.println("The program has ended prematurely because there is no more space for animals.");
                        }
                        return;
                    }
                }
            }
        } // End of grid iteration
    }

    /**
     * finds the closest empty tile to position y, x in the grid
     * only searchs within 1 tile orthogonally and diagonally
     * @param map the map to search
     * @param y the center y position
     * @param x the center x position
     * @return an array with the y position then the x position
     */
    private int[] findClosestEmptyTile(Map map, int y, int x, int maxDistance) {
        int[] closestEmptyTile = null;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.getOrganism(i, j) == null) {
                    int distance = Math.abs(i - y) + Math.abs(j - x);
                    if (distance < minDistance && distance <= maxDistance) {
                        minDistance = distance;
                        closestEmptyTile = new int[]{i, j};
                    }
                }
            }
        }
        return closestEmptyTile; // instead of return new int[0];
    }

    /**
     * Handles animal movement as the days progress
     */
    private void moveAnimal() {
        for (int i = 0; i < map.getHeight(); i++) { // Iterates through each row of the grid
            for (int j = 0; j < map.getWidth(); j++) { // Iterates through each column of the grid
                if (map.getOrganism(i, j) instanceof AbstractAnimal animal) { // Check if the object is an instance of AbstractAnimal
                    if (animal.isStarving()) {
                        map.removeOrganism(i, j);
                        animal.reduceCount();
                        break;
                    }
                    animal.move(map, i, j);
                }
                if (map.getOrganism(i, j) instanceof AbstractPlant plant) {
                    if (RandomManager.getRandom().nextInt(100) < 10) {
                        plant.grow(map, i, j);
                    }
                }
            }
        }
        for (int i = 0; i < map.getHeight(); i++) { // Iterates through each row of the grid
            for (int j = 0; j < map.getWidth(); j++) { // Iterates through each column of the grid
                if(map.getOrganism(i,j) != null) {
                    map.getOrganism(i, j).resetBreeding();
                }
                if (map.getOrganism(i, j) instanceof AbstractAnimal animal) { // Check if the object is an instance of AbstractAnimal
                    animal.resetMove();
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

        if(direction!=null){
            switch(direction) {
                case UP -> {
                    map.removeOrganism(y, x);
                    map.addOrganism(animal, y - 1, x);
                }
                case DOWN -> {
                    map.removeOrganism(y, x);
                    map.addOrganism(animal, y + 1, x);
                }
                case LEFT -> {
                    map.removeOrganism(y, x);
                    map.addOrganism(animal, y, x - 1);
                }
                case RIGHT -> {
                    map.removeOrganism(y, x);
                    map.addOrganism(animal, y, x + 1);
                }

            }
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

    public Map getMap(){return map; }

    /**
     * Sets whether the simulation should print it's output
     * @param printOutput if true it will send the signals to the UI, otherwise it won't
     */
    public void setPrintOutput(boolean printOutput) {
        this.printOutput = printOutput;
    }
}
