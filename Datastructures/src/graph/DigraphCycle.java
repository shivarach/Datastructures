package graph;

/**
 * T: O(E + V)
 * @author Shiva
 *
 */
public class DigraphCycle {

	public boolean[] visited;
	public boolean[] recStack;
	public DirectedGraph g;

	public DigraphCycle(DirectedGraph g) {
		this.g = g;
		visited = new boolean[g.V()];
		recStack = new boolean[g.V()];
	}

	public boolean isCyclic() {
		//all nodes may not be connected. so this loop
		//look for all separate components
		for (int i = 0; i < g.V(); i++) {
			if (!visited[i] && isCyclic(i))
				return true;
		}
		return false;
	}

	private boolean isCyclic(int v) {

		visited[v] = true;
		recStack[v] = true;
		for (int w : g.getAdjacentVertices(v)) {
			if (!visited[w])
				return isCyclic(w);
			else if (recStack[w])
				return true;
		}

		// since it is dfs unwinding it is not part of cycle
		recStack[v] = false;
		return false;
	}

	public static void main(String[] args) {
		DirectedGraph g = new DirectedGraph(5);
		g.addEdge(0, 0);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 0);
		g.addEdge(0, 2);
		g.addEdge(3, 1);
		 

		DigraphCycle dg = new DigraphCycle(g);
		System.out.println(dg.isCyclic());
	}

}
