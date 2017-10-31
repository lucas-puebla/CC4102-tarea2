/**
 * 
 */
package com.cc4102.stringDict;



/**
 * @author Lucas Puebla Silva
 *
 */
public class LinearProbingHashingTree implements StringDictionary {

  private int hashLength;
  private int hashOccupation;
  private int maxOccupation;
  private String[] hashTable;

  /**
   * 
   * Constructor of class LinearProbingHashingTree.
   *
   * <p> </p>
   *
   * @param hl, hashLength, don't use a value lower than 8, still debuging...
   */
  public LinearProbingHashingTree(int hl) {
    hashLength = hl;
    hashOccupation = 0;
    maxOccupation = hashLength > 0 ? (int) (hashLength * 0.4) : 1;
    hashTable = new String[hashLength];

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

  public String[] getHashTable() {
    return hashTable;
  }
  
  public boolean search(String elem) {
    int hash = this.getHash(elem);
    int offset = 0;
    int count = 0;
    while(!elem.equals(hashTable[(hash + offset) % hashLength])
        && count < hashLength) {
      offset++;
      count++;
    }
    return elem.equals(hashTable[(hash + offset) % hashLength]);
  }
  
  /**
   * Only for testing purposes!
   * @param elem
   * @param giveCount
   * @return
   */
  public int search(String elem, boolean giveCount) {
    int hash = this.getHash(elem);
    int offset = 0;
    int count = 0;
    while(!elem.equals(hashTable[(hash + offset) % hashLength])
        && count < hashLength) {
      offset++;
      count++;
    }
    return count;
  }

  public void insert(String elem) {
    if(hashOccupation == maxOccupation - 1 || maxOccupation == 0) {
      this.rehash();
    }
    int hash = getHash(elem);
    int offset = 0;
    int count = 0;
    while(hashTable[(hash + offset) % hashLength] != null
        && count < hashLength) {
      offset++;
      count++;
    }
    hashTable[(hash + offset) % hashLength] = elem;
    hashOccupation++;
  }
  
  private void rehash() {
    String[] tmp = new String[maxOccupation + 1];
    int i = 0;
    for(String elem : hashTable) {
      if(elem != null) tmp[i++] = elem;
    }
    
    hashLength = 2 * hashLength;
    maxOccupation = hashLength > 1 ? (int) (hashLength * 0.4) : 1;
    hashOccupation = 0;
    
    hashTable = new String[hashLength];
    
    for(String elem : tmp) {
      if(elem != null) this.insert(elem);
    }
  }

  /**
   * For testing purposes only!
   * @param elem
   * @param insertAtEnd
   */
  public void insert(String elem, boolean insertAtEnd) {
    if(hashOccupation >= maxOccupation || maxOccupation == 0) {
      this.rehash();
    }
    int hash = hashLength - 1;
    int offset = 0;
    int count = 0;
    while(hashTable[(hash + offset) % hashLength] != null
        && count < hashLength) {
      offset++;
      count++;
    }
    hashTable[(hash + offset) % hashLength] = elem;
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
