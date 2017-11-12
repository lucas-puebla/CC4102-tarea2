package com.cc4102.experiment;

import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;

import java.util.*;

public class TextSearcher {

  private TernarySearchTree tree;
  StopWatch sw = new StopWatch();

  public ArrayList<String> getRandomWords(double fraction, String[] text) {
    tree = new TernarySearchTree();
    for (String word : text) {
      tree.insert(word, 0, 0);
    }
    List<String> keys = Arrays.asList(tree.getKeys());
    Collections.shuffle(keys);
    ArrayList<String> res = new ArrayList<String>();
    double size = (double) keys.size();
    for (int i = 0; i < size * fraction; i++) {
      res.add(keys.get(i));
    }
    return res;
  }

  public void searchForWords(String[] words, StringDictionary dict) {
    int length = words.length * 10;
    for (int i = 0; i < words.length; i++) {
      sw.start();
      dict.search(words[i], 0);
      sw.stop();
      Logger.logSuccSearch(dict.getClassStr(), length, words[i].length(), sw.getTime());
    }
  }

  public void unsuccessfulSearch(String[] words, StringDictionary dict, int searches) {
    for (int i = 0; i < searches; i++) {
      sw.start();
      dict.search(words[i], 0);
      sw.stop();
      Logger.logUnsuccSearch(dict.getClassStr(), searches * 10, words[i].length(), sw.getTime());
    }
  }
}
