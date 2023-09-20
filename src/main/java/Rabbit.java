import java.util.Random;
public class Rabbit extends AbstractAnimal{

    private static char icon = 'R';
    private static int count = 0;

    /**
     * @return Rabbit's icon as a character
     */
    public static char toIcon(){
        return icon;
    };

    /**
     * Takes array of a Rabbit's neighbors, randomly chooses an available space, and returns corresponding direction
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return randomly determined direction based on available spaces
     */
    public Direction availableMovementSpace(AbstractAnimal[] neighbors){
        int i = 0; //tracks iterations of for loop
        int freeSpaceCount = 0; //stores number of free adjacent spaces
        Direction[] freeSpaces = new Direction[4]; //stores available movement directions

        for(AbstractAnimal neighbor : neighbors){
            if(neighbor == null){
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
            i++; //increments loop count
        }
        if(freeSpaceCount==0){ //returns null in case of no free spaces
            return null;
        }
        if(freeSpaceCount==1){
            return freeSpaces[0];
        }
        else{
            return freeSpaces[new Random().nextInt(0,freeSpaceCount-1)]; //randomly picks and returns a free space
        }
    };

    //public abstract void reproduce(); //creates new animal

    /**
     * @return total number of living Rabbits
     */
    public int total(){
        return count;
    };
}
