package team.rocket.Handlers.Terminal;

import team.rocket.AbstractOrganism;
import team.rocket.Enums.Organism;
import team.rocket.Map;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Handles Terminal Flags that pertain to initial Organism Count
 * Ex: --rabbit_count 6
 */
public class InitialOrganismCountFlagHandler extends FlagHandler {
	/**
	 * Takes a request and updates the map to contain an initial amount of organisms
	 * @param tFRequest The request to be handled, It's specifically a TerminalFlagRequest
	 */
	@Override
	public void handleRequest(TerminalFlagRequest tFRequest) {
		//Need to identify strings that follow the pattern "<string>_count 6"
		//Split at "--" to identify each flag relevant to this request Handler
		String[] flags = tFRequest.getTerminalCommand().split("--");
		Pattern pattern = Pattern.compile("[a-zA-Z]+_count [0-9]{1,10}");
		Matcher matcher;
		for(String flag: flags){
			matcher = pattern.matcher(flag);
			boolean matchFound = matcher.find();
			if(matchFound){
				//splitting on " " and "_"
				String[] subflags = flag.split("[ _]");
				//subflag[0] organism exists
				if(Organism.getOrganism(subflags[0])!=null) {
					Map editedMap = new Map(tFRequest.getMap().getWidth(), tFRequest.getMap().getHeight());
					AbstractOrganism organism = Organism.getOrganism(subflags[0]);
					for(int i = Integer.parseInt(subflags[2]); i>0; i--){

					}
				} else {
					// go to next flag
					continue;
				}
			}
		}
	}
}
