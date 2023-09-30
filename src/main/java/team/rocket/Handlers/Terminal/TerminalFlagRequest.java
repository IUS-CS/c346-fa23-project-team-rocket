package team.rocket.Handlers.Terminal;


import team.rocket.Map;

public class TerminalFlagRequest {
	final private Map map;
	final private String terminalCommand;

	public TerminalFlagRequest(String command, Map map){
		this.map = map;
		this.terminalCommand = command;
	}

	public Map getMap() {
		return this.map;
	}

	public String getTerminalCommand() {
		return this.terminalCommand;
	}
}
