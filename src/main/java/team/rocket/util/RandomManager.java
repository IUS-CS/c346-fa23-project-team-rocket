package team.rocket.util;

import java.util.Random;

/**
 * @since 0.6.0
 * @version 0.6.0
 */
public class RandomManager {
	private static Random random;

	private RandomManager(){};
	public static Random getRandom(){
		if (random == null){
			random = new Random();
		}
		return random;
	}
}
