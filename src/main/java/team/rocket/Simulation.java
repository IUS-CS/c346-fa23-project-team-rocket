package team.rocket;

import java.lang.Runnable;
import team.rocket.Enums.Direction;
import team.rocket.Handlers.Terminal.FlagHandler;
import team.rocket.Handlers.Terminal.GridSizeFlagHandler;
import team.rocket.Handlers.Terminal.InitialOrganismCountFlagHandler;
import team.rocket.Handlers.Terminal.TerminalFlagRequest;

/**
 * team.rocket.Simulation is the class that controls the backend of the simulation. It contains a grid of animals. It also runs
 * multiple time steps and days worth of simulated time during which animals can breed.
 *
 * @author Dale Morris, Jon Roberts
 * @version Sprint2
 * @since Prototype
 */
public class Simulation implements Runnable {
    private Map map; // Grid that organisms can exist in
    private static final int DEFAULT_DAYS_PER_RUN = 10; // The default number of days in each run
    private static final int DEFAULT_TIME_STEPS_PER_DAY = 10; // The default number of time steps in each day
    private static final int DEFAULT_MILLISECONDS_PER_TIME_STEP = 100; // The default number of real-world milliseconds in each time step
    private int currentDay; // The current day of the simulation
    private int currentTimeStep; // The current time step within the current day of the simulation
    private int daysPerRun; // The number of days that make up each run of the simulation
    private int timeStepsPerDay; // The number of time steps that make up each day
    private int millisecondsPerTimeStep; // The number of real-world milliseconds that make up each time step
    private FlagHandler flagHandler = new GridSizeFlagHandler();

    /**
     * Returns a new team.rocket.Simulation object with the given constraints.
     *
     * @param terminalFlags the flags entered into the terminal
     * @param mapWidth      the number of columns of the simulated grid
     * @param mapHeight     the number of rows of the simulated grid
     */
    Simulation(String terminalFlags, int mapWidth, int mapHeight) {
        flagHandler.setSuccessor(new InitialOrganismCountFlagHandler());
        OrganismFactory.getInstance().registerOrganism("rabbit", new Rabbit());
        OrganismFactory.getInstance().registerOrganism("grass", new Grass());
        TerminalFlagRequest request = new TerminalFlagRequest(terminalFlags, new Map());
        flagHandler.handleRequest(request);

        map = request.getMap();
    }

    /**
     * Returns a new team.rocket.Simulation object with default constraints.
     *
     * @param terminalFlags the flags entered into the terminal
     */
    Simulation(String terminalFlags) {
        new Simulation(terminalFlags, Map.DEFAULT_WIDTH, Map.DEFAULT_HEIGHT);
    }

    /**
     * Simulates how the environment changes over time based on initial conditions and interactions among animals.
     */
    @Override
    public void run() {
        map.addOrganism(OrganismFactory.getInstance().createOrganism("rabbit"), 0, 0); // Adds a rabbit to the grid

        currentDay = 1;
        outputGrid(); // TODO: Move to UI

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
            outputGrid(); // TODO: Move to UI
        } // End of simulation
    }



    /**
     * Simulates breeding among the animals and creates a new entitys when breeding occurs
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
                            if (map.getOrganism(i, j) == null) {    // If the current grid space is empty
                                map.addOrganism(OrganismFactory.getInstance().createOrganism("rabbit"), i, j);
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
                    outputGrid();

                    System.out.println("The program has ended prematurely because there is no more space for animals.");
                    return;
                }
            }
        } // End of grid iteration
    }

    public void breedHelper() {

    }

    /**
     * Outputs the current Grid with boundaries and letter representations for the animals
     */
    public void outputGrid() {
        System.out.println("Day " + currentDay);

        // Print upper edge
        System.out.print("-");
        for (int i = 1; i < map.getWidth() + 2; i++) {
            System.out.print("--");
        }
        System.out.println();

        for (int i = 0; i < map.getHeight(); i++) {
            System.out.print("| "); // Print left edge
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.getOrganism(i, j) != null) {
                    System.out.print(Rabbit.toIcon() + " "); // Prints an 'R' where an entity is present
                } else {
                    System.out.print("  "); // Print an empty space if there's no animal
                }
            }
            System.out.println("|"); // Print right edge
        }

        // Print lower edge
        System.out.print("-");
        for (int i = 1; i < map.getWidth() + 2; i++) {
            System.out.print("--");
        }
        System.out.println();
    }

    /**
     * Handles animal movement as the days progress
     */
    private void moveAnimal() {
        for (int i = 0; i < map.getHeight(); i++) { // Iterates through each row of the grid
            for (int j = 0; j < map.getWidth(); j++) { // Iterates through each column of the grid
                if (map.getOrganism(i, j) != null) {
                    AbstractOrganism[] neighbors = new AbstractOrganism[4];
                    if (i == 0) {
                        neighbors[0] = new Rabbit();
                    } else {
                        neighbors[0] = map.getOrganism(i-1, j);
                    }

                    if (i == map.getHeight() - 1) {
                        neighbors[1] = new Rabbit();
                    } else {
                        neighbors[1] = map.getOrganism(i+1, j);
                    }

                    if (j == 0) {
                        neighbors[2] = new Rabbit();
                    } else {
                        neighbors[2] = map.getOrganism(i, j-1);
                    }

                    if (j == map.getWidth() - 1) {
                        neighbors[3] = new Rabbit();
                    } else {
                        neighbors[3] = map.getOrganism(i, j+1);
                    }

                    moveDirection((AbstractAnimal) map.getOrganism(i, j), neighbors, i, j);
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

    public void setMillisecondsPerTimeStep() {
        this.millisecondsPerTimeStep = millisecondsPerTimeStep;
    }
}
