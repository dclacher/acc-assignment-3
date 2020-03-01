package minimum_spanning_tree;

import graphs.*;

public class KruskalMain {

    public static void main(String[] args) {
        In in = new In("largeEWG.txt");
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        long startTime = System.nanoTime();
        KruskalMST mst = new KruskalMST(graph);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
        StdOut.printf("CPU Time in Nanoseconds is %d", System.nanoTime() - startTime);
    }
}
