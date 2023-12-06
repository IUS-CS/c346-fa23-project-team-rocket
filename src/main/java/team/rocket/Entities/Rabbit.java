package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.Map;

import java.util.Random;

/**
 * A rabbit is an animal that provides food to foxes and eats plants.
 *
 * @version 0.6.0
 * @since 0.1.0
 */
public class Rabbit extends AbstractAnimal{
    private static final char icon = 'R'; // The icon representation of a rabbit
    private static int count = 0; // The total number of existing rabbits
    private boolean hasMoved; // True if this rabbit has moved this time step, false otherwise
    private boolean hasBred; // True if this rabbit has bred today, false otherwise
    private int hunger; // The hunger value of this rabbit
    private static int deathFood = 0; // This rabbit will die if its hunger falls below this value
    private static int nutrition = 20; // The hunger value that a rabbit rewards when eaten
    private static final int vision = 4; // The number of tiles away a rabbit can see

    /**
     * A default constructor for rabbits.
     */
    public Rabbit() {
        count++;
        hasMoved = true;
        hasBred = true;
        hunger = 100; // 100 is full, 0 is empty
    }

    /**
     * Returns the icon representation of a rabbit.
     *
     * @return rabbit's icon as a character
     */
    public static char toIcon() {
        return icon;
    }

    /**
     * Gets the icon from an instance.
     *
     * @return the icon of the organism
     */
    public char instancedToIcon() {
        return icon;
    }

    /**
     * Returns the total number of existing rabbits.
     *
     * @return current Rabbit count
     */
    public static int getCount() {
        return count;
    }

    @Override
    public void setCount(int i) {
        count = i;
    }

    /**
     * Returns the hunger value that a rabbit rewards when eaten
     *
     * @return Rabbit nutrition
     */
    public int getNutrition() {
        return nutrition;
    }

    /**
     * Decreases rabbit's hunger meter.
     */
    public void reduceHunger() {
        hunger -= 10;
    }

    /**
     * Returns this rabbit's hunger value.
     *
     * @return  rabbit's current hunger
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Decrements the count of rabbits.
     */
    @Override
    public void reduceCount() {
        count--;
    }

    /**
     * Returns a new rabbit using an existing rabbit
     *
     * @return  a new rabbit
     */
    @Override
    public AbstractOrganism getNewObjectFromExistingObject() {
        return new Rabbit();
    }

    /**
     * Creates a new rabbit
     */
    public void reproduce() {

    } //not yet implemented

    /**
     * Resets hasMoved to false, meant to be used to reset movement each day
     */
    public void resetMove(){
        this.hasMoved = false;
    }

    /**
     * Takes array of a rabbit's neighbors, randomly chooses an available space, and returns corresponding direction
     *
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return          randomly determined direction based on available spaces
     */
    public Direction availableMovementSpace(AbstractOrganism[] neighbors){
        int i = 0; //tracks iterations of for loop
        int freeSpaceCount = 0; //stores number of free adjacent spaces
        Direction[] freeSpaces = new Direction[4]; //stores available movement directions

        for (i = 0; i < 4; i++) {
            if(neighbors[i] == null || neighbors[i].instancedToIcon() == 'G' || neighbors[i].instancedToIcon() == 'C') {
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

        if (freeSpaceCount==0) { //returns null in case of no free spaces
            return null;
        }
        if (freeSpaceCount == 1) {
            return freeSpaces[0];
        } else {
            return freeSpaces[new Random().nextInt(freeSpaceCount)]; //randomly picks and returns a free space
        }
    }

    /**
     * Moves Rabbit in grid based on current position, available movement space, and past movement
     *
     * @param map   map of simulation
     * @param y     y position of this rabbit in grid
     * @param x     x position of this rabbit in grid
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
     * Returns whether this rabbit is starving
     * @return  true if this rabbit is starving, false otherwise
     */
    public boolean isStarving() {
        return hunger <= deathFood;
    }

    /**
     * Causes this rabbit to eat the organism in the given row and column of the given map.
     *
     * @param map       the map that this fox is in
     * @param row       the row of the organism that this fox will eat
     * @param column    the column of the organism that this fox will eat
     */
    public void eat(Map map, int row, int column) {
        if (map.getGrid()[row][column] != null) {
            AbstractOrganism org = map.getGrid()[row][column];
            if (org.instancedToIcon() == 'C' || org.instancedToIcon() == 'G') {
                this.hunger += org.getNutrition();
                org.reduceCount();
                map.removeOrganism(row, column);
            }
        }
    }

    /**
     * Finds the contents of the four squares up, down, left, and right from this rabbit in the given map.
     *
     * @param map   the map that this rabbit is in
     * @param y     the y value of this rabbit
     * @param x     the x value of this rabbit
     * @return      the contents of the four squares around this rabbit
     */
    public AbstractOrganism[] findNeighbors(Map map, int y, int x) {
        AbstractOrganism[] neighbors = new AbstractOrganism[4];
        if (y == 0) {
            neighbors[0] = OrganismFactory.getInstance().createOrganism("Rabbit"); //Acting as walls
            neighbors[0].reduceCount(); //Keeping the Rabbit count accurate
        } else {
            neighbors[0] = map.getOrganism(y - 1, x);
        }

        if (y == map.getHeight() - 1) {
            neighbors[1] = OrganismFactory.getInstance().createOrganism("Rabbit");
            neighbors[1].reduceCount();
        } else {
            neighbors[1] = map.getOrganism(y + 1, x);
        }

        if (x == 0) {
            neighbors[2] = OrganismFactory.getInstance().createOrganism("Rabbit");
            neighbors[2].reduceCount();
        } else {
            neighbors[2] = map.getOrganism(y, x - 1);
        }

        if (x == map.getWidth() - 1) {
            neighbors[3] = OrganismFactory.getInstance().createOrganism("Rabbit");
            neighbors[3].reduceCount();
        } else {
            neighbors[3] = map.getOrganism(y, x + 1);
        }

        return neighbors;
    }

    /**
     * Returns the number of tiles away a rabbit can see
     *
     * @return the vision value of a rabbit
     */
    public static int getVision() {
        return vision;
    }
}
