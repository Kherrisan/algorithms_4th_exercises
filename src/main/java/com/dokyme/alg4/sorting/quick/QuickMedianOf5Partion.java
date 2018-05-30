package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/2-12:11
 * Description:
 */
public class QuickMedianOf5Partion implements Sorting {

    @Override
    public void sort(Comparable[] a, Comparator c) {
        throw new RuntimeException();
    }

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo <= 5) {
            Insertion.sort(a);
            return;
        }
        int mid = partition(a, lo, hi);
        sort(a, lo, mid - 1);
        sort(a, mid + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        medianOf5(a, lo, hi);
        int i = lo + 2, j = hi - 1;
        Comparable v = a[lo + 2];
        while (true) {
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, j, lo + 2);
        return j;
    }

    public static void medianOf5(Comparable[] a, int lo, int hi) {

    }

    public static void main(String[] args) {
        doubleTesting(new QuickMedianOf5Partion());
    }
}
