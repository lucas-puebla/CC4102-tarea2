package com.cc4102.stringDict;

import java.util.ArrayList;

class TSTreeNode {
  private char key;
  private ArrayList<Integer> values;
  private TSTreeNode left, right, son;
  private boolean empty;

  TSTreeNode() {
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
    values = new ArrayList<Integer>();
    values.add(value);
  }

  public ArrayList<Integer> search(String s) {
    if (empty) // not found
      return new ArrayList<Integer>(); // empty

    char first = s.charAt(0);

    if (first == key) {
      // word found
      if (s.length() == 1)
        return values;

      // recursive calls
      return son.search(s.substring(1));
    } else if (first < key) {
      return left.search(s);
    } else {
      return right.search(s);
    }
  }

  public void insert(String word, int pos) {
    char first = word.charAt(0);

    // initialize empty nodes
    if (empty) {
      if (word.length() == 1) {
        initLeaf(first, pos);
        return;
      } else {
        initNode(first);
      }
    }

    if (first == key) {
      // word already in
      if (word.length() == 1) {
        values.add(pos);
        return;
      }
      // recursive calls
      son.insert(word.substring(1), pos);
    } else if (first < key) {
      left.insert(word, pos);
    } else {
      right.insert(word, pos);
    }
  }

  public int getSize() {
    if (empty)
      return 0;
    return 1 + son.getSize() + left.getSize() + right.getSize();
  }

  void addKeys(String prefix, ArrayList<String> keys) {
    if (empty)
      return;

    if (values != null)
      keys.add(prefix);

    left.addKeys(prefix, keys);
    son.addKeys(prefix + String.valueOf(key), keys);
    right.addKeys(prefix, keys);
  }

  public int count(String key) {
    return search(key).size();
  }

  @Override
  /**
   * este metodo se usa solo para debuggear
   */
  public String toString() {
    return toString("").trim();
  }

  /**
   * este metodo se usa solo para debuggear
   */
  private String toString(String pre) {
    StringBuilder sb = new StringBuilder();

    sb.append(key);
    if (values != null) {
      sb.append(" ");
      sb.append(values);
    }
    sb.append("\n");

    String newpre = pre + " ";
    if (!son.empty) {
      sb.append(newpre);
      sb.append("son: ");
      sb.append(son.toString(newpre));
    }
    if (!left.empty) {
      sb.append(newpre);
      sb.append("left: ");
      sb.append(left.toString(newpre));
    }
    if (!right.empty) {
      sb.append(newpre);
      sb.append("right: ");
      sb.append(right.toString(newpre));
    }
    return sb.toString();
  }
}
