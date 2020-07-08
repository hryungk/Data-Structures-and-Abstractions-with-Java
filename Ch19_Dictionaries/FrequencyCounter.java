/** A class that implements a sorted version of the ADT dictionary having distinct 
    search keys.
 */
import java.util.Iterator; 
import java.util.Scanner;

public class FrequencyCounter 
{
    private DictionaryInterface<String, Integer> wordTable;
    
    public FrequencyCounter() 
    {
        wordTable = new SortedArrayDictionary<>(); 
    } // end default constructor
    
     /** Reads a text file of words and counts their frequencies of occurrence.
        @param data  A text scanner for the text file of data. */
    public void readFile(Scanner data) 
    {
        data.useDelimiter("\\W+");
        while (data.hasNext()) 
        {
            String nextWord   = data.next();
            nextWord = nextWord.toLowerCase();
            Integer frequency = wordTable.getValue(nextWord);
            if (frequency == null)
            {   // Add new word to table
                wordTable.add(nextWord, 1); 
            }
            else
            {   // Increment count of existing word; replace wordTable entry
                frequency++;
                wordTable.add(nextWord, frequency);                
            } // end if
        } // end while
        data.close();
    } // end readFile
    
    public void display()
    {
        Iterator<String> keyIterator = wordTable.getKeyIterator();
        Iterator<Integer> valueIterator = wordTable.getValueIterator();
        
        while (keyIterator.hasNext())
            System.out.println(keyIterator.next() + "\t" + valueIterator.next());
    } // end display
    
    /** Displays only words that occur with a given frequency. 
        @param frequency An integer count of the desired frequency. */
    public void display(int frequency)
    {
        Iterator<String> keyIterator = wordTable.getKeyIterator();
        Iterator<Integer> valueIterator = wordTable.getValueIterator();
        
        System.out.println("Words that occur " + frequency + " times: ");
        boolean atLeastOneWord = false;
        while (keyIterator.hasNext())
        {
            String word = keyIterator.next();
            Integer count = valueIterator.next();
            if (count.equals(frequency))
            {
                System.out.println(word);
                atLeastOneWord = true;
            }
        } // end while
        if (!atLeastOneWord)
            System.out.println("There are none.");
    } // end display
} // end FrequencyCounter
