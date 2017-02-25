package graph;

import java.util.LinkedList;
import java.util.List;

public class DirectedGraph {

	private int v;
	private int e;
	private List<Integer>[] adj;
	public DirectedGraph(int v) {
		adj = (List<Integer>[])new LinkedList[v];
		for(int i = 0; i < v; i++)
			adj[i] = new LinkedList<Integer>();
		this.v = v;
	}
	
	public void addEdge(int v, int w) {
		validate(v);
		validate(w);
		adj[v].add(w);
		e++;
	}
	
	public Iterable<Integer> getAdjacentVertices(int v) {
		validate(v);
		return adj[v];
	}
	
	public int V() {
		return v;
	}
	
	public int E() {
		return e;
	}

	private void validate(int i) {
		if(i < 0 || i >=v)
			throw new IllegalArgumentException("vertex " + i + " is not between 0 and " + (v-1));
		
	}
}
