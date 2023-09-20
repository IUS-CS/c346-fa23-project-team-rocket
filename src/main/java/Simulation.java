import java.lang.Runnable;

/**
 * Simulation is the class that controls the backend of the simulation. It contains a grid of animals. It also runs
 * multiple time steps and days worth of simulated time during which animals can breed.
 *
 * @author thatdaleguy03
 * @version Prototype
 * @since Alpha V1.0.0
 */
class Simulation implements Runnable {
    AbstractAnimal[][] grid; // Grid that animals move throughout
    private static final int[] DEFAULT_GRID_DIMENSIONS = {10, 10}; // The default values for the width and height of the grid
    int currentDay; // The current day of the simulation
    int currentTimeStep; // The current time step within the current day of the simulation
    private int timeStepsPerDay; // The number of time steps that make up each day
    private int secondsPerTimeStep; // The number of real-world seconds that make up each time step

    /**
     * Returns a new Simulation object with the given constraints.
     *
     * @param gridWidth          the number of columns of the simulated grid
     * @param gridHeight         the number of rows of the simulated grid
     * @param timeStepsPerDay    the number of time steps that make up each day
     * @param secondsPerTimeStep the number of real-world seconds that make up each time step
     */
    Simulation(int gridWidth, int gridHeight, int timeStepsPerDay, int secondsPerTimeStep) {
        grid = new AbstractAnimal[gridWidth][gridHeight];
        this.timeStepsPerDay = timeStepsPerDay;
        this.secondsPerTimeStep = secondsPerTimeStep;
    }

    /**
     * Returns a new Simulation object with default constraints.
     */
    Simulation() {
        grid = new AbstractAnimal[DEFAULT_GRID_DIMENSIONS[0]][DEFAULT_GRID_DIMENSIONS[1]];
    }

    /**
     * Simulates how the environment changes over time based on initial conditions and interactions among animals.
     */
    public void run() {
        grid[0][0] = new Rabbit(); // Adds a rabbit to the grid
        final int DAYS_PER_RUN = 10; // The number of days in a run
        secondsPerTimeStep = 100;

        outputGrid();

        for (currentDay = 1; currentDay < DAYS_PER_RUN; currentDay++) { // Iterates through each day
            for (currentTimeStep = 1; currentTimeStep < timeStepsPerDay; currentTimeStep++) { // Iterates through each time step in the current day
                try {
                    Thread.sleep(secondsPerTimeStep);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for (int i = 0; i < grid.length; i++) { // Iterates through each row of the grid
                    for (int j = 0; j < grid[0].length; j++) { // Iterates through each entry in the current row of the grid
                        if (grid[i][j] != null) {
                            grid[i][j].move();
                        }
                    }
                }
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] != null) {
                        grid[i][j].breed();
                    }
                }
            }

            outputGrid();
        }
    }

    void outputGrid() {
        System.out.println("-------------------");
        for (int i = 0; i < grid.length; i++) {

        }
        System.out.println("-------------------");
    }
}
