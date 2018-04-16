package com.dokyme.alg4.sorting.basic;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

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
//        SortingDrawer drawer = new SortingDrawer(a);
        int N = a.length;
        int h = hArray[a.length];
        while (h >= 1) {
            for (int i = 0; i < N; i++) {
                for (int j = i; j >= h && Example.less(a[j], a[j - h]); j -= h) {
                    Example.exch(a, j, j - h);
//                    drawer.focus(j).focus(j - h).update(a);
                }
            }
            h = h / 3;
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
        for (int i = 100; i < 101; i++) {
            Double[] array = new Double[i];
            for (int j = 0; j < array.length; j++) {
                array[j] = StdRandom.uniform();
            }
            Arrays.sort(array);
            sort(array);
        }
    }

    public static int[] hArray;

    static {
        int i = 1, h = 1;
        hArray = new int[1 << 60];
        while (i < hArray.length) {
            while (i < (h + 1) * 3 && i < hArray.length) {
                hArray[i] = h;
                i++;
            }
            h = 3 * h + 1;
        }
    }
}
