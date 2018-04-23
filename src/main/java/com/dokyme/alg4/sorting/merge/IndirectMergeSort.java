package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/23-19:50
 * Description:
 */
public class IndirectMergeSort {

    private void merge(Comparable[] a, int[] aux, int[] perm, int lo, int mi, int hi) {
        System.arraycopy(perm, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mi + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mi + 1) {
                perm[k] = aux[j++];
            } else if (j == hi + 1) {
                perm[k] = aux[i++];
            } else if (Example.less(a[aux[i]], a[aux[j]])) {
                perm[k] = aux[i++];
            } else {
                perm[k] = aux[j++];
            }
        }
    }

    public int[] perm(Comparable[] a) {
        int[] perm = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            perm[i] = i;
        }
        int[] aux = new int[a.length];
        perm(a, aux, perm, 0, a.length - 1);
        return perm;
    }

    private void perm(Comparable[] a, int[] aux, int[] perm, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        perm(a, aux, perm, lo, mid);
        perm(a, aux, perm, mid + 1, hi);
        merge(a, aux, perm, lo, mid, hi);
    }

    public static void main(String[] args) {
        Comparable[] array = Example.generateTestData(new Double(1), 100);
        int[] perm = new IndirectMergeSort().perm(array);
        for (int p : perm) {
            StdOut.println(p);
        }
    }
}
