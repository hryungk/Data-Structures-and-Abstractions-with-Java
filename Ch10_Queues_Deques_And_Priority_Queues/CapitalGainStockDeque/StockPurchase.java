package CapitalGainStockDeque;

public class StockPurchase 
{
    private double cost;    // Cost of one share
    private int shares;     // Number of shares
    
    public StockPurchase(int num, double pricePerShare)
    {
        shares = num;
        cost = pricePerShare;
    } // end constructor
    
    public int getNumberOfShares()
    {
        return shares;
    } // end getNumberOfShares
    
    public double getCostPerShare()
    {
        return cost;
    } // end getCostPerShare
    
} // end StockPurchase
