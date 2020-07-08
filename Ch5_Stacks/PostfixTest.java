// @author Frank M. Carrano
// @author Timothy M. Henry
// @version 5.0
public class PostfixTest
{
    public static void main(String arg[])
    {
        
        // Test convertToPostfix
        System.out.println("Testing convertToPostfix");
        String[] expressions = {"(a+b)/(c-d)","a/(b-c)*d","a-(b/(c-d)*e+f)^g", 
                                "(a-b*c)/(d*e^f*g+h)"};
        String[] qNums = {"a", "b", "c", "d"};
        for (int i = 0; i < expressions.length; i++)
        {
            String postfix = Postfix.convertToPostfix(expressions[i]);
            System.out.println(qNums[i]+". " + expressions[i] + " is converted to " + postfix);
        }
        
        System.out.println();
        
        // Test evaluatePostfix
        System.out.println("Testing evaluatePostfix");
        String[] postfixes = {"a e + b d - /", "a b c * d * -","a b c - / d *",
                              "e b c a ^ * + d -"};
        for (int i = 0; i < postfixes.length; i++)
        {
            int evaluation = Postfix.evaluatePostfix(postfixes[i]);
            System.out.println(qNums[i]+". " + postfixes[i] + " is evaluated as " + evaluation);
        }
        
        System.out.println();
        
        // Test evaluateInfix
        System.out.println("Testing evaluateInfix");
        String[] infixes = {"a+b*c-9", "(a+ e)/ (b- d)","a+(b+c*d)-e/2",
                              "e-b*c^a+d"};
        for (int i = 0; i < postfixes.length; i++)
        {
            int evaluation = Postfix.evaluateInfix(infixes[i]);
            System.out.println(qNums[i]+". " + infixes[i] + " is evaluated as " + evaluation);
        }        
    }
}
  
