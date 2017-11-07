/**
 * 
 */
package com.cc4102.stringDict.experiment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
 *         Clase que permite realizar experimentos.
 */
public class Experiment {

  StopWatch sw = new StopWatch();
  int hashLength = (int) Math.pow(2, 20);
  // Este valor puede variar en funcion de la cantidad de palabras!
  String text1, text2;

  TextCleaner tc = new TextCleaner();
  TextSimilarity ts;

  public void preProcessText(String text1, String text2) {
    this.text1 = tc.clean(text1);
    this.text2 = tc.clean(text2);
  }

  public boolean preProcessTextFile(String path1, String path2) {
    boolean read1 = true, read2 = true;
    try {
      text1 = tc.clean(this.readFile(path1));
    } catch (Exception e) {
      read1 = false;
      e.printStackTrace();
      System.out.println("Wrong path1, could not read any file");
    }
    try {
      text2 = tc.clean(this.readFile(path2));
    } catch (Exception e) {
      read2 = false;
      e.printStackTrace();
      System.out.println("Wrong path2, could not read any file");
    }
    return read1 && read2;

  }

  /**
   * @param path
   * @return
   * @throws IOException
   */
  private String readFile(String path) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line = null;
    BufferedReader bf = new BufferedReader(new FileReader(path));
    while ((line = bf.readLine()) != null) {
      sb.append(line);
    }
    bf.close();
    System.out.println(path + " has a lenght of " + sb.length() + ", which is about 2^"
        + (int) Math.floor(Math.log(sb.length()) / Math.log(2)));
    return sb.toString();
  }

  private StringDictionary insertText(StringDictionary sd, String[] words) {
    for (int i = 0; i < words.length; i++) {
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
    System.out.println("Construction1 took: " + constructionTime1 + " micro seconds");
    sw.reset();

    sw.start();
    sd2 = insertText(sd2, words2);
    sw.stop();
    constructionTime2 = sw.getTime();
    System.out.println("Construction2 took: " + constructionTime2 + " micro seconds");
    sw.reset();

    // agregar parte de busqueda de n/10 palabras
    // TODO

    // agregar parte de busqueda de palabras que no estan
    // TODO

    // TextSimilarity
    ts = new TextSimilarity(text1, text2);
    sw.start();
    ts.setStringDictType(sdType);
    ts.execute();
    similarity = ts.getSimilarity();
    sw.stop();
    similarityTime = sw.getTime();
    System.out.println("Text Similarity took: " + similarityTime + " micro seconds"
        + "\nSimilarity index: " + similarity);
  }

}
