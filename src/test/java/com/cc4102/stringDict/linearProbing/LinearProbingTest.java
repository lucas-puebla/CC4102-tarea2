/**
 * 
 */
package com.cc4102.stringDict.linearProbing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.LinearProbing.MyLinkedList;

/**
 * @author Lucas Puebla Silva
 *
 */
public class LinearProbingTest {

  int hashLength = 50;
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
    for (MyLinkedList elem : tmp) {
      assertNotNull(elem);
    }
  }

  @Test
  public void hashingTableLinkedListsLengthTest() {
    MyLinkedList[] root = (MyLinkedList[]) lpht.getRoot();
    for (MyLinkedList ll : root) {
      assertEquals(0, ll.size());
    }
  }

  @Test
  public void insertLotsOfElementTest() {
    MyLinkedList[] root = (MyLinkedList[]) lpht.getRoot();
    MyLinkedList tmp;
    String elem = "hola";
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

  @Test
  public void successfulSearchTest() {
    fail("not yet implemented");
  }

  @Test
  public void unsuccessfulSearchTest() {
    fail("not yet implemented");
  }
  
  @Test
  public void goodHashingFunctionTest() {
    // Deberia poder verificar la reparticion de los elementos
    // dentro de la estructura para asegurar que esten bien
    // repartidos.
    fail("not yet implemented");
  }

  @Test
  public void fillPercentageBelow40Test() {
    fail("not yet implemented");
  }
  
  
}
