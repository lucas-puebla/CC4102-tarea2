/**
 * 
 */
package com.cc4102.stringDict.LinearProbing;

/**
 * @author Lucas Puebla Silva
 *
 */
public interface INode {
  
  String getVal();
  
  INode getNext();
  
  boolean find(String elem);

  int size();
  
}
