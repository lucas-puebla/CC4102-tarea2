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
    int experimentReps = 11;
    int reps = 10;
    
    Experiment exp = new Experiment();

    for (int i = 0; i < experimentReps; i++) {
      runExperiments(exp, reps, (int) Math.pow(2, i+10));
    }
    
    
  }
  private static void runExperiments(Experiment exp, int reps, int textLength) {

    // String text1 = "habia una vez un pato imaginario que se dio cuenta que la vida no tiene sentido";
    // String text2 = "habia una vez un lobo feroz que no tenia ganas de vivir la vida sin sentido";
    String path = new File("").getAbsolutePath().concat("/src/main/java/com/cc4102/experiment/textos");
    String path1 = path + "/harry_potter#" + textLength + ".txt";
    String path2 = path + "/asoif#" + textLength + ".txt";
    String path3 = path + "/CREA_total.TXT";

    // Choose text input
    // exp.preProcessText(text1, text2);
    exp.preProcessTextFile(path1, path2, path3);

    // diccionario
    StringDictionary dict;

    
    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning LinearProbingHashingTree test " + i + "...");
      dict = new LinearProbingHashingTree(8);
      exp.runTest(dict);
    }
    

    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning PatriciaTree test " + i + "...");;
      dict = new PatriciaTree();
      exp.runTest(dict);
    }

    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning TernarySearchTree test " + i + "...");
      dict = new TernarySearchTree();
      exp.runTest(dict);
    }
  }

}
