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
        this.root = new PatriciaNode("", false, null, new ArrayList<Integer>(), 2);
    }

    public ArrayList<Integer> search(String key) {
        return root.search(key);
    }

    public ArrayList<Integer> search(String key, int text) {
        return root.search(key, text);
    }

    public void insert(String word, int pos) {
        root.insert(word, pos);
    }

    public void insert(String word, int pos, int text) {
        root.insert(word, pos, text);
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

    public double getSimilarity() {
        return root.getSimilarity();
    }
}
