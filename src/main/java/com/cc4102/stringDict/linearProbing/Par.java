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

  /**
   * 
   * Constructor of class Par.
   *
   * <p>
   * This is the normal version of the constructor. It adds the occurrence to the values. 
   * </p>
   *
   * @param key is the key String
   * @param pos is the position of the occurrence of the key
   */
  public Par(String key, int pos) {
    this.key = key;
    values = new ArrayList<Integer>();
    values.add(pos);
  }
  

  /**
   * Constructor of class Par.
   *
   * <p>
   * This version of the constructor is to be used only when rehashing.
   * </p>
   *
   * @param key is the key
   * @param values are the positions of the key occurrences
   */
  public Par(String key, ArrayList<Integer> values) {
    this.key = key;
    this.values = values;
  }


  /**
   * Getter for the key instance variable.
   * @return key
   */
  public String getKey() {
    return key;
  }

  /**
   * Getter for the values instance variable.
   * @return values
   */
  public ArrayList<Integer> getValues() {
    return values;
  }


  /**
   * Adds the new occurrence position value to values.
   * <p>
   * Used only when the key already exists in the hash.
   * </p>
   * @param newPos is the new position of the occurrence
   */
  public void addVal(int newPos) {
    values.add(newPos);
  }


  /**
   * Gives the total number of occurrences of the key.
   * @return the size of values
   */
  public int count() {
    return values.size();
  }

}
