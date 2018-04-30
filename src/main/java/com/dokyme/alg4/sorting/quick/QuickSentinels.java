package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.3.17
 *
 * @author dokym
 * @date 2018/4/26-15:59
 * Description:
 */
public class QuickSentinels implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (less(a[max], a[i])) {
                max = i;
            }
        }
        exch(a, max, a.length - 1);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mi = partion(a, lo, hi);
        sort(a, mi + 1, hi);
        //让切分元素成为哨兵
        sort(a, lo, mi);
    }

    private static int partion(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[i];
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
        testSorting(new QuickSentinels(), 1000);
    }
}
