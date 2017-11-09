package com.cc4102.experiment;

import com.cc4102.stringDict.StringDictionary;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Logger {

    private static void log(String s, String fileName) {
        byte data[] = s.getBytes();
        Path p = Paths.get("./" + fileName + ".csv");

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x.toString());
        }
    }

    static public void logSuccSearch(String classStr, int totalSize, int wordLength, long time) {
        String s = totalSize + "," + wordLength + "," + time + '\n';
        log(s, "succSearch" + classStr);
    }

    public static void logUnsuccSearch(String classStr, int totalSize, int wordLength, long time) {
        String s = totalSize + "," + wordLength + "," + time + '\n';
        log(s, "unsuccSearch" + classStr);
    }
}
