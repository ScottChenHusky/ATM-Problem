package atm_problem.bank;

import java.util.Map;

/**
 * This is the Bank interface
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public interface IBank {
	
	/**
	 * To open an account at this bank
	 *
	 * @param ssn: the client's SSN, will be used as account ID
	 * @param userName: the client's name
	 * @param password: the client's password
	 * @return the confirmation log
	 */
	String openAccount(long ssn, String userName, String password);
	
	/**
	 * To validate the client's userName and password with database
	 *
	 * @param ssn: the client's account ID
	 * @param password: the client's account password
	 * @return true if it matches, false otherwise
	 */
	boolean validateCredential(long ssn, String password);
	
	/**
	 * Make a deposit at this bank
	 *
	 * @param ssn: the client's account ID
	 * @param password: the client's account password
	 * @param amount: the deposit amount
	 * @return the confirmation log
	 */
	String deposit(long ssn, String password, double amount);
	
	/**
	 * Withdraw money at this bank
	 *
	 * @param ssn: the client's account ID
	 * @param password: the client's account password
	 * @param amount: the withdraw amount
	 * @return the confirmation log
	 */
	String withdraw(long ssn, String password, double amount);
	
	/**
     * Get client's account balance
     *
     * @return the confirmation log with account balance
     */
	double getBalance(long ssn, String password);
	
	/**
     * Get client's transaction history
     *
     * @return the confirmation log with transactions
     */
	Map<String, String> getHistory(long ssn, String password);
	
	/**
	 * Check if the client is using any ATMs of this bank
	 *
	 * @param ssn: the client's account ID
	 * @return true if the client is using one ATM, false otherwise
	 */
	boolean isInCurrentClients(long ssn);
	
	/**
	 * Record this client is using an ATM
	 *
	 * @param ssn: the client's account ID
	 */
	void addToCurrentClients(long ssn);
	
	/**
	 * Remove this client's ID from currentClients records
	 *
	 * @param ssn: the client's account ID
	 */
	void removeFromCurrentClients(long ssn);
}
