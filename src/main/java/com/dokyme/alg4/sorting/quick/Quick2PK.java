package com.dokyme.alg4.sorting.quick;

import com.dokyme.alg4.sorting.Sorting;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/25-14:58
 * Description:
 */
public class Quick2PK implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        Comparable min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], min)) {
                min = a[i];
            }
        }
        sort(a, 0, a.length - 1, min);
    }

    private static void sort(Comparable[] a, int lo, int hi, Comparable min) {
        int i = lo - 1, j = hi + 1;
        while (true) {
            while (a[++i].compareTo(min) == 0) {
                if (i >= hi) {
                    break;
                }
            }
            while (a[--j].compareTo(min) > 0) {
                if (j <= lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(2);
        }
        for (int num : array) {
            StdOut.print(num);
        }
        StdOut.println();
        new Quick2PK().sort(array);
        for (int num : array) {
            StdOut.print(num);
        }
    }
}
