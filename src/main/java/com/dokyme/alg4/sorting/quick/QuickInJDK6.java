package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Insertion;
import com.dokyme.alg4.sorting.basic.SortCompare;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.3.23
 *
 * @author dokym
 * @date 2018/5/5-16:22
 * Description:
 */
public class QuickInJDK6 implements Sorting {

    public static final int INSERTION_SORT_THRESHOLD = 8;
    public static final int MEDIAN_THREE_THRESHOLD = 40;

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void tukeysNinther(Comparable[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        int esp = (hi - lo + 1) / 8;
        int lm = medianOf3(a, lo, lo + esp, lo + 2 * esp);
        int mm = medianOf3(a, mid - esp, mid, mid + esp);
        int hm = medianOf3(a, hi - 2 * esp, hi - esp, hi);
        mid = medianOf3(a, lm, mm, hm);
        exch(a, lo, mid);
    }

    /**
     * 得到a数组中i，j，k三个位置中的中位数所在位置。
     *
     * @param a
     * @param i
     * @param j
     * @param k
     * @return
     */
    private static int medianOf3(Comparable[] a, int i, int j, int k) {
        if (less(a[i], a[j])) {
            if (less(a[j], a[k])) {
                //a[i]<a[j]<a[k]
                return j;
            } else if (less(a[k], a[i])) {
                //a[k]<a[i]<a[j]
                return i;
            } else {
                //a[i]<a[k]<a[j]
                return k;
            }
        } else {
            if (less(a[i], a[k])) {
                //a[j]<a[i]<a[k]
                return i;
            } else if (less(a[k], a[j])) {
                //a[k]<a[j]<a[i]
                return j;
            } else {
                //a[j]<a[k]<a[i]
                return k;
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_THRESHOLD) {
            Insertion.sort(a, lo, hi);
            return;
        } else if (n <= MEDIAN_THREE_THRESHOLD) {
            QuickMedianOf3Partion.medianOf3(a, lo, hi);
        } else {
            tukeysNinther(a, lo, hi);
        }

        int i = lo, p = lo;
        int j = hi + 1, q = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i == j && eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
            if (eq(a[i], v)) {
                exch(a, i, ++p);
            }
            if (eq(a[j], v)) {
                exch(a, j, --q);
            }
        }
        i = j + 1;
        for (int k = lo; k <= p; k++) {
            exch(a, j--, k);
        }
        for (int k = hi; k >= q; k--) {
            exch(a, i++, k);
        }

        sort(a, lo, j);
        sort(a, i, hi);
    }

    public static void main(String[] args) {
//        testSorting(new QuickInJDK6());
        int times = 100;
        for (int n = 1024; n < 1 << 20; n <<= 1) {
            double arrays = SortCompare.testArraysSort(n, times);
            double jdk6 = SortCompare.testSort(new QuickInJDK6(), n, times);
            StdOut.printf("Length:%d\tArrays.sort:%f\tjdk6:%f\n", n, arrays, jdk6);
        }
    }
}
