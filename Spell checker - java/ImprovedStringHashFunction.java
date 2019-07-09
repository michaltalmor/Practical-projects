/**
 * 
 * @Michal Talmor 
 * This is an improved hash-function by improve sum method.
 */
public class ImprovedStringHashFunction implements StringHashFunction {

	/**
	 * This is a hash function
	 * @param str - the string for hashing
	 * @return int after hash function
	 */
	public int hash(String str) {
		if (str=="") {
			return 0;
		}
		int sum=0;
		for (int i=0; i<str.length();i++) {
			int powVal=1;
			for (int h=0;h<str.length()-1-i;h++) {
				powVal=powVal*31;
			}

			sum=sum+(int)((str.charAt(i))*powVal);

		}
		return sum;
	}
}
