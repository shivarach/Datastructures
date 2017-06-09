package queue;

import java.util.NoSuchElementException;

public class MaxPriorityQueue<Key> {

	private int size = 0;
	// array is used to represent max heap
	private Key[] pq;
	
	public MaxPriorityQueue() {
		pq = (Key [])new Object[2]; //zero index is not used
	}
	
	public void insert(Key key) {
		pq[++size] = key;
		swim(size);
		if (size + 1 >= pq.length)
			resize(2 * (size + 1));
	}
	
	public Key getMax() {
		return pq[1];
	}
	
	public Key delMax() {
		if(size == 0) throw new NoSuchElementException("priority queue is empty!");
		Key max = pq[1];
		pq[1] = pq[size];
		pq[size--] = null; //to avoid loitering
		sink(1);
		//resize
		if (size + 1 <= pq.length / 4)
			resize( pq.length / 2);
		return max;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * the key at k would be moved down until max heap property satisfied
	 * @param k
	 */
	private void sink(int k) {
		while(2 * k <= size) {
			//children can be found at 2*k and 2*k + 1
			int j = 2 * k;
			// right child exists and it is greater than left
			if((j < size) && ((Comparable<Key>)pq[j]).compareTo(pq[j + 1]) < 0)
				j++;
			//at this poing j is point to the max child of k
			if(((Comparable<Key>)pq[k]).compareTo(pq[j]) >= 0)
				break;
			swap(k, j);
			k = j;
		}
		
	}

	private void resize(int capacity) {
		Key[] tmp = ( Key[] )new Object[capacity]; 
		for(int i = 0 ; i <= size; i++)
			tmp[i] = pq[i];
		pq = tmp;
	}

	/**
	 * it moves key at x to upward until max heap property is satisfied
	 * @param x
	 */
	private void swim(int x) {
		int tmp = x;
		while(tmp > 1) {
			//x parent can be found at x/2
			int parentIndex = tmp / 2;
			if(((Comparable<Key>)pq[tmp]).compareTo(pq[parentIndex]) > 0) {
				swap(tmp, parentIndex);
			}
			tmp = parentIndex;
		}
	}

	private void swap(int x, int parent) {
		// TODO Auto-generated method stub
		Key tmp = pq[parent];
		pq[parent] = pq[x];
		pq[x] = tmp;
	}
	
	public static void main(String[] args) {
		MaxPriorityQueue<Integer> maxpq = new MaxPriorityQueue<Integer>();
		maxpq.insert(1);
		maxpq.insert(2);
		maxpq.insert(3);
		maxpq.insert(4);
		maxpq.insert(5);
		maxpq.insert(6);
		maxpq.insert(7);
		maxpq.insert(8);
		
		
		System.out.println(maxpq.getMax());
	}
	
	
}
