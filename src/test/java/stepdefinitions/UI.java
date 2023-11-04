package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Entities.Rabbit;
import team.rocket.Entities.AbstractAnimal;
import java.util.Arrays;
import team.rocket.Map;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
public class UI {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private String actualOutput;

    @Given("a map with specific entities is created")
    public void aMapisCreated(){
        // Create the map and add the specific entity
        Map map = new Map(3, 3);
        map.addOrganism(new Rabbit(), 1, 1);

        // Call the outputGrid method and store the output as a string
        actualOutput = outputGridAsString(1, map);

        // Define the expected output
        String expectedOutput = "Day 1\n" +
                "---------\n" +
                "|       |\n" +
                "|   R   |\n" +
                "|       |\n" +
                "---------\n";

        // Compare the expected output with the actual output
        assertEquals(expectedOutput, actualOutput);
    }



    private String outputGridAsString(int currentDay, Map map) {
        StringBuilder output = new StringBuilder();
        output.append("Day ").append(currentDay).append("\n");
        output.append("---------\n");
        for (int i = 0; i < map.getHeight(); i++) {
            output.append("| ");
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.getOrganism(i, j) != null) {
                    output.append(map.getOrganism(i, j).instancedToIcon()).append(" ");
                } else {
                    output.append("  ");
                }
            }
            output.append("|\n");
        }
        output.append("---------\n");
        return output.toString();
    }

    @Given("A map with no entities should be displayed")
    public void aMapWithNoEntitiesShouldBeDisplayed() {
        // Create the map
        Map map = new Map(3, 3);

        // Call the outputGrid method and store the output as a string
        actualOutput = outputGridAsString(1, map);

        // Define the expected output
        String expectedOutput = "Day 1\n" +
                "---------\n" +
                "|       |\n" +
                "|       |\n" +
                "|       |\n" +
                "---------\n";

        // Compare the expected output with the actual output
        assertEquals(expectedOutput, actualOutput);
    }

    @Given("the map should display multiple entities")
    public void theMapShouldDisplayMultipleEntities() {
        // Create the map and add specific entities (rabbits)
        Map map = new Map(3, 3);
        map.addOrganism(new Rabbit(), 1, 1);
        map.addOrganism(new Rabbit(), 0, 2);
        map.addOrganism(new Rabbit(), 2, 0);

        // Call the outputGrid method and store the output as a string
        actualOutput = outputGridAsString(1, map);

        // Define the expected output
        String expectedOutput = "Day 1\n" +
                "---------\n" +
                "|     R |\n" +
                "|   R   |\n" +
                "| R     |\n" +
                "---------\n";

        // Compare the expected output with the actual output
        assertEquals(expectedOutput, actualOutput);
    }
}
