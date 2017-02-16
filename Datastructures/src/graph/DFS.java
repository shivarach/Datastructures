package graph;

import java.util.Stack;

import javax.management.RuntimeErrorException;

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
		if(s < 0 || s >= g.getV())
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
		validateVertex(w);
		return marked[w];
	}
	/**
	 * return path from s to v(iterable), null if there is no path
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v ) {
		// if v is out of bound throw exception
		validateVertex(v);
		Stack<Integer> path = new Stack<Integer>();
		// if there is not path then return that vertex
		if(!hasPath(v)) throw new RuntimeException("no path");
		for(int x = v ; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);//push source node too
		return path;
	}
	
	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex out of bound");
	}

	public static void main(String[] args) {
		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 0);
		g.addEdge(0, 2);
		g.addEdge(3, 1);

		System.out.println("No. of vertices: ");
		System.out.println(g.getE());
		System.out.println("adjacent vertices of '0'");
		for(Integer i : g.getAdjacentVertices(0))
			System.out.println(i);
		
		
		//dfs
		DFS dfs = new DFS(g, 0);
		System.out.println("dfs: ");
		for(int i = 0 ; i < g.getV() ;i++)
			if(dfs.marked[i])
				System.out.print(i + " ");
		System.out.println();
		System.out.println("path to 3: ");
		for(Integer i : dfs.pathTo(3))
				System.out.print(i + " ");
			
	}

}
