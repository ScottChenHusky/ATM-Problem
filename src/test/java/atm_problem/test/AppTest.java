package atm_problem.test;

import atm_problem.bank.*;
import atm_problem.client.*;
import atm_problem.atm.*;
import atm_problem.account.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * ATM Problem
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public class AppTest extends TestCase {
	
	// Assume there are 2 clients
	Client scott = new Client(100000001, "Scott");
	Client mike  = new Client(100000002, "Mike");
	
	// Assume there are 3 banks
	Bank boa = new Bank(); // Bank of America
	Bank tdb = new Bank(); // TD Bank
	Bank co  = new Bank(); // Capital One
	
	// Add some ATM machines to banks
	// Bank of America has 2 ATMs: atm1 and atm2
	ATM atm1 = new ATM(boa);
	ATM atm2 = new ATM(boa);
	// Capital One has 2 ATMs: atm3 and atm4
	ATM atm3 = new ATM(co);
	ATM atm4 = new ATM(co);
	// TD Bank has 1 ATM: atm5
	ATM atm5 = new ATM(tdb);
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    /**
     * Test regular ATM operation
     */
    public void testUsingATM() {
    	//---------------------------------------------------------------------
    	// Test 1: Open Account
    	// SCOTT opens accounts at BOA and TDB
    	String expected = "Account: 100000001 registered successfully";
        assertEquals(expected, boa.openAccount(scott.getSSN(), 
        									   scott.getName(), 
        									   "password123"));
        assertEquals(expected, tdb.openAccount(scott.getSSN(), 
        									   scott.getName(), 
        									   "password123"));
        
        // MIKE opens accounts at BOA and TDB
        expected = "Account: 100000002 registered successfully";
        assertEquals(expected, boa.openAccount(mike.getSSN(), 
        									   mike.getName(), 
        									   "password123"));
        assertEquals(expected, co.openAccount(mike.getSSN(), 
        									  mike.getName(), 
        									  "password123"));
        
        //---------------------------------------------------------------------
        // Test 2: Login to an ATM
        // SCOTT logs into ATM1 (BOA ATM)
        assertEquals("Welcome, Account: 100000001", 
        		     atm1.login(scott.getSSN(), "password123"));
        // MIKE logs into ATM3 (CO ATM)
        assertEquals("Welcome, Account: 100000002", 
        		     atm3.login(mike.getSSN(), "password123"));
        
        //---------------------------------------------------------------------
        // Test 3: Check initial balance
        // ATM1 (BOA ATM) shows SCOTT has $0.0 balance
        assertEquals("Account: 100000001 has: $0.0", atm1.getBalance());
        // ATM3 (CO ATM) shows MIKE has $0.0 balance
        assertEquals("Account: 100000002 has: $0.0", atm3.getBalance());
        
        //---------------------------------------------------------------------
        // Test 4: Make a deposit
        // SCOTT deposits $300.0 using ATM1 (BOA ATM)
        assertEquals("Account: 100000001 + $300.0", atm1.deposit(300.0));
        // MIKE deposits $500.0 using ATM3 (CO ATM)
        assertEquals("Account: 100000002 + $500.0", atm3.deposit(500.0));
       
        //---------------------------------------------------------------------
        // Test 5: Check balance after made a deposit
        // ATM1 (BOA ATM) shows SCOTT has $300.0 balance
        assertEquals("Account: 100000001 has: $300.0", atm1.getBalance());
        // ATM3 (CO ATM) shows MIKE has $500.0 balance
        assertEquals("Account: 100000002 has: $500.0", atm3.getBalance());
        
        //---------------------------------------------------------------------
        // Test 6: Withdraw
        // SCOTT withdraws $100.0 using ATM1 (BOA ATM)
        assertEquals("Account: 100000001 - $100.0", atm1.withdraw(100.0));
        // MIKE withdraws $200.0 using ATM3 (CO ATM)
        assertEquals("Account: 100000002 - $200.0", atm3.withdraw(200.0));
        
        //---------------------------------------------------------------------
        // Test 7: Check balance after withdraw
        // ATM1 (BOA ATM) shows SCOTT has $200.0 balance ($300 - $100 = $200)
        assertEquals("Account: 100000001 has: $200.0", atm1.getBalance());
        // ATM3 (CO ATM) shows MIKE has $300.0 balance ($500 - $200 = $300)
        assertEquals("Account: 100000002 has: $300.0", atm3.getBalance());
        
        //---------------------------------------------------------------------
        // Test 8: Logout
        // Logout SCOTT on ATM1 (BOA ATM)
        assertEquals("Account: 100000001 logout Successfully", atm1.logout());
        // Logout MIKE on ATM3 (CO ATM)
        assertEquals("Account: 100000002 logout Successfully", atm3.logout());
        
        //---------------------------------------------------------------------
        // Test 9: Check balance using other ATM machines of the same bank
        // SCOTT logs into ATM2 (BOA ATM)
        assertEquals("Welcome, Account: 100000001", 
        		     atm2.login(scott.getSSN(), "password123"));
        // MIKE logs into ATM4 (CO ATM)
        assertEquals("Welcome, Account: 100000002", 
        		     atm4.login(mike.getSSN(), "password123"));
        
        // ATM2 (BOA ATM) shows SCOTT has $200.0 balance
        assertEquals("Account: 100000001 has: $200.0", atm2.getBalance());
        // ATM4 (CO ATM) shows MIKE has $300.0 balance
        assertEquals("Account: 100000002 has: $300.0", atm4.getBalance());
        
        //---------------------------------------------------------------------
        // Test 10: Make deposit using other ATM machines of the same bank
        // SCOTT deposits $111.1 using ATM2 (BOA ATM)
        assertEquals("Account: 100000001 + $111.1", atm2.deposit(111.1));
        // MIKE deposits $111.1 using ATM4 (CO ATM)
        assertEquals("Account: 100000002 + $111.1", atm4.deposit(111.1));
        
        // SCOTT deposits $111.1 using ATM2 (BOA ATM) again
        assertEquals("Account: 100000001 + $111.1", atm2.deposit(111.1));
        // MIKE deposits $111.1 using ATM4 (CO ATM) again
        assertEquals("Account: 100000002 + $111.1", atm4.deposit(111.1));
        
        // SCOTT deposits $111.1 using ATM2 (BOA ATM) again
        assertEquals("Account: 100000001 + $111.1", atm2.deposit(111.1));
        // MIKE deposits $111.1 using ATM4 (CO ATM) again
        assertEquals("Account: 100000002 + $111.1", atm4.deposit(111.1));
        
        //---------------------------------------------------------------------
        // Test 11: Check balance after making 3 deposits
        // ATM2 (BOA ATM) shows SCOTT has $533.3 balance (111.1 * 3 + 200)
        assertEquals("Account: 100000001 has: $533.3", atm2.getBalance());
        // ATM4 (CO ATM) shows MIKE has $633.3 balance (111.1 * 3 + 300)
        assertEquals("Account: 100000002 has: $633.3", atm4.getBalance());
        
        //---------------------------------------------------------------------
        // Test 12: Print transaction history
        //          Since transaction ID associates with time stamp which is
        //          different when each time you run the code,
        //          so we only check the event content using substring
        
        // SCOTT's transaction history
        String message = atm2.getHistory();
        // Deposited $300.0 using ATM1 (BOA ATM)
        assertEquals("+ $300.0", message.substring(15, 23));
        // Withdrawn $100.0 using ATM1 (BOA ATM)
        assertEquals("- $100.0", message.substring(39, 47));
        // Deposited $111.1 using ATM2 (BOA ATM)
        assertEquals("+ $111.1", message.substring(63, 71));
        // Deposited $111.1 using ATM2 (BOA ATM)
        assertEquals("+ $111.1", message.substring(87, 95));
        // Deposited $111.1 using ATM2 (BOA ATM)
        assertEquals("+ $111.1", message.substring(111, 119));
        
        // MIKE's transaction history
        message = atm4.getHistory();
        // Deposited $500.0 using ATM3 (CO ATM)
        assertEquals("+ $500.0", message.substring(15, 23));
        // Withdrawn $200.0 using ATM3 (BOA ATM)
        assertEquals("- $200.0", message.substring(39, 47));
        // Deposited $111.1 using ATM4 (CO ATM)
        assertEquals("+ $111.1", message.substring(63, 71));
        // Deposited $111.1 using ATM4 (CO ATM)
        assertEquals("+ $111.1", message.substring(87, 95));
        // Deposited $111.1 using ATM4 (CO ATM)
        assertEquals("+ $111.1", message.substring(111, 119));
        
        //---------------------------------------------------------------------
        // Test 13: Logout from other ATM machines of the same bank
        // Logout SCOTT on ATM2 (BOA ATM)
        assertEquals("Account: 100000001 logout Successfully", atm2.logout());
        // Logout MIKE on ATM4 (CO ATM)
        assertEquals("Account: 100000002 logout Successfully", atm4.logout());
        
    }
    
    /**
     * Test account getter methods
     */
    public void testAccountGetterMethods() {
    	Account account = new Account(123, "scott", "password123");
    	assertEquals(123, account.getSSN());
    	assertEquals("scott", account.getUserName());
    	assertEquals("password123", account.getPassword());
    	assertEquals(0.0, account.getBalance());
    	assertEquals(0, account.getHistory().keySet().size());
    	
    }
  
    
    //---------------------------------------------------------------------
    // Below are bad operation tests
    //---------------------------------------------------------------------
    
    /**
     * Test open multiple accounts using same SSN at a bank
     */
    public void testOpenAccountBadOperation() {
    	// SCOTT creates his first BOA account
    	String expected = "Account: 100000001 registered successfully";
    	assertEquals(expected, boa.openAccount(scott.getSSN(), 
    			               scott.getName(), 
    			               "password123"));
    	
    	// ERROR: SCOTT tries to create his second BOA account
    	expected = "Error: ssn 100000001 has been registered";
    	assertEquals(expected, boa.openAccount(scott.getSSN(), 
    			               scott.getName(), 
    			               "password123"));
    }

    /**
     * Test using ATM functions without login
     */
    public void testATMBadOperations() {
    	String expected = "Needs to login first";
    	assertEquals(expected, atm1.getBalance());
    	assertEquals(expected, atm1.deposit(500.0));
    	assertEquals(expected, atm1.withdraw(100.33));
    	assertEquals(expected, atm1.getHistory());
    	assertEquals(expected, atm1.logout());    	
    }
    
    /**
     * Test withdraw money more than account balance
     */
    public void testWithdrawBadOperation() {
    	// SCOTT creates his BOA account
    	String expected = "Account: 100000001 registered successfully";
    	assertEquals(expected, boa.openAccount(scott.getSSN(), 
    						   				   scott.getName(), 
    						   				   "password123"));
    	
    	// SCOTT logs into ATM1 (BOA ATM)
    	assertEquals("Welcome, Account: 100000001", 
    			     atm1.login(scott.getSSN(), "password123"));
    	
    	// ERROR: withdraw $100.0 when current balance is $0.0
    	expected = "Withdraw amount: 100.0 " + 
    			   "is greater than current balance: 0.0";
    	assertEquals(expected, atm1.withdraw(100.0));
    }
    
    /**
     * Test login with wrong AccountID(SSN) or Password
     */
    public void testLoginBadBehavior() {
    	// SCOTT creates his BOA account
    	String expected = "Account: 100000001 registered successfully";
    	assertEquals(expected, boa.openAccount(scott.getSSN(), 
    						   				   scott.getName(), 
    						   				   "password123"));
    	
    	// Test 1: Login with wrong password
    	expected = "AccountID or password is incorrect";
    	assertEquals(expected, atm1.login(scott.getSSN(), "xxx"));
    	
    	// Test 2: Login with wrong ID
    	assertEquals(expected, atm1.login(123, "password123"));
    }
    
    /**
     * Test log into multiple ATMs simultaneously
     */
    public void testLoginException() {
    	// SCOTT creates his BOA account
    	String expected = "Account: 100000001 registered successfully";
    	assertEquals(expected, boa.openAccount(scott.getSSN(), 
    						   				   scott.getName(), 
    						   				   "password123"));
    	
    	// SCOTT logs into ATM1 (BOA ATM)
    	assertEquals("Welcome, Account: 100000001", 
    			     atm1.login(scott.getSSN(), "password123"));
    	
    	// SCOTT tries to login ATM2(BOA ATM) without logout from ATM1(BOA ATM)
    	try {
    		atm2.login(scott.getSSN(), "password123");
            fail("Expected an IllegalStateException to be thrown");
        } catch (IllegalStateException e) {
        	assertEquals("Already logged in", e.getMessage());
        }
    	
    }
    
    /**
     * Test Bank Exceptions
     */
    public void testBankException() {
    	// Creates a test bank
    	Bank testBank = new Bank();
    	// SCOTT creates an account at this bank
    	testBank.openAccount(123, "scott", "password123");
    	
    	// Test 1: make a deposit using wrong password
    	try {
    		testBank.deposit(123, "123", 100.0);
    		fail("Expected an IllegalStateException to be thrown");
    	} catch (IllegalStateException e) {
    		assertEquals("SSN or Password is incorrect", e.getMessage());
    	}
    	
    	// Test 2: make a withdraw using wrong password
    	try {
    		testBank.withdraw(123, "123", 100.0);
    		fail("Expected an IllegalStateException to be thrown");
    	} catch (IllegalStateException e) {
    		assertEquals("SSN or Password is incorrect", e.getMessage());
    	}
    	
    	// Test 3: get balance using wrong password
    	try {
    		testBank.getBalance(123, "123");
    		fail("Expected an IllegalStateException to be thrown");
    	} catch (IllegalStateException e) {
    		assertEquals("SSN or Password is incorrect", e.getMessage());
    	}
    	
    	// Test 4: get history using wrong password
    	try {
    		testBank.getHistory(123, "123");
    		fail("Expected an IllegalStateException to be thrown");
    	} catch (IllegalStateException e) {
    		assertEquals("SSN or Password is incorrect", e.getMessage());
    	}
    }


}
