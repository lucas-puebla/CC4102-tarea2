/**
 * 
 */
package com.cc4102.stringDict.LinearProbing;

/**
 * @author Lucas Puebla Silva
 *
 */
public class MyLinkedList {

  private INode root;
  
  public MyLinkedList() {
    root = new NullNode();
  }
  
  public INode getRoot() {
    return root;
  }
  
  public void insert(String elem) {
    root = new Node(elem, root);
  }
  
  public int size() {
    return root.size();
  }
  
  public boolean find(String elem) {
    return root.find(elem);
  }
  
}
