package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.CompareUtil;
import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.exch;
import static com.dokyme.alg4.sorting.CompareUtil.less;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/25-13:45
 * Description:
 */
public class Quick implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partion(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partion(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
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
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return i;
    }

    public static void main(String[] args) {
        for (int N = 100; N < 100000000; N *= 10) {
            Integer[] array = new Integer[N];
            for (int i = 0; i < array.length; i++) {
                array[i] = 1;
            }
            CompareUtil.count = 0;
            new Quick().sort(array);
            StdOut.printf("N:%d\tC:%d\tNlogN:%d\n", N, CompareUtil.count, new Double(N * Math.log(N * 1.0) / Math.log(2)).intValue());
        }
    }
}
