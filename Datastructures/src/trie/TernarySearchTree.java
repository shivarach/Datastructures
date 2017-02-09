package trie;
/**
 * Ternary search tree
 * get and put takes ln n time
 * @author Shiva
 *
 * @param <Value>
 */
public class TernarySearchTree<Value> {
	private Node root;
	private class Node {
		char ch;
		Value val;
		Node left, right, mid;
		
		public Node(char ch) {
			this.ch= ch;
		}
	}
	
	public Value get(String key) {
		if(key == null || key.length() == 0) throw new IllegalArgumentException();
		return get(root, key, 0);
	}
	
	private Value get(Node x, String key, int i) {
		if (x == null) return null;
		char c = key.charAt(i);
		if(c < x.ch) return get(x.left, key, i);
		else if(c > x.ch) return get(x.right, key, i);
		else if(i < (key.length() - 1)) return get(x.mid, key, i+1);
		else return x.val;
	}

	public void put(String key, Value val) {
		if(key == null || key.length() == 0) throw new IllegalArgumentException();
		root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int i) {
		char c = key.charAt(i);
		if (x == null)
			x = new Node(c);
		if(c < x.ch)
			x.left = put(x.left, key, val, i);
		else if(c > x.ch)
			x.right = put(x.right, key, val, i);
		else if(i < (key.length() - 1))
			x.mid = put(x.mid, key, val, i+1);
		else x.val = val;
		return x;
	}

	public static void main(String[] args) {
		TernarySearchTree<Integer> tst = new TernarySearchTree<>();
		tst.put("sea", 1);
		tst.put("shelter", 2);
		tst.put("bay", 3);
		tst.put("tea", 4);
		tst.put("tea", 5);
		
		System.out.println(tst.get("sea"));
		System.out.println(tst.get("shelter"));
		System.out.println(tst.get("bay"));
		System.out.println(tst.get("tea"));

	}

}
