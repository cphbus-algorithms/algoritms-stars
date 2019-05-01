package dk.cphbusiness.algorithms.stars;

import java.util.Random;
import static dk.cphbusiness.algorithms.utilities.Heaps.*;
import static dk.cphbusiness.algorithms.utilities.KDTrees.*;
import dk.cphbusiness.algorithms.utilities.Timer;
import static java.lang.Math.*;
import java.util.function.BiConsumer;

public class Program {
    private static final Random random = new Random(7l);
    
    private static void printUniverse(Matter[] universe) {
        printSlice(universe, 0, min(universe.length, 10));
        }
    
    // https://youtu.be/Z4dNLvno-EY
    
    
    private static void fillUniverse(Matter[] universe) {
        for (int index = 0; index < universe.length; index++) {
            universe[index] = new Matter(random);
            }
        }
    
    private static void speedTest() {
      Matter[] universe = new Matter[1_048_575];
      //Stuff[] universe = new Stuff[65_535];
      for (int i = 0; i < 10; i++) {
        long t1, t2;
        t1 = System.currentTimeMillis();
        fillUniverse(universe);
        handle(universe, 2);
        t2 = System.currentTimeMillis();
        System.out.printf("Time %10.2fs\n", 0.001*(t2 - t1));
        }
      System.out.println("------------");
      printUniverse(universe);
      }
    
    static int count1 = 0;
    static int count2 = 0;
    
    private static void rangeTest() {
      double MIN = 499.0;
      double MAX = 501.0;
      //Matter[] universe = new Matter[1_048_575];
      Matter[] universe = new Matter[8_388_607];
      //Matter[] universe = new Matter[134_217_728];

//       double MIN = 300.0;
//       double MAX = 600.0;
//       Matter[] universe = new Matter[31];
      fillUniverse(universe);
      System.out.println("universe filled with matter");
      handle(universe, 2);
      System.out.println("universe handled with kd-tree");
      Matter low = new Matter(MIN, MIN);
      Matter high = new Matter(MAX, MAX);
      long t1 = System.currentTimeMillis();
//      doInRange(universe, 2, low, high, item -> System.out.println(item));
      for (int i = 0; i < 10; i++)
          doInRange(universe, 2, low, high, item -> count1++);
      long t2 = System.currentTimeMillis();
      System.out.println("---");
      long t3 = System.currentTimeMillis();
      for (int i = 0; i < 10; i++)
          for (Matter matter : universe) {
            if (MIN <= matter.x && matter.x < MAX && 
                MIN <= matter.y && matter.y < MAX)
                // System.out.println(matter);
                count2++;
            }
      long t4 = System.currentTimeMillis();
      System.out.println("A: "+(t2 - t1)+" "+count1);
      System.out.println("B: "+(t4 - t3)+" "+count2);
      }
    
    public static void main(String[] args) {
      // speedTest();
      rangeTest();
      }
    
    
    }
