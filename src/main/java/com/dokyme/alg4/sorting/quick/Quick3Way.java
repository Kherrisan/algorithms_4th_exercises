package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Shell;
import com.dokyme.alg4.sorting.basic.SortCompare;
import com.dokyme.alg4.sorting.merge.Merge;
import com.dokyme.alg4.sorting.merge.MergeBU;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/25-13:54
 * Description:
 */
public class Quick3Way implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }


    public static void main(String[] args) {
        int length = 1000000;
        int times = 100;
        double t1 = SortCompare.testSort(new Quick3Way(), length, times);
        double t2 = SortCompare.testSort(new Quick(), length, times);
        double t3 = SortCompare.testSort(new Merge(), length, times);
        double t4 = SortCompare.testSort(new MergeBU(), length, times);
        double t5 = SortCompare.testSort(new Shell(), length, times);
        double t6 = SortCompare.testArraysSort(length, times);
        StdOut.printf("Quick3way:%f\nQuick:%f\nMerge:%f\nMergeBU:%f\nShell:%f\nArrays.sort:%f\n", t1, t2, t3, t4, t5, t6);
    }
}
