package SimulatingAWaitingLine;
/*
    Test the WaitLine class
 */
public class QueueDriver 
{
    public static void main(String[] args)
    {
        WaitLine customerLine = new WaitLine();
        customerLine.simulate(20, 0.5, 5);
        customerLine.displayResults();
    } // end main    
} // end QueueDriver
