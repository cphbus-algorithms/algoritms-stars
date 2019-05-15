package dk.cphbusiness.algorithms.data;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FaultySearchData {
  private static final Random random = new Random(7);
  
  /**
   * Create sorted data with selected faulty data points.
   * Writes double values in ascending order to a stream.
   * 
   * @param out the stream to write to
   * @param size number of data points
   * @param max maximum value
   * @param faultArray array of indexes for faulty points
   */
  public static void createData(
      PrintStream out,
      long size, 
      double max, 
      long... faultArray
      ) {
    Set<Long> faults = new HashSet<>();
    for (long fault : faultArray) faults.add(fault);
    double number = 0.0;
    for (long index = 0; index < size; index++) {
      double delta = random.nextDouble()*2*(max - number)/(size - index);
      number += delta;
      if (faults.contains(index)) out.println(-random.nextDouble()*2*number);
      else out.println(number);
      }
    }

  public static void main(String[] args) throws FileNotFoundException {
    createData(System.out, 16, 1000.0, 2, 8);
    PrintStream file = new PrintStream("/Users/AKA/tmp/faulty-1024.txt");
    createData(file, 1024, 1_000_000.0, 256, 512, 734);
    }

  }
