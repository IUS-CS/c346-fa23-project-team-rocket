package team.rocket.Entities;

/**
 * An abstract class to define characteristics and behaviors for multiple types of organisms
 *
 * @version 0.6.0
 * @since 0.1.0
 */
public abstract class AbstractOrganism {
    private static char icon; // The icon representation of this type of organism
    private static int count; // The total number of existing organisms
    private static int nutrition; // The hunger value that this type of organism rewards when eaten

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
     * Returns the total number of existing organisms
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
