package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.SortCompare;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Collections;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.3.22
 *
 * @author dokym
 * @date 2018/5/2-13:06
 * Description:
 */
public class QuickFast3Way implements Sorting {
    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    /**
     * Bentley-McIlroy 3-way partitioning
     *
     * @param a
     * @param hi
     * @param lo
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        Comparable v = a[lo];
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        while (true) {
            while (less(a[++i], v)) {
                //尽力向后移动i
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                //尽力向前移动j
                if (j == lo) {
                    break;
                }
            }
            if (i == j && eq(a[i], v)) {
                exch(a, i, ++p);
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
            if (eq(a[i], v)) {
                //把p+1上的元素移动到i
                exch(a, i, ++p);
            }
            if (eq(a[j], v)) {
                //把q-1上的元素移动到j
                exch(a, j, --q);
            }
        }
        //经过该循环后，a数组中lo...p-1和q+1...hi的元素都等于v
        //p...i-1的元素都小于v，j+1...q的元素都大于v
        i = j + 1;
        for (int k = lo; k <= p; k++) {
            exch(a, k, j--);
        }
        for (int k = hi; k >= q; k--) {
            exch(a, k, i++);
        }

        sort(a, lo, j);
        sort(a, i, hi);
    }

    public static void main(String[] args) {

    }
}
