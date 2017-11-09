package com.cc4102.stringDict.patricia;

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
        tree.insert("hola", 3);
        assertTrue("Inserted text should be in the tree", tree.search("hola").contains(3));
    }

    @Test
    public void insertNotRelatedElements() {
        tree.insert("a", 0);
        tree.insert("b", 1);
        tree.insert("c", 2);
        assertTrue(tree.search("a").contains(0));
        assertTrue(tree.search("b").contains(1));
        assertTrue(tree.search("c").contains(2));
    }

    @Test
    public void simpleSplit() {
        tree.insert("arbol", 0);
        tree.insert("arboleda", 1);
        assertTrue(tree.search("arbol").contains(0));
        assertTrue(tree.search("arboleda").contains(1));
    }

    @Test
    public void commonPrefixSplit() {
        tree.insert("arbol", 0);
        tree.insert("arboleda", 1);
        tree.insert("arbok", 2);
        assertTrue(tree.search("arbol").contains(0));
        assertTrue(tree.search("arboleda").contains(1));
        assertTrue(tree.search("arbok").contains(2));
    }

    @Test
    public void invertedPrefixSplit() {
        tree.insert("una", 0);
        tree.insert("un", 1);
        tree.insert("u", 2);
        assertTrue(tree.search("una").contains(0));
        assertTrue(tree.search("un").contains(1));
        assertTrue(tree.search("u").contains(2));
    }

    @Test
    public void insertSeveralElements() {
        String elems[] = {"arbol", "arboleda", "arboledal", "barcasa", "barquito", "arbok", "arbol"};
        int values[] = {3, 4, 5, 10, 23, 14, 33};
        for (int i = 0; i < elems.length; i++) {
            tree.insert(elems[i], values[i]);
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
        tree.insert("hola", values.get(0));
        tree.insert("hola", values.get(1));

        assertTrue(tree.search("hola").containsAll(values));
    }

    @Test
    public void getKeysTest() {
        String elems[] = {"arbol", "arboleda", "arboledal", "barcasa", "barquito", "arbok"};
        int values[] = {3, 4, 5, 10, 23, 14};
        for (int i = 0; i < elems.length; i++) {
            tree.insert(elems[i], values[i]);
        }
        String res[] = tree.getKeys();
        assertTrue(Arrays.asList(res).containsAll(Arrays.asList(elems)));
        assertTrue(Arrays.asList(elems).size() == Arrays.asList(res).size());
    }
}
