
// Author: (Michal) (Talmor) (312461080)

public class Warmup {	

	public static void main(String[] args) {


	}

	// **************   Task1   **************
	public static boolean doesDigitAppearInNumber(int number, int digit) {

		if (number%10==digit) {
			return true;
		}
		if (number==0) {
			return false;
		}
		else {
			return doesDigitAppearInNumber(number/10, digit);

		}
	}

	// ************** Task2 **************
	public static int countNumberOfEvenDigits(int number) {
		int count=0;
		if (number==0) {
			return 0;
		}
		if ((number%10)%2==0) {
			count=1;
		}
		return countNumberOfEvenDigits(number/10)+count;
	}

	// ************** Task3 **************
	public static int countTheAmountOfCharInString(String str, char c) {
		int ans=0;
		int counter=0;
		if (str=="") {
			return 0;
		}
		if (str.charAt(0)==c) {
			counter=1;
		}
		if (str.length()==1) {
			return counter;
		}
		ans = countTheAmountOfCharInString(str.substring(1),c )+counter;
		return ans;
	}

	// ************** Task4 **************
	public static boolean checkIfAllLettersAreCapitalOrSmall(String str) {
		boolean isSmall=false;
		if (str=="") {
			return false;
		}
		for (int i=0; i<str.length();i++) {
			if ((int)str.charAt(i)-((int)'A')>26) {
				isSmall=true;
			}
			else {
				if (isSmall==true) {
					return false;
				}
			}
		}

		return true;
	}

}
