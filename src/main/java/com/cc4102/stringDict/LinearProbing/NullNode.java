/**
 * 
 */
package com.cc4102.stringDict.LinearProbing;

/**
 * @author Lucas Puebla Silva
 *
 */
public class NullNode implements INode {
    
  public NullNode() {
    
  }

  public String getVal() {
    return null;
  }

  public INode getNext() {
    return null;
  }

  public boolean find(String elem) {
    return false;
  }

  public int size() {
    return 0;
  }


}
