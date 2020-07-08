package CapitalGainStockDeque;

/** Demonstrate StockLedger and StockPurchase class. */
public class StockSaleDriver 
{
    public static void main(String[] args)
    {
        StockLedger myStocks = new StockLedger();
        myStocks.buy(20, 45);                   // Buy 20 shares at $45
        myStocks.buy(20, 75);                   // Buy 20 shares at $75
        double capGain = myStocks.sell(30, 65); // Sell 30 shares at $65
    } // end main
} // end StockSaleDriver
