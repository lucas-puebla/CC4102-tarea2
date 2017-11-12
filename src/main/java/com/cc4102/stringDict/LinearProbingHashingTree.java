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

  private int[] hashLength;
  private int[] hashOccupation;
  private int[] maxOccupation;
  private Par[][] hashTable;


  /**
   * 
   * Constructor of class LinearProbingHashingTree.
   *
   * <p>
   * This object uses a Par[] as it's elements. Every Par contains a String key and
   * ArrayList<Integer values, which correspond to it's occurrences.
   * </p>
   *
   * @param hl hashLength, don't use a value lower than 8, still debugging...
   */
  public LinearProbingHashingTree(int hl) {
    hashLength = new int[2];
    hashOccupation = new int[2];
    maxOccupation = new int[2];
    hashTable = new Par[2][hl];
    
    for (int i = 0; i < 2; i++) {
      hashLength[i] = hl < 8 ? 8 : hl;
      hashOccupation[i] = 0;
      maxOccupation[i] = hashLength[i] > 0 ? (int) (hashLength[i] * 0.4) : 1;
      hashTable[i] = new Par[hashLength[i]];
    }
  }

  /**
   * Wrapper for the StringDictionary method getLength().
   * <p>
   * Uses the getHashLength() method to return the above.
   * </p>
   * 
   * @return
   */
  public int getLength(int text) {
    return this.getHashLength(text);
  }

  /**
   * Gives the current hashLength.
   * 
   * @return
   */
  private int getHashLength(int text) {
    return hashLength[text];
  }

  /**
   * Wrapper for StringDictionary method getRoot.
   * <p>
   * Uses the getHashTable() method to return the above.
   * </p>
   * 
   * @return
   */
  public Object getRoot(int text) {
    return this.getHashTable(text);
  }

  /**
   * Returns the hashTable, which is the root.
   * 
   * @return
   */
  public Par[] getHashTable(int text) {
    return hashTable[text];
  }

  /**
   * Checks whether elem is contained within the hashTable.
   * 
   * @param elem String corresponding to the key.
   */
  public boolean contains(String elem, int text) {
    boolean contains = false;
    int hash = this.getHash(elem, text);
    int offset = 0;
    while (offset < hashLength[text]) {
      if (hashTable[text][(hash + offset) % hashLength[text]] == null) {
        offset++;
        break;
      }
      if (elem.equals(hashTable[text][(hash + offset) % hashLength[text]].getKey())) {
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
   * <p>
   * Enables a countdown of how many tries it took to find the key elem in the hashTable.
   * </p>
   * 
   * @param elem String corresponding to the key.
   * @return
   */
  public int searchCount(String elem, int text) {
    int hash = this.getHash(elem, text);
    int offset = 0;
    while (offset < hashLength[text]) {
      if (hashTable[text][(hash + offset) % hashLength[text]] == null) {
        offset++;
        continue;
      }
      if (elem.equals(hashTable[text][(hash + offset) % hashLength[text]].getKey())) {
        break;
      }
      offset++;
    }
    return offset;
  }


  /**
   * Inserts a Par element into the hashTable.
   * <p>
   * This is only used when rehashing.
   * </p>
   * 
   * @param elem corresponds to a Par object to be inserted.
   */

  private void insert(Par elem, int text) {
    if (hashOccupation[text] == maxOccupation[text] - 1) {
      this.rehash(text);
    }
    String key = elem.getKey();
    ArrayList<Integer> values = elem.getValues();
    int hash = getHash(key, text);
    int offset = 0;
    while (hashTable[text][(hash + offset) % hashLength[text]] != null 
        && offset < hashLength[text]) {
      offset++;
    }
    hashTable[text][(hash + offset) % hashLength[text]] = new Par(key, values);
    hashOccupation[text]++;
  }



  /**
   * Inserts a word and it's occurrence position into the hashTable.
   * 
   * <p>
   * The element inserted is a Par object. If the word already exists, then it adds the occurrence
   * position to Par.values.
   * </p>
   * 
   * @param word is the key
   * @param pos is the position of the ocurrence of the key
   */
  public void insert(String word, int pos, int text) {
    if (hashOccupation[text] == maxOccupation[text] - 1) {
      this.rehash(text);
    }
    int hash = getHash(word, text);
    int offset = 0;
    boolean repeated = false;
    while (hashTable[text][(hash + offset) % hashLength[text]] != null 
        && offset < hashLength[text]) {
      if ((hashTable[text][(hash + offset) % hashLength[text]].getKey()).equals(word)) {
        repeated = true;
        break;
      } else {
        offset++;
      }
    }
    if (repeated) {
      hashTable[text][(hash + offset) % hashLength[text]].addVal(pos);
    } else {
      hashTable[text][(hash + offset) % hashLength[text]] = new Par(word, pos);
      hashOccupation[text]++;
    }
  }

  /**
   * Extracts the non-null elements from the hashTable.
   * <p>
   * Used only during the rehashing process.
   * </p>
   * 
   * @return
   */
  private Par[] extract(int text) {
    Par[] tmp = new Par[hashOccupation[text] + 1];
    int i = 0;
    for (Par elem : hashTable[text]) {
      if (elem != null)
        tmp[i++] = elem;
    }
    return tmp;
  }


  /**
   * Updates the instance variables when rehashing.
   * <p>
   * The rehashing process doubles the hashTable's length.
   * </p>
   */
  private void update(int text) {
    hashLength[text] = 2 * hashLength[text];
    maxOccupation[text] = (int) (hashLength[text] * 0.4);
    hashOccupation[text] = 0;
    hashTable[text] = new Par[hashLength[text]];
  }


  /**
   * Rehashing process, reinserts all the non-null elements into the new hashTable.
   * <p>
   * In order to preserve the O(1) time, it is necessary to insert with the new hashLength
   * modularity.
   * </p>
   */
  private void rehash(int text) {
    Par[] tmp = this.extract(text);

    this.update(text);

    for (Par elem : tmp) {
      if (elem != null)
        this.insert(elem, text);
    }
  }

  /**
   * For testing purposes only!
   * 
   * <p>
   * Inserts the Par(elem, pos) in the last position of the hashTable. This allows to check the
   * circularity of the insertion.
   * </p>
   * 
   * @param elem is the key
   * @param pos is the position of the occurrence of the key
   */
  public void insertAtEnd(String elem, int pos, int text) {
    if (hashOccupation[text] >= maxOccupation[text]) {
      this.rehash(text);
    }
    int hash = hashLength[text] - 1;
    int offset = 0;
    while (hashTable[text][(hash + offset) % hashLength[text]] != null 
        && offset < hashLength[text]) {
      offset++;
    }
    hashTable[text][(hash + offset) % hashLength[text]] = new Par(elem, pos);
    hashOccupation[text]++;
  }

  /**
   * Hashing function.
   * 
   * @param elem is the key
   * @return the hash value associated to the key
   */
  // Puede que sea una funcion muy mala!!
  // sin el abs tira vaores negativos!! No se pq!!
  // TODO
  private int getHash(String elem, int text) {
    int hash = 7;
    for (int i = 0; i < elem.length(); i++) {
      hash = hash * 31 + elem.charAt(i);
    }
    return Math.abs(hash % hashLength[text]);
  }

  /**
   * For testing purposes only!
   * 
   * @param elem is the key
   * @return the hash value associated to the key
   */
  public int getHashNum(String elem, int text) {
    return this.getHash(elem, text);
  }

  /**
   * Gets the actual occupied size of the hashTable, or how many elements have been inserted so far.
   * 
   * @return the amount of elements inserted so far
   */
  public int getSize(int text) {
    return hashOccupation[text];
  }

  /**
   * Gives the maximum occupation quantity in order to preserve a 40% or less occupation rate.
   * 
   * @return 40% of the actual hashLength
   */
  public int getMaxOccupation(int text) {
    return maxOccupation[text];
  }

  /**
   * Gives the list of the key occurrences positions.
   * 
   * @param key is the key
   * @return the occurrences or an empty ArrayList<Integer if the word does not exist
   */
  public ArrayList<Integer> search(String key, int text) {
    ArrayList<Integer> values = new ArrayList<Integer>();
    Par tmp;
    int hash = this.getHash(key, text);
    int offset = 0;
    while (offset < hashLength[text]) {
      tmp = hashTable[text][(hash + offset) % hashLength[text]];
      if (tmp == null) {
        break;
      }
      if (key.equals(tmp.getKey())) {
        values = tmp.getValues();
        break;
      }
      offset++;
    }
    return values;
  }

  /**
   * TODO
   */
  public String[] getKeys(int text) {
    String[] tmp = new String[hashOccupation[text]];
    int i = 0;
    for (Par elem : hashTable[text]) {
      if (elem != null) {
        tmp[i++] = elem.getKey();
      }
    }
    return tmp;
  }

  /**
   * TODO
   */
  public int count(String key, int text) {
    int res = 0;
    ArrayList<Integer> tmp = this.search(key, text);
    if (tmp != null) {
      res = tmp.size();
    }
    return res;
  }

  public String getClassStr() {
    return "LinearProbingHashingTree";
  }

  public double getSimilarity() {
    return 0; // TODO
  }

  public String[] getKeys() {
    return null; // TODO
  }


}
