/**
 * 
 */
package com.cc4102.stringDict.experiment;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.PatriciaTree;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;
import com.cc4102.textCleaner.TextCleaner;

/**
 * @author Lucas Puebla Silva
 *
 * Clase que permite realizar experimentos.
 */
public class Experiment {

  int hashLength = (int) Math.pow(2, 14);
  // Este valor puede variar en funcion de la cantidad de palabras!
  String text;
  
  StringDictionary pt = new PatriciaTree();
  StringDictionary tst = new TernarySearchTree();
  StringDictionary lpht = new LinearProbingHashingTree(hashLength);
  
  TextCleaner tc = new TextCleaner();
  
  public void preProcessText(String text) {
    this.text = tc.clean(text);
  }
  
  public void runLinearProbingTest() {
    String[] words = text.split(" ");
    
    for(int i = 0 ; i < words.length ; i++) {
      lpht.insert(words[i], i);
    }
    
    
  }
  
}
