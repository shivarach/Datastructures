package graph;

import java.util.Stack;

/**
 * Uses Graph.java
 * @author Siva R
 *
 */
public class DFS {
	private boolean[] marked; // marked[v] = is there an s-v path?
	private int[] edgeTo; // edgeTo[v] = last edge on s-v path
	private final int s; //source vertex
	
	public DFS(Graph g, int s) {
		if(s < g.getV() || s >= g.getV())
			throw new IllegalArgumentException("s is out of bound");
		this.s = s;
		marked = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		dfs(g, s);
	}

	private void dfs(Graph g, int v) {
		marked[v] = true;
		for(int w : g.getAdjacentVertices(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			}
		}
		
	}
	/**
	 * checks whether path exist bw s to w
	 * @param w
	 * @return
	 */
	public boolean hasPath(int w) {
		//throw exception if w out of bound
		return marked[w];
	}
	/**
	 * return path from s to v(iterable), null if there is no path
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v ) {
		// if v is out of bound throw exception
		if(!hasPath(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v ; v != s; x = edgeTo[v]) {
			path.push(x);
		}
		path.push(s);//push source node too
		return path;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
