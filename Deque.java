import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
        public Node(Item item) {
            this.item = item;
        }
    }
	
    private int size;
    private Node first;
    private Node last;
	
    public Deque() {
        size = 0;
        first = new Node((Item) new Object());
        last = new Node((Item) new Object());
        first.next = last;
        last.prev = first;
        first.prev = null;
        last.next = null;
        // construct an empty deque
    }
	
    public boolean isEmpty() {
        return first.next.equals(last);
        // decide if the deque is empty
    }
	
    public int size() {
        return size;
        // return the number of items on the deque
    }
	
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node(item);
        first.next.prev = node;
        node.next = first.next;
        first.next = node;
        node.prev = first;
        size++;
        // add the item to the front
    }
	
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node(item);
        node.prev = last.prev;
        node.next = last;
        last.prev.next = node;
        last.prev = node;
        size++;
        // add the item to the end
    }
    
    public Item removeFirst() {
        if (first.next.equals(last)) {
            throw new NoSuchElementException();
        }
        Node node = first.next;
        first.next = node.next;
        node.next.prev = first;
        size--;
        return node.item;
        // remove and return the item from the front
    }
    
    public Item removeLast() {
        if (first.next.equals(last)) {
            throw new NoSuchElementException();
        }
        Node node = last.prev;
        last.prev = node.prev;
        node.prev.next = last;
        size--;
        return node.item;
        // remove and return the item from the end
    }
	
    public Iterator<Item> iterator() {
        return new ListIterator();
        // return an iterator over items in order from front to end
    }
	
    private class ListIterator implements Iterator<Item> {
        private Node curr = first.next;

        @Override
        public Item next() {
            if (curr.equals(last)) {
                throw new NoSuchElementException();
            }
            Item item = curr.item;
            curr = curr.next;
            return item;
        }
		
        @Override
        public boolean hasNext() {
            return !curr.equals(last);
        }
		
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
	
    public static void main(String[] args) {
    
        // unit testing (optional)
    }
}
