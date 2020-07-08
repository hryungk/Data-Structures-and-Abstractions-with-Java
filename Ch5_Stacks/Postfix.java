/**
   A class that converts and evaluates a postfix expression. 
   Assume the given algebraic expressions are syntactically correct.
   @author Hyunryung Kim 
   @version 5.0 */
public class Postfix
{
    /** Converts an infix expression to an equivalent postfix expression.
        @param expression  A string to be converted.
        @return  String that is converted to a postfix. */
    public static String convertToPostfix(String infix)
    {
        // The standard class StringBuilder, which is in the Java Class Library 
        // and is described in Segment B.79 of Appendix B, will be helpful.
        StackInterface<Character> operatorStack = new LinkedStack<>();
        StringBuilder postfix = new StringBuilder();
        int index = 0;
        char nextCharacter = ' ';
        char topOperator = ' ';
        while (index < infix.length())
        {
            nextCharacter = infix.charAt(index);
            switch (nextCharacter)
            {
                // Append each operand to the en dof the output expression.
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h':
                    postfix.append(nextCharacter); 
                    break;
                // Always push ^ onto the stack.
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+' : case '-': case '*': case '/':
                    while (!operatorStack.isEmpty() && 
                            !isBigger(nextCharacter, operatorStack.peek()))
                    {
                        postfix.append(operatorStack.pop());
                    } // end while
                    operatorStack.push(nextCharacter);
                    break;
                // Always puch ( onto the stack.
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case ')': // Stack is not empty if infix expression is valid
                    topOperator = operatorStack.pop();
                    while (topOperator != '(')
                    {
                        postfix.append(topOperator);
                        topOperator = operatorStack.pop();
                    } // end while
                    break;
                default: 
                    break; // Ignore unexpected characters
            } // end switch
            index++;
        } // end while
        
        while (!operatorStack.isEmpty())
        {
            topOperator = operatorStack.pop();
            postfix.append(topOperator);
        } // end while
       
       return postfix.toString();
    } // end convertToPostfix

    /** Compares whether ch1 has higher precedence level than ch2
        @param ch1 A char needs to be compared.
        @param ch2 A char needs to be compared.
        @return True if ch1 > ch2  */
    private static boolean isBigger (char ch1, char ch2)
    {
        boolean result = false;
        if (precedenceLevel(ch1) > precedenceLevel(ch2))
            result = true;
        return result;
    }    
    
    /** Returns a precedence level of a math operator
        @param op A char containing the character of the operator
        @return An integer that indicates the level of precedence of op  */
    private static int precedenceLevel(char op) 
    {
        switch (op) 
        {
            case '(':
                return 0;
            case '+': case '-':
                return 1;
            case '*': case '/':
                return 2;
            case '^':
                return 3;
            default:
                throw new IllegalArgumentException("Operator unknown: " + op);
        }
    }    
    
    
    
    /** Evaluates a postfix expression.
     * @param postfix A string that contains the postfix expression to be evaluated.
     * @return An integer that contains the value of postfix evaluation. */
    public static int evaluatePostfix(String postfix)
    {
        StackInterface<Integer> valueStack = new LinkedStack<>();
        int index = 0;
        char nextCharacter = ' ';
        Integer operandTwo, operandOne;
        int result;
        while (index < postfix.length())
        {
            nextCharacter = postfix.charAt(index);
            switch (nextCharacter)
            {
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h':
                    valueStack.push(getValue(nextCharacter));
                    break;
                case '+': case '-': case '*': case '/': case '^':
                    operandTwo = valueStack.pop();
                    operandOne = valueStack.pop();
                    result = calculate(operandOne,operandTwo,nextCharacter);
                    valueStack.push(result);
                    break;
                default:
                    break; // Ingore unexpected characters
            } // end switch
            index++;
        } // end while
        
        return valueStack.peek();
    } // end evaluatePostfix
    
    private static Integer getValue(char op)
    {
        Integer[] values = {2, 3, 4, 5, 6}; // a, b, c, d, e
        return values[op-97];        
    }
    
    /** Calculates two operands with the operator
        @param opd1 An Integer that contains the first operand.
        @param opd2 An Integer that contains the second operand.
        @param opr A character that contains the operator.
        @return An Integer that contains the result of calculation.  
        @throws An arithmetic exception when opd2 is zero in '/' case. */
    private static Integer calculate(Integer opd1, Integer opd2, char opr)
    {
        Integer intObj  = 0;
        switch (opr)
        {
            case '+':
                intObj = opd1 + opd2;
                break;
            case '-':
                intObj = opd1 - opd2;
                break;
            case '*':
                intObj = opd1 * opd2;
                break;
            case '/':
                if (opd2.equals(0))
                    throw new ArithmeticException("Division by zero");
                else
                    intObj = opd1 / opd2;
                break;
            case '^':
                intObj =  (int) Math.pow(opd1, opd2);
            default:
                break;
        }
        return intObj;
    }

    /** Evaluates an infix expression.
     * @param infix A string object that contains the infix to be evaluated.
     * @return An integer that contains the evaluated infix value. */
    public static int evaluateInfix(String infix)
    {
        StackInterface<Character> operatorStack = new LinkedStack<>();
        StackInterface<Integer> valueStack = new LinkedStack<>();
        int index = 0;
        char nextCharacter = ' ';
        char topOperator = ' ';
        Integer operandTwo, operandOne;
        int result; // calculated value of two operands
        while (index < infix.length())
        {
            nextCharacter = infix.charAt(index);
            switch (nextCharacter)
            {
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h':
                    valueStack.push(getValue(nextCharacter));
                    //System.out.println("top value in valueStack: " + valueStack.peek());
                    break;
                case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':                    
                    valueStack.push(Character.getNumericValue(nextCharacter));
                    break;
                case '^':
                    operatorStack.push(nextCharacter);
                    //System.out.println("top char in operatorStack: " + operatorStack.peek());
                    break;
                case '+': case '-': case '*': case '/': 
                    while (!operatorStack.isEmpty() && 
                           !isBigger(nextCharacter, operatorStack.peek()))
                    {
                        // Execute operator at top of operatorStack
                        topOperator = operatorStack.pop();
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        result = calculate(operandOne,operandTwo,topOperator);
                        valueStack.push(result);
                    } // end while
                    operatorStack.push(nextCharacter);
                    break;
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case ')': // Stack is not empty if infix expression is valid
                    topOperator = operatorStack.pop();
                    while (topOperator != '(')
                    {
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        result = calculate(operandOne,operandTwo,topOperator);
                        valueStack.push(result); 
                        topOperator = operatorStack.pop();
                    } // end while
                    break;
                default:
                    break; // Ignore unexpected characters
            } // end switch
            index++;
        } // end while        
        while (!operatorStack.isEmpty())
        {
            topOperator = operatorStack.pop();
            operandTwo = valueStack.pop();
            operandOne = valueStack.pop();
            result = calculate(operandOne,operandTwo,topOperator);
            valueStack.push(result);              
        } // end while        
        return valueStack.peek();
    } // end evaluateInfix
} // end Postfix
