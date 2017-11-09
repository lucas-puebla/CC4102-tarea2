/**
 * 
 */
package com.cc4102.stringDict.par;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.cc4102.stringDict.linearProbing.Par;

/**
 * @author Lucas Puebla Silva
 *
 */
public class ParTest {

  String key = "a";

  Par par = new Par(key, 0);

  @Test
  public void notNullTest() {
    assertNotNull(par);
  }

  @Test
  public void firstElementIsStringTest() {
    String tmp = "a";

    assertEquals("Par should be able to return a String key", tmp.getClass(),
        (par.getKey()).getClass());
  }

  @Test
  public void secondElementIsArrayListTest() {
    ArrayList<Integer> tmp = new ArrayList<Integer>();

    assertEquals("Par should be able to return an ArrayList<Integer> values", tmp.getClass(),
        (par.getValues()).getClass());
  }

  @Test
  public void getKeyTest() {
    String tmp = par.getKey();

    assertEquals("Par should be able to return it's key", tmp, key);
  }

  @Test
  public void getValuesWithSingleOccurrenceTest() {
    String[] text = {"hola"};
    Par dict;
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(0);

    dict = new Par(text[0], 0);

    assertEquals("Par should be able to return the position in the text of it's key", expected,
        dict.getValues());
  }

  @Test
  public void getValuesWithMultipleOccurrencesTest() {
    Par word = new Par("hola", 0);
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(0);
    expected.add(1);

    word.addVal(1);

    assertEquals("Par should be able to return the position in the text of it's key", expected,
        word.getValues());
  }
  
  @Test
  public void getNumberOfOccurrencesTest() {
    Par word = new Par("hola", 0);
    word.addVal(1);
    
    assertEquals("Par should be able to return the number of occurrences of a certain key",
        2, word.count());
  }

  @Test
  public void constructorForReinsertionTest() {
    String key = "hola";
    ArrayList<Integer> values = new ArrayList<Integer>();
    values.add(0);
    values.add(1);
    
    Par word = new Par(key, values);
    
    assertEquals("With the second type of constructor, Par should be able to just copy" +
    " it's key", word.getKey(), key);
    assertEquals("With the second type of constructor, Par should be able to just copy" +
        " it's values", word.getValues(), values);
  }
  
}
