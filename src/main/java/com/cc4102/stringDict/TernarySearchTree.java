package com.cc4102.stringDict;

import java.util.ArrayList;

public class TernarySearchTree implements StringDictionary {
  private TSTreeNode root;

  public TernarySearchTree() {
    root = new TSTreeNode();
  }

  public ArrayList<Integer> search(String key) {
    return root.search(key + "$");
  }

  public void insert(String word, int pos) {
    root.insert(word + "$", pos);
  }

  public String[] getKeys() {
    ArrayList<String> keys = new ArrayList<String>();
    root.addKeys("", keys);
    return keys.toArray(new String[keys.size()]);
  }

  public int count(String key) {
    return root.count(key + "$");
  }

  public String getClassStr() {
    return "TernarySearchTree";
  }

  public String toString() {
    return "root: " + root.toString();
  }

  public int getLength() {
    return 0;
  }

  public int getSize() {
    return 0;
  }

  public Object getRoot() {
    return root;
  }
}
