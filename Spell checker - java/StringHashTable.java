import java.util.LinkedList;



/**
 * 
 * @Michal_Talmor
 *This function implements String hash table
 */
public class StringHashTable {
	private int tableSize;
	private StringHashFunction hashFunction;

	private LinkedList<String> hashTable [];


	@SuppressWarnings("unchecked")
	public StringHashTable(int tableSize, StringHashFunction hashFunction) {

		this.tableSize=tableSize;
		this.hashFunction=hashFunction;

		this.hashTable = new LinkedList[tableSize];
	}

	/**
	 * 
	 * @return int - the number oh elements in table 
	 */
	public int numOfElementsInTable() {
		int size=0;
		for (int i=0; i<tableSize;i++) {
			if(hashTable[i]!=null) {
				size=size+this.hashTable[i].size();
			}
		}
		return size;
	}

	/**
	 * @param str - string
	 * @return boolean - true if insert success, false if not 
	 * 
	 * this function insert the str to the table
	 */
	public boolean insert(String str) {



		if (str==null) {
			return false;
		}
		int key= Math.abs(hashFunction.hash(str)%tableSize);

		//if there is an empty cell in array
		if (hashTable[key]==null) {
			hashTable[key]=new LinkedList<>(); //create new linkedlist inside this cell
		}
		//if the str contains another str in the list
		if (hashTable[key].contains(str)==true) {

			return false;
		}
		else {
		hashTable[key].addLast(str);
		return true;
		}


	}
	/**
	 * @param str - string
	 * @return boolean - true if delete success, false if not 
	 * 
	 * this function delete the str to the table
	 */

	public boolean delete(String str) {

		if (str==null) {
			return false;
		}
		int key= Math.abs(hashFunction.hash(str)%tableSize);
		//if there is an empty cell in array
		if (hashTable[key]==null) {
			return false;
		}
		//if the str contains another str in the list
		if (hashTable[key].contains(str)==true) {

			hashTable[key].remove(str);
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * @param str - string
	 * @return boolean - true if str found in the table, false if not 
	 * 
	 * this function search the str in the table
	 */

	public boolean search(String str) {
		
		if (str==null) {
			return false;
		}
		int key= Math.abs(hashFunction.hash(str)%tableSize);
		//if there is an empty cell in array
		if (hashTable[key]==null) {
			return false;
		}
		//if the str contains another str in the list
		if (hashTable[key].contains(str)==true) {

			return true;
		}
		return false;
		
		
	}
}
