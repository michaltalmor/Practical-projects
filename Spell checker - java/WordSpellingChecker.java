
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @Michal_Talmor
 * this class implements word spelling checker. 
 *
 */
public class WordSpellingChecker {

	private StringHashTable wordTable;
	public WordSpellingChecker(StringHashTable wordTable) {

		this.wordTable=wordTable;
	}

	/**
	 * 
	 * @param word 
	 * @return boolean, true if the word existing in dictionary, false if not.
	 */
	public boolean isWordExistingInDictionary(String word) {

		if (word==null||word.length()==0) {
			return false;
		}
		if (wordTable.search(word)==false) {
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * 
	 * @param word 
	 * @return list of suggestions 
	 *this function add space between every letter in the word and check if the new tow word are existing in dictionary
	 */

	public List<String> addSpaceCheck(String word) {

		List<String> list = new LinkedList<>();
		if (word!=null&&word.length()!=0) {
			for (int i=1;i<word.length();i++) {
				String side1 = word.substring(0, i);
				String side2 = word.substring(i, word.length());

				if (isWordExistingInDictionary(side1)&&isWordExistingInDictionary(side2)) {
					if (!(list.contains(side1+" "+side2))&&!word.equals(side1+" "+side2)){
					//return by some way side1 and side2 by "side1 side2" at the list

					list.add(""+side1+" "+side2);
					}

				}

			}
		}
		return list;
	}
	/**
	 * 
	 * @param word 
	 * @return list of suggestions 
	 *this function replace every character in the word by any of character from the a-z and check if the new word is existing in dictionary
	 */

	public List<String> replaceCharacterCheck(String word) {

		List<String> list = new LinkedList<>();
		if (word!=null&&word.length()!=0) {
			String newWord;
			for (int i=0;i<word.length();i++) {
				String side1 = word.substring(0, i);
				String side2 = word.substring(i+1, word.length());
				for (int j=97;j<=122;j++) {
					if ((char)j!=word.charAt(i)) {
						newWord=""+side1+(char)j+side2;
						if (isWordExistingInDictionary(newWord)&&!(list.contains(newWord))&&!word.equals(newWord)) {
							list.add(newWord);
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param word 
	 * @return list of suggestions 
	 *this function delete every character in the word and check if the new word is existing in dictionary
	 */
	public List<String> deleteCharacterCheck(String word) {

		List<String> list = new LinkedList<>();
		if (word!=null&&word.length()!=0) {
			String newWord;
			for (int i=0;i<word.length();i++) {
				String side1 = word.substring(0, i);
				String side2 = word.substring(i+1, word.length());


				newWord=""+side1+side2;
				if (isWordExistingInDictionary(newWord)&&!(list.contains(newWord))&&!word.equals(newWord)) {
					list.add(newWord);


				}
			}
		}
		return list;

	}
	/**
	 * 
	 * @param word 
	 * @return list of suggestions 
	 *this function add between any character in the word new character from the a-z and check if the new word is existing in dictionary
	 */

	public List<String> addCharacterCheck(String word) {

		List<String> list = new LinkedList<>();
		if (word!=null&&word.length()!=0) {
			String newWord;
			for (int i=0;i<word.length();i++) {
				String side1 = word.substring(0, i);
				String side2 = word.substring(i, word.length());
				for (int j=97;j<=122;j++) {

					newWord=""+side1+(char)j+side2;
					if (isWordExistingInDictionary(newWord)&&!(list.contains(newWord))&&!word.equals(newWord)) {
						list.add(newWord);

					}
				}
			}
			//again just for the last character
			for (int j=97;j<=122;j++) {

				newWord=""+word.substring(0, word.length())+(char)j;
				if (isWordExistingInDictionary(newWord)&&!(list.contains(newWord))&&!word.equals(newWord)) {
					list.add(newWord);
				}

			}	

		}
		return list;
	}

	/**
	 * 
	 * @param word 
	 * @return list of suggestions 
	 *this function switch every adjacent character and check if the new word is existing in dictionary
	 */
	public List<String> switchAdjacentCharacterCheck(String word) {

		List<String> list = new LinkedList<>();
		if (word!=null&&word.length()!=0) {
			String newWord;
			for (int i=0;i<word.length()-1;i++) {
				char firstChar = word.charAt(i);
				char secoundChar = word.charAt(i+1);
				String side1 = word.substring(0, i);
				String side2 = word.substring(i+2, word.length());
				newWord=""+side1+secoundChar+firstChar+side2;

				if (isWordExistingInDictionary(newWord)&&!(list.contains(newWord))&&!word.equals(newWord)) {
					list.add(newWord);

				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param word 
	 * @return list of suggestions 
	 *this function implements all of the method below
	 */
	public List<String> getWordSuggestions(String word) {
		List<String> Mainlist = new LinkedList<>();

		if (word!=null&&word.length()!=0) {

			List<String> listAddSpaceCheck = addSpaceCheck(word);
			for (int i=0; i<listAddSpaceCheck.size();i++) {
				if (!Mainlist.contains(listAddSpaceCheck.get(i))){
					Mainlist.add(listAddSpaceCheck.get(i));
				}
			}

			List<String> listReplaceCharacterCheck = replaceCharacterCheck(word);
			for (int i=0; i<listReplaceCharacterCheck.size();i++) {
				if (!Mainlist.contains(listReplaceCharacterCheck.get(i))){
					Mainlist.add(listReplaceCharacterCheck.get(i));
				}
			}

			List<String> listDeleteCharacterCheck = deleteCharacterCheck(word);
			for (int i=0; i<listDeleteCharacterCheck.size();i++) {
				if (!Mainlist.contains(listDeleteCharacterCheck.get(i))){
					Mainlist.add(listDeleteCharacterCheck.get(i));
				}
			}

			List<String> listAddCharacterCheck = addCharacterCheck(word);
			for (int i=0; i<listAddCharacterCheck.size();i++) {
				if (!Mainlist.contains(listAddCharacterCheck.get(i))){
					Mainlist.add(listAddCharacterCheck.get(i));
				}
			}

			List<String> listSwitchAdjacentCharacterCheck = switchAdjacentCharacterCheck(word);
			for (int i=0; i<listSwitchAdjacentCharacterCheck.size();i++) {
				if (!Mainlist.contains(listSwitchAdjacentCharacterCheck.get(i))){
					Mainlist.add(listSwitchAdjacentCharacterCheck.get(i));
				}
			}


		}
		return Mainlist;
	}
}
