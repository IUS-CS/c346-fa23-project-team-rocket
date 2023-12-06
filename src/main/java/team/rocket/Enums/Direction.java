package team.rocket.Enums;

import team.rocket.util.RandomManager;

/**
 * Represents one of the four cardinal directions.
 *
 * @version 0.6.0
 * @since 0.1.0
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * Gets a direction from an int where the range [0,3] is assigned to UP, DOWN, LEFT, RIGHT in that order.
     *
     * @param i the integer representing the Direction
     * @return  a Direction matching the integer
     */
    public static Direction directionFromInt(int i) {
        switch (i) { //identifies which direction is being evaluated
            case 0 -> {
                return Direction.UP;
            }
            case 1 -> {
                return Direction.DOWN;
            }
            case 2 -> {
                return Direction.LEFT;
            }
            case 3 -> {
                return Direction.RIGHT;
            }
            default -> throw new IllegalArgumentException("int i is outside range [0,3]");
        }
    }

    /**
     * Gets a direction from a string
     *
     * @param string    the string representing the direction
     * @return          a direction matching the string
     */
    public static Direction directionFromString(String string){
        switch (string.toLowerCase()) {
            case "up" -> {
                return Direction.UP;
            }
            case "down" -> {
                return Direction.DOWN;
            }
            case "left" -> {
                return Direction.LEFT;
            }
            case "right" -> {
                return Direction.RIGHT;
            }
            default -> throw new IllegalArgumentException("string is not one of the following accepted values: up, down, left, right");
        }
    }

    /**
     * Takes a 4-long array of booleans and returns a random direction. The booleans are used to determine which
     * directions it can choose from where each boolean represents a different direction in the order up, down, left,
     * right.
     *
     * @param booleans  the array of booleans used for determination
     * @return          a random direction from the ones available, or null if none were available
     */
    public static Direction randomDirectionFromBooleanArray(boolean[] booleans){
        // not enough booleans or too many booleans for directions
        if(booleans.length!=4){
            throw new IllegalArgumentException("booleans doesn't contain 4 boolean values");
        }

        // count number of false values
        byte numOfFalse = 0;
        for (boolean b: booleans) {
            if (!b) {
                numOfFalse++;
            }
        }
        // all false, no available directions
        if(numOfFalse==4) {
            return null;
        }

        // one false, one available direction
        else if (numOfFalse == 3) {
            int i = 0;
            while (!booleans[i]) {
                i++;
            }
            return Direction.directionFromInt(i);
        }

        //Max iteration
        final byte MAX_ITER = 4;
        byte iterationCount = 0;

        //Sets a random value and checks if booleans[value] is false, if it is look for a different one
        byte value = (byte) (RandomManager.getRandom().nextInt(3));
        while(!booleans[value] && iterationCount++ < MAX_ITER+1){
            value = (byte) (RandomManager.getRandom().nextInt(3));
        }

        // Gone over iteration limit, pick first available direction
        if (iterationCount >= MAX_ITER) {
            int i = 0;
            while(!booleans[i]) {
                i++;
            }
            return Direction.directionFromInt(i);
        }

        //Return the random Direction from the random value
        return Direction.directionFromInt(value);
    }
}
