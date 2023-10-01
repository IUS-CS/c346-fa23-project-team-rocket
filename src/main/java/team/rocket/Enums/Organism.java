package team.rocket.Enums;

import team.rocket.AbstractOrganism;
import team.rocket.Rabbit;

/**
 * Organism is an Enum class that contains a label and reference to every organism in the program
 *
 */
public enum Organism {
	RABBIT (
		"Rabbit",
		new Rabbit()
		);

	/**
	 * ID of the Organism, typically the organism name
	 */
	private String OrganismId;
	/**
	 * Returns an AbstractOrganism object
	 */
	private AbstractOrganism OrganismClassReference;

	/**
	 * Creates an Organism Entry
	 * @param organismId String ID of organism
	 * @param abstractOrganism Organism Class Reference
	 */
	private Organism(String organismId, AbstractOrganism abstractOrganism){
		this.OrganismId = organismId;
		this.OrganismClassReference = abstractOrganism;
	}

	/**
	 *  Returns the class reference to an Organism
	 * @param s OrganismId to get the class reference of
	 * @return The class reference of the Organism or null
	 */
	static public AbstractOrganism getOrganism(String s){
		try {
			return Organism.valueOf(s.toUpperCase()).OrganismClassReference;
		} catch (IllegalArgumentException e){
			//returns null if illegal argument passed
			return null;
		}
	}



}
