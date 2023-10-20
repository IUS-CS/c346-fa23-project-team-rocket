package team.rocket;

import team.rocket.Entities.AbstractOrganism;

/**
 * A team.rocket.Map contains the information about the arrangement of a set of simulated organisms.
 *
 * @author Dale Morris
 * @version 0.2.0
 * @since 0.2.0
 */
public class Map {
    private AbstractOrganism[][] grid; // The 2D array containing all the organisms on the map
    private int width; // The width of the map
    private int height; // The height of the map
    public static final int DEFAULT_WIDTH = 5; // The default value for the width of the grid
    public static final int DEFAULT_HEIGHT = 5; // The default value for the height of the grid

    /**
     * A constructor for the team.rocket.Map class that sets the grid to an empty grid of default size and sets the
     * width and height to their default values
     */
    public Map() {
        grid = new AbstractOrganism[DEFAULT_HEIGHT][DEFAULT_WIDTH];
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }

    /**
     * A constructor for the team.rocket.Map class that sets the grid to an empty 2D array of AbstractOrganisms with a
     * given width and height.
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
     * A constructor for the team.rocket.Map class that sets the grid to the given grid and sets the width and height
     * to the width and height of the grid.
     *
     * @param grid A 2D array of AbstractOrganisms for the map to set its grid to
     */
    public Map(AbstractOrganism[][] grid) {
        this.grid = grid;
        width = grid[0].length;
        height = grid.length;
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
     * Gets the organism at the specified location
     *
     * @param row the row that the organism is in
     * @param column the column that the organism is in
     */
    public AbstractOrganism getOrganism(int row, int column) {
        return grid[row][column];
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

    /**
     * Returns a boolean telling whether the map is empty
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
     * Returns a boolean telling whether the map is full
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
