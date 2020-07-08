package SimulatingAWaitingLine;
/** 
    Represents a customer waiting in line. 
    @author Hyunryung Kim
*/
public class Customer 
{
    private int arrivalTime;
    private int transactionTime;
    private int customerNumber;
    
    public Customer()
    {
        
    } // end default constructor
    
    public Customer(int clock, int serviceTime, int numberOfArrivals)
    {
        arrivalTime = clock;
        transactionTime = serviceTime;
        customerNumber = numberOfArrivals;
    } // end constructor
    
    public void setArrivalTime(int num)
    {
        arrivalTime = num;
    } // end setArrivalTime
    
    public int getArrivalTime()
    {
        return arrivalTime;        
    } // end getArrivalTime

    public void setTransactionTime(int num)
    {
        transactionTime = num;
    } // end settransactionTime    
    
    public int getTransactionTime()
    {
        return transactionTime;        
    } // end gettransactionTime
    
    public void setCustomerNumber(int num)
    {
        customerNumber = num;
    } // end setCustomerNumber    
    
    public int getCustomerNumber()
    {
        return customerNumber;        
    } // end getCustomerNumber    
} // end Customer
