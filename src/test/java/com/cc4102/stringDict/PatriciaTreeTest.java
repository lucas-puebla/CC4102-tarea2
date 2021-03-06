package com.cc4102.stringDict;

import com.cc4102.stringDict.PatriciaTree;
import com.cc4102.stringDict.StringDictionary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.*;

public class PatriciaTreeTest {

    StringDictionary tree = new PatriciaTree();

    @Test
    public void notNull() {
        assertNotNull(tree);
    }

    @Test
    public void insertOneElement() {
        tree.insert("hola", 3, 0);
        assertTrue("Inserted text should be in the tree", tree.search("hola").contains(3));
    }

    @Test
    public void insertNotRelatedElements() {
        tree.insert("a", 0, 0);
        tree.insert("b", 1, 0);
        tree.insert("c", 2, 0);
        assertTrue(tree.search("a").contains(0));
        assertTrue(tree.search("b").contains(1));
        assertTrue(tree.search("c").contains(2));
    }

    @Test
    public void simpleSplit() {
        tree.insert("arbol", 0, 0);
        tree.insert("arboleda", 1, 0);
        assertTrue(tree.search("arbol").contains(0));
        assertTrue(tree.search("arboleda").contains(1));
    }

    @Test
    public void commonPrefixSplit() {
        tree.insert("arbol", 0, 0);
        tree.insert("arboleda", 1, 0);
        tree.insert("arbok", 2, 0);
        assertTrue(tree.search("arbol").contains(0));
        assertTrue(tree.search("arboleda").contains(1));
        assertTrue(tree.search("arbok").contains(2));
    }

    @Test
    public void invertedPrefixSplit() {
        tree.insert("una", 0, 0);
        tree.insert("un", 1, 0);
        tree.insert("u", 2, 0);
        assertTrue(tree.search("una").contains(0));
        assertTrue(tree.search("un").contains(1));
        assertTrue(tree.search("u").contains(2));
    }

    @Test
    public void insertSeveralElements() {
        String elems[] = {"arbol", "arboleda", "arboledal", "barcasa", "barquito", "arbok", "arbol"};
        int values[] = {3, 4, 5, 10, 23, 14, 33};
        for (int i = 0; i < elems.length; i++) {
            tree.insert(elems[i], values[i], 0);
        }
        for (int i = 0; i < elems.length; i++) {
            assertTrue("Inserted text " + elems[i] + " and its value should be in the tree",
                    tree.search(elems[i]).contains(values[i]));
        }
    }

    @Test
    public void insertCopy() {
        String repeated = "hola";
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(2);
        values.add(3);
        tree.insert("hola", values.get(0), 0);
        tree.insert("hola", values.get(1), 0);

        assertTrue(tree.search("hola").containsAll(values));
    }

    @Test
    public void getKeysTest() {
        String elems[] = {"arbol", "arboleda", "arboledal", "barcasa", "barquito", "arbok"};
        int values[] = {3, 4, 5, 10, 23, 14};
        for (int i = 0; i < elems.length; i++) {
            tree.insert(elems[i], values[i], 0);
        }
        String res[] = tree.getKeys();
        assertTrue(Arrays.asList(res).containsAll(Arrays.asList(elems)));
        assertTrue(Arrays.asList(elems).size() == Arrays.asList(res).size());
    }

    @Test
    public void sameTextSimilarity() {
        String first[] = {"hola", "estos", "textos", "son", "similares"};
        String second[] = {"hola", "estos", "textos", "son", "similares"};
        for (int i = 0; i < first.length; i++) {
            tree.insert(first[i], 0, 0);
            tree.insert(second[i], 0, 1);
        }
        assertEquals("Both texts are the same, so they should have 1.0 of similarity", 1.0, tree.getSimilarity());
    }

    @Test
    public void differentTextsSimilarity() {
        String first[] = {"hola", "estos", "textos", "son", "similares"};
        String second[] = {"hola", "estos", "textos", "son", "parecidos"};
        for (int i = 0; i < first.length; i++) {
            tree.insert(first[i], 0, 0);
            tree.insert(second[i], 0, 1);
        }
        assertTrue("Both texts are different, so they shouldn't have 1.0 of similarity",
                1.0 != tree.getSimilarity());
    }
}
