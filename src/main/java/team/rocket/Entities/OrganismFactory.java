package team.rocket.Entities;

import java.util.HashMap;

/**
 * Imitating <a href="https://www.oodesign.com/singleton-pattern">Singleton</a>. Creates new objects of organisms after
 * they've been registered.
 *
 * @version 0.6.0
 * @since 0.3.0
 */
public class OrganismFactory {
	private static OrganismFactory singleton;
	private HashMap<String, AbstractOrganism> m_RegisteredOrganisms = new HashMap<>();
	private OrganismFactory(){}

	/**
	 * Singleton creator.
	 *
	 * @return  the single instance of the factory
	 */
	public static synchronized OrganismFactory getInstance(){
		if (singleton == null) {
			singleton = new OrganismFactory();
		}

		return singleton;
	}

	/**
	 * Registers an organism to the internal Hashmap. Reduces organism count to 0.
	 *
	 * @param OrganismName  string name of the organism
	 * @param organism 		a new object of the organism
	 */
	public void registerOrganism (String OrganismName, AbstractOrganism organism) {
		OrganismName = OrganismName.toLowerCase();
		if (!m_RegisteredOrganisms.containsKey(OrganismName)) {
			m_RegisteredOrganisms.put(OrganismName, organism);
			organism.setCount(0);
		}
	}

	/**
	 * Creates a new instance of the organism with the id.
	 *
	 * @param OrganismName	organism to get the instance of
	 * @return 				null if organism doesn't exist/wasn't registered, otherwise an object of AbstractOrganism type
	 */
	public AbstractOrganism createOrganism (String OrganismName){
		OrganismName = OrganismName.toLowerCase();
		if (!m_RegisteredOrganisms.containsKey(OrganismName)) {
			return null;
		}
		return m_RegisteredOrganisms.get(OrganismName).getNewObjectFromExistingObject();
	}
}
