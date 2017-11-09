package com.cc4102.stringDict;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Lucas Puebla Silva
 *
 */
public class PatriciaTree implements StringDictionary {

    private PatriciaNode root;

    public PatriciaTree() {
        this.root = new PatriciaNode("", false, null, new ArrayList<Integer>());
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
        HashSet<String> result = new HashSet<String>();
        root.getKeys("", result);
        return result.toArray(new String[result.size()]);
    }

    public int count(String key) {
        return root.search(key).size();
    }

    @Override
    public String getClassStr() {
        return "PatriciaTree";
    }
}
