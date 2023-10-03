package team.rocket;

import team.rocket.Enums.Direction;

public abstract class AbstractAnimal extends AbstractOrganism {
    private static char icon;
    private static int count;

    /**
     * @return Animal's icon as a character
     */
    public static char toIcon(){
        return icon;
    }

    /**
     * @return current Animal count
     */
    public static int getCount(){
        return count;
    }

    /**
     * Creates new Animal
     */
    public abstract void reproduce();

    /**
     * Takes array of an Animal's neighbors, randomly chooses an available space, and returns corresponding direction
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return randomly determined direction based on available spaces
     */
    public abstract Direction availableMovementSpace(AbstractAnimal[] neighbors);
}