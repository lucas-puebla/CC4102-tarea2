/**
 * 
 */
package com.cc4102.stringDict.linearProbing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.LinearProbing.MyLinkedList;
import com.cc4102.stringDict.LinearProbing.Node;

/**
 * @author Lucas Puebla Silva
 *
 */
public class LinearProbingTest {

  int hashLength = 10;
  StringDictionary lpht = new LinearProbingHashingTree(hashLength);

  @Test
  public void hashingTableLengthTest() {
    int expected = hashLength;
    int actual = lpht.getLength();
    assertEquals(expected, actual);
  }

  @Test
  public void hashingTableLinkedListNotNullTest() {
    MyLinkedList[] tmp = (MyLinkedList[]) lpht.getRoot();
    for (int i = 0; i < hashLength; i++) {
      assertNotNull(tmp[i]);
    }
  }

  @Test
  public void insertElementTest() {
    MyLinkedList[] root = (MyLinkedList[]) lpht.getRoot();
    MyLinkedList tmp;
    String elem = "wea";
    boolean isThere = false;

    lpht.insert(elem);

    for (int i = 0; i < hashLength; i++) {
      tmp = root[i];
      if (tmp.find(elem)) {
        isThere = true;
        break;
      }

    }
    assertTrue(isThere);
  }
}
