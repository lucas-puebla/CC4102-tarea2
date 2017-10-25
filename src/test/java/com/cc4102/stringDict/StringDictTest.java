/**
 * 
 */
package com.cc4102.stringDict;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Lucas Puebla Silva
 *
 */
public class StringDictTest {

  StringDictionary pt = new PatriciaTree();
  StringDictionary tst = new TernarySearchTree();
  StringDictionary lpht = new LinearProbingHashingTree();
  
  @Test
  public void notNullTest() {
    assertNotNull(pt);
    assertNotNull(tst);
    assertNotNull(lpht);
  }
  
   
}
