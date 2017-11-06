/**
 * 
 */
package com.cc4102.stringDict;

import java.util.ArrayList;

/**
 * @author Lucas Puebla Silva
 *
 */
public interface StringDictionary {

  /**
   * Gives the total length of the object.
   * @return
   */
  int getLength();

  /**
   * Gives the occupied size of the object.
   * @return
   */
  int getSize();
  
  Object getRoot();


  /**
   * Searches within dictionary the key occurrence positions 
   * @param key
   * @return
   */
  ArrayList<Integer> search(String key);

  /**
   * @param string
   * @param i
   */
  void insert(String word, int pos);

  

  

  
}
