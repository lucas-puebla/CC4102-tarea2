package com.cc4102.stringDict;

import java.util.ArrayList;
import java.util.Collections;

class PatriciaNode {
    private String str;
    private int length;
    private boolean isTerminal;
    private PatriciaNode father;
    private ArrayList<PatriciaNode> children;
    private ArrayList<Integer> values;

    public PatriciaNode(String str, int length, boolean isTerminal, PatriciaNode father, int value) {
        this.str = str;
        this.length = length;
        this.isTerminal = isTerminal;
        this.father = father;
        this.values = new ArrayList<>(Collections.singletonList(value));
    }

    protected String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
        this.setLength(str.length());
    }

    public int getLength() {
        return length;
    }

    private void setLength(int length) {
        this.length = length;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    protected PatriciaNode getFather() {
        return father;
    }

    public void setFather(PatriciaNode father) {
        this.father = father;
    }

    public void addChild(PatriciaNode child) {

    }

    protected ArrayList<Integer> search(String str) {
        if(str.equals(this.str) && isTerminal) {
            return values;
        } else if (isTerminal) {
            return null;
        } else {
            for(PatriciaNode node : this.children) {
                String nodeStr = node.getStr();
                if(str.startsWith(nodeStr)) {
                    int prefixLength = largestCommonPrefix(nodeStr, str).length();
                    String searchStr = str.substring(prefixLength, str.length());
                    return node.search(searchStr);
                }
            }
        }
        return null;
    }

    private void removeChildLocally(PatriciaNode child) {
        children.remove(child);
        if(children.size() == 0) {
            isTerminal = true;
        }
    }

    private void addChildLocally(PatriciaNode child) {
        children.add(child);
        isTerminal = false;
    }

    private String getEntireString() {
        PatriciaNode act = this.getFather();
        StringBuilder res = new StringBuilder();
        while(act != null) {
            res.insert(0, act.getStr());
            act = act.getFather();
        }
        return res.toString();
    }

    private String largestCommonPrefix(String a, String b) {
        int minLength = Math.min(a.length(), b.length());
        for(int i = 0; i < minLength; i++) {
            if(a.charAt(i) != b.charAt(i)) {
                return a.substring(0, i);
            }
        }
        return(a.substring(0, minLength));
    }

    protected void addString(String str, int value) {

    }
}
