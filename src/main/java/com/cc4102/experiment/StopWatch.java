/**
 * 
 */
package com.cc4102.experiment;

/**
 * @author Lucas Puebla Silva
 *
 */
public class StopWatch {
  long initTime, finalTime;

  /**
   * 
   */
  public void start() {
    initTime = System.nanoTime();
  }

  /**
   * 
   */
  public void stop() {
    finalTime = System.nanoTime();
  }

  /**
   * @return time in micros
   */
  public long getTime() {
    return (long) ((finalTime - initTime) / 1e3);
  }

  /**
   * 
   */
  public void reset() {
    initTime = 0;
    finalTime = 0;
  }

}
