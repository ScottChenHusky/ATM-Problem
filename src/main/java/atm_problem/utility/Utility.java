package atm_problem.utility;

/**
 * This is the Utility Class
 *
 * @author  Zewei Chen
 * @since   2018-08-29
 */
public class Utility {
	
	/**
	 * The Utility constructor
	 */
	private Utility() {}
	
	/**
	 * To round a Double number
	 *
	 * @param num: the double number
	 * @param digits: the precision of the round
	 * @return the rounded number
	 */
	public static double roundDouble(double num, int digits) {
		long factor = (long) Math.pow(10, digits);
		num *= factor;
		long tmp = Math.round(num);
		return (double) tmp / factor;
	}
}
