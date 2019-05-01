package dk.cphbusiness.algorithms.utilities;

import java.util.function.BiConsumer;

public class Timer implements AutoCloseable {
  private long startTime;
  private final BiConsumer<Long, Long> action;
  
  public Timer(BiConsumer<Long, Long> action) {
    this.action = action;
    startTime = System.currentTimeMillis();
    }
  
  @Override
  public void close() throws Exception {
    long endTime = System.currentTimeMillis();
    action.accept(startTime, endTime);
    }
  
  }
