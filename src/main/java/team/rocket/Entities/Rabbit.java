package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.Map;

import static java.lang.Byte.parseByte;
import static java.lang.Byte.valueOf;

/**
 *
 * @since 0.1.0
 * @version 0.4.0
 */
public class Rabbit extends AbstractAnimal{
    private static final char icon = 'R';
    private static int count = 0;
    private boolean hasMoved;
    private int food;
    private static int deathFood = 0;
    private static int foodIncrement = 1;

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
        int i = 0; //tracks iterations of for loop

        //Represents available directions in the order up, down, left, right
        boolean[] availableDirections = {false,false,false,false};

        for(i = 0; i < 4; i++){
            if(neighbors[i] == null){
                availableDirections[i]=true;
            }
        }

        return Direction.randomDirectionFromBooleanArray(availableDirections); //randomly picks and returns a free space

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
}
