package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.Map;

/**
 * An abstract class to define characteristics and behaviors for multiple types of animals.
 *
 * @version 0.6.0
 * @since 0.1.0
 */
public abstract class AbstractAnimal extends AbstractOrganism {
    private static char icon; // The icon representation of this type of animal
    private static int count; // The number of animals
    private boolean hasMoved; // True if this animal has moved this time step, false otherwise
    private static int vision; // The number of tiles away that an animal can see
    private int hunger; // The hunger value for this animal

    /**
     * Returns the icon representation of this type of animal.
     *
     * @return  this type of animal's icon as a character
     */
    public static char toIcon() {
        return icon;
    }

    /**
     * Returns the total number of existing animals.
     *
     * @return  the current animal count
     */
    public static int getCount() {
        return count;
    }

    /**
     * Resets hasMoved to false, meant to be used to reset movement each day.
     */
    public void resetMove() {
        this.hasMoved = false;
    }

    /**
     * Creates a new animal.
     */
    public abstract void reproduce();

    /**
     * Returns true if this animal is starving, false otherwise.
     *
     * @return  true if this animal is starving, false otherwise
     */
    public abstract boolean isStarving();

    /**
     * Returns this animal's hunger
     *
     * @return  this animal's current hunger
     */
    public abstract int getHunger();

    /**
     * Takes array of an animal's neighbors, randomly chooses an available space, and returns corresponding direction.
     *
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return          randomly determined direction based on available spaces
     */
    public abstract Direction availableMovementSpace(AbstractOrganism[] neighbors);

    /**
     * Moves this animal in grid based on current position, available movement space, and past movement.
     *
     * @param map   map of simulation
     * @param y     y position of Rabbit in grid
     * @param x     x position of Rabbit in grid
     */
    public abstract void move(Map map, int y, int x);

    /**
     * Returns this animal's nutrition.
     *
     * @return  nutrition
     */
    public abstract int getNutrition();

    /**
     * Returns the vision for this type of animal.
     *
     * @return  this type of animal's vision
     */
    public static int getVision() {
        return vision;
    }
}
