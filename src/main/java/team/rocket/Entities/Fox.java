package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.Map;

import java.util.Random;

/**
 * A fox is an animal that eats rabbits.
 *
 * @version 0.6.0
 * @since 0.4.0
 * @version 0.6.0
 */


public class Fox extends AbstractAnimal {
    private static final char icon = 'F'; // The icon representation of a fox
    private static int count = 0; // The total number of existing foxes
    private boolean hasMoved; // True if this fox has moved this time step, false otherwise
    private boolean hasBred; // True if this fox has bred today, false otherwise
    private int hunger; // The hunger value of this fox
    private int nutrition = 0; // The hunger value that a fox rewards when eaten
    private static final int vision = 5; // The number of tiles away that a fox can see

    /**
     * A default constructor for foxes.
     */
    public Fox() {

        count++;
        hasMoved = true;
        hasBred = true;
        hunger = 100; // 100 is full, 0 is empty
    }

    /**
     * Gets the icon from an instance.
     *
     * @return  the icon of the organism
     */
    public char instancedToIcon()
    {
        return icon;
    }

    /**
     * Returns the icon representation of a fox.
     *
     * @return  the icon representation of a fox
     */
    public static char toIcon() {
        return icon;
    }

    /**
     * Returns the total number of existing foxes.
     *
     * @return  current fox count
     */
    public static int getCount() {
        return count;
    }

    /**
     * Returns this fox's hunger value.
     *
     * @return fox's current hunger
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Returns the nutrition of a fox.
     *
     * @return fox nutrition
     */
    public int getNutrition() {
        return nutrition;
    }

    /**
     * Decreases this fox's hunger meter.
     */
    public void reduceHunger() {
        hunger -= 10;
    }

    /**
     * Returns whether this fox is starving.
     *
     * @return  true if this fox is starving, false otherwise
     */
    public boolean isStarving() {
        return hunger <= 0;
    }

    /**
     * Sets the total number of existing foxes.
     *
     * @param i the number count is being set too
     */
    @Override
    public void setCount(int i) {
        count = i;
    }

    /**
     * Decrements the count of foxes.
     */
    @Override
    public void reduceCount() {
        count--;
    }

    /**
     * Returns a new fox using an existing fox.
     *
     * @return  a new fox
     */
    @Override
    public AbstractOrganism getNewObjectFromExistingObject() {
        return new Fox();
    }

    /**
     * Creates a new fox.
     */
    public void breed() {

    } //not yet implemented

    /**
     * Resets hasMoved to false, meant to be used to reset movement each day.
     */
    public void resetMove() {
        hasMoved = false;
    }

    /**
     * Reproduces and creates a new fox.
     */
    @Override
    public void reproduce() {

    }

    /**
     * Resets hasBred to false, meant to be used to reset breeding each day.
     */
    public void resetBreeding() {
        hasBred = false;
    }

    /**
     * Takes array of a fox's neighbors, randomly chooses an available space, and returns corresponding direction.
     *
     * @param neighbors array of organisms in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return          randomly determined direction based on available spaces
     */
    public Direction availableMovementSpace(AbstractOrganism[] neighbors) {
        int i = 0; //tracks iterations of for loop
        int freeSpaceCount = 0; //stores number of free adjacent spaces
        Direction[] freeSpaces = new Direction[4]; //stores available movement directions

        for (i = 0; i < 4; i++) {
            if (neighbors[i] == null || neighbors[i].instancedToIcon() == 'R') {
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
            return freeSpaces[new Random().nextInt(freeSpaceCount)]; //randomly picks and returns a free space
        }
    }

    /**
     * Moves fox in grid based on current position, available movement space, and past movement.
     *
     * @param map   map of simulation
     * @param y     y position of Fox in grid
     * @param x     x position of Fox in grid
     */
    public void move(Map map, int y, int x) {
        if (hasMoved) {
            return;
        }

        int newX = x;
        int newY = y;

        AbstractOrganism[] neighbors = findNeighbors(map, y, x);

        Direction direction = this.availableMovementSpace(neighbors);

        if (direction == null) {
            return;
        }

        if (direction == Direction.UP) {
            newY--;
        }

        if (direction == Direction.DOWN) {
            newY++;
        }

        if (direction == Direction.LEFT) {
            newX--;
        }

        if (direction == Direction.RIGHT) {
            newX++;
        }
        this.reduceHunger();
        this.eat(map, newY, newX);
        AbstractOrganism[][] griddy = map.getGrid();
        griddy[newY][newX] = this;
        griddy[y][x] = null;
        map.setGrid(griddy);
        hasMoved = true;
    }

    /**
     * Gets the vision value of the Fox, representing how far it can 'see'
     * @return the int representing vision distance
     */
    public static int getVision() {
        return vision;
    }

    /**
     * eats the organism positioned at {row, column}
     * @param map the map of the simulation
     * @param row the row of the organism to be eaten
     * @param column the column of the organism to be eaten
     */
    public void eat(Map map, int row, int column) {
        if (map.getGrid()[row][column] != null) {
            AbstractOrganism org = map.getGrid()[row][column];
            if (org.instancedToIcon() == 'R') {
                this.hunger += org.getNutrition();
                org.reduceCount();
                map.removeOrganism(row, column);
            }
        }
    }

    /**
     * Finds the contents of the four squares up, down, left, and right from this fox in the given map.
     *
     * @param map   the map that this fox is in
     * @param y     the y value of this fox
     * @param x     the x value of this fox
     * @return      the contents of the four squares around this fox
     */
    public AbstractOrganism[] findNeighbors(Map map, int y, int x) {
        AbstractOrganism[] neighbors = new AbstractOrganism[4];
        if (y == 0) {
            neighbors[0] = OrganismFactory.getInstance().createOrganism("Fox"); //Acting as walls
            neighbors[0].reduceCount(); //Keeping the Fox count accurate
        } else {
            neighbors[0] = map.getOrganism(y - 1, x);
        }

        if (y == map.getHeight() - 1) {
            neighbors[1] = OrganismFactory.getInstance().createOrganism("Fox");
            neighbors[1].reduceCount();
        } else {
            neighbors[1] = map.getOrganism(y + 1, x);
        }

        if (x == 0) {
            neighbors[2] = OrganismFactory.getInstance().createOrganism("Fox");
            neighbors[2].reduceCount();
        } else {
            neighbors[2] = map.getOrganism(y, x - 1);
        }

        if (x == map.getWidth() - 1) {
            neighbors[3] = OrganismFactory.getInstance().createOrganism("Fox");
            neighbors[3].reduceCount();
        } else {
            neighbors[3] = map.getOrganism(y, x + 1);
        }

        return neighbors;
    }
}
