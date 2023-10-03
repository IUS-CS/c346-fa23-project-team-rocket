package team.rocket;

/**
 * A team.rocket.Map contains the information about the arrangement of a set of simulated organisms.
 *
 * @author Dale Morris
 * @version Sprint2
 * @since Sprint2
 */
public class Map {
    private AbstractOrganism[][] grid; // The 2D array containing all the organisms on the map
    private int width; // The width of the map
    private int height; // The height of the map

    /**
     * A constructor for the team.rocket.Map class which sets the grid to an empty 2D array of AbstractOrganisms with a
     * given width and height.
     *
     * @param width The desired width (number of columns) of the grid
     * @param height The desired height (number of rows) of the grid
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new AbstractOrganism[height][width];
    }

    /**
     * Returns the grid of the team.rocket.Map.
     *
     * @return A 2D array containing the simulated AbstractOrganisms
     */
    public AbstractOrganism[][] getGrid() {
        return grid;
    }

    /**
     * Sets the grid of the team.rocket.Map to the given grid and changes the width and height values if applicable.
     *
     * @param grid The desired grid of the team.rocket.Map.
     */
    public void setGrid(AbstractOrganism[][] grid) {
        this.grid = grid;
        width = grid[0].length;
        height = grid.length;
    }

    /**
     * Returns the width of the grid of the team.rocket.Map.
     *
     * @return an int representing the width of the grid of the team.rocket.Map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the grid of the team.rocket.Map.
     *
     * @return an int representing the height of the grid of the team.rocket.Map
     */
    public int getHeight() {
        return height;
    }

    /**
     * Adds an organism to the specified location
     *
     * @param organism the organism that is to be added to the map
     * @param row the row that the organism will be in
     * @param column the column that the organism will be in
     */
    public void addOrganism(AbstractOrganism organism, int row, int column) {
        grid[row][column] = organism;
    }

    /**
     * Removes the organism at the specified location
     *
     * @param row the row of the organism that is to be removed from the map
     * @param column the column of the organism that is to be removed from the map
     */
    public void removeOrganism(int row, int column) {
        grid[row][column] = null;
    }
}
