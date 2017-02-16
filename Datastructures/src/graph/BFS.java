package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * Tc = o(V + E)
 * @author Shiva
 *
 */
public class BFS {
	
	private boolean[] marked; // to check whether node is already visited
	private int[] edgeTo; // used to find the path
	private int s;
	
	public BFS(Graph g, int source) {
		this.s = source;
		marked = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		bfs(g, s);
	}

	private void bfs(Graph g, int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		marked[s] = true;
		while(!q.isEmpty()) {
			int v = q.poll();
			for(int i : g.getAdjacentVertices(v)) {
				if(!marked[i]) {
					marked[i] = true;
					edgeTo[i] = edgeTo[v];
					q.add(i);
				}
			}
		}
	}
	
	public boolean hasPath(int w) {
		return marked[w];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if(!hasPath(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}

	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 0);
		g.addEdge(0, 2);
		g.addEdge(3, 1);

		System.out.println("No. of vertices: ");
		System.out.println(g.getV());
		System.out.println("adjacent vertices of '0'");
		for(Integer i : g.getAdjacentVertices(0))
			System.out.println(i);
		
		
		//bfs
		BFS bfs = new BFS(g, 0);
		System.out.println("BFS: ");
		for(int i = 0 ; i < g.getV() ;i++)
			if(bfs.marked[i])
				System.out.print(i + " ");
		System.out.println();
		System.out.println("path to 3(look output in reverse way: ");
		for(Integer i : bfs.pathTo(3))
				System.out.print(i + " ");

	}

}
