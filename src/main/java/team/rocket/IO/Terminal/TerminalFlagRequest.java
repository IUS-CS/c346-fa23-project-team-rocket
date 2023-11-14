package team.rocket.IO.Terminal;


import team.rocket.Map;
import team.rocket.Simulation;

/**
 * A request to be used with the Terminal FlagHandlers
 * @since 0.3.0
 * @version 0.5.0
 */
public class TerminalFlagRequest {
	/**
	 * Contains a Map to be used in conjunction with Terminal Flags
	 */
	private Map map;
	/**
	 * The full terminal command in string form
	 */
	final private String terminalCommand;

	/**
	 * The number of Days to run the simulation, starts as the default
	 */
	private int numOfDays = Simulation.DEFAULT_DAYS_PER_RUN;

	/**
	 * The number of steps per day in the simulation, starts as the default
	 */
	private int stepsPerDay = Simulation.DEFAULT_TIME_STEPS_PER_DAY;

	/**
	 * Constructs a TerminalFlagRequest
	 * @param command The terminal Command
	 * @param map The current map being used, typically blank
	 */
	public TerminalFlagRequest(String command, Map map){
		this.map = map;
		this.terminalCommand = command;
	}

	/**
	 * Gets the map object
	 * @return the map
	 */
	public Map getMap() {
		return this.map;
	}

	/**
	 * Allows the map to be set, can be used for passing information
	 * @param map 2D array of Abstract organisms
	 */
	public void setMap(Map map){
		this.map = map;
	}

	/**
	 * Gets the terminalCommand string
	 * @return the TerminalCommand string
	 */
	public String getTerminalCommand() {
		return this.terminalCommand;
	}

	/**
	 * Gets the numOfDays integer
	 * @return the numOfDays integer
	 */
	public int getNumOfDays(){
		return this.numOfDays;
	}

	/**
	 * Sets the numOfDays integer
	 * @param num the new number of days
	 */
	public void setNumOfDays(int num){
		this.numOfDays = num;
	}

	/**
	 * gets the stepsPerDay integer
	 * @return the stepsPerDay integer
	 */
	public int getStepsPerDay() {return this.stepsPerDay; }

	/**
	 * sets the stepsPerDay integer
	 * @param num the new number of steps per day
	 */
	public void setStepsPerDay(int num){this.stepsPerDay = num; }
}
