/**
 * 
 */
package com.cc4102.stringDict.experiment;

import com.cc4102.stopWatch.StopWatch;
import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.PatriciaTree;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;
import com.cc4102.textCleaner.TextCleaner;
import com.cc4102.textSimilarity.TextSimilarity;

/**
 * @author Lucas Puebla Silva
 *
 * Clase que permite realizar experimentos.
 */
public class Experiment {

  StopWatch sw = new StopWatch();
  int hashLength = (int) Math.pow(2, 14);
  // Este valor puede variar en funcion de la cantidad de palabras!
  String text1, text2;
  
  
  StringDictionary pt = new PatriciaTree();
  StringDictionary tst = new TernarySearchTree();
  StringDictionary lpht = new LinearProbingHashingTree(hashLength);
  
  TextCleaner tc = new TextCleaner();
  TextSimilarity ts;
  
  public void preProcessText(String text1, String text2) {
    this.text1 = tc.clean(text1);
    this.text2 = tc.clean(text2);
  }
  
  private StringDictionary insertText(StringDictionary sd, String[] words) {
    for(int i = 0 ; i < words.length ; i++) {
      sd.insert(words[i], i);
    }
    
    return sd;
  }
  
  public void runTest(StringDictionary sd1, StringDictionary sd2, StringDictionary sdType) {
    long constructionTime1, succesfulSearchTime1, unsuccesfulSearchTime1, similarityTime,
        constructionTime2, succesfulSearchTime2, unsuccesfulSearchTime2;
    double similarity;
    String[] words1 = text1.split(" ");
    String[] words2 = text2.split(" ");
    
    // construction
    sw.start();
    sd1 = insertText(sd1, words1);
    sw.stop();
    constructionTime1 = sw.getTime();
    sw.reset();
    
    sw.start();
    sd2 = insertText(sd2, words2);
    sw.stop();
    constructionTime2 = sw.getTime();
    sw.reset();    
    
    // agregar parte de busqueda de n/10 palabras
    
    // agregar parte de busqueda de palabras que no estan
    
    // TextSimilarity
    ts = new TextSimilarity(text1, text2);
    sw.start();
    ts.setStringDictType(sdType);
    ts.execute();
    similarity = ts.getSimilarity();
    sw.stop();
    similarityTime = sw.getTime();
    
    System.out.println("construction1: " + constructionTime1 +
                        "\nconstruction2: " + constructionTime2 +
                        //"\nsuccesfulTime1: " + succesfulSearchTime1 + 
                        //"\nsuccesfulTime2: " + succesfulSearchTime2 +
                        //"\nunsuccesfulTime1: " + unsuccesfulSearchTime1 +
                        //"\nunsuccesfulTime1: " + unsuccesfulSearchTime2 +
                        "\nsimilarity: " + similarity +
                        "\nsimilarityTime: " + similarityTime);
  }
  
}
