package com.dokyme.alg4.sorting.basic;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/3/10-15:08
 * Description:
 */
public class Shell {

    public static boolean compare(Comparable[] a, Comparable[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean check(Comparable[] a) {
        Comparable[] backup = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            backup[i] = a[i];
        }
        sort(a);
        if (!compare(a, backup)) {
            return false;
        }
        Arrays.sort(backup);
        if (!compare(a, backup)) {
            return false;
        }
        return true;
    }

    public static void sort(Comparable[] a) {
        SortingDrawer drawer = new SortingDrawer(a);
        int N = a.length;
        int h = hArray[a.length];
        while (h >= 1) {
            for (int i = 0; i < N; i++) {
                for (int j = i; j >= h && Example.less(a[j], a[j - h]); j -= h) {
                    Example.exch(a, j, j - h);
                    drawer.focus(j).focus(j - h).update(a);
                }
            }
            h = h / 3;
        }
    }

    public static int[] sedgewick() {
        int[] seq = new int[1 << 10];
        int i = 0, j = 2;
        int c = 0;
        int value1 = 1;
        int value2 = 5;
        while (c < seq.length) {
            if (value1 < value2) {
                seq[c++] = value1;
                i++;
                value1 = Double.valueOf(9 * Math.pow(4.0, i * 1.0) - 9 * Math.pow(2.0, i * 1.0) + 1).intValue();
            } else {
                seq[c++] = value2;
                j++;
                value2 = Double.valueOf(Math.pow(4.0, j * 1.0) - 3 * Math.pow(2.0, j * 1.0) + 1).intValue();
            }
        }
        return seq;
    }

    public static int[] gonnet() {
        int[] seq = new int[1 << 20];
        seq[0] = 1;
        for (int i = 1; i < seq.length; i++) {
            seq[i] = 2 * seq[i - 1] + 1;
        }
        return seq;
    }

    public static int[] hibbard() {
        int[] seq = new int[1 << 20];
        seq[0] = 1;
        for (int i = 1; i < seq.length; i++) {
            seq[i] = 2 * seq[i - 1] + 1;
        }
        return seq;
    }

    public static int[] shell() {
        int[] seq = new int[1 << 20];
        seq[0] = 1;
        for (int i = 1; i < seq.length; i++) {
            seq[i] = 2 * seq[i - 1];
        }
        return seq;
    }

    public static int[] knuth() {
        int i = 1, h = 1;
        int[] seq = new int[1 << 20];
        while (i < seq.length) {
            while (i < (h + 1) * 3 && i < seq.length) {
                seq[i] = h;
                i++;
            }
            h = 3 * h + 1;
        }
        return seq;
    }


    public static double testSpecifiedSequence(int times, int length, int[] hArray) {
        double[] array = new double[length];
        double t = 0.0;
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j] = StdRandom.uniform();
            }
            Stopwatch stopwatch = new Stopwatch();
            sort(array, hArray);
            t += stopwatch.elapsedTime();
            if (i % 10 == 0) {
                StdOut.println(i);
            }
        }
        return t;
    }

    public static void sort(double[] a, int[] hArray) {
        int N = a.length;
        int hi = 0;
        for (; hi < hArray.length && hArray[hi] < N; hi++) {
        }
        while (hi >= 0) {
            for (int i = 0; i < a.length; i++) {
                for (int j = i; j >= hArray[hi] && a[j] < a[j - 1]; j -= hArray[hi]) {
                    Example.exch(a, j, j - hArray[hi]);
                }
            }
            hi--;
        }
    }

    public static void sort(Comparable[] a, int[] hArray) {
        int hi = 0;
        int N = a.length;
        for (; hi < hArray.length && N > hArray[hi]; hi++) {
        }
        while (hi >= 0) {
            for (int i = 0; i < a.length; i++) {
                for (int j = i; j >= hArray[hi] && a[j].compareTo(a[j - hArray[hi]]) < 0; j -= hArray[hi]) {
                    Example.exch(a, j, j - hArray[hi]);
                }
            }
            hi--;
        }
    }

    public static void sort(Comparable[] a, boolean x) {
        int N = a.length;
        int h = hArray[a.length];
        Map<Integer, Double> comparsionFactor = new LinkedHashMap();
        while (h >= 1) {
            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    time++;
                    if (Example.less(a[j], a[j - h])) {
                        Example.exch(a, j, j - h);
                    } else {
                        break;
                    }
                }
            }
            comparsionFactor.put(h, (double) time / (double) N);
            h = h / 3;
        }
        System.out.println("N=" + a.length);
        for (Map.Entry<Integer, Double> hi : comparsionFactor.entrySet()) {
            System.out.println("\th=" + hi.getKey() + "\tcomparsion/N=" + hi.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Double[] a = new Double[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }
        sort(a);
        assert Example.isSorted(a);
    }

    public static int[] hArray;

    static {
        int i = 1, h = 1;
        hArray = new int[1 << 20];
        while (i < hArray.length) {
            while (i < (h + 1) * 3 && i < hArray.length) {
                hArray[i] = h;
                i++;
            }
            h = 3 * h + 1;
        }
    }
}
