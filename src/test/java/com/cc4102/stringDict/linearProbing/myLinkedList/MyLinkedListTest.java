/**
 * 
 */
package com.cc4102.stringDict.linearProbing.myLinkedList;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cc4102.stringDict.LinearProbing.INode;
import com.cc4102.stringDict.LinearProbing.MyLinkedList;
import com.cc4102.stringDict.LinearProbing.Node;
import com.cc4102.stringDict.LinearProbing.NullNode;

/**
 * @author Lucas Puebla Silva
 *
 */
public class MyLinkedListTest {

  MyLinkedList ll = new MyLinkedList();
  
  @Test
  public void notNullTest() {
    assertNotNull(ll);
  }
  
  @Test
  public void firstNodeIsNullNode() {
    INode root = ll.getRoot();
    
    assertEquals(root.getClass(), (new NullNode()).getClass());
    
  }

  
  @Test
  public void insertOneElemValueAndNextTest() {
    String elem = "hola";
    
    ll.insert(elem);
    INode tmp = ll.getRoot();
    
    assertEquals(elem, tmp.getVal());
    assertEquals(tmp.getNext().getClass(), (new NullNode()).getClass());
    
  }
  
  @Test
  public void insertOneElementSizeTest() {
    String elem = "hola";
    int size1 = ll.size();
    
    ll.insert(elem);
    
    assertEquals(size1 + 1, ll.size());
  }
  
  @Test
  public void insertManyElementsTest() {
    String[] elems = {"a", "b", "c", "d", "f"};
    int size = ll.size();
    
    for (String elem : elems) {
      ll.insert(elem);
    }
    
    assertEquals(elems.length, ll.size());
  }
  
  @Test
  public void findElemTest() {
    String[] elems = {"a", "b", "c", "d", "f"};
    
    for (String elem : elems) {
      ll.insert(elem);
    }
    
    assertTrue(ll.find("a"));
  }
}
