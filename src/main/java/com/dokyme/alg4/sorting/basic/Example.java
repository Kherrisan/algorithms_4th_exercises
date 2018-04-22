package com.dokyme.alg4.sorting.basic;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;
import java.util.Arrays;

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

    /**
     * 检查一个数组的元素是否按照升序排列。
     *
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
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
        Double[] array = new Double[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform();
        }
        sorting.sort(array);
        assert isSorted(array);
        for (double d : array) {
            StdOut.print(d + "\n");
        }
    }

    public static void main(String[] args) {
        Double[] a = (Double[]) generateTestData(new Double(1.0), 100);
        sort(a);
        exch(a, 0, 1);
        boolean r = isSorted(a);
        assert r;
        show(a);
    }
}
