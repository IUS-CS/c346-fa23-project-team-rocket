package team.rocket.Handlers.Terminal;

import team.rocket.AbstractOrganism;
import team.rocket.Map;
import team.rocket.OrganismFactory;

import java.util.Random;
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
		String Organism;
		int Organism_Amount;
		//Need to identify strings that follow the pattern "<string>_count 6"
		//Split at "--" to identify each flag relevant to this request Handler
		String[] flags = tFRequest.getTerminalCommand().split("--");
		Pattern pattern = Pattern.compile("[a-zA-Z]+_count [0-9]{1,10}");
		Matcher matcher;
		Map editedMap = new Map(tFRequest.getMap().getWidth(), tFRequest.getMap().getHeight());
		AbstractOrganism[][] grid = editedMap.getGrid();
		for(String flag:flags){
			matcher = pattern.matcher(flag);
			boolean matchFound = matcher.find();
			if(matchFound){
				//Gets the organism name
				Organism = flag.split("[_]")[0];
				//Gets the count of that organism
				//Splits on the space to get the string number to parse into an int
				Organism_Amount = Integer.parseInt(flag.split(" ")[1]);
				OrganismFactory factory = OrganismFactory.getInstance();
				AbstractOrganism organism = factory.createOrganism(Organism);
				if(organism==null){
					continue;
				} else {
					organism.reduceCount();

					//Places the Organisms one after another in the grid as long as theres an empty space
					for (int y = 0; y < grid.length; y++) {
						for (int x = 0; x < grid[y].length; x++) {
							if (grid[y][x] == null && Organism_Amount > 0) { //Checks whether there's organisms left
								grid[y][x] = OrganismFactory.getInstance().createOrganism(Organism);
								Organism_Amount--; //Made an organism
							}
						}
					}


				}

			}

		}
		editedMap = new Map(grid);
		tFRequest.setMap(editedMap);
		super.handleRequest(tFRequest);

	}
}
