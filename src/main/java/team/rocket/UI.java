package team.rocket;

import team.rocket.Handlers.Terminal.FlagHandler;
import team.rocket.Handlers.Terminal.GridSizeFlagHandler;
import team.rocket.Handlers.Terminal.InitialOrganismCountFlagHandler;
import team.rocket.Handlers.Terminal.TerminalFlagRequest;

import java.lang.Thread;
import java.util.Scanner;

/**
 * UI is the standard entry point for using the simulation. It allows users to type in input to modify the simulation.
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
            FlagHandler handler = new GridSizeFlagHandler();
            //set the handlers successor to the appropriate successor
            handler.setSuccessor(new InitialOrganismCountFlagHandler());
            //Appropriately handle the request with the handlers, data will be passed back through the request
            handler.handleRequest(tFR);
            //Create a Simulation from the tFR map
            simulation = new Simulation(tFR.getMap());
        } else {
            simulation = new Simulation();
        }
        simulation.setDaysPerRun(Simulation.DEFAULT_DAYS_PER_RUN);
        simulation.setTimeStepsPerDay(Simulation.DEFAULT_TIME_STEPS_PER_DAY);
        simulation.setMillisecondsPerTimeStep(Simulation.DEFAULT_MILLISECONDS_PER_TIME_STEP);

        Thread simulationThread = new Thread(simulation);
        simulationThread.start();
    }
    static private void setupOrganismFactory(){
        OrganismFactory organismFactory = OrganismFactory.getInstance();
        //Register organisms so that they can be created
        organismFactory.registerOrganism("Rabbit", new Rabbit());
        organismFactory.registerOrganism("Grass", new Grass());
    }
}
