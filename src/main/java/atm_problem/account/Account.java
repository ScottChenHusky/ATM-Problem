package atm_problem.account;

import atm_problem.utility.*;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * This is the Account Class
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public class Account implements IAccount {
	private final long ssn;
	private String userName;
	private String password;
	private double balance;
	private Map<String, String> history;
	
	/**
     * Constructor of Account
     *
     * @param ssn: the client's SSN, will be used as account ID
     * @param userName: the client's name
     * @param password: the client's password
     */
	public Account(final long ssn, String userName, String password) {
		this.ssn = ssn;
		this.userName = userName;
		this.password = password;
		this.balance = 0;
		this.history = new LinkedHashMap<String, String>();
	}

	/**
     * Gets the user's ID which is his/her SSN
     *
     * @return the user's SSN
     */
	public long getSSN() {
		return ssn;
	}

	/**
     * Gets the user's name
     *
     * @return the user's name
     */
	public String getUserName() {
		return userName;
	}

	/**
     * Gets the user's password
     *
     * @return the user's password
     */
	public String getPassword() {
		return password;
	}

	/**
     * Gets the user's account balance
     *
     * @return the user's account balance
     */
	public double getBalance() {
		return balance;
	}

	/**
     * Gets the user's transaction history
     *
     * @return the user's transaction history
     */
	public Map<String, String> getHistory() {
		return history;
	}

	/**
     * Make a deposit to this account
     *
     * @param amount: the deposit amount
     * @return the confirmation log
     */
	public String deposit(double amount) {
		balance = Utility.roundDouble(balance + amount, 2);
		String transactionID = Long.toString(System.currentTimeMillis());
		String transactionLog = "+ $" + Double.toString(amount);
		history.put(transactionID, transactionLog);
		
		try {
            Thread.sleep(1);
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
		return "Account: " + Long.toString(ssn) + " " + transactionLog;
	}

	/**
     * Withdraw money from this account
     *
     * @param amount: the withdraw amount
     * @return the confirmation log
     */
	public String withdraw(double amount) {
		if (amount > balance) {
			String message = "Withdraw amount: " 
							+ Double.toString(amount) 
							+ " is greater than current balance: "
							+ Double.toString(balance);
			throw new IllegalArgumentException(message);
		}
		balance  = Utility.roundDouble(balance - amount, 2);
		String transactionID = Long.toString(System.currentTimeMillis());
		String transactionLog = "- $" + Double.toString(amount);
		history.put(transactionID, transactionLog);
		
		try {
            Thread.sleep(1);
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
		return "Account: " + Long.toString(ssn) + " " + transactionLog;
	}
}
