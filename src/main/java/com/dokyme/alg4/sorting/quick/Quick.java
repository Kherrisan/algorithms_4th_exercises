package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.CompareUtil;
import com.dokyme.alg4.sorting.DefaultComparator;
import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/25-13:45
 * Description:
 */
public class Quick implements Sorting {

    @Override
    public void sort(Comparable[] a, Comparator c) {
        throw new RuntimeException();
    }

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, new DefaultComparator());
    }

    public static void sort(Comparable[] a, int lo, int hi, Comparator c) {
        if (hi <= lo) {
            return;
        }
        int j = partion(a, lo, hi, c);
        sort(a, lo, j - 1, c);
        sort(a, j + 1, hi, c);
    }

    private static int partion(Comparable[] a, int lo, int hi, Comparator c) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v, c)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j], c)) {
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
        return j;
    }

    public static void main(String[] args) {

    }
}
