/**
 * A Map contains the information about the arrangement of a set of simulated animals.
 *
 * @author Dale Morris
 * @version Sprint2
 * @since Sprint2
 */
public class Map {
    private AbstractAnimal[][] grid; // The 2D array containing all of the animals on the map
    private int width; // The width of the map
    private int height; // The height of the map

    /**
     * A constructor for the Map class which sets the grid to an empty 2D array of AbstractAnimals with a given width
     * and height.
     *
     * @param width The desired width (number of columns) of the grid
     * @param height The desired height (number of rows) of the grid
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new AbstractAnimal[height][width];
    }

    /**
     * Returns the grid of the Map.
     *
     * @return A 2D array containing the simulated AbstractAnimals
     */
    public AbstractAnimal[][] getGrid() {
        return grid;
    }

    /**
     * Sets the grid of the Map to the given grid and changes the width and height values if applicable.
     *
     * @param grid The desired grid of the Map.
     */
    public void setGrid(AbstractAnimal[][] grid) {
        this.grid = grid;
        width = grid[0].length;
        height = grid.length;
    }

    /**
     * Returns the width of the grid of the Map.
     *
     * @return an int representing the width of the grid of the Map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the grid of the Map.
     *
     * @return an int representing the height of the grid of the Map
     */
    public int getHeight() {
        return height;
    }
}
