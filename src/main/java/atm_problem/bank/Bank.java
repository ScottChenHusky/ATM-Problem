package atm_problem.bank;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;


import atm_problem.account.*;

/**
 * This is the Bank Class
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public class Bank implements IBank {
	private Map<Long, Account> db;
	private Set<Long> currentClients;
	private String errorMessage = "SSN or Password is incorrect";
	
	/**
	 * The bank constructor
	 */
	public Bank() {
		this.db = new HashMap<Long, Account>();
		this.currentClients = new HashSet<Long>();
	}

	/**
	 * To open an account at this bank
	 *
	 * @param ssn: the client's SSN, will be used as account ID
	 * @param userName: the client's name
	 * @param password: the client's password
	 * @return the confirmation log
	 */
	public String openAccount(long ssn, String userName, String password) {
		if (db.containsKey(ssn)) {
			return "Error: ssn " + Long.toString(ssn) + " has been registered";
		}
		Account account = new Account(ssn, userName, password);
		db.put(ssn, account);
		return "Account: " + Long.toString(ssn) + " registered successfully";
	}

	/**
	 * To validate the client's userName and password with database
	 *
	 * @param ssn: the client's account ID
	 * @param password: the client's account password
	 * @return true if it matches, false otherwise
	 */
	public boolean validateCredential(long ssn, String password) {
		if (!db.containsKey(ssn)) {
			return false;
		}
		return db.get(ssn).getPassword().equals(password);
	}

	/**
	 * Make a deposit at this bank
	 *
	 * @param ssn: the client's account ID
	 * @param password: the client's account password
	 * @param amount: the deposit amount
	 * @return the confirmation log
	 */
	public String deposit(long ssn, String password, double amount) {
		if (validateCredential(ssn, password)) {
			return db.get(ssn).deposit(amount);
		}
		throw new IllegalStateException(errorMessage);
	}

	/**
	 * Withdraw money at this bank
	 *
	 * @param ssn: the client's account ID
	 * @param password: the client's account password
	 * @param amount: the withdraw amount
	 * @return the confirmation log
	 */
	public String withdraw(long ssn, String password, double amount) {
		String message;
		if (validateCredential(ssn, password)) {
			try {
				message = db.get(ssn).withdraw(amount);
			} catch (IllegalArgumentException e) {
				return e.getMessage();
			}
			
			return message;
		}
		throw new IllegalStateException(errorMessage);
	}

	/**
     * Get client's account balance
     *
     * @return the confirmation log with account balance
     */
	public double getBalance(long ssn, String password) {
		if (validateCredential(ssn, password)) {
			return db.get(ssn).getBalance();
		}
		throw new IllegalStateException(errorMessage);
	}

	/**
     * Get client's transaction history
     *
     * @return the confirmation log with transactions
     */
	public Map<String, String> getHistory(long ssn, String password) {
		if (validateCredential(ssn, password)) {
			return db.get(ssn).getHistory();
		}
		throw new IllegalStateException(errorMessage);
	}
	
	/**
	 * Check if the client is using any ATMs of this bank
	 *
	 * @param ssn: the client's account ID
	 * @return true if the client is using one ATM, false otherwise
	 */
	public boolean isInCurrentClients(long ssn) {
		return currentClients.contains(ssn);
	}
	
	/**
	 * Record this client is using an ATM
	 *
	 * @param ssn: the client's account ID
	 */
	public void addToCurrentClients(long ssn) {
		currentClients.add(ssn);
	}
	
	/**
	 * Remove this client's ID from currentClients records
	 *
	 * @param ssn: the client's account ID
	 */
	public void removeFromCurrentClients(long ssn) {
		currentClients.remove(ssn);
	}
	
}
