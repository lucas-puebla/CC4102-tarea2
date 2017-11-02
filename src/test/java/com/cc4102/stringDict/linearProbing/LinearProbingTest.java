/**
 * 
 */
package com.cc4102.stringDict.linearProbing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.cc4102.stringDict.LinearProbingHashingTree;
import com.cc4102.stringDict.StringDictionary;

/**
 * @author Lucas Puebla Silva
 *
 */
public class LinearProbingTest {

  int hashLength = 8;
  StringDictionary lpht = new LinearProbingHashingTree(hashLength);
  String[] root = (String[]) lpht.getRoot();

  @Test
  public void hashingTableLengthTest() {
    int expected = hashLength;
    int actual = lpht.getLength();
    assertEquals("The initial hashing table length should be the one specified in the construction",
        expected, actual);
  }

  @Test
  public void insertOneElementTest() {
    String tmp;
    String elem = "hola";
    boolean isThere = false;

    lpht.insert(elem);

    assertTrue("The element inserted should be somewhere inside the hash", lpht.search(elem));
  }

  @Test
  public void insertLotsOfElementsTest() {
    String[] elems = {"a", "ab", "abc", "abcd", "abcde"};

    for (String elem : elems) {
      lpht.insert(elem);
    }

    for (String elem : elems) {
      assertTrue("Every element inserted should be in the hash: " + elem, lpht.search(elem));
    }
  }
  
  @Test
  public void insertSameElementTest() {
    // Es posible que no haya que implementar esto
    // pero es razonable pensar que si se inserta muchas
    // veces el mismo String, no debiera volver a agregarse
    // al hash!
    
    // Si se implementa esto, tendr[e que cambiar varios tests!
    fail("do not know if I should implement this");
  }

  @Test
  public void hashInsertionCircularityTest() {
    String elem = "hola";
    String elem2 = "chao";

    ((LinearProbingHashingTree) lpht).insert(elem, true);
    ((LinearProbingHashingTree) lpht).insert(elem2, true);

    assertTrue(
        "Both elements should be in the LPHT, and this insert method "
            + "does so at the last space of the hashingTable, so it confirms circularity",
        lpht.search(elem) && lpht.search(elem2));
    assertEquals("The last hashTable space should be the first element inserted", elem,
        root[lpht.getLength() - 1]);
    assertEquals("The first hashTable space should be the second element inserted", elem2, root[0]);
  }

  @Test
  public void rehashInsertionTest() {
    int maxOccupation = ((LinearProbingHashingTree) lpht).getMaxOccupation();
    int length1, length2, length3, size1, size2, size3;
    size1 = lpht.getSize();
    length1 = lpht.getLength();

    for (int i = 0; i < maxOccupation + 1; i++) {
      lpht.insert("a");
    }
    size2 = lpht.getSize();
    length2 = lpht.getLength();


    assertEquals("First size shoulb be 0", 0, size1);
    assertEquals("First length should be hashLength", hashLength, length1);
    assertEquals("Second size should be maxOccupation + 1", maxOccupation + 1, size2);
    assertEquals("Second length should also be double the hashLength", 2 * hashLength, length2);
  }

  @Test
  public void hashSucccesfulSearchTest() {
    String elem = "hola";

    lpht.insert(elem);

    assertTrue("The element should be in the hashTable", lpht.search(elem));
  }

  @Test
  public void hashUnsucccesfulSearchTest() {
    String elem = "hola";

    assertFalse("The element should not be in the hashTable", lpht.search(elem));
  }

  @Test
  public void hashSearchCircularityTest() {
    String elem = "hola";

    assertEquals(hashLength, ((LinearProbingHashingTree) lpht).search(elem, true));
  }

  @Test
  public void hashOccupationLevelTest() {
    String[] elems = {"a", "ab", "abc", "abcd", "abcde"};
    int size1 = lpht.getSize();

    for (String elem : elems) {
      lpht.insert(elem);
    }

    assertEquals("LPHT should initialy have size 0.", 0, size1);
    assertEquals("LPHT should know elements were inserted.", size1 + elems.length, lpht.getSize());
  }


