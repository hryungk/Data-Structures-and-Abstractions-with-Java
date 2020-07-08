public class hashFunctions 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String s = "Java";
        int hash = 0;
        int n = s.length();
        int g = 31;
        for (int i = 0; i < n; i++)
            hash = g * hash + s.charAt(i);
        System.out.println("Using Horner's method: " + hash);
        System.out.println("Using Java's default method: " + "Java".hashCode());
    } // end main        
}
