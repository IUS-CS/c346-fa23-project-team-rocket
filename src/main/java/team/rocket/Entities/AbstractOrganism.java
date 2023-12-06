package team.rocket.Entities;

/**
 * @since 0.1.0
 * @version 0.4.0
 */
public abstract class AbstractOrganism {
    private static char icon;
    private static int count;
    private static int nutrition;

    /**
     * @return Organism's icon as a character
     */
    public static char toIcon(){
        return icon;
    }

    /**
     * gets the icon from an instance
     * @return the icon of the organism
     */
    abstract public char instancedToIcon();

    /**
     * @return current Organism count
     */
    public static int getCount(){
        return count;
    }

    /**
     * Sets the count of animals
     * @param i the number count is being set too
     */
    public abstract void setCount(int i);


    /**
     * Needed for very specific instance with OrganismEnum so that the instance in the enum doesn't count towards the total number of Organisms
     */
    public abstract void reduceCount();

    /**
     * Takes the instance of an object and creates a brand new one and returns that new object
     * @return a fresh new not-copied AbstractOrganism
     */
    public abstract AbstractOrganism getNewObjectFromExistingObject();

    /**
     * @return nutrition
     */
    public abstract int getNutrition();

    /**
     * Gets breeding status of organism
     * @return hasBred
     */
    public abstract boolean getBreeding();

    /**
     * Sets hasBred to true, meant to disable breeding after breed
     */
    public abstract void breed();

    /**
     * Resets hasBred to false, meant to be used to reset breeding each day
     */
    public abstract void resetBreeding();
}
