/** A class that represents a name. */
public class Name implements NameInterface, Comparable<Name>
{
    private String fullName, first, last;
    
    public Name()
    {
        this("","");
    } // end default constructor
    
    public Name(String firstName, String lastName)
    {
        first = firstName;
        last = lastName;
        fullName = first + " " + last;
    } // end constructor    
    
    /** Sets the first and last names.
         @param firstName  A string that is the desired first name.
         @param lastName   A string that is the desired last name. */
    public void setName(String firstName, String lastName)
    {
        setFirst(firstName);
        setLast(lastName);
        fullName = first + " " + last;
    } // end setname
    
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
    }// end setLast
    
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
        return fullName;
    } // end toString
    
    public int compareTo(Name other)
    {
        int result = last.compareToIgnoreCase(other.getLast());
        // If last names are equal, check first names
        if (result == 0)
            result = first.compareToIgnoreCase(other.getFirst());
        
        return result;                
    } // end compareTo
        
} // end Name1