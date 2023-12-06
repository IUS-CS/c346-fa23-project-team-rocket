package team.rocket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import team.rocket.Entities.AbstractAnimal;
import team.rocket.Entities.AbstractOrganism;
import team.rocket.Entities.Fox;
import team.rocket.Entities.Grass;
import team.rocket.Entities.Rabbit;
import team.rocket.Enums.Direction;

/**
 * A Map contains the information about the arrangement of a set of simulated organisms.
 *
 * @version 0.6.0
 * @since 0.2.0
 */
public class Map {
    private AbstractOrganism[][] grid; // The 2D array containing all the organisms on the map
    private int width; // The width of the map
    private int height; // The height of the map
    private long numberOfOrganisms; // The total number of organisms in this map
    public static final int DEFAULT_WIDTH = 5; // The default value for the width of the map
    public static final int DEFAULT_HEIGHT = 5; // The default value for the height of the map

    /**
     * A constructor for the map class that sets the grid to an empty grid of default size and sets the
     * width and height to their default values.
     */
    public Map() {
        grid = new AbstractOrganism[DEFAULT_HEIGHT][DEFAULT_WIDTH];
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        numberOfOrganisms = 0;
    }

    /**
     * A constructor for the Map class that sets the grid to an empty 2D array of AbstractOrganisms with a
     * given width and height.
     *
     * @param width     the desired width (number of columns) of the grid
     * @param height    the desired height (number of rows) of the grid
     */
    public Map(int width, int height) {
        if (width == 0 || height == 0) {
            grid = null;
        } else {
            grid = new AbstractOrganism[height][width];
        }

        this.width = width;
        this.height = height;
        numberOfOrganisms = 0;
    }

