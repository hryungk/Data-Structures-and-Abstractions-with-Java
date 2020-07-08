package TrackAssignments;

import java.sql.Date;
/** Demonstrate StockLedger and StockPurchase class. */
public class TrackAssignmentDriver 
{
    public static void main(String[] args)
    {
        AssignmentLog myHomework = new AssignmentLog(); 
        myHomework.addProject("CSC211", "Pg 50, Ex 2", Date.valueOf("2020-6-2")); 
        Assignment pg75Ex8 = new Assignment("CSC215", "Pg 75, Ex 8",
                                            Date.valueOf("2020-5-16"));
        myHomework.addProject(pg75Ex8);

        System.out.println("The following assignment is due next:"); 
        System.out.println(myHomework.getNextProject());
    } // end main
} // end StockSaleDriver
