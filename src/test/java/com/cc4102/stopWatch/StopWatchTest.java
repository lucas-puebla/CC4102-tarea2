/**
 * 
 */
package com.cc4102.stopWatch;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import com.cc4102.experiment.StopWatch;
import org.junit.Test;

/**
 * @author Lucas Puebla Silva
 *
 */
public class StopWatchTest {
  StopWatch sw = new StopWatch();

  @Test
  public void notNullTest() {
    assertNotNull("Should not be null", sw);
  }

  @Test
  public void startStopTest() {
    long error = 1;
    long initTime, finalTime, time;
    initTime = System.nanoTime();
    sw.start();
    sw.stop();
    finalTime = System.nanoTime();
    
    assertTrue("Stopwatch should be able to start and stop and return the time in that interval",
        (finalTime - initTime) > sw.getTime());
  }
  
  @Test
  public void resetTest() throws InterruptedException {
    long time1, time2;
    sw.start();
    TimeUnit.MICROSECONDS.sleep(100);
    sw.stop();
    time1 = sw.getTime();
    
    sw.reset();
    time2 = sw.getTime();
    
    assertNotEquals("After resetting the stopwatch, the value should change",
        time1, time2);
    assertEquals("After resetting, the value should be 0",
        0, time2);
  }
}
