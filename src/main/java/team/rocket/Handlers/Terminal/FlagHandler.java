package team.rocket.Handlers.Terminal;

import java.util.logging.Handler;

/**
 * Largely based on <a href="https://www.oodesign.com/chain-of-responsibility-pattern">OODesign Chain of Responsibility</a>.
 * Intended to be used as a base for other terminal flag handlers.
 */
public abstract class FlagHandler {
	/**
	 * Successor handler which comes after current handler
	 */
	protected  FlagHandler m_successor;

	/**
	 * Sets the successor of the handler
	 * @param successor the FlagHandler successor to be set as the current Handlers successor
	 */
	public void setSuccessor(FlagHandler successor){
		m_successor = successor;
	}

	/**
	 *  Actually handles the request
	 * @param tFRequest The request to be handled, It's specifically a TerminalFlagRequest
	 */
	public void handleRequest(TerminalFlagRequest tFRequest){
		if(m_successor!=null){
			m_successor.handleRequest(tFRequest);
		}
	}
}
