package team.rocket.IO;

import java.lang.Thread;

import team.rocket.Entities.*;
import team.rocket.IO.Terminal.*;
import team.rocket.Map;
import team.rocket.Simulation;

/**
 * UI is the standard entry point for using the simulation. It allows users to type in input to modify the simulation.
 *
 * @version 0.6.0
 * @since 0.1.0
 */
public class UI {
    public static void main(String[] args) throws InterruptedException {
        // Prepares the factories for construction
        setupOrganismFactory();

        Simulation simulation;
        // If args length == 1 make a map from terminal flags and create simulation that way
        if (args.length == 1) {
            // Create a TerminalFlagRequest
            TerminalFlagRequest tFR = new TerminalFlagRequest(args[0], new Map(3,3));
            // Create a flag handler
            FlagHandler mainHandler = new GridSizeFlagHandler();
            FlagHandler subHandlerOne = new InitialOrganismCountFlagHandler();
            FlagHandler subHandlerTwo = new DaysPerRunFlagHandler();
            FlagHandler subHandlerThree = new TimeStepsPerDayFlagHandler();
            // Set the handlers successor to the appropriate successor
            mainHandler.setSuccessor(subHandlerOne);
            // Set subHandlerOne's successor to the DaysPerRun Flag Handler
            subHandlerOne.setSuccessor(subHandlerTwo);
            // Set subHandlerTwo's successor to the TimeStepsPerDay Flag Handler
            subHandlerTwo.setSuccessor(subHandlerThree);
            // Appropriately handle the request with the handlers, data will be passed back through the request
            mainHandler.handleRequest(tFR);
            // Create a Simulation from the tFR map
            simulation = new Simulation(tFR.getMap());
            // Set the simulations # of days
            simulation.setDaysPerRun(tFR.getNumOfDays());
            // Set the simulations # of Time steps per day
            simulation.setTimeStepsPerDay(tFR.getStepsPerDay());
        } else {
            simulation = new Simulation();
            simulation.setDaysPerRun(Simulation.DEFAULT_DAYS_PER_RUN);
            simulation.setTimeStepsPerDay(Simulation.DEFAULT_TIME_STEPS_PER_DAY);
        }

        simulation.setMillisecondsPerTimeStep(Simulation.DEFAULT_MILLISECONDS_PER_TIME_STEP);

        Thread simulationThread = new Thread(simulation);
        simulationThread.start();
    }

    /**
     * Outputs the current Grid with boundaries and letter representations for the animals. Also outputs what day the
     * simulation is on. Spins up a new thread every time it's run.
     *
     * @param currentDay    the current day of the simulation
     * @param map           a map of the simulation
     */
    public static void outputGrid(int currentDay, Map map) {
        Thread t1 = new Thread(() -> {
            printFormattedDayAndGrid(currentDay, map);
        });
        t1.start();
    }

    /**
     * Outputs the current Grid with boundaries and letter representations for the animals. Also outputs what day the
     * simulation is on. Prints using the main compute thread.
     *
     * @param currentDay    the current day of the simulation
     * @param map           a map of the simulation
     */
    public static void outputGridViaMainThread(int currentDay, Map map) {
        printFormattedDayAndGrid(currentDay, map);
    }

    /**
     * Prints out the day and grid.
     *
     * @param currentDay    the current day of the simulation
     * @param map           a map of the simulation
     */
    private static void printFormattedDayAndGrid(int currentDay, Map map) {
        System.out.println("\n" + "Day " + currentDay);
        System.out.print(gridString(map));
    }


    /**
     * Constructs a string from the map with some formatting.
     *
     * @param map   the map to be turned into a string
     * @return      the newly made and formatted string
     */
    private static String gridString(Map map) {
        // constructs a var representing upper edge and prints it
        String verticalBorder = "-" +
                "--".repeat(Math.max(0, map.getWidth() + 1));

        StringBuilder grid = new StringBuilder(verticalBorder + "\n");


        for (int i = 0; i < map.getHeight(); i++) {
            grid.append("| ");
            for (int j = 0; j < map.getWidth(); j++) {
                String row = "";
                if (map.getOrganism(i, j) != null) {
                    row += (map.getOrganism(i, j).instancedToIcon() + " "); //appends to row
                } else {
                    row += ("  "); // append an empty space if there's no animal
                }
                grid.append(row);
            }
            grid.append("|\n"); // Print right edge
        }

        // Print lower edge
        grid.append(verticalBorder).append("\n");
        return String.valueOf(grid);
    }

    /**
     * Registers all organisms with the organism factory.
     */
    private static void setupOrganismFactory() {
        OrganismFactory organismFactory = OrganismFactory.getInstance();
        //Register organisms so that they can be created
        organismFactory.registerOrganism("Rabbit", new Rabbit());
        organismFactory.registerOrganism("Grass", new Grass());
        organismFactory.registerOrganism("Fox", new Fox());
        organismFactory.registerOrganism("Carrot", new Carrot());
    }
}
