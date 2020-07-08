// NOTE: We have substituted the class ArrayBag from Chapter 2
// for the class Bag that is used in Chapter 1.
/**
    A class that implements a piggy bank by using a bag.
    @author Frank M. Carrano
    @author Timothy M. Henry
    @version 5.0
*/
public interface PiggyBankInterface
{
    /** Adds a new entry to this piggy bank.
        @param aCoin  The Coin object to be added as a new entry.
        @return  True if the addition is successful, or false if not. */    
    public boolean add(Coin aCoin);
    
    /** Removes one unspecified entry from this piggy bank, if possible.
        @return  Either the removed entry, if the removal was successful, 
                or null. */
    public Coin remove();

    /** Sees whether this piggy bank is empty.
        @return  True if the piggy bank is empty, or false if not. */
    public boolean isEmpty();
} // end PiggyBankInterface

