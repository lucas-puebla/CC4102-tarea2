/**
 * 
 */
package com.cc4102.stringDict;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import javax.print.attribute.HashPrintServiceAttributeSet;

import com.cc4102.stringDict.linearProbing.Par;
import org.junit.Test;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.StringDictionary;

/**
 * @author Lucas Puebla Silva
 *
 */
public class LinearProbingTest {

  int hashLength = 8;
  LinearProbingHashingTree lpht = new LinearProbingHashingTree(hashLength);
  Par[][] root = {(Par[]) lpht.getRoot(0), (Par[]) lpht.getRoot(1)};


  private void hashingTableLength(int text) {
    int expected = hashLength;
    int actual = lpht.getLength(text);
    assertEquals("The initial hashing table length should be the one specified in the construction",
        expected, actual);
  }

  @Test
  public void hashingTableLengthTest() {
    hashingTableLength(0);
    hashingTableLength(1);
  }

  private void insertOneElement(int text) {
    String elem = "hola";

    lpht.insert(elem, 0, text);

    assertTrue("The element inserted should be somewhere inside the hash",
        ((LinearProbingHashingTree) lpht).contains(elem, text));
  }

  @Test
  public void insertOneElementTest() {
    insertOneElement(0);
    insertOneElement(1);

  }

  private void insertLotsOfElements(int text) {
    String[] elems = {"a", "ab", "abc", "abcd", "abcde"};

    for (int i = 0; i < elems.length; i++) {
      lpht.insert(elems[i], i, text);
    }

    for (String elem : elems) {
      assertTrue("Every element inserted should be in the hash: " + elem,
          ((LinearProbingHashingTree) lpht).contains(elem, text));
    }
  }

  @Test
  public void insertLotsOfElementsTest() {
    insertLotsOfElements(0);
    insertLotsOfElements(1);
  }

  private void insertSameElement(int text) {
    String[] texto = {"a", "a"};
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(0);
    expected.add(1);

    for (int i = 0; i < texto.length; i++) {
      lpht.insert(texto[i], i, text);
    }

    for (int i = 0; i < texto.length; i++) {
      assertEquals(
          "When inserting the same element more than once, search() "
              + "should return an ArrayList<Integer> with the position of the ocurrences",
          expected, lpht.search(texto[i], text));
    }
  }

  @Test
  public void insertSameElementTest() {
    insertSameElement(0);
    insertSameElement(1);
  }

  private void hashInsertionCircularity(int text) {
    String elem = "hola";
    String elem2 = "chao";

    ((LinearProbingHashingTree) lpht).insertAtEnd(elem, 0, text);
    ((LinearProbingHashingTree) lpht).insertAtEnd(elem2, 1, text);

    assertEquals("The last hashTable space should be the first element inserted", elem,
        root[text][lpht.getLength(text) - 1].getKey());
    assertEquals("The first hashTable space should be the second element inserted", elem2,
        root[text][0].getKey());
  }

  @Test
  public void hashInsertionCircularityTest() {
    hashInsertionCircularity(0);
    hashInsertionCircularity(1);
  }

  private void rehashInsertion(int text) {
    String texto = "habia una vez un pajarito";
    String[] words = texto.split(" ");
    int length1, length2, size1, size2;
    size1 = lpht.getSize(text);
    length1 = lpht.getLength(text);

    for (int i = 0; i < words.length; i++) {
      lpht.insert(words[i], i, text);
    }
    size2 = lpht.getSize(text);
    length2 = lpht.getLength(text);


    assertEquals("First size shoulb be 0", 0, size1);
    assertEquals("First length should be hashLength", hashLength, length1);
    assertEquals("Second size should be maxOccupation + 1", words.length, size2);
    assertEquals("Second length should also be double the hashLength", 2 * hashLength, length2);
  }

  @Test
  public void rehashInsertionTest() {
    rehashInsertion(0);
    rehashInsertion(1);
  }

  private void hashSucccesfulContainment(int text) {
    String elem = "hola";

    lpht.insert(elem, 0, text);

    assertTrue("The element should be in the hashTable",
        ((LinearProbingHashingTree) lpht).contains(elem, text));
  }

  @Test
  public void hashSucccesfulContainmentTest() {
    hashSucccesfulContainment(0);
    hashSucccesfulContainment(1);
  }

  private void hashUnsucccesfulContainment(int text) {
    String elem = "hola";

    assertFalse("The element should not be in the hashTable",
        ((LinearProbingHashingTree) lpht).contains(elem, text));
  }

  @Test
  public void hashUnsucccesfulContainmentTest() {
    hashUnsucccesfulContainment(0);
    hashUnsucccesfulContainment(1);
  }

  private void hashSearchCircularity(int text) {
    String elem = "hola";

    assertEquals(hashLength, ((LinearProbingHashingTree) lpht).searchCount(elem, text));
  }

  @Test
  public void hashSearchCircularityTest() {
    hashSearchCircularity(0);
    hashSearchCircularity(1);
  }

  private void hashOccupationLevel(int text) {
    String[] elems = {"a", "ab", "abc", "abcd", "abcde"};
    int size1 = lpht.getSize(text);

    for (int i = 0; i < elems.length; i++) {
      lpht.insert(elems[i], i, text);
    }

    assertEquals("LPHT should initialy have size 0.", 0, size1);
    assertEquals("LPHT should know elements were inserted.", size1 + elems.length,
        lpht.getSize(text));
  }

  @Test
  public void hashOccupationLevelTest() {
    hashOccupationLevel(0);
    hashOccupationLevel(1);
  }


