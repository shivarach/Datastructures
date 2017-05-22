package graph;

import java.util.LinkedList;
import java.util.Queue;

public class TopologicalOrder {
	
	private boolean[] visited;
	private Queue<Integer> q = new LinkedList();
	private DirectedGraph g;
	
	public TopologicalOrder(DirectedGraph g) {
		visited = new boolean[g.V()];
		this.g = g;
	}
	
	public Iterable<Integer> getTopologicalOrder() {
		if(! new DigraphCycle(g).isCyclic())
			dfs(g, 0);
		return q;
	}

	private void dfs(DirectedGraph g, int i) {
		visited[i] = true;
		for(int w : g.getAdjacentVertices(i)) {
			if(!visited[w]) {
				dfs(g, w);
			}
		}
		q.add(i);//adding in post order gives topological order
	}

	public static void main(String[] args) {
		DirectedGraph g = new DirectedGraph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		//g.addEdge(3, 0);
		g.addEdge(0, 2);
		
		TopologicalOrder tp = new TopologicalOrder(g);
		Iterable<Integer> itr = tp.getTopologicalOrder();
		if(!itr.iterator().hasNext())
			System.out.println("Graph has cycle! Topological order is not possible!");
		for(int i : itr) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}

}
