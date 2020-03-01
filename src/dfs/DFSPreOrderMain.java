package dfs;

import graphs.DepthFirstOrder;
import graphs.Digraph;
import graphs.In;
import graphs.StdOut;

public class DFSPreOrderMain {

    public static void main(String[] args) {
        In in = new In("largeDG.txt");
        Digraph graph = new Digraph(in);
        StdOut.println("PreOrder:  ");

        // Starting the timer for CPU time
        long startTimePreOrder = System.nanoTime();
        // Run DFS on the graph
        DepthFirstOrder dfs = new DepthFirstOrder(graph);
        // Show the vertices in pre-order
        for (int v : dfs.pre()) {
            StdOut.println(v);
        }
        StdOut.printf("CPU Time in Nanoseconds for PreOrder is %d", System.nanoTime() - startTimePreOrder);
    }
}
