import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array; 
    private int last;
	
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
        // construct an empty randomized queue
    }
	
    public boolean isEmpty() {
        return last == 0;
        // decide if the queue is empty
    }
	
    public int size() {
        return last;
        // return the number of items on the queue
    }
	
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (last >= array.length) {
            Item[] newArray = (Item[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[last++] = item;
        // add the item
    }
	
    public Item dequeue() {
        if (last == 0) {
            throw new NoSuchElementException();
        }
        if (last <= array.length/4) {
            Item[] newArray = (Item[]) new Object[array.length/2];
            for (int i = 0; i < last; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        int ran = StdRandom.uniform(last);
        Item temp = array[last - 1];
        array[last - 1] = array[ran];
        array[ran] = temp;
        Item item = array[--last];
        array[last] = null;
        return item;
        // remove and return a random item
    }
	
    public Item sample()  {
        if (last == 0) {
            throw new NoSuchElementException();
        }
        int ran = StdRandom.uniform(last);
        return array[ran];
        // return (but do not remove) a random item
    }
	
    public Iterator<Item> iterator() {
        return new ListIterator();
        // return an independent iterator over items in random order
    }
	
    private class ListIterator implements Iterator<Item> {
        private int index;
        private Item[] copyArray;
		
        public ListIterator() {
            copyArray = (Item[]) new Object[last];
            for (int i = 0; i < last; i++) {
                copyArray[i] = array[i];
            }
            index = last - 1;
        }

        @Override
        public Item next() {
            if (index < 0) {
                throw new NoSuchElementException();
            }
            int ran = StdRandom.uniform(index + 1);
            Item item = copyArray[ran];
            copyArray[ran] = copyArray[index];
            copyArray[index--] = null;
            return item;
        }
		
        @Override
        public boolean hasNext() {
            return index >= 0;
        }
		
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
	
    public static void main(String[] args) {
    
        // unit testing
    }
}
