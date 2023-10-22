package team.rocket.Handlers.Terminal;

import team.rocket.Entities.AbstractOrganism;
import team.rocket.Map;
import team.rocket.Entities.OrganismFactory;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Handles Terminal Flags that pertain to initial Organism Count
 * Will put an initial amount of Organisms onto the map
 * Ex: --rabbit_count 6
 * @since 0.3.0
 * @version 0.5.0
 */
public class InitialOrganismCountFlagHandler extends FlagHandler {
	/**
	 * Takes a request and updates the map to contain an initial amount of organisms
	 * @param tFRequest The request to be handled, It's specifically a TerminalFlagRequest
	 */
	@Override
	public void handleRequest(TerminalFlagRequest tFRequest) {
		int totalOrganismsBeingAdded = 0;
		int totalOrganismsCurrentlyPlaced = 0;
		String Organism;
		int Organism_Amount;
		//Need to identify strings that follow the pattern "<string>_count 6"
		//Split at "--" to identify each flag relevant to this request Handler
		String[] flags = tFRequest.getTerminalCommand().split("--");
		Pattern pattern = Pattern.compile("[a-zA-Z]+_count [0-9]{1,10}");
		Matcher matcher;
		Map editedMap = new Map(tFRequest.getMap().getWidth(), tFRequest.getMap().getHeight());
		for(String flag:flags){
			matcher = pattern.matcher(flag);
			boolean matchFound = matcher.find();
			if(matchFound){

				//Gets the organism name
				Organism = flag.split("[_]")[0];
				//Gets the count of that organism
				//Splits on the space to get the string number to parse into an int
				Organism_Amount = Integer.parseInt(flag.split(" ")[1]);
				totalOrganismsBeingAdded += Organism_Amount;
				if(totalOrganismsBeingAdded > tFRequest.getMap().getWidth() * tFRequest.getMap().getHeight()){
					System.out.println("WARNING: You are attempting to place more organisms then the grid can hold, unexpected behavior may occur.");
				}
				OrganismFactory factory = OrganismFactory.getInstance();
				AbstractOrganism organism = factory.createOrganism(Organism);
				if(organism==null){
					continue;
				} else {
					organism.reduceCount();
					//Places the Organisms one after another in the grid as long as theres an empty space
					final int RANDOMPLACEMENTFAILURECAP = 10; //Tries to put the organism in a random place in the grid this many times before placing them sequentially
					while(Organism_Amount > 0 && totalOrganismsCurrentlyPlaced < editedMap.getHeight()*editedMap.getWidth()){
						boolean organismPlaced = false;
						int placementAttemptCount = 0; //How many random placement attempts have occurred
						while(RANDOMPLACEMENTFAILURECAP > placementAttemptCount && !organismPlaced){ //We're fine to make a random placement attempt
							Random rand = new Random();
							int randX = rand.nextInt(editedMap.getWidth());
							int randY = rand.nextInt(editedMap.getHeight());
							if(editedMap.getOrganism(randY, randX) == null){
								editedMap.addOrganism(OrganismFactory.getInstance().createOrganism(Organism),randY, randX);
								Organism_Amount--;
								totalOrganismsCurrentlyPlaced++;
								organismPlaced = true;
							}
							placementAttemptCount++;
						}
						if(!organismPlaced && totalOrganismsCurrentlyPlaced < editedMap.getHeight()*editedMap.getWidth()) {
							//place sequentially
							for (int y = 0; y < editedMap.getHeight(); y++) {
								for (int x = 0; x < editedMap.getWidth(); x++) {
									if (editedMap.getOrganism(y, x) == null) { //Checks whether there's organisms left
										editedMap.addOrganism(OrganismFactory.getInstance().createOrganism(Organism), y, x);
										Organism_Amount--; //Made an organism
										totalOrganismsCurrentlyPlaced++;
										organismPlaced = true;
										break;
									}
								}
								if(organismPlaced){
									break;
								}
							}
						}

					}






				}

			}

		}
		tFRequest.setMap(editedMap);
		super.handleRequest(tFRequest);

	}
}
