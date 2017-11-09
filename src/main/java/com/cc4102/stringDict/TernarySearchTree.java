package com.cc4102.stringDict;

import java.util.ArrayList;

public class TernarySearchTree implements StringDictionary {
  private TSTreeNode root;

  public TernarySearchTree() {
    root = new TSTreeNode();
  }

  @Override
  public ArrayList<Integer> search(String key) {
    return root.search(key + "$");
  }

  @Override
  public void insert(String word, int pos) {
    root.insert(word + "$", pos);
  }

  @Override
  public String[] getKeys() {
    ArrayList<String> keys = new ArrayList<String>();
    root.addKeys("", keys);
    return keys.toArray(new String[keys.size()]);
  }

  @Override
  public int count(String key) {
    return root.count(key + "$");
  }

  @Override
  public String getClassStr() {
    return "TernarySearchTree";
  }

  @Override
  public String toString() {
    return "root: " + root.toString();
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public Object getRoot() {
    return root;
  }
}
