package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.Map;
import team.rocket.util.RandomManager;

import java.util.Random;

/**
 * @since 0.3.0
 * @version 0.4.0
 */
public class Grass extends AbstractPlant {
    private static char icon = 'G';
    private static int nutrition = 15;
    private static int count;
    private boolean hasGrown;

    /**
     * Creates new grass, not ready to grow
     */
    public Grass(){
        count++;
        hasGrown = true;
    }

    /**
     * @return boolean indication if grass has grown this cycle
     */
    public boolean growthStatus(){
        return this.hasGrown;
    }

    /**
     * @return Grass's icon as a character
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
     * @return current Grass count
     */
    public static int getCount(){
        return count;
    }

    /**
     * @return Grass nutrition
     */
    public int getNutrition(){
        return nutrition;
    }

    /**
     * Sets the count of Grass
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
        return new Grass();
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
     * Creates new Grass in free adjacent slot
     * @param map map of simulation
     * @param y - y position of Rabbit in grid
     * @param x - x position of Rabbit in grid
     */
    public void grow(Map map, int y, int x) {
        if (hasGrown) {
            return;
        }
        int newX = x;
        int newY = y;

        AbstractOrganism[] neighbors = findNeighbors(map, y, x);

        Direction direction = this.availableSpace(neighbors);

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

        AbstractOrganism[][] griddy = map.getGrid();
        griddy[newY][newX] = getNewObjectFromExistingObject();
        map.setGrid(griddy);
        hasGrown = true;
    }

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
     * Gets breeding status of organism
     * @return hasBred
     */
    public boolean getBreeding(){
        return hasGrown;
    }

    /**
     * Sets hasBred to true, meant to disable breeding after breed
     */
    public void breed(){
        hasGrown = true;
    }

    /**
     * Resets hasBred to false, meant to be used to reset breeding each day
     */
    public void resetBreeding(){
        hasGrown = false;
    }

}
