package com.dokyme.alg4.searching.st;

import java.util.Arrays;
import java.util.Comparator;

import com.dokyme.alg4.FileIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/2-19:12
 * Description:
 */
public class FrequencyCountDictionary {

    private static class Word implements Comparable<Word> {
        String value;
        int frequency;

        public Word(String value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Word o) {
            return frequency - o.frequency;
        }
    }

    public static void count(String dictname, String filename, SymbolTable<String, Integer> symbolTable) {
        try {
            FileIn.setFile(dictname);
            String[] dictWords = FileIn.readAllStrings();
            FileIn.setFile(filename);
            String[] words = FileIn.readAllStrings();
            for (String word : words) {
                if (symbolTable.contains(word)) {
                    symbolTable.put(word, symbolTable.get(word) + 1);
                } else {
                    symbolTable.put(word, 1);
                }
            }
            Word[] wl = new Word[symbolTable.size()];
            int i = 0;
            for (String w : symbolTable.keys()) {
                wl[i++] = new Word(w, symbolTable.get(w));
            }
            Arrays.sort(wl, new Comparator<Word>() {
                @Override
                public int compare(Word o1, Word o2) {
                    return o1.frequency - o2.frequency;
                }
            });
            for (Word w : wl) {
                StdOut.println(w + "\t-\t" + w.frequency);
            }
            StdOut.println("=================================");
            for (String w : dictWords) {
                if (symbolTable.contains(w)) {
                    StdOut.println(w + "\t-\t" + symbolTable.get(w));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
