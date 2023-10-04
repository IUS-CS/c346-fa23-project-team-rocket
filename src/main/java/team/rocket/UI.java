package team.rocket;

import java.lang.Thread;
import java.util.Scanner;

/**
 * UI is the standard entry point for using the simulation. It allows users to type in input to modify the simulation.
 */
public class UI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Simulation simulation = new Simulation();

        simulation.setDaysPerRun(Simulation.DEFAULT_DAYS_PER_RUN);
        simulation.setTimeStepsPerDay(Simulation.DEFAULT_TIME_STEPS_PER_DAY);
        simulation.setMillisecondsPerTimeStep(Simulation.DEFAULT_MILLISECONDS_PER_TIME_STEP);

        Thread simulationThread = new Thread(simulation);
        simulationThread.start();
    }
}
