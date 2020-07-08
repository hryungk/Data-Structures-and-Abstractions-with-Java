/** A class that represents a name. */
public class Name implements NameInterface
{
    private String fullName, first, last;
    
    /** Sets the first and last names.
         @param firstName  A string that is the desired first name.
         @param lastName   A string that is the desired last name. */
    public void setName(String firstName, String lastName)
    {
        fullName = first + " " + last;
    }
    
    /** Gets the full name.
        @return  A string containing the first and last names. */
    public String getName()
    {
        return fullName;
    }
    
    public void setFirst(String firstName)
    {
        first = firstName;
    }
    public String getFirst()
    {
        return first;
    }
    
    public void setLast(String lastName)
    {
        last = lastName;
    }
    public String getLast()
    {
        return last;
    }
    
    public void giveLastNameTo(NameInterface aName)
    {
        aName.setLast(last);
    }
    
    public String toString()
    {
        return fullName;
    }
} // end Name