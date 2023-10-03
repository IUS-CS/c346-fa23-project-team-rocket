package team.rocket;

import java.util.Random;
public class Rabbit extends AbstractAnimal{
    private static final char icon = 'R';
    private static int count = 0;
    private boolean hasMoved;

    public Rabbit(){
        count++;
    }

    /**
     * @return team.rocket.Rabbit's icon as a character
     */
    public static char toIcon(){
        return icon;
    }

    /**
     * @return current Rabbit count
     */
    public static int getCount(){
        return count;
    }

    /**
     * Creates new Rabbit
     */
    public void reproduce(){} //not yet implemented

    public void resetMove(){
        this.hasMoved = false;
    }

    /**
     * Takes array of a team.rocket.Rabbit's neighbors, randomly chooses an available space, and returns corresponding direction
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return randomly determined direction based on available spaces
     */
    public Direction availableMovementSpace(AbstractAnimal[] neighbors){
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

    public void move(AbstractAnimal grid[][], AbstractAnimal[] neighbors, int y, int x) {
        Direction direction = this.availableMovementSpace(neighbors);
        if (hasMoved) {
            return;
        }

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
}
