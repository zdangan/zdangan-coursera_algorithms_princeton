import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString(); 
            if (rq.size() == k) {
                rq.dequeue();
            }  
            rq.enqueue(s); 
        } 
        Iterator<String> value = rq.iterator();


        // Displaying the values
        // after iterating through the deque
        // System.out.println("The iterator values are: ");
        
        while (value.hasNext() && rq.size() > 0) {
            System.out.println(value.next());
        }
    }
}
