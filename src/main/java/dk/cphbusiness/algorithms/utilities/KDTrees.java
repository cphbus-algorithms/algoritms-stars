package dk.cphbusiness.algorithms.utilities;

import static dk.cphbusiness.algorithms.utilities.Heaps.sortSlice;
import java.util.function.Consumer;

public class KDTrees {
    public static <T extends ComparableByAxis<T>> void handle(
            T[] items, 
            int start, 
            int end, 
            int dimensions, 
            int axis
            ) {
        sortSlice(items, start, end, (s1, s2) -> s1.compareToByAxis(axis, s2));
        if (end - start > 3) {
            int mid = (end + start)/2;
            handle(items, start, mid, dimensions, (axis + 1)/dimensions);
            handle(items, mid + 1, end, dimensions, (axis + 1)/dimensions);
            }
        }
    
    public static <T extends ComparableByAxis<T>> void handle(
        T[] items, 
        int dimensions 
        ) {
      handle(items, 0, items.length, dimensions, 0);
      } 

    private static <T extends ComparableByAxis<T>> void doInRange(T item, int dimensions, T low, T high, Consumer<T> consumer) {
      for (int axis = 0; axis < dimensions; axis++) {
        if (low.compareToByAxis(axis, item) > 0) return;
        if (high.compareToByAxis(axis, item) <= 0) return;
        }
      consumer.accept(item);
      }
    
    private static <T extends ComparableByAxis<T>> void doInRange(
            T[] items,
            int start,
            int end,
            int dimensions,
            int axis,
            T low,
            T high,
            Consumer<T> consumer
            ) {
        int mid = (end + start)/2;
        T root = items[mid];
        doInRange(root, dimensions, low, high, consumer);
        
        if (end - start == 1) return;
        if (low.compareToByAxis(axis, root) <= 0)
            doInRange(items, start, mid, dimensions, (axis + 1)/dimensions, low, high, consumer);
        if (high.compareToByAxis(axis, root) > 0)
            doInRange(items, mid + 1, end, dimensions, (axis + 1)/dimensions, low, high, consumer);
        }
    
    public static <T extends ComparableByAxis<T>> void doInRange(
        T[] items,
        int dimensions,
        T low,
        T high,
        Consumer<T> consumer
        ) {
      doInRange(items, 0, items.length, dimensions, 0, low, high, consumer);
      }
    
    }
