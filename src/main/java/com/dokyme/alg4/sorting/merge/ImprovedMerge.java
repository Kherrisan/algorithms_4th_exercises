package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

import static com.dokyme.alg4.sorting.basic.Example.*;
import static com.dokyme.alg4.sorting.basic.SortCompare.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.11
 *
 * @author dokym
 * @date 2018/4/22-9:51
 * Description:
 */
public class ImprovedMerge implements Sorting {

    private static final int INSERT_THRESHOLD = 20;

    @Override
    public void sort(Comparable[] a) {
        Comparable[] aux = Arrays.copyOf(a, a.length);
        sort(a, 0, a.length - 1, aux, false);
    }

    public static void sort(Comparable[] a, int lo, int hi, Comparable[] aux, boolean inverse) {
        if (lo >= hi) {
            return;
        }
        if (hi - lo <= INSERT_THRESHOLD) {
            if (inverse) {
                insertSort(aux, lo, hi);
            } else {
                insertSort(a, lo, hi);
            }
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux, !inverse);
        sort(a, mid + 1, hi, aux, !inverse);
        if (inverse) {
            mergeTo(a, aux, lo, mid, hi);
        } else {
            mergeTo(aux, a, lo, mid, hi);
        }
    }

    private static void mergeTo(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        if (less(src[mid], src[mid + 1])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (less(src[i], src[j])) {
                dst[k] = src[i++];
            } else {
                dst[k] = src[j++];
            }
        }
    }

    private static void insertSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi + 1; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        int length = 10000;
        int times = 1000;
        double im = testSort(new ImprovedMerge(), length, times);
        double qm = testSort(new QuickMerge(), length, times);
        double m = testSort(new Merge(), length, times);
        StdOut.printf("Raw Merged:%f\nQuick Merged:%f\nImproved Merged:%f\n", m, qm, im);
    }
}
