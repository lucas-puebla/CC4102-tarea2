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
   * @return
   */
  boolean contains(String elem);

  /**
   * @param string
   * @param i
   */
  void insert(String word, int pos);

  

  

  
}
