package TreePackage;
public interface ExpressionTreeInterface extends BinaryTreeInterface<String>
{
    /** Computes the value of the expression in this tree.
        @return  The value of the expression. */
    public double evaluate();
} // end ExpressionTreeInterface
