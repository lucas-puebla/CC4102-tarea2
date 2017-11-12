/**
 * 
 */
package com.cc4102.stringDict.linearProbing;

import java.util.ArrayList;

/**
 * @author Lucas Puebla Silva
 *
 */
public class Trio {
  
  private String key;
  private Object[] values;

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
  @SuppressWarnings("unchecked")
  public Trio(String key, int pos, int text) {
    this.key = key;
    values = new Object[2];
    values[0] = new ArrayList<Integer>();
    values[1] = new ArrayList<Integer>();
    ((ArrayList<Integer>) values[text]).add(pos);
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
  public Trio(String key, Object[] values) {
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
  @SuppressWarnings("unchecked")
  public ArrayList<Integer> getValues(int text) {
    return (ArrayList<Integer>) values[text];
  }
  
  public Object[] getValues() {
    return values;
  }


  /**
   * Adds the new occurrence position value to values.
   * <p>
   * Used only when the key already exists in the hash.
   * </p>
   * @param newPos is the new position of the occurrence
   */
  @SuppressWarnings("unchecked")
  public void addVal(int newPos, int text) {
    ((ArrayList<Integer>) values[text]).add(newPos);
  }


  /**
   * Gives the total number of occurrences of the key.
   * @return the size of values
   */
  @SuppressWarnings("unchecked")
  public int count(int text) {
    return ((ArrayList<Integer>) values[text]).size();
  }

}
