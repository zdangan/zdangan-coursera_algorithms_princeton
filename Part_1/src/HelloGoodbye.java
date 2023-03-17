// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;

public class HelloGoodbye {
    /**
     * Reads in a sequence of 2 names (A and B) and prints 
     * Hello A and B.
     * Goodbye A and B.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello " + args[0] + " and " + args[1] + ".");
        System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
    }
}
