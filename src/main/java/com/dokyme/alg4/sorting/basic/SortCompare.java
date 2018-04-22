package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/3/10-14:12
 * Description:
 */
public class SortCompare {
    public static final String INSERTION = "insertion";
    public static final String SELECTION = "selection";
    public static final String SHELL = "shell";
    public static final String INSERTION_WITHOUT_EXCH = "insertion without exchange";
    public static final String INSERTION_INT = "insertion int";

    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (INSERTION.equals(alg.toLowerCase())) {
            Insertion.sort(a);
        } else if (SELECTION.equals(alg.toLowerCase())) {
            Selection.sort(a);
        } else if (SHELL.equals(alg.toLowerCase())) {
            Shell.sort(a);
        } else if (INSERTION_WITHOUT_EXCH.equals(alg.toLowerCase())) {
            Insertion.sortWithoutExch(a);
        } else if (INSERTION_INT.equals(alg.toLowerCase())) {
        }

        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int length, int times) {
        double total = 0.0;
        Double[] a = new Double[length];
        for (int t = 0; t < times; t++) {
            for (int i = 0; i < length; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void testTwoDistinctPrimaryKey(int length, int times) {
        int[] array = new int[length];
        double t1 = 0.0, t2 = 0.0;
        for (int j = 0; j < times; j++) {
            for (int i = 0; i < length; i++) {
                array[i] = StdRandom.uniform(2);
            }
            Stopwatch stopwatch = new Stopwatch();
            Insertion.sort(array);
            t1 += stopwatch.elapsedTime();
            for (int i = 0; i < length; i++) {
                array[i] = StdRandom.uniform(2);
            }
            Stopwatch stopwatch2 = new Stopwatch();
            Insertion.sort(array);
            t2 += stopwatch2.elapsedTime();
        }
        StdOut.printf("Insertion:%.1f\tSelection:%.1f\n", t1, t2);
    }

    public static void testIntWithIntegerAutoBoxing(int length, int times) {
        Integer[] array = new Integer[length];
        double total1 = 0.0;
        for (int t = 0; t < times; t++) {
            for (int i = 0; i < length; i++) {
                array[i] = StdRandom.uniform(length);
            }
            Stopwatch stopwatch = new Stopwatch();
            Insertion.sort(array);
            total1 += stopwatch.elapsedTime();
        }
        double total2 = 0.0;
        for (int t = 0; t < times; t++) {
            for (int i = 0; i < length; i++) {
                array[i] = StdRandom.uniform(length);
            }
            Stopwatch stopwatch = new Stopwatch();
            Insertion.sortWithAutoBoxing(array);
            total2 += stopwatch.elapsedTime();
        }
        StdOut.printf("For %d random Integers\n  %s is", length, INSERTION);
        StdOut.printf(" %.1f times faster than %s\n", total2 / total1, INSERTION_INT);
    }

    public static void testThreeAlgsWithPowerOf2() {
        int length = 1 << 6;
        int times = 256;
        //数组长度从2^6增到2^16
        for (int i = 0; i < 10; i++) {
            double t1 = timeRandomInput(SELECTION, length, times);
            double t2 = timeRandomInput(INSERTION, length, times);
            double t3 = timeRandomInput(SHELL, length, times);
            StdOut.printf("Selection:%.1f\tInsertion:%.1f\tShell:%.1f", t1, t2, t3);
            System.out.println();
            length <<= 1;
        }
    }

    public static double testArraysSort(int length, int times) {
        double[] array = new double[length];
        double total = 0.0;
        for (int j = 0; j < times; j++) {
            for (int i = 0; i < array.length; i++) {
                array[i] = StdRandom.uniform();
            }
            Stopwatch w = new Stopwatch();
            Arrays.sort(array);
            total += w.elapsedTime();
            if (j % 10 == 0) {
                StdOut.println(j);
            }
        }
        return total;

    }

    public static double testSort(Sorting sorting, int length, int times) {
        double total = 0;
        try {
            for (int t = 0; t < times; t++) {
                Double[] array = (Double[]) generateTestData(new Double(1.0), length);
                Stopwatch stopwatch = new Stopwatch();
                sorting.sort(array);
                total += stopwatch.elapsedTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0;
        }
        return total;
    }

    public static void main(String[] args) {
//        int N = 10000;
//        int T = 200;
//        String alg1 = SHELL;
//        String alg2 = INSERTION_WITHOUT_EXCH;
//        double t1 = timeRandomInput(alg1, N, T);
//        double t2 = timeRandomInput(alg2, N, T);
//        StdOut.printf("For %d random Doubles\n  %s is", N, alg1);
//        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
//        testIntWithIntegerAutoBoxing(1000, 100);
//        testThreeAlgsWithPowerOf2();
        testTwoDistinctPrimaryKey(10000, 1000);
    }

}
