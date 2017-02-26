package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * 
 * @author Shiva
 *  Run breadth-first search on a digraph.
 *  Runs in O(E + V) time.
 *
 */
public class BFSDirectedGraph {
	
	private boolean marked[];
	private int edgeTo[];
	private int dist[];
	private int v;//number of vertices
	private int s;
	
	public BFSDirectedGraph(DirectedGraph g, int s) {
		this.v = g.V();
		this.s = s;
		marked = new boolean[v];
		edgeTo = new int[v];
		dist = new int[v];
		for(int i = 0; i < v ; i++)
			dist[i] = Integer.MAX_VALUE;
		validateVertex(s);
		bfs(g, s);
	}

	private void bfs(DirectedGraph g, int s) {
		marked[s] = true;
		dist[s] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		while(!q.isEmpty()) {
			int v = q.poll();
			for (int i : g.getAdjacentVertices(v)) {
				if(!marked[i]) {
					marked[i] = true;
					edgeTo[i] = v;
					dist[i] = dist[v] + 1;
					q.add(i);
				}
			}
		}
		
	}
	
	public int distTo(int v) {
		validateVertex(v);
		return dist[v];	
	}
	public boolean hasPathTo(int w) {
		validateVertex(w);
		return marked[w];
	}
	// gives shortest path
	public Iterable<Integer> pathTo(int v) {
		validateVertex(v);
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for(x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}
	

	private void validateVertex(int w) {
		if(w < 0 || w >= v)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (v-1));
		
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
		
		
		//Bfs
		BFSDirectedGraph bfs = new BFSDirectedGraph(g, 0);
		System.out.println("bfs: ");
		for(int i = 0 ; i < g.V() ;i++)
			if(bfs.marked[i])	
				System.out.print(i + " ");
		System.out.println();
		System.out.println("path to 3(look output in reverse way) ");
		for(Integer i : bfs.pathTo(3))
				System.out.print(i + " ");
	}

		
	}
