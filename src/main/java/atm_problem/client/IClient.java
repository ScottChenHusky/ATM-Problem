package atm_problem.client;


/**
 * This is the Client interface
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public interface IClient {
	
	/**
	 * Get the client's name
	 *
	 * @return the client's name
	 */
	String getName();
	
	/**
	 * Get the client's SSN
	 *
	 * @return the client's SSN
	 */
	long getSSN();
}
