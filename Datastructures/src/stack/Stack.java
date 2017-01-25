package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
	private Node<Item> first;
	private int n;
	
	private static class Node<Item> {
		private Item data;
		private Node next;
	}
	
	public Stack() {
		first = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}
	
	public void push(Item item) {
		Node<Item> tmp = first;
		first = new Node<Item>();
		first.data = item;
		first.next = tmp;
		n++;
	}
	
	public Item pop() {
		if(isEmpty()) throw new NoSuchElementException("stack underflow");
		Item tmp = first.data;
		first = first.next;
		n--;
		return tmp;
	}
	
	public Item peek() {
		if(isEmpty()) throw new NoSuchElementException("stack underflow");
		return first.data;
	}

	@Override
	public Iterator<Item> iterator() {
		return new StackIterator<Item>(first);
	}
	
	private class StackIterator<Item> implements Iterator<Item> {
		private Node<Item> cur;
		
		public StackIterator(Node<Item> first) {
			this.cur = first;
		}
		@Override
		public boolean hasNext() {
			return cur != null;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException("stack underflow");
			Item tmp = cur.data;
			cur = cur.next;
			return tmp;
		}
		
	}
	
	public static void main(String[] args) {
		Stack<Integer> myStack = new Stack<>();
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		myStack.push(4);
		
		for(Integer element : myStack)
			System.out.println(element);
		
		System.out.println("after pop");
		myStack.pop();
		for(Integer element : myStack)
			System.out.println(element);
	}

}
