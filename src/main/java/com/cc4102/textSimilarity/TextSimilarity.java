/**
 * 
 */
package com.cc4102.textSimilarity;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.PatriciaTree;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;
import com.cc4102.textCleaner.TextCleaner;

/**
 * @author Lucas Puebla Silva
 *
 */
public class TextSimilarity {

  private String[] texts;
  private StringDictionary stringDictType = null;
  private StringDictionary[] sds;

  public TextSimilarity(String text1, String text2) {
    texts = new String[3];
    texts[0] = text1;
    texts[1] = text2;
    texts[2] = text1 + " " + text2;
    sds = new StringDictionary[3];
  }

  /**
   * @return
   */
  public String getText1() {
    return texts[0];
  }

  /**
   * @return
   */
  public String getText2() {
    return texts[1];
  }

  /**
   * @return
   */
  public String getConcatText() {
    return texts[2];
  }

  /**
   * @param lpht
   */
  public void setStringDictType(StringDictionary sd) {
    stringDictType = sd;
  }

  /**
   * @return
   */
  public StringDictionary getStringDictType() {
    return stringDictType;
  }


  private StringDictionary initializeStringDictStructure() {
    StringDictionary structure = null;
    if (stringDictType instanceof LinearProbingHashingTree) {
      structure = new LinearProbingHashingTree(8);
    } else if (stringDictType instanceof TernarySearchTree) {
      structure = new TernarySearchTree();
    } else if (stringDictType instanceof PatriciaTree) {
      structure = new PatriciaTree();
    }
    return structure;
  }

  /**
   * 
   */
  public void execute() {
    TextCleaner tc = new TextCleaner();
    String[] tmp;

    for (int i = 0; i < texts.length; i++) {
      sds[i] = initializeStringDictStructure();
      tmp = tc.clean(texts[i]).split(" ");
      for (int j = 0; j < tmp.length ; j++) {
        sds[i].insert(tmp[j], j);
      }
    }
  }


  /**
   * @return
   */
  public double getSimilarity() {
    double sim = 0.0;
    String key;
    double size1, size2, total = 0.0;
    String[] keys = sds[2].getKeys();
    for (int i = 0; i < keys.length; i++) {
      key = keys[i];
      if(key != null) {
        size1 = sds[0].count(key);
        size2 = sds[1].count(key);
        total += sds[2].count(key);
        sim += Math.abs(size1 - size2); 
      }
    }
    return 1.0 - (sim / total);
  }

}
