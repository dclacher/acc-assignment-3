package memory_management;

import memoryManagement.In;
import memoryManagement.IndexMinPQ;
import memoryManagement.StdOut;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiwayMain {

    public static void main(String[] args) {
        FileWriter writerA = null;
        FileWriter writerB = null;
        FileWriter writerC = null;
        FileWriter writerD = null;
        try {
            // Read the original file and copy it into a list
            List<String> result = Files.readAllLines(Paths.get("ChIP-seq-reads-1M.dat"), StandardCharsets.UTF_8);
            // Partition the list of reads into four sub-lists
            List<String> subListA = result.subList(0, 250000);
            List<String> subListB = result.subList(250000, 500000);
            List<String> subListC = result.subList(500000, 750000);
            List<String> subListD = result.subList(750000, 1000000);
            // Save each sub-list in a separate file (called A.dat, B.dat, C.dat, and D.dat)
            writerA = new FileWriter("A.dat");
            writeFileFromList(subListA, writerA);
            writerB = new FileWriter("B.dat");
            writeFileFromList(subListB, writerB);
            writerC = new FileWriter("C.dat");
            writeFileFromList(subListC, writerC);
            writerD = new FileWriter("D.dat");
            writeFileFromList(subListD, writerD);
            // Sort each sub-list
            Collections.sort(subListA);
            Collections.sort(subListB);
            Collections.sort(subListC);
            Collections.sort(subListD);
            // Store each SORTED sub-list in a file (AS.dat, BS.dat, CS.dat, DS.dat)
            writerA = new FileWriter("AS.dat");
            writeFileFromList(subListA, writerA);
            writerB = new FileWriter("BS.dat");
            writeFileFromList(subListB, writerB);
            writerC = new FileWriter("CS.dat");
            writeFileFromList(subListC, writerC);
            writerD = new FileWriter("DS.dat");
            writeFileFromList(subListD, writerD);
            // Take the 4 sorted sub-lists from the files and merge them into a sorted list
            // Store the sorted list in a file called Chip-seq-reads-1M-sorted.dat
            String[] fileNames = {"AS.dat", "BS.dat", "CS.dat", "DS.dat"};
            int N = fileNames.length;
            In[] streams = new In[N];
            for (int i = 0; i < N; i++)
                streams[i] = new In(fileNames[i]);
            merge(streams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility generic method to write a file from a list of strings
     * @param list The list to be written
     * @param writer The Java object to write to a file
     * @throws IOException
     */
    private static void writeFileFromList(List<String> list, FileWriter writer)
            throws IOException {
        for (int i = 0; i < list.size(); i++) {
            if (i == 249999) {
                writer.write(list.get(i).trim());
            } else {
                writer.write(list.get(i).trim() + System.lineSeparator());
            }
        }
        writer.close();
    }

    /**
     * Method that merges several files into one big sorted file
     * (derived from provided class Multiway.java, in the project Memory Management)
     * @param streams The files to be merged
     */
    private static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
        List<String> mergedList = new ArrayList<>();

        for (int i = 0; i < N; i++)
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());

        // Extract and add min to list, and read next from its stream
        while (!pq.isEmpty()) {
            mergedList.add(pq.minKey());
            //StdOut.print(pq.minKey() + " ");
            int i = pq.delMin();
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("Chip-seq-reads-1M-sorted.dat");
            writeFileFromList(mergedList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
