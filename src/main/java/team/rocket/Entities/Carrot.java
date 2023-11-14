package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.util.RandomManager;

import java.util.Random;

/**
 * @since 0.5.0
 * @version 0.5.0
 */
public class Carrot extends AbstractPlant {
    private static char icon = 'C';
    private static int nutrition = 25;
    private static int count;
    private boolean hasGrown;

    /**
     * Creates new Carrot, not ready to grow
     */
    public Carrot(){
        count++;
        hasGrown = true;
    }

    /**
     * @return boolean indication if Carrot has grown this cycle
     */
    public boolean growthStatus(){
        return this.hasGrown;
    }

    /**
     * @return Carrot's icon as a character
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
     * @return current Carrot count
     */
    public static int getCount(){
        return count;
    }

    /**
     * @return Carrot nutrition
     */
    public static int getNutrition(){
        return nutrition;
    }

    /**
     * Sets the count of Carrot
     * @param i the number count is being set too
     */
    public void setCount(int i) {
        count = i;
    }

    /**
     * Needed for very specific instance with OrganismEnum so that the instance in the enum doesn't count towards the total number of Organisms
     */
    public void reduceCount() {
        count--;
    }

    /**
     * Takes the instance of an object and creates a brand new one and returns that new object
     * @return a fresh new not-copied AbstractOrganism
     */
    public AbstractOrganism getNewObjectFromExistingObject(){
        return new Carrot();
    }

    /**
     * Resets hasGrown to false, meant to be used to reset growth each day
     */
    public void resetGrown(){
        this.hasGrown = false;
    }

    /**
     * Takes array of a team.rocket.Entities.Rabbit's neighbors, randomly chooses an available space, and returns corresponding direction
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return randomly determined direction based on available spaces
     */
    public Direction availableSpace(AbstractOrganism[] neighbors){
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
            return freeSpaces[RandomManager.getRandom().nextInt(freeSpaceCount)]; //randomly picks and returns a free space
        }
    }

    /**
     * Creates new Carrot in free adjacent slot
     * @param grid 2D array holding all Organisms in simulation
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @param y - y position of Carrot in grid
     * @param x - x position of Carrot in grid
     */
    public void grow(AbstractOrganism grid[][], AbstractOrganism[] neighbors, int y, int x) {
        if (hasGrown) {
            return;
        }

        Direction direction = this.availableSpace(neighbors);

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
