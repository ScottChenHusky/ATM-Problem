package atm_problem.atm;

/**
 * This is the ATM interface
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public interface IATM {
	/**
     * Login a client with credential
     *
     * @param ssn: the account ID
     * @param password: the account password
     * @return the confirmation log
     */
	String login(long ssn, String password);
	
	/**
     * Logout a client
     *
     * @return the confirmation log
     */
	String logout();
	
	/**
     * Make a deposit using ATM
     *
     * @param amount: the deposit amount
     * @return the confirmation log
     */
	String deposit(double amount);
	
	/**
     * Withdraw money using ATM
     *
     * @param amount: the withdraw amount
     * @return the confirmation log
     */
	String withdraw(double amount);
	
	/**
     * Get client's account balance
     *
     * @return the confirmation log with account balance
     */
	String getBalance();
	
	/**
     * Get client's transaction history
     *
     * @return the confirmation log with transactions
     */
	String getHistory();
}
