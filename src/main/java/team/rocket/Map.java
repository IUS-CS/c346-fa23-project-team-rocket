package team.rocket;

/**
 * A team.rocket.Map contains the information about the arrangement of a set of simulated animals.
 *
 * @author Dale Morris
 * @version Sprint2
 * @since Sprint2
 */
public class Map {
    private AbstractOrganism[][] grid; // The 2D array containing all of the organisms on the map
    private int width; // The width of the map
    private int height; // The height of the map

    /**
     * A constructor for the team.rocket.Map class which sets the grid to an empty 2D array of AbstractOrganisms with a given width
     * and height.
     *
     * @param width The desired width (number of columns) of the grid
     * @param height The desired height (number of rows) of the grid
     */
    public Map(int width, int height) {
        if (width == 0 || height == 0) {
            grid = null;
        } else {
            grid = new AbstractOrganism[height][width];
        }

        this.width = width;
        this.height = height;
    }

    /**
     * A constructor for the team.rocket.Map class which sets the grid to the given grid and width and height to the
     * width and height of the given grid
     *
     * @param grid The desired grid for the map
     */
    public Map(AbstractOrganism[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            this.grid = null;
        } else {
            this.grid = grid;
        }

        width = grid[0].length;
        height = grid.length;
    }

    /**
     * Returns the grid of the team.rocket.Map.
     *
     * @return A 2D array containing the simulated AbstractAnimals
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
     * Returns a boolean telling whether or not the map is empty
     *
     * @return true if the map is empty, false otherwise
     */
    public boolean isEmpty() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns a boolean telling whether or not the map is full
     *
     * @return true if the map is full, false otherwise
     */
    public boolean isFull() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == null) {
                    return false;
                }
            }
        }

        return true;
    }
}
