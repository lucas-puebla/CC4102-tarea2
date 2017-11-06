/**
 * 
 */
package com.cc4102.stringDict;

import java.util.ArrayList;

import com.cc4102.stringDict.linearProbing.Par;

/**
 * @author Lucas Puebla Silva
 *
 */
public class LinearProbingHashingTree implements StringDictionary {

  private int hashLength;
  private int hashOccupation;
  private int maxOccupation;
  private Par[] hashTable;

  /**
   * 
   * Constructor of class LinearProbingHashingTree.
   *
   * <p> </p>
   *
   * @param hl, hashLength, don't use a value lower than 8, still debugging...
   */
  public LinearProbingHashingTree(int hl) {
    hashLength = hl < 8 ? 8 : hl;
    hashOccupation = 0;
    maxOccupation = hashLength > 0 ? (int) (hashLength * 0.4) : 1;
    hashTable = new Par[hashLength];
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

  public Par[] getHashTable() {
    return hashTable;
  }
  
  public boolean contains(String elem) {
    boolean contains = false;
    int hash = this.getHash(elem);
    int offset = 0;
    while(offset < hashLength) {
      if(hashTable[(hash + offset) % hashLength] == null) {
        offset++;
        continue;
      } if(elem.equals(hashTable[(hash + offset) % hashLength].getKey())) {
        contains = true;
        break;
      }
      offset++;
    }
    return contains;
  }
  
  /**
   * Only for testing purposes!
   * 
   * @param elem
   * @return
   */
  public int searchCount(String elem) {
    int hash = this.getHash(elem);
    int offset = 0;
    while(offset < hashLength) {
      if(hashTable[(hash + offset) % hashLength] == null) {
        offset++;
        continue;
      } if(elem.equals(hashTable[(hash + offset) % hashLength].getKey())) {
        break;
      }
      offset++;
    }
    return offset;
  }

  
  
  public void insert(Par elem) {
    if(hashOccupation == maxOccupation - 1 || maxOccupation == 0) {
      this.rehash();
    }
    String key = elem.getKey();
    ArrayList<Integer> values = elem.getValues();
    int hash = getHash(key);
    int offset = 0;
    while(hashTable[(hash + offset) % hashLength] != null
        && offset < hashLength) {
      offset++;
    }
    hashTable[(hash + offset) % hashLength] = new Par(key, values);
    hashOccupation++;
  }
  
  

  public void insert(String word, int pos) {
    if(hashOccupation == maxOccupation - 1 || maxOccupation == 0) {
      this.rehash();
    }
    int hash = getHash(word);
    int offset = 0;
    boolean repeated = false;
    while(hashTable[(hash + offset) % hashLength] != null
        && offset < hashLength) {
      if((hashTable[(hash + offset) % hashLength].getKey()).equals(word)) {
        repeated = true;
        break;
      } else {
      offset++;
      }
    }
    if(repeated) {
      hashTable[(hash + offset) % hashLength].addVal(pos);
    } else {
      hashTable[(hash + offset) % hashLength] = new Par(word, pos);
    }
    hashOccupation++;
  }
  
  private Par[] extract() {
    Par[] tmp = new Par[maxOccupation + 1];
    int i = 0;
    for(Par elem : hashTable) {
      if(elem != null) tmp[i++] = elem;
    }
    return tmp;
  }
  
  private void update() {
    hashLength = 2 * hashLength;
    //maxOccupation = hashLength > 1 ? (int) (hashLength * 0.4) : 1;
    maxOccupation = (int) (hashLength * 0.4);
    hashOccupation = 0;
    hashTable = new Par[hashLength];
  }
  
  
  
  private void rehash() {
    Par[] tmp = this.extract();
    
    this.update();    
    
    for(Par elem : tmp) {
      if(elem != null) this.insert(elem);
    }
  }

  /**
   * For testing purposes only!
   * 
   * @param elem
   */
  public void insertAtEnd(String elem, int pos) {
    if(hashOccupation >= maxOccupation || maxOccupation == 0) {
      this.rehash();
    }
    int hash = hashLength - 1;
    int offset = 0;
    while(hashTable[(hash + offset) % hashLength] != null
        && offset < hashLength) {
      offset++;
    }
    hashTable[(hash + offset) % hashLength] = new Par(elem, pos);
    hashOccupation++;
  }

  /**
   * @param elem
   * @return
   */
  // Puede que sea una funcion muy mala!!
  // sin el abs tira vaores negativos!! No se pq!!
  // TODO
  private int getHash(String elem) {
    int hash = 7;
    for (int i = 0 ; i < elem.length() ; i++) {
        hash = hash * 31 + elem.charAt(i);
    }
    return Math.abs(hash % hashLength);
  }

  public int getHashNum(String elem) {
    return this.getHash(elem);
  }
  
  public int getSize() {
    return hashOccupation;
  }


  public int getMaxOccupation() {
    return maxOccupation;
  }

  
  
  

}
