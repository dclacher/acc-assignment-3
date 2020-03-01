package connected_components;

import graphs.*;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponentsMain {

    public static void main(String[] args) {
        SymbolGraph symbolGraph = new SymbolGraph("movies.txt", "/");
        Graph graph = symbolGraph.G();

        /*
        QUESTION #3
         */
        // Starting the timer for the CC algorithm using DFS
        long startTime = System.nanoTime();
        // Creating a new CC object (Connected Components), which uses DFS to find all the CCs.
        CC cc = new CC(graph);
        // Stopping the timer since everything was already done during the creation of the CC object.
        StdOut.printf("CPU Time in Nanoseconds is %d", System.nanoTime() - startTime);
        StdOut.println();

        /*
        QUESTION #4
         */
        // Movies starred by Leonardo DiCaprio
        List<String> diCaprioMovies = printVertexConnections(symbolGraph, graph, "DiCaprio, Leonardo");
        StdOut.println("Movies starred by Leonardo DiCaprio:");
        for (String s: diCaprioMovies) {
            StdOut.println(s);
        }
        StdOut.println();
        // Movies starred by Julia Roberts
        List<String> robertsMovies = printVertexConnections(symbolGraph, graph, "Roberts, Julia (I)");
        StdOut.println("Movies starred by Julia Roberts:");
        for (String s: robertsMovies) {
            StdOut.println(s);
        }
        StdOut.println();
        // Movies starred by Hugh Grant
        List<String> grantMovies = printVertexConnections(symbolGraph, graph, "Grant, Hugh (I)");
        StdOut.println("Movies starred by Hugh Grant:");
        for (String s: grantMovies) {
            StdOut.println(s);
        }
        StdOut.println();
        // Movies starred by Julia Roberts AND by Hugh Grant
        List<String> robertsGrantMovies = new ArrayList<>(robertsMovies);
        robertsGrantMovies.retainAll(grantMovies);
        StdOut.println("Movies starred by Julia Roberts AND by Hugh Grant:");
        for (String s: robertsGrantMovies) {
            StdOut.println(s);
        }

        // printConnectedComponentsInformation(graph, cc);
    }

    private static List<String> printVertexConnections(SymbolGraph sg, Graph graph, String source) {
        List<String> result = new ArrayList<>();
        if (sg.contains(source)) {
            int s = sg.index(source);
            for (int v : graph.adj(s)) {
                result.add(sg.name(v));
                // StdOut.println("   " + sg.name(v));
            }
        }
        else {
            StdOut.println("input does not contain '" + source + "'\n");
        }
        return result;
    }

    private static void printConnectedComponentsInformation(Graph graph, CC cc) {
        // Showing some information after CC object is created.
        // How many connected components are there in the graph?
        int connectedComponents = cc.count();
        StdOut.println("There are " + connectedComponents + " connected components in the graph");
        // Get all the vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[connectedComponents];
        for (int i = 0; i < connectedComponents; i++) {
            components[i] = new Queue<>();
        }
        for (int v = 0; v < graph.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }
        // Print results
        for (int i = 0; i < connectedComponents; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
