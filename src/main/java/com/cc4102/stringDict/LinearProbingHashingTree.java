/**
 * 
 */
package com.cc4102.stringDict;

import java.util.ArrayList;

import com.cc4102.stringDict.linearProbing.Trio;

/**
 * @author Lucas Puebla Silva
 *
 */
public class LinearProbingHashingTree implements StringDictionary {

  private int hashLength;
  private int hashOccupation;
  private int maxOccupation;
  private Trio[] hashTable;


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
    /*
     * hashLength = new int[2]; hashOccupation = new int[2]; maxOccupation = new int[2]; hashTable =
     * new Par[2][hl];
     * 
     * for (int i = 0; i < 2; i++) { hashLength[i] = hl < 8 ? 8 : hl; hashOccupation[i] = 0;
     * maxOccupation[i] = hashLength[i] > 0 ? (int) (hashLength[i] * 0.4) : 1; hashTable[i] = new
     * Par[hashLength[i]]; }
     */
    hashLength = hl < 8 ? 8 : hl;
    hashOccupation = 0;
    maxOccupation = hashLength > 0 ? (int) (hashLength * 0.4) : 1;
    hashTable = new Trio[hashLength];

  }

  /**
   * Wrapper for the StringDictionary method getLength().
   * <p>
   * Uses the getHashLength() method to return the above.
   * </p>
   * 
   * @return
   */
  public int getLength() {
    return this.getHashLength();
  }

  /**
   * Gives the current hashLength.
   * 
   * @return
   */
  private int getHashLength() {
    return hashLength;
  }

  /**
   * Wrapper for StringDictionary method getRoot.
   * <p>
   * Uses the getHashTable() method to return the above.
   * </p>
   * 
   * @return
   */
  public Object getRoot() {
    return this.getHashTable();
  }

  /**
   * Returns the hashTable, which is the root.
   * 
   * @return
   */
  public Trio[] getHashTable() {
    return hashTable;
  }

  /**
   * Checks whether elem is contained within the hashTable.
   * 
   * @param elem String corresponding to the key.
   */
  public boolean contains(String elem, int text) {
    boolean contains = false;
    int hash = this.getHash(elem);
    int offset = 0;
    while (offset < hashLength) {
      if (hashTable[(hash + offset) % hashLength] == null) {
        offset++;
        break;
      }
      if (elem.equals(hashTable[(hash + offset) % hashLength].getKey())) {
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
    int hash = this.getHash(elem);
    int offset = 0;
    while (offset < hashLength) {
      if (hashTable[(hash + offset) % hashLength] == null) {
        offset++;
        continue;
      }
      if (elem.equals(hashTable[(hash + offset) % hashLength].getKey())) {
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
  private void insert(Trio elem) {
    if (hashOccupation == maxOccupation - 1) {
      this.rehash();
    }
    String key = elem.getKey();
    Object[] values = elem.getValues();
    int hash = getHash(key);
    int offset = 0;
    while (hashTable[(hash + offset) % hashLength] != null && offset < hashLength) {
      offset++;
    }
    hashTable[(hash + offset) % hashLength] = new Trio(key, values);
    hashOccupation++;
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
    if (hashOccupation == maxOccupation - 1) {
      this.rehash();
    }
    int hash = getHash(word);
    int offset = 0;
    boolean repeated = false;
    while (hashTable[(hash + offset) % hashLength] != null && offset < hashLength) {
      if ((hashTable[(hash + offset) % hashLength].getKey()).equals(word)) {
        repeated = true;
        break;
      } else {
        offset++;
      }
    }
    if (repeated) {
      hashTable[(hash + offset) % hashLength].addVal(pos, text);
    } else {
      hashTable[(hash + offset) % hashLength] = new Trio(word, pos, text);
      hashOccupation++;
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
  private Trio[] extract() {
    Trio[] tmp = new Trio[hashOccupation + 1];
    int i = 0;
    for (Trio elem : hashTable) {
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
  private void update() {
    hashLength = 2 * hashLength;
    maxOccupation = (int) (hashLength * 0.4);
    hashOccupation = 0;
    hashTable = new Trio[hashLength];
  }


  /**
   * Rehashing process, reinserts all the non-null elements into the new hashTable.
   * <p>
   * In order to preserve the O(1) time, it is necessary to insert with the new hashLength
   * modularity.
   * </p>
   */
  private void rehash() {
    Trio[] tmp = this.extract();

    this.update();

    for (Trio elem : tmp) {
      if (elem != null)
        this.insert(elem);
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
    if (hashOccupation >= maxOccupation) {
      this.rehash();
    }
    int hash = hashLength - 1;
    int offset = 0;
    while (hashTable[(hash + offset) % hashLength] != null && offset < hashLength) {
      offset++;
    }
    hashTable[(hash + offset) % hashLength] = new Trio(elem, pos, text);
    hashOccupation++;
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
  private int getHash(String elem) {
    int hash = 7;
    for (int i = 0; i < elem.length(); i++) {
      hash = hash * 31 + elem.charAt(i);
    }
    return Math.abs(hash % hashLength);
  }

  /**
   * For testing purposes only!
   * 
   * @param elem is the key
   * @return the hash value associated to the key
   */
  public int getHashNum(String elem) {
    return this.getHash(elem);
  }

  /**
   * Gets the actual occupied size of the hashTable, or how many elements have been inserted so far.
   * 
   * @return the amount of elements inserted so far
   */
  public int getSize() {
    return hashOccupation;
  }

  /**
   * Gives the maximum occupation quantity in order to preserve a 40% or less occupation rate.
   * 
   * @return 40% of the actual hashLength
   */
  public int getMaxOccupation() {
    return maxOccupation;
  }

  /**
   * Gives the list of the key occurrences positions.
   * 
   * @param key is the key
   * @return the occurrences or an empty ArrayList<Integer if the word does not exist
   */
  public ArrayList<Integer> search(String key, int text) {
    ArrayList<Integer> values = new ArrayList<Integer>();
    Trio tmp;
    int hash = this.getHash(key);
    int offset = 0;
    while (offset < hashLength) {
      tmp = hashTable[(hash + offset) % hashLength];
      if (tmp == null) {
        break;
      }
      if (key.equals(tmp.getKey())) {
        values = tmp.getValues(text);
        break;
      }
      offset++;
    }
    return values;
  }

  /**
   * TODO
   */
  public String[] getKeys() {
    String[] tmp = new String[hashOccupation];
    int i = 0;
    for (Trio elem : hashTable) {
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
    double sum[] = new double[2];
    for (Trio elem : hashTable) {
      if (elem != null) {
        sum[0] += Math.abs(elem.count(0) - elem.count(1));
        sum[1] += elem.count(0) + elem.count(1);
      }
    }
    return 1 - sum[0] / sum[1];
  }


}
