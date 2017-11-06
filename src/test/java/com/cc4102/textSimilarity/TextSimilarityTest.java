/**
 * 
 */
package com.cc4102.textSimilarity;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Lucas Puebla Silva
 *
 */
public class TextSimilarityTest {

  TextSimilarity ts = new TextSimilarity();
  
  @Test
  public void notNullTest() {
    assertNotNull("TextSimilarity object should not be null", ts);
  }
  
  

}
