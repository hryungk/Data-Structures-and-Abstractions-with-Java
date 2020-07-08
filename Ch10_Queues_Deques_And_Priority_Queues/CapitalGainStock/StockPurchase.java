package CapitalGainStock;

public class StockPurchase 
{
    private double cost;   // Cost of one share
    
    public StockPurchase(double pricePerShare)
    {
        cost = pricePerShare;
    } // end constructor
    
    public double getCostPerShare()
    {
        return cost;
    } // end getCostPerShare
    
} // end StockPurchase
