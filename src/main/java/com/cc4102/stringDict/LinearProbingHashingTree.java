/**
 * 
 */
package com.cc4102.stringDict;


import com.cc4102.stringDict.LinearProbing.MyLinkedList;
import com.cc4102.stringDict.LinearProbing.Node;

/**
 * @author Lucas Puebla Silva
 *
 */
public class LinearProbingHashingTree implements StringDictionary {

  private int hashLength;
  private MyLinkedList[] hashTable;

  public LinearProbingHashingTree(int hl) {
    hashLength = hl;
    hashTable = new MyLinkedList[hashLength];
    for (int i = 0; i < hashLength; i++) {
      hashTable[i] = new MyLinkedList();
    }

  }

  public int getLength() {
    return this.getHashLength();
  }

  private int getHashLength() {
    return hashLength;
  }

  public Object getRoot() {
    return this.getHashTable();
  }

  public MyLinkedList[] getHashTable() {
    return hashTable;
  }

  public void insert(String elem) {
    // TODO
  }

}
