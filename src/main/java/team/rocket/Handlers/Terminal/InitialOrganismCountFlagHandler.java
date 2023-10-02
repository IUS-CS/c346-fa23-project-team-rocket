package team.rocket.Handlers.Terminal;

import team.rocket.AbstractOrganism;
import team.rocket.Enums.Organism;
import team.rocket.Map;

import java.util.Random;
import java.util.random.RandomGenerator;
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
				AbstractOrganism organism = Organism.getOrganism(subflags[0]);
				//subflag[0] organism exists
				if(organism!=null) {
					Map editedMap = new Map(tFRequest.getMap().getWidth(), tFRequest.getMap().getHeight());
					AbstractOrganism[][] grid = editedMap.getGrid();
					organism.reduceCount();
					Random rand = new Random();

					//Fixes weird issue where number strings had additional "
					subflags[2] = subflags[2].split("\"")[0];

					//Perform operation Integer.parseInt(subflags[2]) times
					for(int i = Integer.parseInt(subflags[2]); i>0; i--){
						boolean spaceNotFound = true;
						int iterationCount = 0;
						//Looks for a random space within map
						while(spaceNotFound && iterationCount < editedMap.getHeight()* editedMap.getWidth()+1) {
							iterationCount++;
							int randX = rand.nextInt(editedMap.getWidth());
							int randY = rand.nextInt(editedMap.getHeight());
							//Is space empty?
							if(grid[randY][randX]!=null){
								grid[randY][randX] = organism.getNewObjectFromExistingObject();
								spaceNotFound = false;
							}
						}
						//Catch for if the random space randomly returned the same space every iteration and failed
						//Sticks in the first open space
						if(spaceNotFound){
							for(int k = 0; k < tFRequest.getMap().getWidth(); k++){
								for(int j = 0; j < tFRequest.getMap().getHeight(); j++){
									if(grid[j][k] != null){
										grid[j][k] = organism.getNewObjectFromExistingObject();
									}
								}
							}
						}
						//No space available so there's no work left to do
						//No space means no space to put new organisms without overwriting other organisms
						else {
							super.handleRequest(tFRequest);
							break;
						}
						//Transfer the data changes out via changing the request
						editedMap = new Map(grid);
						tFRequest.setMap(editedMap);
					}
				}
			} else {
				// go to next flag
				continue;
			}
		}
		super.handleRequest(tFRequest);
	}
}
