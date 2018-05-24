package com.dokyme.alg4.sorting.application;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/27-12:30
 * Description:
 */
public class Select {

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        return select(a, k, 0, a.length - 1);
    }

    public static Comparable select(Comparable[] a, int k, int lo, int hi) {
        int mid = partion(a, lo, hi);
        if (mid < k) {
            return select(a, k, mid + 1, hi);
        } else if (mid > k) {
            return select(a, k, lo, mid - 1);
        } else {
            return a[k];
        }
    }

    public static int partion(Comparable[] a, int lo, int hi) {
        Comparable v = a[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
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
        Integer[] a = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            a[i] = i;
        }
        StdOut.println(select(a, 500));
    }
}
