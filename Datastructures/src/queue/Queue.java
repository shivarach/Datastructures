package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

	private Node<Item> first;
	private Node<Item> last;
	private int n;
	
	private static class Node<Item> {
		private Item data;
		private Node next;
	}
	
	public Queue() {
		first = last = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}
	
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		return first.data;
	}
	
	public void enqueue(Item item) {
		Node<Item> tmp = last;
		last = new Node<Item>();
		last.data = item;
		last.next = null;
		n++;
		if(isEmpty()) first = last;
		else tmp.next = last;
	}
	
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Item tmp = first.data;
		first = first.next;
		n--;
		if (isEmpty()) last = null;   // to avoid loitering
		return tmp;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.next; 
            return item;
        }
    }
	
	public static void main(String[] args) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		for(Integer elem : q)
			System.out.println(elem);
		System.out.println("dequeue");
		
		q.dequeue();
		for(Integer elem : q)
			System.out.println(elem);

	}

	

}
