package team.rocket;

public abstract class AbstractOrganism {
    private static char icon;
    private static int count;

    /**
     * @return Organism's icon as a character
     */
    public static char toIcon(){
        return icon;
    }

    /**
     * @return current Organism count
     */
    public static int getCount(){
        return count;
    }


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
     * Creates new Organism
     */
    public abstract void reproduce();

}
