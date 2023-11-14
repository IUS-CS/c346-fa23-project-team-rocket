package team.rocket.util;

import java.util.Random;

/**
 * A utility class for the Random java tool
 * @since 0.6.0
 * @version 0.6.0
 */
public class RandomManager {
	private static Random random;

	private RandomManager(){}

	/**
	 * Creates one instance of Random and reuses it
	 * @return the Random instance
	 */
	public static Random getRandom(){
		if (random == null){
			random = new Random();
		}
		return random;
	}
}
