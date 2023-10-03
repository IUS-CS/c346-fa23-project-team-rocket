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
     * Creates new Organism
     */
    public abstract void reproduce();

}
