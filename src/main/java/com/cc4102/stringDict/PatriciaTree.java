package com.cc4102.stringDict;

import java.util.ArrayList;

/**
 * @author Lucas Puebla Silva
 *
 */
public class PatriciaTree implements StringDictionary {

    private PatriciaNode root;

    public PatriciaTree() {
        this.root = new PatriciaNode("", true, null, new ArrayList<>());
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public ArrayList<Integer> search(String key) {
        return root.search(key);
    }

    @Override
    public void insert(String word, int pos) {
        root.insert(word, pos);
    }
}
