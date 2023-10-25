package team.rocket;

import team.rocket.Entities.*;
import team.rocket.Handlers.Terminal.*;

import java.lang.Thread;
import java.util.Scanner;

/**
 * UI is the standard entry point for using the simulation. It allows users to type in input to modify the simulation.
 *
 *  * @author Dale Morris, Jon Roberts
 *  * @version 0.4.0
 *  * @since 0.1.0
 */
public class UI {

    public static void main(String[] args) {
        //Prepares the factories for construction
        setupOrganismFactory();

        Scanner input = new Scanner(System.in);
        Simulation simulation;
        //If args length == 1 make a map from terminal flags and create simulation that way
        if(args.length == 1){
            //Create a TerminalFlagRequest
            TerminalFlagRequest tFR = new TerminalFlagRequest(args[0], new Map(3,3));
            //Create a flag handler
            FlagHandler mainHandler = new GridSizeFlagHandler();
            FlagHandler subHandlerOne = new InitialOrganismCountFlagHandler();
            FlagHandler subHandlerTwo = new DaysPerRunFlagHandler();
            FlagHandler subHandlerThree = new TimeStepsPerDayFlagHandler();
            //set the handlers successor to the appropriate successor
            mainHandler.setSuccessor(subHandlerOne);
            //set subHandlerOne's successor to the DaysPerRun Flag Handler
            subHandlerOne.setSuccessor(subHandlerTwo);
            //set subHandlerTwo's successor to the TimeStepsPerDay Flag Handler
            subHandlerTwo.setSuccessor(subHandlerThree);
            //Appropriately handle the request with the handlers, data will be passed back through the request
            mainHandler.handleRequest(tFR);
            //Create a Simulation from the tFR map
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
     * Outputs the current Grid with boundaries and letter representations for the animals
     */
    public static void outputGrid(int currentDay, Map map) {
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
                    System.out.print(map.getOrganism(i, j).instancedToIcon()+ " "); // Prints an icon where an entity is present
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

    static private void setupOrganismFactory(){
        OrganismFactory organismFactory = OrganismFactory.getInstance();
        //Register organisms so that they can be created
        organismFactory.registerOrganism("Rabbit", new Rabbit());
        organismFactory.registerOrganism("Grass", new Grass());
        organismFactory.registerOrganism("Fox", new Fox());
        organismFactory.registerOrganism("Carrot", new Carrot());
    }
}
