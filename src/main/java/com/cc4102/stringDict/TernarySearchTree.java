package com.cc4102.stringDict;

import java.util.ArrayList;

public class TernarySearchTree implements StringDictionary {
  private TSTreeNode root;

  public TernarySearchTree() {
    root = new TSTreeNode();
  }

  public ArrayList<Integer> search(String key, int text) {
    return root.search(key + "$", text);
  }

  public void insert(String word, int pos, int text) {
    root.insert(word + "$", pos, text);
  }

  public String[] getKeys() {
    ArrayList<String> keys = new ArrayList<String>();
    root.getKeys("", keys);
    return keys.toArray(new String[keys.size()]);
  }

  public String getClassStr() {
    return "TernarySearchTree";
  }

  public double getSimilarity() {
    double[] sums = new double[2];
    root.getSimilarity(sums);
    return 1 - sums[0] / sums[1];
  }

  public String toString() {
    return "root: " + root.toString();
  }
}
