/** An interface for a class of names */
public interface NameInterface 
{
    /** Sets the first and last names.
         @param firstName  A string that is the desired first name.
         @param lastName   A string that is the desired last name. */
    public void setName(String firstName, String lastName);
    
    /** Gets the full name.
        @return  A string containing the first and last names. */
    public String getName();
    
    /** Sets the first name.
     * @param firstName A string that is the desired first name. */
    public void setFirst(String firstName);
    /** Gets the first name
     * @return A string containing the first name. */
    public String getFirst();
    
    /** Sets the last name.
     * @param lastName A string that is the desired last name. */
    public void setLast(String lastName);
    /** Gets the last name
     * @return A string containing the last name. */
    public String getLast();
    
    /** Assigns a last name to a different name
     * @param aName An object that implements NameInterface that will change its
     *              last name. */
    public void giveLastNameTo(NameInterface aName);
    
    /** Returns a string that describes this NameInterface object
     * @return A string containing description of this name. */
    public String toString(); 
} // end NameInterface