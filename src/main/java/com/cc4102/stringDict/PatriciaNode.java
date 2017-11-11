package com.cc4102.stringDict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class PatriciaNode {
    private String str;
    private boolean isTerminal;
    private PatriciaNode father;
    private ArrayList<PatriciaNode> children;
    private ArrayList<Integer>[] values;

    public PatriciaNode(String str, boolean isTerminal, PatriciaNode father, ArrayList<Integer> values, int textsNum) {
        this.str = str;
        this.isTerminal = isTerminal;
        this.father = father;
        this.children = new ArrayList<PatriciaNode>();
        this.values = (ArrayList<Integer>[]) new ArrayList[textsNum];
        this.values[0] = values;
        this.values[1] = new ArrayList<Integer>();
    }

    public PatriciaNode(String str, boolean isTerminal, PatriciaNode father, int value) {
        this(str, isTerminal, father, new ArrayList<Integer>(Collections.singletonList(value)), 2);
    }

    public PatriciaNode(String str, boolean isTerminal, PatriciaNode father) {
        this(str, isTerminal, father, new ArrayList<Integer>(), 2);
    }

    public PatriciaNode() {
        this("", false, null, -1);
    }

    protected String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    protected void setTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    protected PatriciaNode getFather() {
        return father;
    }

    public void setFather(PatriciaNode father) {
        this.father = father;
    }

    protected ArrayList<Integer> search(String str) {
        return search(str, 0);
    }

    protected ArrayList<Integer> search(String str, int text) {
        int offset = 0;
        PatriciaNode current = this;
        boolean found = false;
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (!found) {
            boolean tookPath = false;
            for (PatriciaNode child : current.children) {
                if (child.getStr().equals(str.substring(offset)) && child.isTerminal()) {
                    result.addAll(child.getValues(text));
                    found = true;
                    tookPath = true;
                    break;
                } else if (str.substring(offset).startsWith(child.getStr()) && !child.getStr().equals("")) {
                    current = child;
                    tookPath = true;
                    offset += largestCommonPrefix(child.getStr(), str.substring(offset)).length();
                    break;
                }
            }
            if (!tookPath) {
                break;
            }
        }
        return result;
    }

    private String getEntireString() {
        PatriciaNode act = this;
        StringBuilder res = new StringBuilder();
        while (act.getFather() != null) {
            res.insert(0, act.getStr());
            act = act.getFather();
        }
        return res.toString();
    }

    private String largestCommonPrefix(String a, String b) {
        int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return a.substring(0, i);
            }
        }
        return (a.substring(0, minLength));
    }

    public ArrayList<Integer> getValues(int i) {
        return values[i];
    }

    public void removeChild(PatriciaNode child) {
        this.children.remove(child);
    }

    public void addChild(PatriciaNode child) {
        this.children.add(child);
        child.setFather(this);
    }

    public void insert(String word, int pos) {
        insert(word, pos, 0);
    }

    public void insert(String word, int pos, int text) {
        int offset = 0;
        PatriciaNode current = this;
        boolean found = false;
        while (!found) {
            boolean tookPath = false;
            for (PatriciaNode child : current.children) {
                if (child.getStr().equals(word.substring(offset)) && child.isTerminal()) {
                    child.getValues(text).add(pos);
                    return;
                } else if (word.substring(offset).startsWith(child.getStr()) && !child.getStr().equals("")) {
                    current = child;
                    offset += largestCommonPrefix(word.substring(offset), child.getStr()).length();
                    tookPath = true;
                    break;
                } else if (largestCommonPrefix(word.substring(offset), child.getStr()).length() > 0) {
                    String lcp = largestCommonPrefix(word.substring(offset), child.getStr());
                    offset += lcp.length();
                    String newWord = word.substring(offset);
                    PatriciaNode newNode = new PatriciaNode(lcp, current.isTerminal(), current);
                    if (newWord.length() > 0) {
                        PatriciaNode insertedNode = new PatriciaNode(newWord, true, current, pos);
                        newNode.addChild(insertedNode);
                        child.setStr(child.getStr().substring(lcp.length()));
                    } else {
                        newNode.setTerminal(true);
                        newNode.getValues(text).add(pos);
                        String str = child.getEntireString().replace(word, "");
                        child.setStr(str);
                    }
                    current.removeChild(child);
                    newNode.addChild(child);
                    current.addChild(newNode);
                    return;
                }
            }
            if (!tookPath) {
                PatriciaNode newNode = new PatriciaNode(word.substring(offset), true, current, pos);
                current.addChild(newNode);
                return;
            }
        }
    }

    public HashSet<String> getKeys(String prefix, HashSet<String> result) {
        if (this.isTerminal()) {
            result.add(prefix + this.getStr());
        }
        for (PatriciaNode child : this.getChildren()) {
            child.getKeys(prefix + this.getStr(), result);
        }
        return result;
    }

    public ArrayList<PatriciaNode> getChildren() {
        return children;
    }

    public double getSimilarity() {
        double[] sim = new double[2];
        this.addSim(sim);
        return 1.0 - (sim[0] / sim[1]);
    }

    private void addSim(double[] sim) {
        if (this.isTerminal()) {
            int firstSize = getValues(0).size();
            int secondSize = getValues(1).size();
            sim[1] += firstSize + secondSize;
            sim[0] += Math.abs(firstSize - secondSize);
        }
        for (PatriciaNode child : children)
            child.addSim(sim);
    }
}