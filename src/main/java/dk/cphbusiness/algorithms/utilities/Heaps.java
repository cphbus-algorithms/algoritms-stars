package dk.cphbusiness.algorithms.utilities;

import java.util.Comparator;

public class Heaps {

    private static int parentOf(int offset, int index) {
        return (index - offset)/2 + offset;
        }
    
    private static boolean isRoot(int offset, int index) {
        return index == offset + 1;
        }
    
    private static int leftChildOf(int offset, int index) {
        return 2*(index - offset) + offset;
        }
    
    private static int rightChildOf(int offset, int index) {
        return 2*(index - offset) + offset + 1;
        }
    
    public static <T> void printSlice(T[] items, int start, int end) {
        for (int i = start; i < end; i++) {
            if (i == start) System.out.print("["+items[i]);
            else System.out.print("; "+items[i]);
            }
        System.out.println("]");
        }
    
    private static <T> void swap(T[] items, int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
        }
    
    public static <T> void sortSlice(T[] items, int start, int end, Comparator<T> comparator) {
        int offset = start - 1;
        // Heapify
        for (int stop = start + 1; stop < end; stop++) {
            int c = stop;
            int p = parentOf(offset, c);
            while (comparator.compare(items[c], items[p]) > 0) {
                swap(items, c, p);
                if (isRoot(offset, p)) break;
                c = p;
                p = parentOf(offset, c);
                }
            }
        // Sort
        for (int stop = end - 1; stop > start; stop--) {
            swap(items, start, stop);
            int m = start;
            do {
                int p = m;
                int l = leftChildOf(offset, p);
                int r = rightChildOf(offset, p);
                if (l >= stop) break; // both children empty
                if (r >= stop || comparator.compare(items[l], items[r]) > 0) m = l;
                else m = r;
                if (comparator.compare(items[p], items[m]) > 0) break;
                swap(items, p, m);
                }
            while (true);
            }
        }
    
    public static <T> void printAll(T[] items) {
        printSlice(items, 0, items.length);
        }
    
    public static void main(String[] args) {
        String[] letters = new String[] { "X", "Y", "Z", "G", "E", "B", "A", "D", "C", "F", "J" };
        printAll(letters);
        System.out.print("         "); printSlice(letters, 3, 10);
        sortSlice(letters, 3, 10, (l1, l2) -> l1.compareTo(l2));
        printAll(letters);
        System.out.print("         "); printSlice(letters, 3, 10);
        }

    }
