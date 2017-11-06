package com.cc4102.stringDict;

import java.util.ArrayList;
import java.util.Collections;

class PatriciaNode {
    private String str;
    private boolean isTerminal;
    private PatriciaNode father;
    private ArrayList<PatriciaNode> children;
    private ArrayList<Integer> values;

    public PatriciaNode(String str, boolean isTerminal, PatriciaNode father, int value) {
        this.str = str;
        this.isTerminal = isTerminal;
        this.father = father;
        this.children = new ArrayList<>(2);
        this.values = new ArrayList<>(Collections.singletonList(value));
    }

    public PatriciaNode(String str, boolean isTerminal, PatriciaNode father, ArrayList<Integer> values) {
        this.str = str;
        this.isTerminal = isTerminal;
        this.father = father;
        this.children = new ArrayList<>(2);
        this.values = new ArrayList<>(values);
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

    public void addChild(PatriciaNode child) {
        children.add(child);
        child.setFather(this);
        this.setTerminal(false);
    }

    protected ArrayList<Integer> search(String str) {
        if (str.equals(this.str) && isTerminal) {
            return values;
        } else if (isTerminal) {
            return null;
        } else {
            for (PatriciaNode node : this.children) {
                String nodeStr = node.getStr();
                if (str.startsWith(nodeStr)) {
                    if (str.equals(nodeStr) && node.isTerminal) {
                        return node.values;
                    }
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
        if (children.size() == 0) {
            isTerminal = true;
        }
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

    protected void addString(String str, int value, String completeStr) {
        if (str.length() == 0) { // Caso 2
            if (children.get(0) != null) {
                children.get(0).reinsertFromLeaf(completeStr, value);
            } else if (children.get(1) != null) {
                children.get(1).reinsertFromLeaf(completeStr, value);
            }
        }
        char first = str.charAt(0);
        if (children.size() > 0) {
            char a = '&';
            if (!children.get(0).getStr().equals(""))
                a = children.get(0).getStr().charAt(0);
            if (a != first && children.size() > 1) {
                if (children.get(1).getStr().charAt(0) != first) { // Caso 1
                    children.get(0).reinsertFromLeaf(completeStr, value);
                } else {
                    boolean foundPath = false;
                    for (PatriciaNode node : this.children) {
                        String nodeStr = node.getStr();
                        if (str.startsWith(nodeStr) && !nodeStr.equals("")) {
                            foundPath = true;
                            int prefixLength = largestCommonPrefix(nodeStr, str).length();
                            String insertStr = str.substring(prefixLength, str.length());
                            node.addString(insertStr, value, completeStr);
                        }
                    }
                    if (!foundPath) {
                        PatriciaNode newNode = new PatriciaNode(str, true, this, value);
                        this.addChild(newNode);
                    }
                }
            } else {
                boolean foundPath = false;
                for (PatriciaNode node : this.children) {
                    String nodeStr = node.getStr();
                    if (str.startsWith(nodeStr) && !nodeStr.equals("")) {
                        foundPath = true;
                        int prefixLength = largestCommonPrefix(nodeStr, str).length();
                        String insertStr = str.substring(prefixLength, str.length());
                        node.addString(insertStr, value, completeStr);
                    }
                }
                if (!foundPath) {
                    PatriciaNode newNode = new PatriciaNode(str, true, this, value);
                    this.addChild(newNode);
                }
            }
        } else if (isTerminal) { // Caso 3
            this.reinsertFromLeaf(completeStr, value);
        }
    }

    private void reinsertFromLeaf(String str, int value) {
        PatriciaNode leaf = this;
        while (!leaf.isTerminal()) {
            leaf = leaf.children.get(0);
        }
        String leafStr = leaf.getEntireString();
        String lcp = largestCommonPrefix(str, leafStr);
        String insertString = str.substring(lcp.length());

        PatriciaNode root = this;
        while (root.father != null) {
            root = root.father;
        }
        PatriciaNode current = root;

        boolean found = false;
        while (!found) {
            String curStr = current.getStr();
            if (curStr.startsWith(lcp) && current.isTerminal()) {
                if (curStr.length() == lcp.length()) { // Caso 2
                    PatriciaNode toInsert = new PatriciaNode(insertString, true, current, value);
                    current.addChild(toInsert);
                    found = true;
                } else { // Caso 1: reemplazar nodo por uno con dos hijos
                    current.getFather().removeChildLocally(current);
                    String all = current.getEntireString();
                    String nodePrefix = largestCommonPrefix(str, all);
                    String newNodeStr = curStr;
                    String leftChildStr = all.substring(nodePrefix.length());
                    String rightChildStr = str.substring(nodePrefix.length());
                    PatriciaNode newNode = new PatriciaNode(newNodeStr, false, current.getFather(), -1);
                    PatriciaNode leftChild = new PatriciaNode(leftChildStr, true, newNode, current.getValues());
                    PatriciaNode rightChild = new PatriciaNode(rightChildStr, true, newNode, value);
                    newNode.addChild(leftChild);
                    newNode.addChild(rightChild);
                    current.getFather().addChild(newNode);
                    found = true;
                }
            } else { // Seguir buscando
                String childStr;
                PatriciaNode node;
                if (current.children.get(0) != null) {
                    node = current.children.get(0);
                    childStr = current.children.get(0).getStr();
                    if (lcp.startsWith(childStr) && !childStr.equals("")) {
                        String trim = largestCommonPrefix(childStr, lcp);
                        lcp = lcp.substring(trim.length(), lcp.length());
                        current = node;
                    } else if (current.children.get(1) != null) {
                        node = current.children.get(1);
                        childStr = current.children.get(1).getStr();
                        if (lcp.startsWith(childStr)) {
                            String trim = largestCommonPrefix(childStr, lcp);
                            lcp = lcp.substring(trim.length());
                            current = node;
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public static void main(String[] args) {
        PatriciaNode node = new PatriciaNode("", true, null, 0);
        System.out.println(node.largestCommonPrefix("arboleda", "arboledal"));
        node.addString("arbol", 5, "arbol");
        node.addString("arboleda", 2, "arboleda");
        node.addString("arboledal", 3, "arboledal");
        node.addString("barcasa", 4, "barcasa");
        node.addString("barquito", 3, "barquito");
        node.addString("arbok", 10, "arbok");
        System.out.println(node.search("arbol"));
    }
}
