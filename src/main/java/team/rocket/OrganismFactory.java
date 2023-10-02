package team.rocket;

import java.util.HashMap;

/**
 * Imitating <a href="https://www.oodesign.com/singleton-pattern">Singleton</a>
 * Creates new objects of organisms after they've been registered
 */
public class OrganismFactory {
	private static OrganismFactory singleton;
	private HashMap<String, AbstractOrganism> m_RegisteredOrganisms = new HashMap<>();
	public OrganismFactory(){}

	/**
	 * Singleton creator
	 * @return The single instance of the factory
	 */
	public static synchronized OrganismFactory getInstance(){
		if(singleton == null){
			singleton = new OrganismFactory();
		}
		return singleton;
	}

	/**
	 *  Registers an organism to the internal Hashmap
	 * @param OrganismName String name of the organism
	 * @param organism A new object of the organism
	 */
	public void registerOrganism (String OrganismName, AbstractOrganism organism){
		if(!m_RegisteredOrganisms.containsKey(OrganismName)){
			organism.reduceCount();
			m_RegisteredOrganisms.put(OrganismName, organism);
		}
	}

	/**
	 * Creates a new instance of the organism with the id
	 * @param OrganismName Organism to get the instance of
	 * @return null if organism doesn't exist/wasn't registered, otherwise an object of AbstractOrganism type
	 */
	public AbstractOrganism createOrganism (String OrganismName){
		if(!m_RegisteredOrganisms.containsKey(OrganismName)){
			return null;
		}
		return ((AbstractOrganism)m_RegisteredOrganisms.get(OrganismName)).getNewObjectFromExistingObject();
	}

}
