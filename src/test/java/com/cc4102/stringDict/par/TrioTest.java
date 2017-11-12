/**
 * 
 */
package com.cc4102.stringDict.par;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.cc4102.stringDict.linearProbing.Trio;

/**
 * @author Lucas Puebla Silva
 *
 */
public class TrioTest {

  String key = "a";

  Trio trio = new Trio(key, 0, 0);

  @Test
  public void notNullTest() {
    assertNotNull(trio);
  }

  @Test
  public void firstElementIsStringTest() {
    String tmp = "a";

    assertEquals("Par should be able to return a String key", tmp.getClass(),
        (trio.getKey()).getClass());
  }

  @Test
  public void secondElementIsObjectArrayTest() {
    Object[] tmp = new Object[2];

    assertEquals("Par should be able to return an Object[] values", tmp.getClass(),
        (trio.getValues()).getClass());
  }
  
  @Test
  public void valuesElementsAreArrayListTest() {
    ArrayList<Integer> tmp = new ArrayList<Integer>();

    assertEquals("Par should be able to return an ArrayList<Integer> values", tmp.getClass(),
        (trio.getValues(0)).getClass());
    assertEquals("Par should be able to return an ArrayList<Integer> values", tmp.getClass(),
        (trio.getValues(1)).getClass());
  }

  @Test
  public void getKeyTest() {
    String tmp = trio.getKey();

    assertEquals("Par should be able to return it's key", tmp, key);
  }

  private void getValuesWithSingleOccurrence(int text) {
    String[] texto = {"hola"};
    Trio dict;
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(0);

    dict = new Trio(texto[0], 0, text);

    assertEquals("Par should be able to return the position in the text of it's key", expected,
        dict.getValues(text));
  }
  
  @Test
  public void getValuesWithSingleOccurrenceTest() {
    getValuesWithSingleOccurrence(0);
    getValuesWithSingleOccurrence(1);
  }

  private void getValuesWithMultipleOccurrences(int text) {
    Trio word = new Trio("hola", 0, text);
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(0);
    expected.add(1);

    word.addVal(1, text);

    assertEquals("Par should be able to return the position in the text of it's key", expected,
        word.getValues(text));
  }
  
  @Test
  public void getValuesWithMultipleOccurrencesTest() {
    getValuesWithMultipleOccurrences(0);
    getValuesWithMultipleOccurrences(1);
  }
  
  private void getNumberOfOccurrences(int text) {
    Trio word = new Trio("hola", 0, text);
    word.addVal(1, text);
    
    assertEquals("Par should be able to return the number of occurrences of a certain key",
        2, word.count(text));
  }
  
  @Test
  public void getNumberOfOccurrencesTest() {
    getNumberOfOccurrences(0);
    getNumberOfOccurrences(1);
  }

  @Test
  public void constructorForReinsertionTest() {
    ArrayList<Integer> val1 = new ArrayList<Integer>();
    val1.add(0);
    ArrayList<Integer> val2 = new ArrayList<Integer>();
    val1.add(1);
    
    String key = "hola";
    Object[] values = new Object[2];
    
    values[0] = val1;
    values[1] = val2;
    
    Trio word = new Trio(key, values);
    
    assertEquals("With the second type of constructor, Par should be able to just copy" +
    " it's key", word.getKey(), key);
    assertEquals("With the second type of constructor, Par should be able to just copy" +
        " it's values", word.getValues(0), val1);
    assertEquals("With the second type of constructor, Par should be able to just copy" +
        " it's values", word.getValues(1), val2);
  }
  
}
