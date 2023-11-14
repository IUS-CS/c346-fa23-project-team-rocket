package team.rocket.IO.Terminal;

import team.rocket.Map;

import java.util.regex.Pattern;

/**
 * Handles Grid Size Flags
 * Ex --grid_width 55 --grid_height 35
 * @since 0.3.0
 * @version 0.3.0
 */
public class GridSizeFlagHandler extends FlagHandler{

	/**
	 * Handles grid size requests
	 * @param tFRequest The request to be handled, It's specifically a TerminalFlagRequest
	 */
	@Override
	public void handleRequest(TerminalFlagRequest tFRequest) {

		int Grid_Height;
		int Grid_Width;
		Grid_Height = tFRequest.getMap().getHeight();
		Grid_Width = tFRequest.getMap().getWidth();

		String TerminalCommand = tFRequest.getTerminalCommand().toLowerCase();

		//Need to identify strings that follow the pattern "<string>)_width 6"
		//Split at "--" to identify each flag relevant to this request Handler
		String[] flags = TerminalCommand.split("--");

		Pattern width_pattern = Pattern.compile("grid_width [0-9]{1,10}"); //grid_width 3
		Pattern height_pattern = Pattern.compile("grid_height [0-9]{1,10}"); //grid_height 6645

		for(String flag: flags){

			if(width_pattern.matcher(flag).find()){
				int widthWanted = Integer.parseInt(flag.split(" ")[1]);
				tFRequest.setMap(new Map(widthWanted, tFRequest.getMap().getHeight()));
			}
			else if(height_pattern.matcher(flag).find()){
				int heightWanted = Integer.parseInt(flag.split(" ")[1]);
				tFRequest.setMap(new Map(tFRequest.getMap().getWidth(),heightWanted));
			}
		}

		super.handleRequest(tFRequest);
	}
}
