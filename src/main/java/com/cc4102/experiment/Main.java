/**
 * 
 */
package com.cc4102.experiment;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.PatriciaTree;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;

import java.io.File;

/**
 * @author Lucas Puebla Silva
 *
 */
public class Main {


  public static void main(String[] args) {
    Experiment exp = new Experiment();
    int reps = 1;

    // String text1 = "habia una vez un pato imaginario que se dio cuenta que la vida no tiene
    // sentido";
    // String text2 = "habia una vez un lobo feroz que no tenia ganas de vivir la vida sin sentido";
    String path = new File("").getAbsolutePath().concat("/src/main/java/com/cc4102/experiment/textos");
    String path1 = path + "/19-Pride&Prejudice.txt";
    String path2 = path + "/19-Dracula.txt";
    String path3 = path + "/CREA_total.TXT";

    // lpht
    StringDictionary lpht1, lpht2;
    lpht1 = new LinearProbingHashingTree(8);
    lpht2 = new LinearProbingHashingTree(8);

    // pt
    StringDictionary pt1, pt2;
    pt1 = new PatriciaTree();
    pt2 = new PatriciaTree();

    // tst
    StringDictionary tst1, tst2;
    tst1 = new TernarySearchTree();
    tst2 = new TernarySearchTree();

    // Choose text input
    // exp.preProcessText(text1, text2);
    exp.preProcessTextFile(path1, path2, path3);

    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning LinearProbingHashingTree test " + i + "...");
      exp.runTest(lpht1, lpht2, new LinearProbingHashingTree(8));
    }

    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning PatriciaTree test " + i + "...");;
      exp.runTest(pt1, pt2, new PatriciaTree());
    }

    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning TernarySearchTree test " + i + "...");
      exp.runTest(tst1, tst2, new TernarySearchTree());
    }
  }

}
