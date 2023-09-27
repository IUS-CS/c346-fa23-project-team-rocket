import java.lang.Thread;
import java.util.Scanner;

/**
 * UI is the standard entry point for using the simulation. It allows users to type in input to modify the simulation.
 */
public class UI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Simulation simulation = new Simulation();
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();
    }
}
