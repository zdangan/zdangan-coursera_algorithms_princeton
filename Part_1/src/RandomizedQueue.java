import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;
    private int n;               // size of the queue
    private Item[] arr;          // array of items

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }


    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = arr[i];
        }
        arr = copy;

       // alternative implementation
       // a = java.util.Arrays.copyOf(a, capacity);
    }


    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add null to the queue.");
        if (n == arr.length) resize(2*arr.length);    // double size of array if necessary
        if (n == 0) {
            arr[n++] = item;
            return;
        }
        int i = StdRandom.uniformInt(0, n);
        Item tmpItem = arr[i];
        arr[i] = item;
        arr[n++] = tmpItem;                          // add item
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("RandQ underflow");
        //int i = StdRandom.uniformInt(0, n);
        Item item = arr[n - 1];
        // arr[i] = arr[n - 1];
        // arr[n - 1] = null;
        n--;
        return item;                   // return the saved item
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandQ underflow");
        int i = StdRandom.uniformInt(0, n);
        return arr[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        if (isEmpty()) throw new NoSuchElementException("RandQ underflow");
        return new RandQIterator(arr[0]);
    }

    // the iterator
    private class RandQIterator implements Iterator<Item> {
        private Item current;
        private int i = 0;

        public RandQIterator(Item first) {
            current = first;
        }

        // is there a next item?
        public boolean hasNext() {
            return current != null;
        }

        // returns the next item
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = arr[i];
            i++;
            if (i < n) {
                current = arr[i];
            }
            else {
                current = null;
            }
            
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("Florian");
        rq.enqueue("Florian2");
        rq.enqueue("Florian3");
        // dq.removeLast();
        System.out.println("Randomized queue size: " + rq.n);

        Iterator<String> value = rq.iterator();
  
        // Displaying the values
        // after iterating through the deque
        System.out.println("The iterator values are: ");
        while (value.hasNext()) {
            System.out.println(value.next());
        }
    }
}