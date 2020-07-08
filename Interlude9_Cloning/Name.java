/** A class that represents a name. */
public class Name implements Cloneable
{
    private String first; // First name 
    private String last; // Last name
    
    public Name()
    {
        
    } // end default constructor
    
    public Name(String firstName, String lastName) 
    {
        first = firstName;
        last = lastName;
    } // end constructor

    
    /** Sets the first and last names.
         @param firstName  A string that is the desired first name.
         @param lastName   A string that is the desired last name. */
    public void setName(String firstName, String lastName)
    {
        setFirst(firstName);
        setLast(lastName);
    } // end setName
    
    /** Gets the full name.
        @return  A string containing the first and last names. */
    public String getName()
    {
        return toString();
    } // end getName
    
    public void setFirst(String firstName)
    {
        first = firstName;
    } // end setFirst
    public String getFirst()
    {
        return first;
    } // end getFirst
    
    public void setLast(String lastName)
    {
        last = lastName;
    } // end setLast
    
    public String getLast()
    {
        return last;
    } // end getLast
    
    public void giveLastNameTo(NameInterface aName)
    {
        aName.setLast(last);
    } // end giveLastNameTo
    
    public String toString()
    {
        return first + " " + last;
    } // end toString
    
    public Object clone()
    {
        Name theCopy = null;
        try
        {
            theCopy = (Name) super.clone(); // Object can throw an exception
        }
        catch (CloneNotSupportedException e)
        {
            System.err.println("Name cannot clone: " + e.toString());
            // Or
            // throw new Error(e.toString());
        }
        return theCopy;
    } // end clone
} // end Name