/**
 * 
 */
package com.cc4102.stringDict.LinearProbing;

/**
 * @author Lucas Puebla Silva
 *
 */
public class Node implements INode {
  
  String val;
  INode next;
  
  public Node(String value, INode nextNode) {
    val = value;
    next = nextNode;
  }
  
  public Node(String value) {
    this(value, new NullNode());
  }
  
  public String getVal() {
    return val;
  }

  public INode getNext() {
    return next;
  }


  /**
   * @param elem
   * @return
   */
  public boolean find(String elem) {
    return val.equals(elem) || next.find(elem);
  }

  public int size() {
    return 1 + next.size();
  }
  
  
}