  @Test
  public void goodHashingFunctionTest() {
    // Deberia poder verificar la reparticion de los elementos
    // dentro de la estructura para asegurar que esten bien
    // repartidos.
    fail("not yet implemented");
  }

  @Test
  public void alwaysPositiveHashFunctionTest() {
    String text =
        "nam at lectus urna duis convallis convallis tellus id interdum velit laoreet id donec ultrices tincidunt arcu non sodales neque sodales ut etiam sit amet nisl purus in mollis nunc sed id semper risus in hendrerit gravida rutrum quisque non tellus orci ac auctor augue mauris augue neque gravida in fermentum et sollicitudin ac orci phasellus egestas tellus rutrum tellus pellentesque eu tincidunt tortor aliquam nulla facilisi cras fermentum odio eu feugiat pretium nibh ipsum consequat nisl vel pretium lectus quam id leo in vitae turpis massa sed elementum tempus egestas sed sed risus pretium quam vulputate dignissim suspendisse in est ante";
    String[] elems = text.split(" ");
    LinearProbingHashingTree tmp = (LinearProbingHashingTree) lpht;
    int hash;

    for (String elem : elems) {
      hash = tmp.getHashNum(elem);
      assertTrue("Every hash should be between 0 and hashLength.", hash >= 0 && hash < hashLength);
    }
  }

  @Test
  public void fillPercentageBelow40PercentTest() {
    int occupation;
    LinearProbingHashingTree tmp = (LinearProbingHashingTree) lpht;

    occupation = tmp.getMaxOccupation();

    for (int i = 0; i < occupation; i++) {
      lpht.insert("a");
    }
    occupation = lpht.getSize();

    assertTrue("The occupation level should be lower than 40% before rehashing.",
        occupation <= tmp.getMaxOccupation());

    occupation = tmp.getMaxOccupation();
    for (int i = 0; i < occupation; i++) {
      lpht.insert("b");
    }
    occupation = lpht.getSize();

    assertTrue("The occupation level should be lower than 40% after rehashing.",
        occupation <= tmp.getMaxOccupation());
  }

  @Test
  public void insertionResistanceTest() {
    int hashLength = 262144;
    StringDictionary lpht = new LinearProbingHashingTree(hashLength);
    // lo anterior es una aproximacion del espacio ocupado
    
    String text =
        "nam at lectus urna duis convallis convallis tellus id interdum velit laoreet id donec ultrices tincidunt arcu non sodales neque sodales ut etiam sit amet nisl purus in mollis nunc sed id semper risus in hendrerit gravida rutrum quisque non tellus orci ac auctor augue mauris augue neque gravida in fermentum et sollicitudin ac orci phasellus egestas tellus rutrum tellus pellentesque eu tincidunt tortor aliquam nulla facilisi cras fermentum odio eu feugiat pretium nibh ipsum consequat nisl vel pretium lectus quam id leo in vitae turpis massa sed elementum tempus egestas sed sed risus pretium quam vulputate dignissim suspendisse in est ante";
    String[] elems = text.split(" ");
    int iter = 1000;

    long initTime = System.nanoTime();
    for (int i = 0; i < iter; i++) {
      for (String elem : elems) {
        lpht.insert(elem);
      }
    }
    long finalTime = System.nanoTime();
    long time = (finalTime - initTime);
    System.out.println("Test de Resistencia de Inserciones");
    System.out.println("Se insertaron: " + (iter * elems.length) + " elementos.");
    System.out.println("Tiempo total: " + time / 1e6 + "ms");
    System.out.println("Largo LPHT: " + lpht.getLength());
    System.out.println("Size LPHT: " + lpht.getSize());
    System.out.println("maxOccupation LPHT: " +
          ((LinearProbingHashingTree) lpht).getMaxOccupation());
    assertTrue(true);
  }

}
