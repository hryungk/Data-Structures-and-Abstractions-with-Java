// NOTE: We have substituted the class ArrayBag from Chapter 2
// for the class Bag that is used in Chapter 1.
/**
    A class that implements a piggy bank by using a bag.
    @author Frank M. Carrano
    @author Timothy M. Henry
    @version 5.0
*/
public class PiggyBank
{
	private BagInterface<Coin> coins;

	public PiggyBank() 
	{
		coins = new ArrayBag<>();
	} // end default constructor

	public boolean add(Coin aCoin) 
	{
		return coins.add(aCoin);
	} // end add

	public Coin remove() 
	{
		return coins.remove();
	} // end remove

	public boolean isEmpty() 
	{
		return coins.isEmpty();
	} // end isEmpty
} // end PiggyBank
