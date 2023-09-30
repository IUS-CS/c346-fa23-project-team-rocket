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

	private String OrganismId;
	private AbstractOrganism OrganismClassReference;

	private Organism(String organismId, AbstractOrganism abstractOrganism){
		this.OrganismId = organismId;
		this.OrganismClassReference = abstractOrganism;
	}

	/**
	 *  Returns the class reference to an Organism
	 * @param s OrganismId to get the class reference of
	 * @return The class reference of the Organism
	 */
	static public AbstractOrganism getOrganism(String s){
		return Organism.valueOf(s.toUpperCase()).OrganismClassReference;
	}



}
