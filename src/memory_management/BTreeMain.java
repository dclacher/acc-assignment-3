package memory_management;

import memoryManagement.StdOut;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BTreeMain {

    public static void main(String[] args) {

        FileWriter writer = null;
        List<String> result = null;
        try {
            long startTime = System.nanoTime();
            // Create a B-tree
            BTreeModel<String, String> bTreeModel = new BTreeModel<>();
            // Insert all the reads from the original list (Chip-seq-reads-1M.dat) as they appear in the file
            result = Files.readAllLines(Paths.get("ChIP-seq-reads-1M.dat"), StandardCharsets.UTF_8);
            for (String s : result) {
                bTreeModel.put(s, s);
            }
            // List the B-tree in in-order traversal and save the output all keys in a file (called B-tree.dat)
            writer = new FileWriter("B-tree.dat");
            writeFileFromList(bTreeModel.printInOrder(bTreeModel.getRoot(), bTreeModel.height(), new ArrayList<>()),
                    writer);
            StdOut.printf("CPU Time in Nanoseconds is %d", System.nanoTime() - startTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility generic method to write a file from a list of strings
     *
     * @param list   The list to be written
     * @param writer The Java object to write to a file
     * @throws IOException
     */
    private static void writeFileFromList(List<String> list, FileWriter writer)
            throws IOException {
        for (int i = 0; i < list.size(); i++) {
            if (i == 999999) {
                writer.write(list.get(i).trim());
            } else {
                writer.write(list.get(i).trim() + System.lineSeparator());
            }
        }
        writer.close();
    }
}
