
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @Michal_Talmor
 * 
 * this is a spelling checker by the word from the word table
 *
 */
public class SpellingChecker {

	private StringHashTable wordTable;
	public SpellingChecker(StringHashTable wordTable) {

		this.wordTable=wordTable;
	}


	/**
	 * 
	 * @param text
	 * @return list of spelling suggestions
	 */
	public List<SpellingSuggestion> spellingCheck(String text) {

		List<SpellingSuggestion> list = new LinkedList<>();
		if (text!=null&&text.length()!=0) {
			String [] splitText = text.split(" ");
			for (int i=0; i<splitText.length;i++) {
				WordSpellingChecker wordSpellCheck = new WordSpellingChecker(wordTable);
				if (!wordSpellCheck.isWordExistingInDictionary(splitText[i])) {
					SpellingSuggestion spellSuggest = new SpellingSuggestion(splitText[i], wordSpellCheck.getWordSuggestions(splitText[i]));

					boolean isIn=false;
					for (int j=0;j<list.size();j++) {
						if (list.get(j).equals(spellSuggest)) {
							isIn=true;
						}
					}
					if (isIn==false) {
						list.add(spellSuggest);
						
					}


				}
			}
		}
		return list;
	}
}
