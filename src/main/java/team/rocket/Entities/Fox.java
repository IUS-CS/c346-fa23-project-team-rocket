package team.rocket.Entities;

import team.rocket.Enums.Direction;

import java.util.Random;

/**
 * @since 0.4.0
 * @version 0.4.0
 */
public class Fox extends AbstractAnimal{
    private static final char icon = 'F';
    private static int count = 0;
    private boolean hasMoved;
    private boolean hasBred;
    private int hunger;
    private static final int vision = 5;

    public Fox(){
        count++;
        hasMoved = true;
        hasBred = true;
        hunger = 100; //100 is full, 0 is empty
    }

    /**
     * gets the icon from an instance
     * @return the icon of the organism
     */
    public char instancedToIcon(){return icon;}

    /**
     * @return team.rocket.Entities.Fox's icon as a character
     */
    public static char toIcon(){
        return icon;
    }

    /**
     * @return current Fox count
     */
    public static int getCount(){
        return count;
    }

    /**
     * @return Fox's current hunger
     */
    public int getHunger(){return hunger;}

    /**
     * decreases Fox's hunger meter
     */
    public void reduceHunger(){
        hunger-=10;
        if (hunger < 0){
            hunger = 0;
        }
    }

    /**
     * increases Fox's hunger meter
     */
    public void eat() {
        hunger+=10;
        if (hunger > 100){
            hunger = 100;
        }
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
        return new Fox();
    }

    /**
     * Creates new Fox
     */
    public void breed(){} //not yet implemented

    /**
     * Resets hasMoved to false, meant to be used to reset movement each day
     */
    public void resetMove(){
        hasMoved = false;
    }

    @Override
    public void reproduce() {

    }

    /**
     * Resets hasBred to false, meant to be used to reset breeding each day
     */
    public void resetBreeding(){
        hasBred = false;
    }

    /**
     * Takes array of a team.rocket.Entities.Fox's neighbors, randomly chooses an available space, and returns corresponding direction
     * @param neighbors array of organisms in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return randomly determined direction based on available spaces
     */
    public Direction availableMovementSpace(AbstractOrganism[] neighbors){
        int i = 0; //tracks iterations of for loop
        int freeSpaceCount = 0; //stores number of free adjacent spaces
        Direction[] freeSpaces = new Direction[4]; //stores available movement directions

        for(i = 0; i < 4; i++){
            if(neighbors[i] == null){
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

        if(freeSpaceCount==0){ //returns null in case of no free spaces
            return null;
        }
        if(freeSpaceCount==1){
            return freeSpaces[0];
        }
        else{
            return freeSpaces[new Random().nextInt(freeSpaceCount)]; //randomly picks and returns a free space
        }
    }

    /**
     * Moves Fox in grid based on current position, available movement space, and past movement
     * @param grid 2D array holding all Organisms in simulation
     * @param neighbors array of organisms in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @param y - y position of Fox in grid
     * @param x - x position of Fox in grid
     */
    public void move(AbstractAnimal grid[][], AbstractAnimal[] neighbors, int y, int x) {
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

    public static int getVision() {
        return vision;
    }
}
