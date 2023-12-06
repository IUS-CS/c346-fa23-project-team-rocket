package team.rocket.IO.Terminal;


import team.rocket.Map;
import team.rocket.Simulation;

/**
 * A request to be used with the Terminal FlagHandlers.
 *
 * @version 0.6.0
 * @since 0.3.0
 */
public class TerminalFlagRequest {
	private Map map; // A map to be used in conjunction with Terminal Flags
	final private String terminalCommand; // The full terminal command in string form
	private int numOfDays = Simulation.DEFAULT_DAYS_PER_RUN; // The number of Days to run the simulation, starts as the default
	private int stepsPerDay = Simulation.DEFAULT_TIME_STEPS_PER_DAY; // The number of steps per day in the simulation, starts as the default

	/**
	 * Constructs a TerminalFlagRequest
	 *
	 * @param command	the terminal command
	 * @param map		the current map being used, typically blank
	 */
	public TerminalFlagRequest(String command, Map map) {
		this.map = map;
		this.terminalCommand = command;
	}

	/**
	 * Gets the map object.
	 *
	 * @return	the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Allows the map to be set. Can be used for passing information.
	 *
	 * @param map	2D array of Abstract organisms
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Gets the terminalCommand string.
	 *
	 * @return 	the TerminalCommand string
	 */
	public String getTerminalCommand() {
		return terminalCommand;
	}

	/**
	 * Gets the numOfDays integer.
	 *
	 * @return the numOfDays integer
	 */
	public int getNumOfDays() {
		return numOfDays;
	}

	/**
	 * Sets the numOfDays integer.
	 *
	 * @param num	the new number of days
	 */
	public void setNumOfDays(int num) {
		numOfDays = num;
	}

	/**
	 * Gets the stepsPerDay integer.
	 *
	 * @return 	the stepsPerDay integer
	 */
	public int getStepsPerDay() {
		return stepsPerDay;
	}

	/**
	 * Sets the stepsPerDay integer.
	 *
	 * @param num	the new number of steps per day
	 */
	public void setStepsPerDay(int num) {
		stepsPerDay = num;
	}
}