  @Test
  public void goodHashingFunctionTest() {
    // Deberia poder verificar la reparticion de los elementos
    // dentro de la estructura para asegurar que esten bien
    // repartidos.
    fail("not yet implemented");
  }

  private void alwaysPositiveHashFunction(int text) {
    String texto =
        "nam at lectus urna duis convallis convallis tellus id interdum velit laoreet id donec ultrices tincidunt arcu non sodales neque sodales ut etiam sit amet nisl purus in mollis nunc sed id semper risus in hendrerit gravida rutrum quisque non tellus orci ac auctor augue mauris augue neque gravida in fermentum et sollicitudin ac orci phasellus egestas tellus rutrum tellus pellentesque eu tincidunt tortor aliquam nulla facilisi cras fermentum odio eu feugiat pretium nibh ipsum consequat nisl vel pretium lectus quam id leo in vitae turpis massa sed elementum tempus egestas sed sed risus pretium quam vulputate dignissim suspendisse in est ante";
    String[] elems = texto.split(" ");
    LinearProbingHashingTree tmp = (LinearProbingHashingTree) lpht;
    int hash;

    for (String elem : elems) {
      hash = tmp.getHashNum(elem, text);
      assertTrue("Every hash should be between 0 and hashLength.", hash >= 0 && hash < hashLength);
    }
  }

  @Test
  public void alwaysPositiveHashFunctionTest() {
    alwaysPositiveHashFunction(0);
    alwaysPositiveHashFunction(1);

  }

  private void fillPercentageBelow40Percent(int text) {
    int occupation;
    LinearProbingHashingTree tmp = (LinearProbingHashingTree) lpht;

    occupation = tmp.getMaxOccupation(text);

    for (int i = 0; i < occupation; i++) {
      lpht.insert("a", i, text);
    }
    occupation = lpht.getSize(text);

    assertTrue("The occupation level should be lower than 40% before rehashing.",
        occupation <= tmp.getMaxOccupation(text));

    occupation = tmp.getMaxOccupation(text);
    for (int i = 0; i < occupation; i++) {
      lpht.insert("b", i, text);
    }
    occupation = lpht.getSize(text);

    assertTrue("The occupation level should be lower than 40% after rehashing.",
        occupation <= tmp.getMaxOccupation(text));
  }

  @Test
  public void fillPercentageBelow40PercentTest() {
    fillPercentageBelow40Percent(0);
    fillPercentageBelow40Percent(1);
  }

  // TODO
  /*
   * @Test public void getKeysTest() { String[] expected = {"hola", "como", "estas"}; boolean[]
   * contains = {false, false, false}; String text = "hola como estas como estas"; String[] words =
   * text.split(" ");
   * 
   * for (int i = 0; i < words.length; i++) { lpht.insert(words[i], i); }
   * 
   * String[] actual = lpht.getKeys(); for (int i = 0; i < expected.length; i++) { for (int j = 0; j
   * < actual.length; j++) { if (expected[i].equals(actual[j])) { contains[i] = true; } } } for (int
   * i = 0; i < contains.length; i++) {
   * assertTrue("Every key should be returned with the getKeys() method", contains[i]); } }
   */

  private void succesfulCount(int text) {
    lpht.insert("hola", 0, text);
    lpht.insert("hola", 1, text);

    assertEquals("count(word) should return the number of occurrences of word", 2,
        lpht.count("hola", text));
  }

  @Test
  public void succesfulCountTest() {
    succesfulCount(0);
    succesfulCount(1);
  }

  private void unsuccesfulCount(int text) {
    assertEquals("count(word) should return 0 if the word is not contained", 0,
        lpht.count("hola", text));
  }

  @Test
  public void unsuccesfulCountTest() {
    unsuccesfulCount(0);
    unsuccesfulCount(1);
  }

  private void insertionResistance(int text) {
    int hashLength = 262144;
    LinearProbingHashingTree lpht = new LinearProbingHashingTree(hashLength);
    // lo anterior es una aproximacion del espacio ocupado

    String texto =
        "nam at lectus urna duis convallis convallis tellus id interdum velit laoreet id donec ultrices tincidunt arcu non sodales neque sodales ut etiam sit amet nisl purus in mollis nunc sed id semper risus in hendrerit gravida rutrum quisque non tellus orci ac auctor augue mauris augue neque gravida in fermentum et sollicitudin ac orci phasellus egestas tellus rutrum tellus pellentesque eu tincidunt tortor aliquam nulla facilisi cras fermentum odio eu feugiat pretium nibh ipsum consequat nisl vel pretium lectus quam id leo in vitae turpis massa sed elementum tempus egestas sed sed risus pretium quam vulputate dignissim suspendisse in est ante";
    String[] elems = texto.split(" ");
    int iter = 10;

    long initTime = System.nanoTime();
    for (int i = 0; i < iter; i++) {
      for (int j = 0; j < elems.length; j++) {
        lpht.insert(elems[j], j, text);
      }
    }
    long finalTime = System.nanoTime();
    long time = (finalTime - initTime);
    System.out.println("Test de Resistencia de Inserciones");
    System.out.println("Se insertaron: " + (iter * elems.length) + " elementos.");
    System.out.println("Tiempo total: " + time / 1e6 + "ms");
    System.out.println("Largo LPHT: " + lpht.getLength(text));
    System.out.println("Size LPHT: " + lpht.getSize(text));
    System.out
        .println("maxOccupation LPHT: " + ((LinearProbingHashingTree) lpht).getMaxOccupation(text));
    assertTrue(true);
  }

  @Test
  public void insertionResistanceTest() {
    insertionResistance(0);
    insertionResistance(1);
  }

}
