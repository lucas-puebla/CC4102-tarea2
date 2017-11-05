package com.cc4102.stringDict;

public class TernarySearchTree implements StringDictionary {
  private TSTreeNode root;

  public TSTree() {
    root = new TSTreeNode();
  }

  @Override
  public boolean search(String s) {
    return root.search(s + "$") != null;
  }

  @Override
  public void insert(String s, int value) {
    root.insert(s + "$", value);
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
