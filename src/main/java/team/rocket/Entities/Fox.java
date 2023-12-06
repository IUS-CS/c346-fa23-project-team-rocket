package team.rocket.Entities;

import team.rocket.Enums.Direction;
import team.rocket.Map;

import java.util.Random;

/**
 * @since 0.4.0
 * @version 0.4.0
 */
public class Fox extends AbstractAnimal {
    private static final char icon = 'F';
    private static int count = 0;
    private boolean hasMoved;
    private boolean hasBred;
    private int hunger;
    private int nutrition = 0;
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
    public int getHunger() {
        return hunger;
    }

    /**
     * @return Fox nutrition
     */
    public int getNutrition() {
        return nutrition;
    }

    /**
     * decreases Fox's hunger meter
     */
    public void reduceHunger(){
        hunger-=10;
    }

    public boolean isStarving() {
        return hunger <= 0;
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
     *
     * @param map map of simulation
     * @param y - y position of Fox in grid
     * @param x - x position of Fox in grid
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

    public static int getVision() {
        return vision;
    }

    public int instancedGetVision() {
        return vision;
  
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
