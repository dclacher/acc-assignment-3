package dfs;

import graphs.DepthFirstOrder;
import graphs.Digraph;
import graphs.In;
import graphs.StdOut;

public class DFSMain {

	public static void main(String[] args) {
        //In in = new In(args[0]);
    	In in = new In("largeDG.txt");
        Digraph graph = new Digraph(in);

        DepthFirstOrder dfs = new DepthFirstOrder(graph);
        StdOut.println("   v  pre post");
        StdOut.println("--------------");
        for (int v = 0; v < graph.V(); v++) {
            StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
        }

        /*StdOut.print("Preorder:  ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        StdOut.println();*/

	}

}
