package com.dokyme.alg4.searching.st;

import edu.princeton.cs.algs4.StdOut;

import java.io.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-19:02
 * Description:
 */
public class FrequencyCounter {

    private static void count(SymbolTable<String, Integer> symbolTable, int threshold, String filename, int count) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (String tok : tokens) {
                    if (tok.length() < threshold) {
                        continue;
                    }
                    if (symbolTable.contains(tok)) {
                        symbolTable.put(tok, symbolTable.get(tok) + 1);
                    } else {
                        symbolTable.put(tok, 1);
                    }
                }
            }
            
        } catch (IOException e) {

        }
    }

    public static void count(SymbolTable<String, Integer> symbolTable, int threshold, String filename) {
        try {
            int total = 0;
            String lastWord = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (String tok : tokens) {
                    if (tok.length() < threshold) {
                        continue;
                    }
                    total++;
                    lastWord = tok;
                    if (symbolTable.contains(tok)) {
                        symbolTable.put(tok, symbolTable.get(tok) + 1);
                    } else {
                        symbolTable.put(tok, 1);
                    }
                }
            }
            StdOut.println("Last word:" + lastWord + ", " + total + " words so far.");
//            String max = "";
//            symbolTable.put(max, 0);
//            for (String word : symbolTable.keys()) {
//                if (word.length() > 10 && symbolTable.get(word) > symbolTable.get(max)) {
//                    max = word;
//                }
//            }
//            StdOut.println(max + " " + symbolTable.get(max));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        count(new BinarySearchSymbolTable<>(10), 10, "src/main/resources/tale.txt");
    }
}
