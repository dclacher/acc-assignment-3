package memory_management;

import memoryManagement.BTree;
import memoryManagement.StdOut;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BTreeMain {

    public static void main(String[] args) {
        /*Create a B-tree and insert all the reads from the original list (Chip-seq-reads-1M.dat) as they appear in the file.
        List the B-tree in in-order traversal and save the output all keys in a file (called B-tree.dat).*/

        List<String> result = null;
        try {
            // Create a B-tree
            BTree<String, String> bTree = new BTree<>();
            // Insert all the reads from the original list (Chip-seq-reads-1M.dat) as they appear in the file
            result = Files.readAllLines(Paths.get("ChIP-seq-reads-1M.dat"), StandardCharsets.UTF_8);
            for (String s : result) {
                bTree.put(s, s);
            }
            // List the B-tree in in-order traversal and save the output all keys in a file (called B-tree.dat)
            StdOut.println(bTree.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printInOrderTraversalBTree(BTree<String, String> bTree) {
    }
}
