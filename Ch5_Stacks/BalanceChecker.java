//Note: We are using the class LinkedStack instead of 
//      the class OurStack that the book uses.
/**
   A class that checks whether the parentheses, brackets, and braces 
   in a string occur in left/right pairs.
   @author Frank M. Carrano, Timothy M. Henry
   @version 5.0 */
public class BalanceChecker
{
   /** Decides whether the parentheses, brackets, and braces 
       in a string occur in left/right pairs.
       @param expression  A string to be checked.
       @return  True if the delimiters are paired correctly. */
   public static boolean checkBalance(String expression)
   {
      StackInterface<Character> openDelimiterStack = new LinkedStack<>();

      int characterCount = expression.length();
      boolean isBalanced = true;
      int index = 0;
      char nextCharacter = ' ';
       
    while (isBalanced && (index < characterCount))
    {
       nextCharacter = expression.charAt(index);
       switch (nextCharacter)
       {
            case '(': case '[': case '{':
                openDelimiterStack.push(nextCharacter);
                break;             
            case ')': case ']': case '}':
                if (openDelimiterStack.isEmpty())
                  isBalanced = false;
                else
                {
                   char openDelimiter = openDelimiterStack.pop();
                   isBalanced = isPaired(openDelimiter, nextCharacter);
                } // end if
                break;
            default: break; // Ignore unexpected characters
        } // end switch
        index++;
    } // end while
       
    if (!openDelimiterStack.isEmpty())
        isBalanced = false;
         
    return isBalanced;
    } // end checkBalance
  
    // Returns true if the given characters, open and close, form a pair
    // of parentheses, brackets, or braces.
    private static boolean isPaired(char open, char close)
    {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    } // end isPaired
} // end BalanceChecker
