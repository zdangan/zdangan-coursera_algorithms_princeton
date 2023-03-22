import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;     // top of deque
    private Node<Item> last;      // bottom of deque
    private int n;                // size of the deque

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add null to the deque.");
        if (n == 0) {
            first = new Node<Item>();
            first.item = item;
            last = first;
            n++;
            return;
        }
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.prev = oldfirst;
        oldfirst.next = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Can't add null to the deque.");
        if (n == 0) {
            last = new Node<Item>();
            last.item = item;
            first = last;
            n++;
            return;
        }
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = oldlast;
        oldlast.prev = last;
        n++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;        // save item to return
        if (n == 1) {
            last = null;
            first = null;
            n--;
            return item;
        }
        first = first.prev;            // delete first node
        first.next = null;
        n--;
        return item;                   // return the saved item
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = last.item;        // save item to return
        if (n == 1) {
            last = null;
            first = null;
            n--;
            return item;
        }
        last = last.next;             // delete first node
        last.prev = null;
        n--;
        return item;                  // return the saved item
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    // the iterator
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        // is there a next item?
        public boolean hasNext() {
            return current != null;
        }

        // returns the next item
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        // dq.addFirst("Florian");
        // dq.addLast("Florian2");
        // dq.addLast("Florian3");
        deque.addFirst("1"); 
        // System.out.println("first.item = " + deque.first.item);
        // System.out.println("last.item = " + deque.last.item);
        // System.out.println("deque is empty " + deque.isEmpty());
        System.out.println("n = " + deque.n);
        deque.removeFirst();
        // System.out.println(deque.isEmpty());
        deque.removeLast();

        System.out.println("Deque size: " + deque.n);

        Iterator<String> value = deque.iterator();
  
        // Displaying the values
        // after iterating through the deque
        System.out.println("The iterator values are: ");
        while (value.hasNext()) {
            System.out.println(value.next());
        }
    }

}