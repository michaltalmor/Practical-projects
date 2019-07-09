import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @Michal_Talmor
 * 
 *  This class read txt file to table
 *
 */
public class Parser {

	public static boolean parseWordsToTable(String filePath, StringHashTable table) {
		if (filePath==null||table==null) {
			return false;
		}
	

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(filePath);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                table.insert(line);
            }   

            // Always close files.
            bufferedReader.close(); 
            return true;
        }
        catch(FileNotFoundException ex) {
           return false;              
        }
        catch(IOException ex) {
           return false;
        }
       
	}
	
}
