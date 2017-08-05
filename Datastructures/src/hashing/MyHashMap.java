package hashing;

import java.util.LinkedList;

public class MyHashMap<Key, Value> {
	
	private static int CAPACITY = 4;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private LinkedList<Node>[] map;
	private int size;
	
	private class Node {
		private Object key;
		private Object value;
		private Node next;
		
		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
	}
	public MyHashMap() {
		this(CAPACITY);
	}
	public MyHashMap(int cap) {
		this.CAPACITY = cap;
		map = new LinkedList[cap];
		for(int i = 0; i < cap; i++)
			map[i] = new LinkedList<Node>();
	}
	
	public int hash(Key key) {
		return (key.hashCode() & Integer.MAX_VALUE) % CAPACITY;
	}
	
	public void put(Key key, Value value) {
		int i = hash(key);
		LinkedList<Node> ll = map[i];
		for(Node node : ll) {
			if(node.key.equals(key)) {
				node.value = value;
			}
		}
		ll.add(new Node(key, value));
		size++;
		//resize
		if (size >= CAPACITY * DEFAULT_LOAD_FACTOR ) {
			resize(2 * CAPACITY);
		}
	}
	
	private void resize(int i) {
		int oldCapacity = CAPACITY;
		MyHashMap<Key, Value> tmp = new MyHashMap<Key, Value>(i);
		for (int j = 0 ; j < oldCapacity ; j++) {
			for(Node node : map[j]) {
				tmp.put((Key)node.key, (Value)node.value);
			}
		}
		map = tmp.map;
	}

	public Value get(Key key) {
		int i = hash(key);
		LinkedList<Node> ll = map[i];
		for(Node node : ll) {
			if(node.key.equals(key)) {
				return (Value) node.value;
			}
		}
		return null;
	}
	
	private Node getNode(Key key) {
		int i = hash(key);
		LinkedList<Node> ll = map[i];
		for(Node node : ll) {
			if(node.key.equals(key)) {
				return node;
			}
		}
		return null;
	}
	public void delete(Key key) {
		int i = hash(key);
		Node x = getNode(key);
		if (x != null)
		{
			map[i].remove(x);
			size--;
		}
	}
	
	public boolean contains(Key key) {
		return get(key) == null ? false : true;
	}
	
	

	public static void main(String[] args) {
		MyHashMap<String, String> map = new MyHashMap<>();
		map.put("a", "b");
		map.put("c", "d");
		map.put("e", "f");
		map.put("g", "h");
		
		System.out.println(map.get("c"));
		System.out.println(map.get("a"));
		

	}

}
