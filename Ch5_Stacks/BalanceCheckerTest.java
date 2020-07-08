// @author Frank M. Carrano
// @author Timothy M. Henry
// @version 5.0
public class BalanceCheckerTest
{
    public static void main(String arg[])
    {
        String expression = "a {b [c (d + e)/2 - f] + 1}";
        boolean isBalanced = BalanceChecker.checkBalance(expression);
        if (isBalanced)
            System.out.println(expression + " is balanced");
        else
            System.out.println(expression + " is not balanced");
    }
}
  
