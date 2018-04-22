package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import com.dokyme.alg4.sorting.basic.SortingDrawer;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/4/17-12:20
 * Description:
 */
public class Merge implements Sorting {
    private static Comparable[] aux;
    private static List<Integer> accessCounts;

    public static class AccessCounter {
        public int value = 0;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        //把a[lo...mid]和a[mid+1...hi]归并
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                //左半边用尽
                a[k] = aux[j++];
            } else if (j > hi) {
                //右半边用尽
                a[k] = aux[i++];
            } else if (Example.less(aux[j], aux[i])) {
                //右半边当前元素小于左半边当前元素
                a[k] = aux[j++];
            } else {
                //左半边当前元素小于右半边当前元素
                a[k] = aux[i++];
            }
        }
    }

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi, aux);
    }

//    public static List<Integer> testAccessMemoryCounts() {
//        accessCounts = new ArrayList<>();
//        for (int N = 1; N < 512; N++) {
//            Double[] a = new Double[N];
//            for (int i = 0; i < a.length; i++) {
//                a[i] = StdRandom.uniform();
//            }
//            sort(a);
//        }
//        return accessCounts;
//    }
}
