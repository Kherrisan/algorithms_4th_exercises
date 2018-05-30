package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

import static com.dokyme.alg4.sorting.basic.Example.*;
import static com.dokyme.alg4.sorting.basic.SortCompare.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.10
 *
 * @author dokym
 * @date 2018/4/22-9:40
 * Description:
 */
public class QuickMerge implements Sorting {

    private static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = hi;
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }
        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[hi - (k - mid - 1)];
        }
        //如果前半部分耗尽，那么i会移动到后半部分去，i和j都在后半部分移动直到相遇。
        for (int k = lo; k <= hi; k++) {
            if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j--];
            }
        }
    }

    @Override
    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, aux);
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {
        throw new RuntimeException();
    }

    private static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }

    public static void main(String[] args) {

    }
}
