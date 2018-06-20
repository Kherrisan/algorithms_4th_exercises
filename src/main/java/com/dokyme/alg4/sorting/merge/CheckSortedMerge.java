package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 在merge之前检查数组是否已经有序的排序
 *
 * @author dokym
 * @date 2018/6/20-13:49
 * Description:
 */
public class CheckSortedMerge implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, aux);
    }

    public void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }


    public void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        //把a[lo...mid]和a[mid+1...hi]归并
        if (Example.less(a[mid], a[mid + 1])) {
            return;
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (Example.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {

    }

    public static void main(String[] args) {
        assert Example.test(new CheckSortedMerge(), new Example.DataMocker<Double>() {
            @Override
            public Double mock(int i) {
                return StdRandom.uniform();
            }
        }, 10000);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