    /**
     * A constructor for the Map class that sets the grid to the given grid and sets the width and height
     * to the width and height of the grid.
     *
     * @param grid  a 2D array of AbstractOrganisms for the map to set its grid to
     */
    public Map(AbstractOrganism[][] grid) {
        this.grid = grid;
        width = grid[0].length;
        height = grid.length;

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++){
                if (grid[h][w] != null) {
                    numberOfOrganisms++;
                }
            }
        }
    }

    /**
     * Returns the grid of this map.
     *
     * @return  a 2D array containing the simulated AbstractOrganisms
     */
    public AbstractOrganism[][] getGrid() {
        return grid;
    }

    /**
     * Sets the grid of the map to the given grid and changes the width and height values if applicable.
     *
     * @param grid  the desired grid of the map.
     */
    public void setGrid(AbstractOrganism[][] grid) {
        this.grid = grid;
        width = grid[0].length;
        height = grid.length;

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++){
                if (grid[h][w] != null) {
                    numberOfOrganisms++;
                }
            }
        }
    }

    /**
     * Returns the width of the map.
     *
     * @return  an int representing the width of the grid of the map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the grid of the map.
     *
     * @return an int representing the height of the grid of the map
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the organism at the specified location.
     *
     * @param row       the row that the organism is in
     * @param column    the column that the organism is in
     * @return          AbstractOrganism or null if the location is empty
     */
    public AbstractOrganism getOrganism(int row, int column) {
        return grid[row][column];
    }

    /**
     * Adds an organism to the specified location.
     *
     * @param organism  the organism that is to be added to the map
     * @param row       the row that the organism will be in
     * @param column    the column that the organism will be in
     */
    public void addOrganism(AbstractOrganism organism, int row, int column) {
        if (grid[row][column] == null) {
            this.numberOfOrganisms++;
        }

        grid[row][column] = organism;
    }

    /**
     * Removes the organism at the specified location.
     *
     * @param row       the row of the organism that is to be removed from the map
     * @param column    the column of the organism that is to be removed from the map
     */
    public void removeOrganism(int row, int column) {
        if (grid[row][column] != null) {
            this.numberOfOrganisms--;
        }

        grid[row][column] = null;
    }

    /**
     * Returns a boolean telling whether the map is empty.
     *
     * @return true if the map is empty, false otherwise
     */
    public boolean isEmpty() {
        return numberOfOrganisms == 0;
    }

    /**
     * Returns a boolean telling whether the map is full.
     *
     * @return true if the map is full, false otherwise
     */
    public boolean isFull() {
        return numberOfOrganisms == (long) width * height;
    }

    /**
     * Gets the neighbors of the position (x,y). If a neighbor is a wall then that entry isn't included.
     *
     * @param x     the x coordinate of the position to be checked
     * @param y     the y coordinate of the position to be checked
     * @return      a HashMap with entries of the neighbors with the Directions as keys
     */
    public HashMap<Direction, AbstractOrganism> getNeighbors(int x, int y) {
        if (x < 0 || x >= width) {
            throw new IllegalArgumentException("x is outside bounds [0,%d].".formatted(width));
        } else if (y < 0 || y >= height){
            throw new IllegalArgumentException("y is outside bounds [0,%d].".formatted(height));
        }

        HashMap<Direction, AbstractOrganism> hashMap = new HashMap<>();

        if (y-1 >= 0) {
            hashMap.put(Direction.UP, grid[y-1][x]);
        }
        if (y+1 < height) {
            hashMap.put(Direction.DOWN, grid[y+1][x]);
        }
        if (x-1 >= 0) {
            hashMap.put(Direction.LEFT, grid[y][x-1]);
        }
        if (x+1 < width) {
            hashMap.put(Direction.RIGHT, grid[y][x+1]);
        }

        return hashMap;
    }

    /**
     * Gets the neighbors of the position (x,y) as characters. If a neighbor is a wall, then that entry isn't included.
     *
     * @param x the x coordinate of the position to be checked
     * @param y the y coordinate of the position to be checked
     * @return  a HashMap of with entries of the neighbors with directions as keys
     */
    public HashMap<Direction, Character> getNeighborsAsCharacter(int x, int y) {
        if (x < 0 || x >= width) {
            throw new IllegalArgumentException("x is outside bounds [0,%d].".formatted(width));
        } else if (y < 0 || y >= height){
            throw new IllegalArgumentException("y is outside bounds [0,%d].".formatted(height));
        }

        HashMap<Direction, Character> returnArrayList = new HashMap<>();
        char icon;

        if (y-1 >= 0) {
            icon = ' ';
            if (grid[y-1][x]!=null) {
                icon = grid[y-1][x].instancedToIcon();
            }
            returnArrayList.put(Direction.UP, icon);
        }
        if (y+1 < height) {
            icon = ' ';
            if (grid[y+1][x]!=null) {
                icon = grid[y+1][x].instancedToIcon();
            }
            returnArrayList.put(Direction.DOWN, icon);
        }
        if (x-1 >= 0) {
            icon = ' ';
            if (grid[y][x-1]!=null) {
                icon = grid[y][x-1].instancedToIcon();
            }
            returnArrayList.put(Direction.LEFT, icon);
        }
        if (x+1 < width) {
            icon = ' ';
            if (grid[y][x+1]!=null) {
                icon = grid[y][x+1].instancedToIcon();
            }
            returnArrayList.put(Direction.RIGHT, icon);
        }

        return returnArrayList;
    }

    /**
     * Gets the number of entities held by the map.
     *
     * @return  a long representing the number of entities held by the map
     */
    public long getNumberOfOrganisms() {
        return numberOfOrganisms;
    }

    /**
     * Moves an organism at (currentCol, currentRow) to (newCol, newRow) and removes the organism at that location if
     * one is there.
     *
     * @param currentRow                        the current x position of the organism
     * @param currentCol                        the current y position of the organism
     * @param newRow                            the x destination of the organism
     * @param newCol                            the y destination of the organism
     * @throws UnsupportedOperationException    when the value at (currentCol, currentRow) is null
     */
    public void moveOrganism(int currentRow, int currentCol, int newRow, int newCol) {
        if (grid[currentRow][currentCol] == null) {
            throw new UnsupportedOperationException("Tried to move a null value to a new position.");
        }
        if (grid[newRow][newCol] != null) {
            numberOfOrganisms--;
        }

        grid[newRow][newCol] = grid[currentRow][currentCol];
        grid[currentRow][currentCol] = null;
    }

    /**
     * Finds the location of the nearest target to the given animal within the animal's range of vision
     *
     * @param animal    the animal that is looking for a target
     * @return          an array containing the row and column of the nearest target to the animal or null if it can't
     *                  find a target
     */
    public Optional<int[]> locateNearestTarget(AbstractAnimal animal, int row, int column) {
        Optional<int[]> location = Optional.empty();

        Queue<int[]> queue = new LinkedList<>();
        boolean targetFound = false;
        int[] coordinates;

        if (animal instanceof Rabbit) {
            for (int checkingRange = 1; checkingRange <= Rabbit.getVision(); checkingRange++) { // Checks a rabbit's whole range of vision
                /*
                    The following for loop adds locations to the queue in the following pattern (using a checking range
                    of 3 and a starting animal of a rabbit for example):

                    Before:           i = 0:            i = 1:            i = 2:
                    . . . . . . .     . . . X . . .     . . . X . . .     . . . X . . .
                    . . . . . . .     . . . . . . .     . . X . . . .     . . X . X . .
                    . . . . . . .     . . . . . . .     . . . . . X .     . X . . . X .
                    . . . R . . .     X . . R . . X     X . . R . . X     X . . R . . X
                    . . . . . . .     . . . . . . .     . X . . . . .     . X . . . X .
                    . . . . . . .     . . . . . . .     . . . . X . .     . . X . X . .
                    . . . . . . .     . . . X . . .     . . . X . . .     . . . X . . .
                 */
                int checkingRow;
                int checkingColumn;
                for (int i = 0; i < checkingRange; i++) {
                    checkingRow = row + i;
                    checkingColumn = column + (checkingRange - i);
                    if ((checkingRow >= 0 && checkingRow < this.height) &&
                            (checkingColumn >= 0 && checkingColumn < this.width)) {
                        queue.add(new int[] {checkingRow, checkingColumn});
                    }

                    checkingRow = row + (checkingRange - i);
                    checkingColumn = column - i;
                    if ((checkingRow >= 0 && checkingRow < this.height) &&
                            (checkingColumn >= 0 && checkingColumn < this.width)) {
                        queue.add(new int[] {checkingRow, checkingColumn});
                    }

                    checkingRow = row - i;
                    checkingColumn = column - (checkingRange - i);
                    if ((checkingRow >= 0 && checkingRow < this.height) &&
                            (checkingColumn >= 0 && checkingColumn < this.width)) {
                        queue.add(new int[] {checkingRow, checkingColumn});
                    }

                    checkingRow = row - (checkingRange - i);
                    checkingColumn = column + i;
                    if ((checkingRow >= 0 && checkingRow < this.height) &&
                            (checkingColumn >= 0 && checkingColumn < this.width)) {
                        queue.add(new int[] {checkingRow, checkingColumn});
                    }
                }

                while ((coordinates = queue.poll()) != null) { // Checks the coordinates currently in the queue
                    if (this.getOrganism(coordinates[0], coordinates[1]) instanceof Grass) {
                        location = Optional.of(coordinates);
                        targetFound = true;
                        break;
                    }
                }

                if (targetFound) {
                    break;
                }
            }

            if (!targetFound) {
                location = Optional.empty();
            }
        } else if (animal instanceof Fox) { // Checks a fox's whole range of vision
            for (int checkingRange = 1; checkingRange <= Fox.getVision(); checkingRange++) { // Checks a rabbit's whole range of vision
                /*
                    The following for loop adds locations to the queue in the following pattern (using a checking range
                    of 3 and a starting animal of a rabbit for example):

                    Before:           i = 0:            i = 1:            i = 2:
                    . . . . . . .     . . . X . . .     . . . X . . .     . . . X . . .
                    . . . . . . .     . . . . . . .     . . X . . . .     . . X . X . .
                    . . . . . . .     . . . . . . .     . . . . . X .     . X . . . X .
                    . . . R . . .     X . . R . . X     X . . R . . X     X . . R . . X
                    . . . . . . .     . . . . . . .     . X . . . . .     . X . . . X .
                    . . . . . . .     . . . . . . .     . . . . X . .     . . X . X . .
                    . . . . . . .     . . . X . . .     . . . X . . .     . . . X . . .
                 */
                int checkingRow;
                int checkingColumn;
                for (int i = 0; i < checkingRange; i++) {
                    checkingRow = row + i;
                    checkingColumn = column + (checkingRange - i);
                    if ((checkingRow >= 0 && checkingRow < this.height) &&
                            (checkingColumn >= 0 && checkingColumn < this.width)) {
                        queue.add(new int[] {checkingRow, checkingColumn});
                    }

                    checkingRow = row + (checkingRange - i);
                    checkingColumn = column - i;
                    if ((checkingRow >= 0 && checkingRow < this.height) &&
                            (checkingColumn >= 0 && checkingColumn < this.width)) {
                        queue.add(new int[] {checkingRow, checkingColumn});
                    }

                    checkingRow = row - i;
                    checkingColumn = column - (checkingRange - i);
                    if ((checkingRow >= 0 && checkingRow < this.height) &&
                            (checkingColumn >= 0 && checkingColumn < this.width)) {
                        queue.add(new int[] {checkingRow, checkingColumn});
                    }

                    checkingRow = row - (checkingRange - i);
                    checkingColumn = column + i;
                    if ((checkingRow >= 0 && checkingRow < this.height) &&
                            (checkingColumn >= 0 && checkingColumn < this.width)) {
                        queue.add(new int[] {checkingRow, checkingColumn});
                    }
                }

                while ((coordinates = queue.poll()) != null) { // Checks the coordinates currently in the queue
                    if (this.getOrganism(coordinates[0], coordinates[1]) instanceof Rabbit) {
                        location = Optional.of(coordinates);
                        targetFound = true;
                        break;
                    }
                }

                if (targetFound) {
                    break;
                }
            }

            if (!targetFound) {
                location = Optional.empty();
            }
        } else {
            location = Optional.empty();
        }

        return location;
    }
}
