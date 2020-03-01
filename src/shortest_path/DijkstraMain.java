package shortest_path;

import graphs.*;

public class DijkstraMain {

	public static void main(String[] args) {
		In in = new In("mediumEWG.txt");
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);

		/*The all-pairs shortest path problem is the determination of the shortest graph distances between
		every pair of vertices in a given graph. The problem can be solved using n executions of Dijkstra's
		algorithm or all at once using the Floyd-Warshall algorithm. Since we don't have the Floyd-Warshall
		algorithm, let's try with Dijkstra's algorithm n times. */

		long startTime = System.nanoTime();
		for (int s = 0; s < graph.V(); s++) {
			// Compute shortest paths for the current vertex
			DijkstraSP sp = new DijkstraSP(graph, s);

			// print shortest path
			for (int t = 0; t < graph.V(); t++) {
				if (sp.hasPathTo(t)) {
					StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
					if (sp.hasPathTo(t)) {
						for (DirectedEdge e : sp.pathTo(t)) {
							StdOut.print(e + "   ");
						}
					}
					StdOut.println();
				}
				else {
					StdOut.printf("%d to %d         no path\n", s, t);
				}
			}
		}
		StdOut.printf("CPU Time in Nanoseconds is %d", System.nanoTime() - startTime);
	}
}
