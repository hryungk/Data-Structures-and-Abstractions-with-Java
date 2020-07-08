import TreePackage.DecisionTreeInterface; 
import TreePackage.DecisionTree;
public class GuessingGame
{
    private DecisionTreeInterface<String> tree;
    public GuessingGame(String question, String noAnswer, String yesAnswer) 
    {
        DecisionTree<String> no = new DecisionTree<>(noAnswer); 
        DecisionTree<String> yes = new DecisionTree<>(yesAnswer); 
        tree = new DecisionTree<>(question, no, yes);
    } // end default constructor
    
    public void play() 
    {
        tree.resetCurrentNode(); // Initialize current node to root
        while (!tree.isAnswer())
        {
            // Ask current question
            System.out.println(tree.getCurrentData()); 
            if (Client.isUserResponseYes())
                tree.advanceToYes();
            else
                tree.advanceToNo();
        } // end while
        assert tree.isAnswer(); // Assertion: Leaf is reached
        
        // Make guess
        System.out.println("My guess is " + tree.getCurrentData() +
                            ". Am I right?");
        if (Client.isUserResponseYes()) 
            System.out.println("I win.");
        else
            learn();
       } // end play
    
    private void learn() 
    {
        // STUB
    } // end learn
} // end GuessingGame