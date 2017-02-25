package graph;

import java.util.Stack;

public class DFSDirectedGraph {
	private boolean[] marked;
	private int[] edgeTo;
	private int v;
	private int s;
	public DFSDirectedGraph(DirectedGraph g, int s) {
		this.v = g.V();
		marked = new boolean[v];
		edgeTo = new int[v];
		dfs(g, s);
	}
	private void dfs(DirectedGraph g, int s) {
		validate(s);
		for(int i : g.getAdjacentVertices(s)) {
			if(!marked[i]) {
				marked[i] = true;
				edgeTo[i] = s;
				dfs(g, i);
			}
		}
	}
	public boolean hasPath(int v) {
		validate(v);
		return marked[v];
	}
	
	private void validate(int i) {
		if(i < 0 || i >= v)
			throw new IllegalArgumentException("vertex " + i + " is not between 0 and " + (v-1));
		
	}
	
	public Iterable<Integer> pathTo(int v) {
		validate(v);
		Stack<Integer> stack = new Stack<Integer>();
		for(int x = v ; edgeTo[x] != s; x = edgeTo[x])
			stack.push(x);
		stack.push(s);
		return stack;
	}
	public static void main(String[] args) {
		DirectedGraph g = new DirectedGraph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 0);
		g.addEdge(0, 2);
		g.addEdge(3, 1);

		System.out.println("No. of vertices: ");
		System.out.println(g.V());
		System.out.println("adjacent vertices of '0'");
		for(Integer i : g.getAdjacentVertices(0))
			System.out.println(i);
		
		
		//dfs
		DFSDirectedGraph dfs = new DFSDirectedGraph(g, 0);
		System.out.println("dfs: ");
		for(int i = 0 ; i < g.V() ;i++)
			if(dfs.marked[i])	
				System.out.print(i + " ");
		System.out.println();
		System.out.println("path to 3(look output in reverse way) ");
		for(Integer i : dfs.pathTo(3))
				System.out.print(i + " ");
	}

}
