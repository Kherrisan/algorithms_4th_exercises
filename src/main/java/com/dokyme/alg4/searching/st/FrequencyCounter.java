package com.dokyme.alg4.searching.st;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.util.Scanner;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/1-19:02
 * Description:
 */
public class FrequencyCounter {

    public static void count(ST<String, Integer> st, int threshold, String filename) {
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
                    if (st.contains(tok)) {
                        st.put(tok, st.get(tok) + 1);
                    } else {
                        st.put(tok, 1);
                    }
                }
            }
            StdOut.println("Last word:" + lastWord + ", " + total + " words so far.");
//            String max = "";
//            st.put(max, 0);
//            for (String word : st.keys()) {
//                if (word.length() > 10 && st.get(word) > st.get(max)) {
//                    max = word;
//                }
//            }
//            StdOut.println(max + " " + st.get(max));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        count(new BinarySearchST<>(10), 10, "src/main/resources/tale.txt");
    }
}
