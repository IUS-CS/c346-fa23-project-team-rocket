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

}
