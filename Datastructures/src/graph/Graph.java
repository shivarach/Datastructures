package graph;

import java.util.LinkedList;
import java.util.List;

public class Graph {
	// vertices wont' change
	private final int v;
	private int e;
	private List<Integer>[] adj;
	
	public Graph(int v) {
		if (v < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.v = v;
		adj = (List<Integer>[])new LinkedList[v];
		for(int i = 0; i < v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int a, int b) {
		if(a < 0 || a > v || b < 0 || b > v)
			throw new IllegalArgumentException("vertex out of bound");
		adj[a].add(b);
		adj[b].add(a);
		e++;
	}
	
	public List<Integer> getAdjacentVertices(int w) {
		if(w < v || v >= w)
			throw new IllegalArgumentException("vertex of of bound");
		return adj[w];
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getV() {
		return v;
	}

}
