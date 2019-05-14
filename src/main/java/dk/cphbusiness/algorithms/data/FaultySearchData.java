package dk.cphbusiness.algorithms.data;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FaultySearchData {
  private static final Random random = new Random(7);
  
  public static void createData(
      PrintStream out,
      long size, 
      double max, 
      long... faultArray
      ) {
    Set<Long> faults = new HashSet<>();
    for (long fault : faultArray) faults.add(fault);
    double number = 0.0;
    out.println(number);
    for (long index = 1; index < size; index++) {
      double delta = random.nextDouble()*2*(max - number)/(size - index);
      number += delta;
      if (faults.contains(index)) out.println(-random.nextDouble()*2*number);
      else out.println(number);
      }
    }

  public static void main(String[] args) {
    createData(System.out, 16, 1000.0, 2, 8);
    }
  

  }
