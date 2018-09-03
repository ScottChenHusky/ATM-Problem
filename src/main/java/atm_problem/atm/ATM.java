package atm_problem.atm;

import java.util.Map;
import atm_problem.bank.*;

/**
 * This is the ATM Class
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public class ATM implements IATM {
	private Bank bank;
	private long currentClientId;
	private String currentClientPassword;
	
	/**
	 * This is the ATM constructor
	 *
	 * @param bank: the bank that this ATM belongs to
	 */
	public ATM(Bank bank) {
		this.bank = bank;
		this.currentClientId = 0;
		this.currentClientPassword = "";
	}
	
	/**
     * Login a client with credential
     *
     * @param ssn: the account ID
     * @param password: the account password
     * @return the confirmation log
     */
	public String login(long ssn, String password) {
		if (bank.isInCurrentClients(ssn)) {
			throw new IllegalStateException("Already logged in");
		}
		
		if (bank.validateCredential(ssn, password)) {
			currentClientId = ssn;
			currentClientPassword = password;
			bank.addToCurrentClients(ssn);
			return "Welcome, Account: " + Long.toString(ssn);
		}
		return "AccountID or password is incorrect";
	}

	/**
     * Logout a client
     *
     * @return the confirmation log
     */
	public String logout() {
		try {
			isLoggedIn();
		} catch (IllegalStateException e) {
			return e.getMessage();
		}
		long temp = currentClientId;
		bank.removeFromCurrentClients(currentClientId);
		currentClientId = 0;
		currentClientPassword = "";
		return "Account: " 
				+ Long.toString(temp) 
				+ " logout Successfully";
	}

	/**
     * Make a deposit using ATM
     *
     * @param amount: the deposit amount
     * @return the confirmation log
     */
	public String deposit(double amount) {
		String message;
		try {
			isLoggedIn();
			message = bank.deposit(currentClientId, currentClientPassword, amount);
		} catch (IllegalStateException e) {
			message = e.getMessage();
		}

		return message;
	}

	/**
     * Withdraw money using ATM
     *
     * @param amount: the withdraw amount
     * @return the confirmation log
     */
	public String withdraw(double amount) {
		String message;
		try {
			isLoggedIn();
			message = bank.withdraw(currentClientId, currentClientPassword, amount);
		} catch (IllegalStateException e) {
			message = e.getMessage();
		}

		return message;
	}

	/**
     * Get client's account balance
     *
     * @return the confirmation log with account balance
     */
	public String getBalance() {
		try {
			isLoggedIn();
		} catch (IllegalStateException e) {
			return e.getMessage();
		}
		double balance = bank.getBalance(currentClientId, currentClientPassword);
		return "Account: " 
				+ Long.toString(currentClientId) 
				+ " has: $" + Double.toString(balance);
	}

	/**
     * Get client's transaction history
     *
     * @return the confirmation log with transactions
     */
	public String getHistory() {
		try {
			isLoggedIn();
		} catch (IllegalStateException e) {
			return e.getMessage();
		}
		Map<String, String> history = bank.getHistory(currentClientId, currentClientPassword);
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry: history.entrySet()) {
			sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
		}
		
		return sb.toString();
	}
	
	/**
     * To validate if a client is using the ATM
     */
	private void isLoggedIn() {
		if (currentClientId == 0) {
			throw new IllegalStateException("Needs to login first");
		}
	}
	
	
}
