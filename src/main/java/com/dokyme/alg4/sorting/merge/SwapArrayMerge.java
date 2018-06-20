package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 在递归中交换排序数组和辅助数组来绕过元素复制的归并排序。
 *
 * @author dokym
 * @date 2018/6/20-13:54
 * Description:
 */
public class SwapArrayMerge implements Sorting {

    @Override
    public void sort(Comparable[] a) {
        Comparable[] aux = Arrays.copyOf(a, a.length);
        sort(a, 0, a.length - 1, aux);
    }

    private void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(aux, lo, mid, a);
        sort(aux, mid + 1, hi, a);
        merge(aux, a, lo, mid, hi);
    }

    private void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                dst[k] = src[j++];
            } else if (j == hi + 1) {
                dst[k] = src[i++];
            } else if (Example.less(src[i], src[j])) {
                dst[k] = src[i];
            } else {
                dst[k] = src[j];
            }
        }
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public static void main(String[] args) {
        assert Example.test(new SwapArrayMerge(), new Example.DataMocker<Double>() {
            @Override
            public Double mock(int i) {
                return StdRandom.uniform();
            }
        }, 10000);
    }
}
