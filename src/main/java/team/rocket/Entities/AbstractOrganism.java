package team.rocket.Entities;

/**
 * @version 0.6.0
 * @since 0.1.0
 */
public abstract class AbstractOrganism {
    private static char icon;
    private static int count;
    private static int nutrition;

    /**
     * Returns the icon representation for this type of organism
     *
     * @return  this type of organism's icon as a character
     */
    public static char toIcon() {
        return icon;
    }

    /**
     * Gets the icon from an instance
     *
     * @return  the icon of the organism
     */
    abstract public char instancedToIcon();

    /**
     * Returns the total number of organisms
     *
     * @return  current organism count
     */
    public static int getCount() {
        return count;
    }

    /**
     * Sets the count of organisms
     *
     * @param i the number count is being set too
     */
    public abstract void setCount(int i);

    /**
     * Needed for very specific instance with OrganismEnum so that the instance in the enum doesn't count towards the
     * total number of organisms
     */
    public abstract void reduceCount();

    /**
     * Takes the instance of an object and creates a brand new one and returns that new object
     *
     * @return  a fresh new not-copied abstract organism
     */
    public abstract AbstractOrganism getNewObjectFromExistingObject();

    /**
     * @return nutrition
     */
    public abstract int getNutrition();
}
