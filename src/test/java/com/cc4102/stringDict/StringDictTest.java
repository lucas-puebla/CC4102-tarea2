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
  
  int hashLength = 10;
  StringDictionary lpht = new LinearProbingHashingTree(hashLength);
  
  @Test
  public void notNullTest() {
    assertNotNull(pt);
    assertNotNull(tst);
    assertNotNull(lpht);
  }
  
   
}
