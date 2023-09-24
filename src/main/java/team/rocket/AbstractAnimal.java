package team.rocket;

public abstract class AbstractAnimal {
    private static char icon = 'A';
    private static int count;

    /**
     * @return animal's icon as a character
     */
    public static char toIcon() {
        return icon;
    }

    /**
     * Takes array of an animal's neighbors, randomly chooses an available space, and returns corresponding direction
     * @param neighbors array of animals in adjacent tiles, 0-3 representing UP, DOWN, LEFT, or RIGHT respectively
     * @return randomly determined direction based on available spaces
     */
    public abstract Direction availableMovementSpace(AbstractAnimal[] neighbors);

    //public abstract void reproduce(); //creates new animal

    /**
     * @return total number of living animals
     */
    public abstract int total();
}