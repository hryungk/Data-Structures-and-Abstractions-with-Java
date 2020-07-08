public class DictionaryClient 
{
    public static void main(String[] args) 
    {
        testList();
    } // end main
    
    public static void testList()
    {
        DictionaryInterface<Name, String> myDictionary = new SortedArrayDictionary<>();
        
        
        myDictionary.add(new Name("John","Smith"), "123-456-7890");
        
        Name bStorm = new Name("Britney", "Storm");
        String phoneNumber = myDictionary.getValue(bStorm);
        if (phoneNumber == null)
            System.out.println(bStorm.getName() + " is not in the dictionary.");
        else
            System.out.println(bStorm.getFirst() + "'s phone number is " + phoneNumber);        
    } // end testList
    
    
} // end ListClient
