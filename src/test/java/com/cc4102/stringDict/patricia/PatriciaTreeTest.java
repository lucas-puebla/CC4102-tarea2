package com.cc4102.stringDict.patricia;

import com.cc4102.stringDict.PatriciaTree;
import com.cc4102.stringDict.StringDictionary;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

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
    public void insertSubStr() {
        tree.insert("una", 1);
        tree.insert("un", 2);

        assertTrue(tree.search("una").contains(1));
        assertTrue(tree.search("un").contains(2));
    }
}
