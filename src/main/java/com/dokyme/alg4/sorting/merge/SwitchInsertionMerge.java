package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Insertion;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 切换到插入排序的归并排序
 *
 * @author dokym
 * @date 2018/6/20-13:32
 * Description:
 */
public class SwitchInsertionMerge implements Sorting {

    private int threshold;

    public SwitchInsertionMerge(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, aux);
    }

    private void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (lo >= hi) {
            return;
        }
        if (hi - lo <= threshold) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }

    private void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                a[k] = aux[j++];
            } else if (j == hi + 1) {
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(threshold:" + threshold + ")";
    }

    public static void main(String[] args) {
        assert test(new SwitchInsertionMerge(10), new DataMocker<Double>() {
            @Override
            public Double mock(int i) {
                return StdRandom.uniform();
            }
        }, 1000);
    }
}
