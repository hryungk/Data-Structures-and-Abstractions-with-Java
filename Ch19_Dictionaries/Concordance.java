/** A class that implements a sorted version of the ADT dictionary having distinct 
    search keys.
 */
import java.util.Iterator; 
import java.util.Scanner;

public class Concordance 
{
    private DictionaryInterface<String, ListWithIteratorInterface<Integer>> wordTable;
    
    public Concordance() 
    {
        //wordTable = new SortedArrayDictionary<>(); 
        wordTable = new SortedLinkedDictionary<>(); 
    } // end default constructor
    
     /** Reads a text file of words and creates a concordance.
        @param data  A text scanner for the text file of data. */
    public void readFile(Scanner data) 
    {        
        int lineNumber = 1;
        while (data.hasNextLine())
        {
            String line = data.nextLine();
            line = line.toLowerCase();
            Scanner lineProcessor = new Scanner(line);
            lineProcessor.useDelimiter("\\W+");
            while (lineProcessor.hasNext())
            {
                String nextWord   = lineProcessor.next();
                nextWord = nextWord.toLowerCase();
                ListWithIteratorInterface<Integer> lineList = wordTable.getValue(nextWord);
                if (lineList == null)
                {   // Create new list for new word; add list and word to index
                    lineList = new LinkedListWithIterator<>();
                    wordTable.add(nextWord, lineList); 
                } // end if
                // Add line number to end of list so list is sorted                
                lineList.add(lineNumber);                 
            } // end while            
            lineNumber++;
        } // end while
        data.close();
    } // end readFile
    
    /** Displays words and the lines in which they occur. */
    public void display()
    {
        Iterator<String> keyIterator = wordTable.getKeyIterator();
        Iterator<ListWithIteratorInterface<Integer>> valueIterator = wordTable.getValueIterator();
        
        while (keyIterator.hasNext())
        {
            // Display the word
            System.out.print(keyIterator.next() + "\t");
            // Get line numbers and iterator
            ListWithIteratorInterface<Integer> lineList = valueIterator.next();
            Iterator<Integer> listIterator = lineList.getIterator();
            // Display line numbers
            while (listIterator.hasNext())
                System.out.print(listIterator.next() + " ");
            // end while
            System.out.println();
        } // end while
    } // end display    
    
    /** Returns a list of the numbers of the lines that contain a given word.
        @param word A string containing a word to be found.
        @return A list of line numbers in which the word occurs. */
    public ListWithIteratorInterface<Integer> getLineNumbers(String word)
    {
        return wordTable.getValue(word);
    } // end getLineNumbers    
} // end Concordance
