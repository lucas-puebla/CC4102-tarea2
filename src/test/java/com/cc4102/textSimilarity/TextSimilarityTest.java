/**
 * 
 */
package com.cc4102.textSimilarity;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.PatriciaTree;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;

/**
 * @author Lucas Puebla Silva
 *
 */
public class TextSimilarityTest {

  String text1 = "hola buenas chao", text2 = "hola hola chao";
  TextSimilarity ts = new TextSimilarity(text1, text2);
  
  @Test
  public void notNullTest() {
    assertNotNull("TextSimilarity object should not be null", ts);
  }
  
  @Test
  public void getText1Test() {
    assertEquals("TextSimilarity should be able to access and return it's text1 instance " +
  " variables", text1, ts.getText1());
  }
  
  @Test
  public void getText2Test() {
    assertEquals("TextSimilarity should be able to access and return it's text2 instance " +
  " variables", text2, ts.getText2());
  }
  
  @Test
  public void getJoinedTextTest() {
    assertEquals("TextSimilarity should be able to access and return a concatText instance" + 
  " variable, that corresponds to the concatenation of text1 and text2",
  text1 + text2, ts.getConcatText());
  }
  
  @Test
  public void setStringDictionaryTypeToLinearProbingTest() {
    StringDictionary lpht = new LinearProbingHashingTree(8);
    ts.setStringDictType(lpht);
    
    assertEquals("TextSimilarity should be able to set it's StringDictionary type " +
    "as LinearProbingHashTree",
        lpht.getClass(), ts.getStringDictType().getClass());
  }
  
  @Test
  public void setStringDictionaryTypeToTernarySearchTest() {
    StringDictionary tst = new TernarySearchTree();
    ts.setStringDictType(tst);
    
    assertEquals("TextSimilarity should be able to set it's StringDictionary type " +
    "as TernarySearchTree",
        tst.getClass(), ts.getStringDictType().getClass());
  }
  
  @Test
  public void setStringDictionaryTypeToPatriciaTreeTest() {
    StringDictionary pt = new PatriciaTree();
    ts.setStringDictType(pt);
    
    assertEquals("TextSimilarity should be able to set it's StringDictionary type " +
    "as PatriciaTree",
        pt.getClass(), ts.getStringDictType().getClass());
  }
  
  @Test
  public void executeLinearProbingTest() {
    double error = 0.1;
    StringDictionary lpht = new LinearProbingHashingTree(8);
    ts.setStringDictType(lpht);
    
    // TODO
    double expected = (Math.abs(1 - 2) + Math.abs(1 - 0) + Math.abs(1 - 1)) / 2;
    
    ts.execute();
    
    assertEquals("TextSimilarity should be able to calculate the similarity between texts " +
        "and return it when calling the getSimilarity() method",
        expected, ts.getSimilarity(), error);
  }
  
  @Test
  public void executeTernarySearchTest() {
    double error = 0.1;
    StringDictionary lpht = new TernarySearchTree();
    ts.setStringDictType(lpht);
    
    // TODO
    double expected = (Math.abs(1 - 2) + Math.abs(1 - 0) + Math.abs(1 - 1)) / 2;
    
    ts.execute();
    
    assertEquals("TextSimilarity should be able to calculate the similarity between texts " +
        "and return it when calling the getSimilarity() method",
        expected, ts.getSimilarity(), error);
  }
  
  @Test
  public void executePatriciaTest() {
    double error = 0.1;
    StringDictionary lpht = new PatriciaTree();
    ts.setStringDictType(lpht);
    
    // TODO
    double expected = (Math.abs(1 - 2) + Math.abs(1 - 0) + Math.abs(1 - 1)) / 2;
    
    ts.execute();
    
    assertEquals("TextSimilarity should be able to calculate the similarity between texts " +
        "and return it when calling the getSimilarity() method",
        expected, ts.getSimilarity(), error);
  }
}
