package CapitalGainStockDeque;

/** A class that records the purchase and sale of stocks, and provides the 
    capital gain or loss. */
public class StockLedger 
{
    private DequeInterface<StockPurchase>  ledger;
    
    public StockLedger()
    {
        ledger = new LinkedDeque<>();
    } // end default constructor
    
    /** Records a stock purchase in this ledger.
        @param sharesBought The number of shares purchased.
        @param pricePerShare The price per share. */
    public void buy(int sharesBought, double pricePerShare)
    {
        StockPurchase purchase = new StockPurchase(sharesBought, pricePerShare);
        ledger.addToBack(purchase);
    } // end buy
    
    /** Removes from this ledger any shares that were sold 
        and computes the capital gain or loss.
        @param sharesSold    The number of shares sold.
        @param pricePerShare The price per share.
        @return              The capital gain (loss). */
    public double sell(int sharesSold, int pricePerShare)
    {
        double saleAmount = sharesSold * pricePerShare;
        double totalCost = 0;
        
        while (sharesSold > 0)
        {
            StockPurchase transaction = ledger.removeFront();
            double shareCost = transaction.getCostPerShare();
            int numberOfShares = transaction.getNumberOfShares();
            
            if (sharesSold < numberOfShares)
            {
                totalCost = totalCost + sharesSold * shareCost;
                int numberToPutBack = numberOfShares - sharesSold;
                StockPurchase leftover = new StockPurchase(numberToPutBack,shareCost);
                ledger.addToFront(leftover);
            } // end if
            else
                totalCost = totalCost + numberOfShares * shareCost;
            
            sharesSold = sharesSold - numberOfShares;
        } // end while        
        return saleAmount - totalCost; // Gain or loss
    } // end sell
} // end StockLedger
