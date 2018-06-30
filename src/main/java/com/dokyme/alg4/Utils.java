package com.dokyme.alg4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/8-18:16
 * Description:
 */
public class Utils {

    public static void printSeperation() {
        StdOut.println();
        StdOut.println("===========================");
    }

    public static String randomString(int maxLength) {
        int length = StdRandom.uniform(maxLength);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append((char) ('a' + StdRandom.uniform(0, 26)));
        }
        return builder.toString();
    }

    public static void main(String[] args) {

    }
}
