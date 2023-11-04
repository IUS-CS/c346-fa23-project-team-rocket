package team.rocket.Enums;

/**
 * @since 0.1.0
 * @version 0.1.0
 */
public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        /**
         * Gets a Direction from an int where the range [0,3] is assigned to UP, DOWN, LEFT, RIGHT in that order
         * @param i the integer representing the Direction
         * @return a Direction
         */
        public static Direction directionFromInt(int i){

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
    }


