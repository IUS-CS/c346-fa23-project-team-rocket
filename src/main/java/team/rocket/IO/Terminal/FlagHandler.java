package team.rocket.IO.Terminal;

/**
 * Largely based on <a href="https://www.oodesign.com/chain-of-responsibility-pattern">OODesign Chain of Responsibility</a>.
 * Intended to be used as a base for other terminal flag handlers.
 * @since 0.3.0
 * @version 0.3.0
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
	 * Actually handles the request.
	 *
	 * @param tFRequest	the request to be handled, specifically a TerminalFlagRequest
	 */
	public void handleRequest(TerminalFlagRequest tFRequest) {
		if (m_successor!=null) {
			m_successor.handleRequest(tFRequest);
		}
	}
}
