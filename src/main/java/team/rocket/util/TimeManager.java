package team.rocket.util;

import java.time.Instant;

/**
 * Handles Getting the time and makes it require less instantiation.
 *
 * @since 0.6.0
 * @version 0.6.0
 */
public class TimeManager {
	static Instant instant; // Java time library Instant instance

	/**
	 * A default constructor for a TimeManager
	 */
	private TimeManager() {

	}

	/**
	 * Gets the systems current epoch time and returns it as a long.
	 *
	 * @return	a long representing the current system time
	 */
	public static long getCurrentTime() {
		instant = Instant.now();
		return instant.toEpochMilli();
	}
}
