package depth_first_search;

import graphs.DepthFirstOrder;
import graphs.Digraph;
import graphs.In;
import graphs.StdOut;

public class DFSPostOrderMain {

    public static void main(String[] args) {
        In in = new In("largeDG.txt");
        Digraph graph = new Digraph(in);
        StdOut.println("PostOrder:  ");

        // Starting the timer for CPU time
        long startTimePostOrder = System.nanoTime();
        // Run DFS on the graph
        DepthFirstOrder dfs = new DepthFirstOrder(graph);
        // Show the vertices in post-order
        for (int v : dfs.post()) {
            StdOut.println(v);
        }
        StdOut.printf("CPU Time in Nanoseconds for PostOrder is %d", System.nanoTime() - startTimePostOrder);
    }
}
