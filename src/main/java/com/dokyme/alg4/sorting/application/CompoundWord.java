package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.5.2
 *
 * @author dokym
 * @date 2018/5/27-12:07
 * Description:
 */
public class CompoundWord {

    public static int identify(String[] input, String[] compound) {
        new Quick().sort(input);
        int size = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[j].startsWith(input[i])) {
                    compound[size++] = input[j];
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        String[] input = new String[]{"a", "b", "c", "ab", "ac"};
        String[] compound = new String[5];
        int size = identify(input, compound);
        for (int i = 0; i < size; i++) {
            StdOut.println(compound[i]);
        }
    }
}
