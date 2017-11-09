/**
 * 
 */
package com.cc4102.experiment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.cc4102.stringDict.StringDictionary;

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
  String spanishWords;

  TextCleaner tc = new TextCleaner();
  TextSimilarity ts;
  TextSearcher searcher = new TextSearcher();
  String[] wordsToSearch;

  public void preProcessText(String text1, String text2) {
    this.text1 = tc.clean(text1);
    this.text2 = tc.clean(text2);
  }

  public boolean preProcessTextFile(String path1, String path2, String path3) {
    boolean read1 = true, read2 = true, read3 = true;
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
    try {
      spanishWords = tc.clean(this.readFile(path3));
    } catch (Exception e) {
      read3 = false;
      e.printStackTrace();
      System.out.println("Wrong path3, could not read any file");
    }

    // definir palabras que se van a buscar para todos los textos
    ArrayList<String> wordsToSearch = searcher.getRandomWords(1.0 / 10.0, text1.split(" "));
    this.wordsToSearch = wordsToSearch.toArray(new String[wordsToSearch.size()]);

    return read1 && read2 && read3;

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
    double similarity = 0;
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

    // movido a preprocess para que sea igual para todos.
    // ArrayList<String> wordsToSearch = searcher.getRandomWords(1.0 / 10.0, words1);
    searcher.searchForWords(wordsToSearch, sd1);

    searcher.unsuccessfulSearch(spanishWords.split(" "), sd1, wordsToSearch.length);

    // TextSimilarity
    ts = new TextSimilarity(text1, text2);
    sw.start();
    ts.setStringDictType(sdType);
    ts.execute();
    similarity = ts.getSimilarity();
    sw.stop();
    similarityTime = sw.getTime();
    Logger.logConstructionTime(sd1.getClassStr(), "Construction", words1.length, constructionTime1);
    Logger.logConstructionTime(sd1.getClassStr(), "Construction", words2.length, constructionTime2);
    Logger.logConstructionTime(sd1.getClassStr(), "Similarity", words2.length, similarityTime);
    System.out.println("Text Similarity took: " + similarityTime + " micro seconds"
        + "\nSimilarity index: " + similarity);
  }

}
