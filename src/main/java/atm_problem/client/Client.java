package atm_problem.client;

/**
 * This is the Client Class
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public class Client implements IClient {
	private final long ssn;
	private String name;
	
	/**
	 * The Client constructor
	 *
	 * @param ssn: the client's SSN
	 * @param name: the client's name
	 */
	public Client(final long ssn, String name) {
		this.ssn = ssn;
		this.name = name;
	}
	
	/**
	 * Get the client's name
	 *
	 * @return the client's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the client's SSN
	 *
	 * @return the client's SSN
	 */
	public long getSSN() {
		return ssn;
	}
}
