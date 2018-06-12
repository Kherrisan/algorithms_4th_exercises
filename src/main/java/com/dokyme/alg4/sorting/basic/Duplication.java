package com.dokyme.alg4.sorting.basic;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/12-21:29
 * Description:
 */
public class Duplication {

    public static void main(String[] args) {
        double tInsertion = 0, tSelection = 0, tQuick = 0;
        for (int t = 100; t < 1000000; t *= 10) {
            int[] array = new int[t];
            int[] copy = new int[t];
            int[] cpy = new int[t];
            for (int i = 0; i < array.length; i++) {
                int j = StdRandom.uniform(10);
                array[i] = j;
                copy[i] = j;
                cpy[i] = j;
            }
            Stopwatch sw = new Stopwatch();
            Insertion.sort(array);
            tInsertion += sw.elapsedTime();
            sw = new Stopwatch();
            Selection.sort(copy);
            tSelection += sw.elapsedTime();
            sw = new Stopwatch();
            new com.dokyme.alg4.sorting.quick.Quick().sort(cpy);
            tQuick += sw.elapsedTime();
        }
        StdOut.println("Selection: " + tSelection);
        StdOut.println("Insertion: " + tInsertion);
        StdOut.println("Quick: " + tQuick);
    }
}
