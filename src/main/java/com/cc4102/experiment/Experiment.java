/**
 *
 */
package com.cc4102.experiment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;

/**
 * @author Lucas Puebla Silva
 *         <p>
 *         Clase que permite realizar experimentos.
 */
public class Experiment {

  private String text1, text2;
  private String spanishWords;

  private StopWatch sw = new StopWatch();
  private TextCleaner tc = new TextCleaner();
  private TextSearcher searcher = new TextSearcher();
  private String[] wordsToSearch;

  public void preProcessText(String text1, String text2) {
    this.text1 = tc.clean(text1);
    this.text2 = tc.clean(text2);
  }

  public boolean preProcessTextFile(String path1, String path2, String path3) {
    boolean read1 = true, read2 = true, read3 = true;
    try {
      text1 = tc.clean(readFile(path1));
    } catch (Exception e) {
      read1 = false;
      e.printStackTrace();
      System.out.println("Wrong path1, could not read any file");
    }
    try {
      text2 = tc.clean(readFile(path2));
    } catch (Exception e) {
      read2 = false;
      e.printStackTrace();
      System.out.println("Wrong path2, could not read any file");
    }
    try {
      spanishWords = tc.clean(readFile(path3));
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

  private void insertText(StringDictionary sd, String[] words, int text) {
    for (int i = 0; i < words.length; i++) {
      sd.insert(words[i], i, text);
    }
  }

  public void runTest(StringDictionary dict) {
    long constructionTime1, constructionTime2, similarityTime;
    double similarity;
    String[] words1 = text1.split(" ");
    String[] words2 = text2.split(" ");

    // construction
    sw.start();
    insertText(dict, words1, 0);
    sw.stop();
    constructionTime1 = sw.getTime();
    System.out.println("Construction1 took: " + constructionTime1 + " micro seconds");

    // busquedas exitosas
    sw.start();
    searcher.searchForWords(wordsToSearch, dict);
    sw.stop();
    System.out.println("succesfull searches took: " + sw.getTime()
        + " micro seconds in " + wordsToSearch.length + " words");

    // busquedas infructuosas
    sw.start();
    searcher.unsuccessfulSearch(spanishWords.split(" "), dict, wordsToSearch.length);
    sw.stop();
    System.out.println("unsuccesfull searches took: " + sw.getTime() + " micro seconds");

    // agregar segundo texto para text similarity
    sw.start();
    insertText(dict, words2, 1);
    sw.stop();
    constructionTime2 = sw.getTime();
    System.out.println("Construction2 took: " + constructionTime2 + " micro seconds");

    // text similarity
    sw.start();
    similarity = dict.getSimilarity();
    sw.stop();
    similarityTime = sw.getTime();

    // registrar tiempos
    Logger.logConstructionTime(dict.getClassStr(), "Construction", words1.length, constructionTime1);
    Logger.logConstructionTime(dict.getClassStr(), "Similarity", words1.length, similarityTime);
    System.out.println("Text Similarity took: " + similarityTime + " micro seconds"
        + "\nSimilarity index: " + similarity);
  }

}
