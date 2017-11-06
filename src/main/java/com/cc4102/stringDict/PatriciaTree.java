package com.cc4102.stringDict;

import java.util.ArrayList;

/**
 * @author Lucas Puebla Silva
 *
 */
public class PatriciaTree implements StringDictionary {

    private PatriciaNode root;

    public PatriciaTree() {
        this.root = new PatriciaNode("", true, null, new ArrayList<Integer>());
    }

    public int getLength() {
        return 0;
    }

    public int getSize() {
        return 0;
    }

    public Object getRoot() {
        return root;
    }

    public ArrayList<Integer> search(String key) {
        return root.search(key);
    }

    public void insert(String word, int pos) {
        root.insert(word, pos);
    }

    public String[] getKeys() {
        return new String[0];
    }

    public int count(String key) {
        return root.search(key).size();
    }
}
