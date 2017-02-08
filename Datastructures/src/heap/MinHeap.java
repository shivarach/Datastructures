package heap;

import java.util.NoSuchElementException;

public class MinHeap<Item extends Comparable<Item>> {
	
	private Item[] mHeap;
	private int size;
	
	public MinHeap() {
		mHeap = (Item[]) new Comparable[10];
	}
	//resize array if needed
	public void insert(Item item) {
		mHeap[++size] = item;//leave 0th index to make traversal easy
		swim(size);
	}
	
	public Item delMin() {
		if(size < 1) throw new NoSuchElementException();
		Item tmp = mHeap[1];
		mHeap[1] = mHeap[size];
		mHeap[size--] = null; //loitering
		sink(1);
		return tmp;
	}

	private void sink(int k) {
		while (2*k <= size) {
			int j = 2*k;
			// j < size makes sure that second child is also exist at j + 1
			if (j < size && min(j + 1, j))
				j++;
			// node at k is already less than the children
			if(min(k, j))
				return;
			exchange(k, j);
			k = j;
		}
		
	}

	private void swim(int k) {
		while (k > 1 && min(k, k/2)) {
			exchange(k, k/2);
			k = k/2;
		}
	}

	private boolean min(int k, int i) {
		return (mHeap[k].compareTo(mHeap[i]) < 0);
	}

	private void exchange(int a, int b) {
		Item tmp = mHeap[a];
		mHeap[a] = mHeap[b];
		mHeap[b] = tmp;
	}

	public static void main(String[] args) {
		MinHeap<Integer> mh = new MinHeap<Integer>();
		mh.insert(5);
		mh.insert(4);
		mh.insert(3);
		mh.insert(1);
		mh.insert(2);
		
		System.out.println(mh.delMin());
		System.out.println(mh.delMin());
		System.out.println(mh.delMin());
		mh.insert(100);
		System.out.println(mh.delMin());
		System.out.println(mh.delMin());
		System.out.println(mh.delMin());
	}

}
