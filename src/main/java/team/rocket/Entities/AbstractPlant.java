package team.rocket.Entities;

/**
 * @version 0.6.0
 * @since 0.3.0
 */
public abstract class AbstractPlant extends AbstractOrganism{
    private static char icon;
    private static int count;
    private boolean hasGrown;
    private static int nutrition;

    /**
     * Returns an icon representation of this type of plant
     *
     * @return  Plant's icon as a character
     */
    public static char toIcon() {
        return icon;
    }

    /**
     * Gets the icon from an instance
     *
     * @return  the icon of the organism
     */
    public char instancedToIcon() {
        return icon;
    }

    /**
     * Returns the total number of plants
     *
     * @return  current Plant count
     */
    public static int getCount(){
        return count;
    }

    /**
     * Sets the count of Plants
     *
     * @param i the number count is being set too
     */
    public abstract void setCount(int i);

    /**
     * Needed for very specific instance with OrganismEnum so that the instance in the enum doesn't count towards the total number of Organisms
     */
    public abstract void reduceCount();

    /**
     * Takes the instance of an object and creates a brand new one and returns that new object
     *
     * @return  a fresh new not-copied AbstractOrganism
     */
    public abstract AbstractOrganism getNewObjectFromExistingObject();

    /**
     * Resets hasGrown to false, meant to be used to reset growth each day
     */
    public void resetGrown() {
        this.hasGrown = false;
    }

    /**
     * Creates new Organism
     */
    public abstract void grow(AbstractOrganism[][] grid, AbstractOrganism[] neighbors, int y, int x);

    /**
     * Returns this plant's nutrition
     *
     * @return  nutrition
     */
    public abstract int getNutrition();
}
