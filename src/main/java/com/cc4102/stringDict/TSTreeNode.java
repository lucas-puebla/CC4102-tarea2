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
    values = new ArrayList<Integer>();
    values.add(value);
  }

  public ArrayList<Integer> search(String s) {
    if (s.length() == 0)
      return new ArrayList<Integer>(); // vacío

    char first = s.charAt(0);

    if (s.length() == 1) {
      if (key == first)
        return values;
      return new ArrayList<Integer>(); // vacío
    }

    if (first == key)
      return son.search(s.substring(1));
    else if (first < key)
      return left.search(s);
    else
      return right.search(s);
  }

  public void insert(String word, int pos) {
    char first = word.charAt(0);

    // inicializar nodos vacios
    if (empty) {
      if (word.length() == 1) {
        initLeaf(first, pos);
        return;
      } else {
        initNode(first);
      }
    }

    if (first == key) {

      // palabra ya agregada
      if (word.length() == 1) {
        values.add(pos);
        return;
      }
      // continuar recursivamente
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

  public void addKeys(String prefix, ArrayList<String> keys) {
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
