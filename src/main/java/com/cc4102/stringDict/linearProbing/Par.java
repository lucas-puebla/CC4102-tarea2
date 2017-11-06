/**
 * 
 */
package com.cc4102.stringDict.linearProbing;

import java.util.ArrayList;

/**
 * @author Lucas Puebla Silva
 *
 */
public class Par {
  
  private String key;
  private ArrayList<Integer> values;

  public Par(String key, int pos) {
    this.key = key;
    values = new ArrayList<Integer>();
    values.add(pos);
  }
  

  /**
   * Constructor of class Par.
   *
   * <p> </p>
   *
   * @param key
   * @param values
   */
  public Par(String key, ArrayList<Integer> values) {
    this.key = key;
    this.values = values;
  }


  /**
   * @return
   */
  public String getKey() {
    return key;
  }

  /**
   * @return
   */
  public ArrayList<Integer> getValues() {
    return values;
  }


  /**
   * @param i
   */
  public void addVal(int i) {
    values.add(i);
  }


  /**
   * @return
   */
  public int count() {
    return values.size();
  }

}
