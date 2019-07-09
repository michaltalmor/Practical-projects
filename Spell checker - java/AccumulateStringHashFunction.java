/**
 * 
 * @Michal Talmor

 */
public class AccumulateStringHashFunction implements StringHashFunction {
/**
 * This is a hash function by sum ascii representation
 * @param str - the string for hashing
 * @return int after hash function
 */

	public int hash(String str) {
		int sum =0;
		if (str=="") {
			return 0;
		}

		for (int i=0; i<str.length();i++) {
			sum=sum+(int)(str.charAt(i));
		}

		return sum;

	}
}
