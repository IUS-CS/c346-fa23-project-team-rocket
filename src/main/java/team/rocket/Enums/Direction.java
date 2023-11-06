package team.rocket.Enums;

/**
 * @since 0.1.0
 * @version 0.1.0
 */
public enum Direction {
        UP(0),
        DOWN(1),
        LEFT(2),
        RIGHT(3);

        private final int intValue;
        Direction(int i) {
            intValue = i;
        }

        public static int toInteger(Direction direction) {
            return direction.intValue;
        }
}
