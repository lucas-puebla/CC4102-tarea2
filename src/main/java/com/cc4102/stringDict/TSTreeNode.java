package com.cc4102.stringDict;

import java.util.ArrayList;

class TSTreeNode {

  private char key;
  private ArrayList<Integer> values1;
  private ArrayList<Integer> values2;
  private TSTreeNode left, right, son;
  private boolean empty;

  TSTreeNode() {
    empty = true;
    key = 0;
    values1 = values2 = null;
    left = right = son = null;
  }

  private void initNode(char key) {
    empty = false;

    this.key = key;
    left = new TSTreeNode();
    right = new TSTreeNode();
    son = new TSTreeNode();
  }

  private void initLeaf(char key, int value, int text) {
    int[] a = new int[2];
    initNode(key);
    values1 = new ArrayList<Integer>();
    values2 = new ArrayList<Integer>();
    addValue(value, text);
  }

  public ArrayList<Integer> search(String word, int text) {
    if (empty) // not found
      return new ArrayList<Integer>(); // empty

    char first = word.charAt(0);

    if (first == key) {
      // word found
      if (word.length() == 1)
        return getValues(text);

      // recursive calls
      return son.search(word.substring(1), text);
    } else if (first < key) {
      return left.search(word, text);
    } else {
      return right.search(word, text);
    }
  }

  private ArrayList<Integer> getValues(int text) {
    if (text == 0)
      return values1;
    else
      return values2;
  }

  public void insert(String word, int pos, int text) {
    char first = word.charAt(0);

    // initialize empty nodes
    if (empty) {
      if (word.length() == 1) {
        initLeaf(first, pos, text);
        return;
      } else {
        initNode(first);
      }
    }

    if (first == key) {
      // word already in
      if (word.length() == 1) {
        addValue(pos, text);
        return;
      }
      // recursive calls
      son.insert(word.substring(1), pos, text);
    } else if (first < key) {
      left.insert(word, pos, text);
    } else {
      right.insert(word, pos, text);
    }
  }

  private void addValue(int pos, int text) {
    if (text == 0)
      values1.add(pos);
    else
      values2.add(pos);
  }

  public int getSize() {
    if (empty)
      return 0;
    return 1 + son.getSize() + left.getSize() + right.getSize();
  }

  void getKeys(String prefix, ArrayList<String> keys) {
    if (empty)
      return;

    if (values1 != null)
      keys.add(prefix);

    left.getKeys(prefix, keys);
    son.getKeys(prefix + String.valueOf(key), keys);
    right.getKeys(prefix, keys);
  }

  void getSimilarity(double[] sums) {
    if (empty)
      return;

    if (values1 != null) {
      sums[0] += Math.abs(values1.size() - values2.size());
      sums[1] += values1.size() + values2.size();
    }

    left.getSimilarity(sums);
    son.getSimilarity(sums);
    right.getSimilarity(sums);
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
    if (values1 != null) {
      sb.append(" ");
      sb.append(values1);
      sb.append(", ");
      sb.append(values2);
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
