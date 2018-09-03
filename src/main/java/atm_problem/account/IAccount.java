package atm_problem.account;

import java.util.Map;

/**
 * This is the Account interface
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public interface IAccount {
	
	/**
     * Gets the user's ID which is his/her SSN
     *
     * @return the user's SSN
     */
	long getSSN();
	
	/**
     * Gets the user's name
     *
     * @return the user's name
     */
	String getUserName();
	
	/**
     * Gets the user's password
     *
     * @return the user's password
     */
	String getPassword();
	
	/**
     * Gets the user's account balance
     *
     * @return the user's account balance
     */
	double getBalance();
	
	/**
     * Gets the user's transaction history
     *
     * @return the user's transaction history
     */
	Map<String, String> getHistory();
	
	/**
     * Make a deposit to this account
     *
     * @param amount: the deposit amount
     * @return the confirmation log
     */
	String deposit(double amount);
	
	
	/**
     * Withdraw money from this account
     *
     * @param amount: the withdraw amount
     * @return the confirmation log
     */
	String withdraw(double amount);
	
}