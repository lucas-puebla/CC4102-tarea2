package com.cc4102.stringDict;

import java.util.ArrayList;

public class TSTreeNode {
  private char key;
  private ArrayList<Integer> values;
  private TSTreeNode left, right, son;
  private boolean empty;

  public TSTreeNode() {
    empty = true;
    key = 0;
    values = null;
    left = right = son = null;
  }

  private void initNode(char key) {
    empty = false;

    this.key = key;
    values = null;
    left = new TSTreeNode();
    right = new TSTreeNode();
    son = new TSTreeNode();
  }

  private void initLeaf(char key, int value) {
    initNode(key);
    values = new ArrayList<>();
    values.add(value);
  }

  public ArrayList<Integer> search(String s) {
    if (s.length() == 0)
      return null;

    char first = s.charAt(0);

    if (s.length() == 1) {
      if (key == first)
        return values;
      return null;
    }

    if (first == key)
      return son.search(s.substring(1));
    else if (first < key)
      return left.search(s);
    else
      return right.search(s);
  }

  public void insert(String s, int value) {

    char first = s.charAt(0);
    if (isEmpty()) {
      if (s.length() == 1) {
        initLeaf(first, value);
        return;
      } else {
        initNode(first);
      }
    }

    if (first == key) {
      if (s.length() == 1) {
        values.add(value);
        return;
      }
      son.insert(s.substring(1), value);
    } else if (first < key) {
      left.insert(s, value);
    } else {
      right.insert(s, value);
    }
  }

  private boolean isEmpty() {
    return empty;
  }

  @Override
  public String toString() {
    return toString("").trim();
  }

  private String toString(String pre) {
    StringBuilder sb = new StringBuilder();

    sb.append(key);
    if (values != null) {
      sb.append(" ");
      sb.append(values);
    }
    sb.append("\n");

    String newpre = pre + " ";
    if (!son.isEmpty()) {
      sb.append(newpre);
      sb.append("son: ");
      sb.append(son.toString(newpre));
    }
    if (!left.isEmpty()) {
      sb.append(newpre);
      sb.append("left: ");
      sb.append(left.toString(newpre));
    }
    if (!right.isEmpty()) {
      sb.append(newpre);
      sb.append("right: ");
      sb.append(right.toString(newpre));
    }
    return sb.toString();
  }
}
