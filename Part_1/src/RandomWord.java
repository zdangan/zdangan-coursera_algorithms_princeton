import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        double c = 0;
        String winner = "";
        while (true)
        {
            if (StdIn.isEmpty()) {
                break;
             }
            c += 1;
            String s = StdIn.readString();
            // System.out.println("here outside " + c); 
            if (StdRandom.bernoulli(1/c))
            {
                // System.out.println("here inside " + c); 
                winner = s;
            }
        }   
        System.out.println(winner);  
    }    
}
