/**
 * 
 */
package com.cc4102.experiment;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.PatriciaTree;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;
import com.cc4102.stringDict.experiment.Experiment;

/**
 * @author Lucas Puebla Silva
 *
 */
public class Main {

  
  public static void main(String[] args) {
    Experiment exp = new Experiment();
    
    String text1 = "habia una vez un pato imaginario que se dio cuenta que la vida no tiene sentido";
    String text2 = "habia una vez un lobo feroz que no tenia ganas de vivir la vida sin sentido";
    
    // lpht
    StringDictionary lpht1, lpht2;
    lpht1 = new LinearProbingHashingTree(8);
    lpht2 = new LinearProbingHashingTree(8);
    
    // pt
    StringDictionary pt1, pt2;
    pt1 = new PatriciaTree();
    pt2 = new PatriciaTree();
    
    // tst
    StringDictionary tst1, tst2;
    tst1 = new TernarySearchTree();
    tst2 = new TernarySearchTree();
    
    exp.preProcessText(text1, text2);
    
    exp.runTest(lpht1, lpht2, new LinearProbingHashingTree(8));
    
    exp.runTest(pt1, pt2, new PatriciaTree());
    
    exp.runTest(tst1, tst2, new TernarySearchTree());
  }

}
