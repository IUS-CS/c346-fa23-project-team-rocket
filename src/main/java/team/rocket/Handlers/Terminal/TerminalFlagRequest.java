package team.rocket.Handlers.Terminal;


import team.rocket.Map;

/**
 * A request to be used with the Terminal FlagHandlers
 * @since 0.3.0
 * @version 0.3.0
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

	private int numOfDays = 0;

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
}
