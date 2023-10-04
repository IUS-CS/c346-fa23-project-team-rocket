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
    private int daysPerRun; // The number of days that make up each run of the simulation
    private int currentDay; // The current day of the simulation
    private int currentTimeStep; // The current time step within the current day of the simulation
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
    public void run() {
        dayIterator();
    }

    /**
     * Iterates through the days and makes calls to breeding, moveAnimal, and outputGrid every day that occurs
     */
    private void dayIterator() {
        grid[0][0] = new Rabbit(); // Adds a rabbit to the grid
        final int DAYS_PER_RUN = 10; // The number of days in a run

        currentDay = 1;
        outputGrid();

        for (currentDay = 2; currentDay <= DAYS_PER_RUN; currentDay++) { // Iterates through each day
            for (currentTimeStep = 1; currentTimeStep <= timeStepsPerDay; currentTimeStep++) { // Iterates through each time step in the current day
                try {
                    Thread.sleep(millisecondsPerTimeStep);
                } catch (InterruptedException e) {
                    System.out.println("There was an unexpected issue with the simulation.");
                    return;
                }
                moveAnimal();
            } // End of current day

            breeding();
            outputGrid();
        } // End of simulation

    }

    /**
     * Simulates breeding among the animals and creates a new entitys when breeding occurs
     */
    private void breeding() {

        /* Breeding section */
        int rabbitsBred = 0;
        double expectedBreeds = Math.pow(2, currentDay-1);
        AbstractAnimal[][] oldGrid = grid; // A copy of the current grid
        boolean hasBred;                   // Indicates if the animal in the current grid space has bred yet
        boolean animalFound;               // Indicates if a rabbit is in the current grid space
        boolean gridIsFull = false;        // Indicates if the grid is full
        for (int i = 0; i < oldGrid.length; i++) {        // Iterates through each row of a copy of the grid
            hasBred = false;
            for (int j = 0; j < oldGrid[0].length; j++) { // Iterates through each column of a copy of the grid
                animalFound = false;


                if (oldGrid[i][j] != null) { // Found an animal
                    animalFound = true;

                    for (int k = 0; k < grid.length; k++) {     // Iterates through each row of the grid
                        for (int l = 0; l < grid.length; l++) { // Iterates through each column of the grid
                            if (grid[k][l] == null) { // If the current grid space is empty
                                grid[k][l] = new Rabbit();
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

                if (animalFound && !hasBred) { // Grid must be full
                    outputGrid();

                    System.out.println("The program has ended prematurely because there is no more space for animals.");
                    return;
                }
            }
        } // End of grid iteration

    }

    /**
     * Outputs the current Grid with boundaries and letter representations for the animals
     */
    public void outputGrid() {
        System.out.println("Day " + currentDay);

        // Print upper edge
        System.out.print("-");
        for (int i = 1; i < grid[0].length + 2; i++) {
            System.out.print("--");
        }
        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            System.out.print("| "); // Print left edge
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null) {
                    System.out.print(Rabbit.toIcon() + " "); // Prints an 'R' where an entity is present
                } else {
                    System.out.print("  "); // Print an empty space if there's no animal
                }
            }
            System.out.println("|"); // Print right edge
        }

        // Print lower edge
        System.out.print("-");
        for (int i = 1; i < grid[0].length + 2; i++) {
            System.out.print("--");
        }
        System.out.println();
    }

    /**
     * Handles animal movement as the days progress
     */
    private void moveAnimal() {
        for (int i = 0; i < grid.length; i++) { // Iterates through each row of the grid
            for (int j = 0; j < grid[0].length; j++) { // Iterates through each column of the grid
                if (grid[i][j] != null) {
                    AbstractAnimal[] neighbors = new AbstractAnimal[4];
                    if (i == 0) {
                        neighbors[0] = new Rabbit();
                    } else {
                        neighbors[0] = grid[i-1][j];
                    }

                    if (i == grid.length - 1) {
                        neighbors[1] = new Rabbit();
                    } else {
                        neighbors[1] = grid[i+1][j];
                    }

                    if (j == 0) {
                        neighbors[2] = new Rabbit();
                    } else {
                        neighbors[2] = grid[i][j-1];
                    }

                    if (j == grid[0].length - 1) {
                        neighbors[3] = new Rabbit();
                    } else {
                        neighbors[3] = grid[i][j+1];
                    }

                    moveDirection(grid[i][j], neighbors, i, j);
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
    private void moveDirection(AbstractAnimal animal, AbstractAnimal[] neighbors, int y, int x) {
        Direction direction = animal.availableMovementSpace(neighbors);

        if (direction == null) {
            return;
        }

        if (direction == Direction.UP) {
            grid[y][x] = null;
            grid[y-1][x] = animal;
        }

        if (direction == Direction.DOWN) {
            grid[y][x] = null;
            grid[y+1][x] = animal;
        }

        if (direction == Direction.LEFT) {
            grid[y][x] = null;
            grid[y][x-1] = animal;
        }

        if (direction == Direction.RIGHT) {
            grid[y][x] = null;
            grid[y][x+1] = animal;
        }
    }
}
