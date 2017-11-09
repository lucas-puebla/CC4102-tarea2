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
    int experimentReps = 10;
    int reps = 10;
    
    Experiment exp = new Experiment();
    
    
    for (int i = 0; i < experimentReps; i++) {
      runExperiments(exp, reps, (int) Math.pow(2, i+10));
    }
    
    
  }
  private static void runExperiments(Experiment exp, int reps, int textLength) {

    // String text1 = "habia una vez un pato imaginario que se dio cuenta que la vida no tiene
    // sentido";
    // String text2 = "habia una vez un lobo feroz que no tenia ganas de vivir la vida sin sentido";
    String path = new File("").getAbsolutePath().concat("/src/main/java/com/cc4102/experiment/textos");
    String path1 = path + "/asoif#" + textLength + ".txt";
    String path2 = path + "/harry_potter#" + textLength + ".txt";
    String path3 = path + "/CREA_total.TXT";
    
    

    // lpht
    StringDictionary sd1, sd2, sd3;

    // Choose text input
    // exp.preProcessText(text1, text2);
    exp.preProcessTextFile(path1, path2, path3);

  
    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning LinearProbingHashingTree test " + i + "...");
      sd1 = new LinearProbingHashingTree(8);
      sd2 = new LinearProbingHashingTree(8);
      sd3 = new LinearProbingHashingTree(8);
      exp.runTest(sd1, sd2, sd3);
    }

    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning PatriciaTree test " + i + "...");;
      sd1 = new PatriciaTree();
      sd2 = new PatriciaTree();
      sd3 = new PatriciaTree();
      exp.runTest(sd1, sd2, sd3);
    }

    for (int i = 0; i < reps; i++) {
      System.out.println("\nRunning TernarySearchTree test " + i + "...");
      sd1 = new TernarySearchTree();
      sd2 = new TernarySearchTree();
      sd3 = new TernarySearchTree();
      exp.runTest(sd1, sd2, sd3);
    }
  }

}
