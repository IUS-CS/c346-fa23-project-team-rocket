package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.util.RandomManager;

import java.util.Random;

/**
 * @since 0.5.0
 * @version 0.5.0
 */
public class Carrot extends AbstractPlant {
    private static char icon = 'C'; // The icon representation of a carrot
    private static int nutrition = 25; // The hunger value that a carrot rewards when eaten
    private static int count; // The total number of existing carrots
    private boolean hasGrown; // True is this carrot has grown today, false otherwise

    /**
     * Creates a new carrot, not ready to grow
     */
    public Carrot() {
        count++;
        hasGrown = true;
    }

    /**
     * Returns a boolean indication if this carrot has grown this cycle
     *
     * @return true if this carrot has grown this cycle, false otherwise
     */
    public boolean growthStatus() {
        return hasGrown;
    }

    /**
     * @return Carrot's icon as a character
     */
    public static char toIcon() {
        return icon;
    }

    /**
     * Gets the icon from an instance
     *
     * @return the icon of the organism
     */
    public char instancedToIcon() {
        return icon;
    }

    /**
     * Returns the current number of existing carrots
     *
     * @return  current carrot count
     */
    public static int getCount() {
        return count;
    }

    /**
     * Returns the nutrition of this carrot
     *
     * @return  carrot nutrition
     */
    public int getNutrition() {
        return nutrition;
    }

    /**
     * Sets the count of Carrot
     *
     * @param i the number count is being set too
     */
    public void setCount(int i) {
        count = i;
    }

    /**
     * Needed for very specific instance with OrganismEnum so that the instance in the enum doesn't count towards the
     * total number of organisms
     */
    public void reduceCount() {
        count--;
    }

    /**
     * Takes the instance of an object and creates a brand new one and returns that new object
     *
     * @return a fresh new not-copied AbstractOrganism
     */
    public AbstractOrganism getNewObjectFromExistingObject() {
        return new Carrot();
    }

    /**
     * Resets hasGrown to false, meant to be used to reset growth each day
     */
    public void resetGrown() {
        hasGrown = false;
    }

    /**
     * Takes array of a rabbit's neighbors, randomly chooses an available space, and returns corresponding direction
     *
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return          randomly determined direction based on available spaces
     */
    public Direction availableSpace(AbstractOrganism[] neighbors) {
        int i = 0; //tracks iterations of for loop
        int freeSpaceCount = 0; //stores number of free adjacent spaces
        Direction[] freeSpaces = new Direction[4]; //stores available movement directions

        for (i = 0; i < 4; i++) {
            if (neighbors[i] == null) {
                switch (i) { //identifies which direction is being evaluated
                    case 0 -> {
                        freeSpaces[freeSpaceCount] = Direction.UP; //stores open direction in freeSpaces
                        freeSpaceCount++; //increments number of free spaces
                    }
                    case 1 -> {
                        freeSpaces[freeSpaceCount] = Direction.DOWN;
                        freeSpaceCount++;
                    }
                    case 2 -> {
                        freeSpaces[freeSpaceCount] = Direction.LEFT;
                        freeSpaceCount++;
                    }
                    case 3 -> {
                        freeSpaces[freeSpaceCount] = Direction.RIGHT;
                        freeSpaceCount++;
                    }
                }
            }
        }

        if (freeSpaceCount == 0) { //returns null in case of no free spaces
            return null;
        }
        if (freeSpaceCount == 1) {
            return freeSpaces[0];
        } else {
            // Randomly picks and returns a free space
            return freeSpaces[RandomManager.getRandom().nextInt(freeSpaceCount)];
        }
    }

    /**
     * Creates a new carrot in free adjacent slot
     *
     * @param grid      2D array holding all Organisms in simulation
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @param y         y position of Carrot in grid
     * @param x         x position of Carrot in grid
     */
    public void grow(AbstractOrganism[][] grid, AbstractOrganism[] neighbors, int y, int x) {
        if (hasGrown) {
            return;
        }

        Direction direction = availableSpace(neighbors);

        if (direction == null) {
            return;
        }

        if (direction == Direction.UP) {
            grid[y-1][x] = getNewObjectFromExistingObject();
        }

        if (direction == Direction.DOWN) {
            grid[y+1][x] = getNewObjectFromExistingObject();
        }

        if (direction == Direction.LEFT) {
            grid[y][x-1] = getNewObjectFromExistingObject();
        }

        if (direction == Direction.RIGHT) {
            grid[y][x+1] = getNewObjectFromExistingObject();
        }

        hasGrown = true;
    }
}
