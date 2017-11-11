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
  ArrayList<Integer> search(String key, int text);

  /**
   * @param word
   * @param pos
   */
  void insert(String word, int pos, int text);

  /**
   * @return
   */
  String[] getKeys();

  String getClassStr();

  /**
   * @return
   */
  double getSimilarity();
}
