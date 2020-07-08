/** Simulates a waiting line. */
package SimulatingAWaitingLine;

public class WaitLine 
{
    private QueueInterface<Customer> line;  // A queue of customers
    private int numberOfArrivals;           // Number of customers
    private int numberServed;               // Number of customers actually served
    private int totalTimeWaited;            // Total time customers have waited
    
    public WaitLine()
    {
        line = new LinkedQueue<>();
        reset();
    } // end default constructor
    
    public void simulate(int duration, double arrivalProbability, int maxTransactionTime)
    {
        int transactionTimeLeft = 0;
        for (int clock = 0; clock < duration; clock++)
        {
            System.out.println("Time: " + clock);
            if (Math.random() < arrivalProbability) // If a new customer arrives
            {
                numberOfArrivals++;
                int transactionTime = (int) (Math.random() * maxTransactionTime + 1);
                Customer nextArrival = new Customer(clock, transactionTime, numberOfArrivals);
                line.enqueue(nextArrival);
                System.out.println("Customer " + numberOfArrivals + " enters line"
                                    + " at time " + clock + ". Transaction time "
                                    + "is " + transactionTime + ".");
            } // end if
            
            if (transactionTimeLeft > 0) // If present customer is still being served
                transactionTimeLeft--;
            else
                if (!line.isEmpty())
                {
                    Customer nextCustomer = line.dequeue();
                    transactionTimeLeft = nextCustomer.getTransactionTime() - 1;
                    int timeWaited = clock - nextCustomer.getArrivalTime();
                    totalTimeWaited = totalTimeWaited + timeWaited;
                    numberServed++;
                    System.out.println("Customer " + nextCustomer.getCustomerNumber()
                                    + " begins service at time " + clock + ". " 
                                    + "Time waited is " + timeWaited + ".");
                } // end else if        
        } // end for
    } // end simulate
    
    /** Displays summary results of the simulation. */
    public void displayResults()
    {
        System.out.println();
        System.out.println("Number served = " + numberServed); 
        System.out.println("Total time waited = " + totalTimeWaited);
        double averageTimeWaited = ((double)totalTimeWaited) / numberServed; 
        System.out.println("Average time waited = " + averageTimeWaited); 
        int leftInLine = numberOfArrivals - numberServed; 
        System.out.println("Number left in line = " + leftInLine);
        
    } // end displayResults
    
    /** Initializes the simulation. */
    public final void reset()
    {
        line.clear();
        numberOfArrivals = 0;
        numberServed = 0;
        totalTimeWaited = 0;
    } // end reset
} // end waitLine
