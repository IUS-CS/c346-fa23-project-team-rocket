package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.Map;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @since 0.1.0
 * @version 0.6.0
 */
public class Rabbit extends AbstractAnimal{
    private static final char icon = 'R';
    private static int count = 0;
    private boolean hasMoved;
    private int food;
    private static int deathFood = 0;
    private static int foodIncrement = 1;
    private static final int vision = 4;

    public Rabbit(){
        count++;
    }

    /**
     * @return team.rocket.Entities.Rabbit's icon as a character
     */
    public static char toIcon(){
        return icon;
    }

    /**
     * gets the icon from an instance
     * @return the icon of the organism
     */
    public char instancedToIcon(){return icon;}

    /**
     * @return current Rabbit count
     */
    public static int getCount(){
        return count;
    }

    @Override
    public void setCount(int i) {
        count = i;
    }


    @Override
    public void reduceCount() {
        count--;
    }

    @Override
    public AbstractOrganism getNewObjectFromExistingObject() {
        return new Rabbit();
    }

    /**
     * Creates new Rabbit
     */
    public void reproduce(){} //not yet implemented

    /**
     * Resets hasMoved to false, meant to be used to reset movement each day
     */
    public void resetMove(){
        this.hasMoved = false;
    }

    /**
     * Takes array of a team.rocket.Entities.Rabbit's neighbors, randomly chooses an available space, and returns corresponding direction
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return randomly determined direction based on available spaces
     */
    public Direction availableMovementSpace(AbstractOrganism[] neighbors){
        List<Direction> freeSpaces = new LinkedList<>(); // Stores available movement directions

        for (Direction direction: Direction.values()) {
            if (neighbors[Direction.toInteger(direction)] == null) {
                freeSpaces.add(direction);
            }
        }

        if (freeSpaces.isEmpty()) {
            return null;
        } else if (freeSpaces.size() == 1) {
            return freeSpaces.get(0);
        } else {
            return freeSpaces.get((new Random()).nextInt(freeSpaces.size())); // Randomly picks and returns a free space
        }
    }

    /**
     * Moves Rabbit in grid based on current position, available movement space, and past movement
     * @param grid 2D array holding all Organisms in simulation
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @param y - y position of Rabbit in grid
     * @param x - x position of Rabbit in grid
     */
    public void move(AbstractAnimal[][] grid, AbstractAnimal[] neighbors, int y, int x) {
        if (hasMoved) {
            return;
        }

        Direction direction = this.availableMovementSpace(neighbors);

        if (direction == null) {
            return;
        }

        if (direction == Direction.UP) {
            grid[y][x] = null;
            grid[y-1][x] = this;
        }

        if (direction == Direction.DOWN) {
            grid[y][x] = null;
            grid[y+1][x] = this;
        }

        if (direction == Direction.LEFT) {
            grid[y][x] = null;
            grid[y][x-1] = this;
        }

        if (direction == Direction.RIGHT) {
            grid[y][x] = null;
            grid[y][x+1] = this;
        }
        hasMoved = true;
    }

    public boolean isStarving() {
        return food < deathFood;
    }

    public void eat(Map map, int row, int column) {
        map.removeOrganism(row, column);
        food += foodIncrement;
    }

    public static int getVision() {
        return vision;
    }
}
