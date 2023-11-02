package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.Map;

/**
 * @since 0.1.0
 * @version 0.4.0
 */
public abstract class AbstractAnimal extends AbstractOrganism {
    private static char icon;
    private static int count;
    private boolean hasMoved;

    private int hunger;

    /**
     * @return Animal's icon as a character
     */
    public static char toIcon(){
        return icon;
    }

    /**
     * @return current Animal count
     */
    public static int getCount(){
        return count;
    }

    /**
     * Resets hasMoved to false, meant to be used to reset movement each day
     */
    public void resetMove(){
        this.hasMoved = false;
    }

    /**
     * Creates new Animal
     */
    public abstract void reproduce();

    /**
     * Takes array of an Animal's neighbors, randomly chooses an available space, and returns corresponding direction
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return randomly determined direction based on available spaces
     */
    public abstract Direction availableMovementSpace(AbstractOrganism[] neighbors);

    /**
     * Moves Animal in grid based on current position, available movement space, and past movement
     * @param map map of simulation
     * @param y - y position of Rabbit in grid
     * @param x - x position of Rabbit in grid
     */
    public abstract void move(Map map, int y, int x);

    /**
     * @return nutrition
     */
    public abstract int getNutrition();
}