package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.3.18
 *
 * @author dokym
 * @date 2018/4/26-16:16
 * Description:
 */
public class QuickMedianOf3Partion implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = partion(a, lo, hi);
        sort(a, lo, mid - 1);
        sort(a, mid + 1, hi);
    }

    /**
     * 取lo,mid,hi中中位数为pivot，放到lo，最大值放到hi
     *
     * @param a
     * @param lo
     * @param hi
     */
    private static void medianOf3(Comparable[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (less(a[mid], a[lo])) {
            exch(a, lo, mid);
        }
        if (less(a[hi], a[mid])) {
            exch(a, mid, hi);
        }
        if (less(a[hi], a[lo])) {
            exch(a, lo, hi);
        }
        exch(a, lo, mid);
    }

    private static int partion(Comparable[] a, int lo, int hi) {
        medianOf3(a, lo, hi);
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        testSorting(new QuickMedianOf3Partion(), 1000);
//        doubleTesting(new QuickMedianOf3Partion());
    }
}
