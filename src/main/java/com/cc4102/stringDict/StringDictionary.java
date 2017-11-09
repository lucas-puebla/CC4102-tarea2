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
   * Searches within dictionary the key occurrence positions 
   * @param key
   * @return
   */
  ArrayList<Integer> search(String key);

  /**
   * @param word
   * @param pos
   */
  void insert(String word, int pos);

  /**
   * @return
   */
  String[] getKeys();

  /**
   * @param key
   * @return
   */
  int count(String key);

  String getClassStr();
}
