/**
 * 
 */
package com.cc4102.stringDict;


/**
 * @author Lucas Puebla Silva
 *
 */
public interface StringDictionary {

  int getLength();

  /**
   * @return
   */
  int getSize();
  
  Object getRoot();

  /**
   * @param elem
   */
  void insert(String elem);

  /**
   * @param elem
   * @return
   */
  boolean search(String elem);

  

  

  
}
