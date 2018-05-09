package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.lang.reflect.Array;
import java.util.Arrays;

import static com.dokyme.alg4.sorting.basic.SortCompare.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/3/10-13:19
 * Description:
 */
public class Example {
    public static void sort(Comparable[] a) {
    }

    public static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, false);
    }

    /**
     * 检查一个数组的元素是否按照升序排列。
     *
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a, boolean desc) {
        if (!desc) {
            for (int i = 1; i < a.length; i++) {
                if (less(a[i], a[i - 1])) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < a.length; i++) {
                if (less(a[i - 1], a[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Comparable[] generateTestData(Object cls, int length) {
        if (cls.getClass().equals(Double.class)) {
            Double[] a = new Double[length];
            for (int i = 0; i < length; i++) {
                a[i] = StdRandom.uniform();
            }
            return a;
        } else if (cls.getClass().equals(Integer.class)) {
            Integer[] a = new Integer[length];
            for (int i = 0; i < length; i++) {
                a[i] = StdRandom.uniform(length);
            }
            return a;
        } else {
            return null;
        }
    }

    public static void testSorting(Sorting sorting) {
        testSorting(sorting, 1000, false);
    }

    public static void testSorting(Sorting sorting, int length) {
        testSorting(sorting, length, false);
    }

    public static void testSorting(Sorting sorting, boolean desc) {
        testSorting(sorting, 1000, desc);
    }

    public static void testSorting(Sorting sorting, int length, boolean desc) {
        Double[] array = new Double[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform();
        }
        sorting.sort(array);
        assert isSorted(array, desc);
        for (double d : array) {
            StdOut.print(d + "\n");
        }
    }

    public static void doubleTesting(Sorting sorting) {
        final int start = 1 << 8;
        final int end = 1 << 22;
        double prev = 1;
        for (int i = start; i <= end; i <<= 1) {
            double time = 0;
            for (int t = 0; t < 100; t++) {
                Comparable[] array = generateTestData(new Double(1), i);
                Stopwatch stopwatch = new Stopwatch();
                sorting.sort(array);
                time += stopwatch.elapsedTime();
            }
            double arraysort = testArraysSort(i, 100);
            StdOut.printf("size:%d\ttime:%f\tratio:%f\tArrays.sort:%f\n", i, time, time / prev, arraysort);
            prev = time;
        }
    }

    public static void main(String[] args) {
//        Double[] pq = (Double[]) generateTestData(new Double(1.0), 100);
//        sort(pq);
//        exch(pq, 0, 1);
//        boolean r = isSorted(pq);
//        assert r;
//        show(pq);
    }
}
