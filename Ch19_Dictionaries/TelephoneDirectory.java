/** A class that implements a sorted version of the ADT dictionary having distinct 
    search keys.
 */
import java.util.Iterator; 
import java.util.Scanner;

public class TelephoneDirectory 
{
    private DictionaryInterface<Name, String> phoneBook;
    
    public TelephoneDirectory() 
    {
        //phoneBook = new SortedArrayDictionary<>(); 
        phoneBook = new SortedLinkedDictionary<>(); 
    } // end default constructor
    
    /** Reads a text file of names and telephone numbers.
        @param data  A text scanner for the text file of data. */
    public void readFile(Scanner data) 
    {
        //data.useDelimiter("\\W+");
        data.useDelimiter("[\n ,]");
        while (data.hasNext()) 
        {
            String firstName   = data.next();
            String lastName    = data.next();
            String phoneNumber = data.next();
            Name fullName = new Name(firstName, lastName);
            phoneBook.add(fullName, phoneNumber);
       } // end while
       data.close();
    } // end readFile
    
    /** Gets the phone number of a given person. */
    public String getPhoneNumber(Name personName) 
    {
        return phoneBook.getValue(personName);
    } // end getPhoneNumber
    public String getPhoneNumber(String firstName, String lastName) 
    {
        Name fullName = new Name(firstName, lastName);
        return phoneBook.getValue(fullName); 
    } // end getPhoneNumber
        
    /** Changes a person's telephone number
        @param personName A Name of the new entry.
        @param phoneNumber A phone number associated with the name.
        @return Either person's old telephone number if that value was replaced 
                or null if the person was not in the directory but has been added 
                to it. */
    public String changePhoneNumber(Name personName, String newPhoneNumber)
    {
        return phoneBook.add(personName, newPhoneNumber);
    } // end replace
    
    /** Removes an entry from the directory.
        @param personName A search key name to be removed.
        @return Either the person's telephone number or 
                null if the person is not in the directly.  */
    public String remove(Name personName)
    {
        return phoneBook.remove(personName);
    } // end add

    public void display()
    {
        Iterator<Name> keyIterator = phoneBook.getKeyIterator();
        Iterator<String> valueIterator = phoneBook.getValueIterator();
        
        System.out.println("-------------------------------------");
        int index = 0;
        while (keyIterator.hasNext())
        {
            System.out.println(index + " " + keyIterator.next() + "\t" 
                                + valueIterator.next());    
            index++;
        }
        System.out.println("-------------------------------------");
        System.out.println();
    } // end display
    
    
    public void test(Name name)
    {
        Iterator<Name> keyIterator = phoneBook.getKeyIterator();
        boolean found = false;
        while (!found && keyIterator.hasNext())
        {
            Name nextKey = keyIterator.next();
            found = name.equals(nextKey);
            System.out.println(nextKey.getName() + " == " + 
                    name + ": " + found);                
        }
    } // end display
} // end TelephoneDirectory
