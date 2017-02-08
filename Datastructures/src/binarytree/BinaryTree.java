package binarytree;

import java.util.NoSuchElementException;

public class BinaryTree<Item extends Comparable<Item>> {
	
	private Node root = null;
	
	private class Node {
		private Item item;
		private Node left, right;
		
		public Node(Item item) {
			this.item = item;
		}
	}
	
	public void insert(Item item) {
		root = insert(root, item);
	}

	private BinaryTree<Item>.Node insert(BinaryTree<Item>.Node x, Item item) {
		if (x == null)
			return new Node(item);
		int cmp = item.compareTo(x.item);
		if(cmp <= 0)
			x.left = insert(x.left, item);
		else x.right = insert(x.right, item);
		return x;
	}
	
	public void delete(Item item) {
		root = delete(root, item);
	}
	
	private BinaryTree<Item>.Node delete(BinaryTree<Item>.Node x, Item item) {
		if (x == null)
			return null;
		int cmp = item.compareTo(x.item);
		if (cmp < 0)
			x.left = delete(x.left, item);
		else if (cmp > 0 )
			x.right = delete(x.right, item);
		else {
			if(x.right == null) return x.left;
			if(x.left == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		return x;
	}

	private BinaryTree<Item>.Node min(BinaryTree<Item>.Node x) {
		if (x == null) return null;
		if(x.left == null) return x;
		return min(x.left);
	}

	public void deleteMin() {
		if(root == null)
			throw new NoSuchElementException("no elements");
		root = deleteMin(root);
	}
	/*
	 * Deletes tree rooted at x and returns root
	 */
	private BinaryTree<Item>.Node deleteMin(BinaryTree<Item>.Node x) {
		if (x == null) return null;
		if(x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		return x;
	}

	public void printInorder() {
		printInorder(root);
	}

	private void printInorder(BinaryTree<Item>.Node x) {
		if (x == null)
			return;
		printInorder(x.left);
		System.out.print(x.item + "  ");
		printInorder(x.right);
	}

	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.insert(5);
		bt.insert(3);
		bt.insert(8);
		bt.insert(1);
		bt.insert(4);
		bt.insert(6);
		bt.insert(10);
		System.out.println("inorder: ");
		bt.printInorder();
		System.out.println();
		bt.delete(5);
		bt.printInorder();

	}

}
