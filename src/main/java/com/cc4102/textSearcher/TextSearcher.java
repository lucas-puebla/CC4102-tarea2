package com.cc4102.textSearcher;

import com.cc4102.logger.Logger;
import com.cc4102.stopWatch.StopWatch;
import com.cc4102.stringDict.StringDictionary;
import com.cc4102.stringDict.TernarySearchTree;

import java.util.*;

public class TextSearcher {

    private TernarySearchTree tree = new TernarySearchTree();
    StopWatch sw = new StopWatch();
    Logger log = new Logger();

    public ArrayList<String> getRandomWords(double fraction, String[] text) {
        for (String word : text) {
            tree.insert(word, 0);
        }
        List<String> keys = Arrays.asList(tree.getKeys());
        Collections.shuffle(keys);
        ArrayList<String> res = new ArrayList<String>();
        double size = (double) keys.size();
        for (int i = 0; i < size * fraction; i++) {
            res.add(keys.get(i));
        }
        return res;
    }

    public void searchForWords(String[] words, StringDictionary dict) {
        int length = words.length * 10;
        for(int i = 0; i < words.length; i++) {
            sw.start();
            dict.search(words[i]);
            sw.stop();
            Logger.logSuccSearch(dict.getClassStr(), length, words[i].length(), sw.getTime());
        }
    }
}
