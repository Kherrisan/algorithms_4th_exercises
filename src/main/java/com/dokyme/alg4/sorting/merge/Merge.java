package com.dokyme.alg4.sorting.merge;

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
public class Merge {
    private static Comparable[] aux;
    private static List<Integer> accessCounts;

    public static class AccessCounter {
        public int value = 0;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux, AccessCounter counter) {
        //把a[lo...mid]和a[mid+1...hi]归并
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        counter.value += (hi - lo) * 2;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                //左半边用尽
                a[k] = aux[j++];
            } else if (j > hi) {
                //右半边用尽
                a[k] = aux[i++];
            } else if (Example.less(aux[j], aux[i])) {
                counter.value += 2;
                //右半边当前元素小于左半边当前元素
                a[k] = aux[j++];
            } else {
                counter.value += 2;
                //左半边当前元素小于右半边当前元素
                a[k] = aux[i++];
            }
            counter.value += 2;
        }
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        AccessCounter counter = new AccessCounter();
        sort(a, 0, a.length - 1, counter);
        accessCounts.add(counter.value);
    }

    public static void sort(Comparable[] a, int lo, int hi, AccessCounter counter) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, counter);
        sort(a, mid + 1, hi, counter);
        merge(a, lo, mid, hi, aux, counter);
    }

    public static List<Integer> testAccessMemoryCounts() {
        accessCounts = new ArrayList<>();
        for (int N = 1; N < 512; N++) {
            Double[] a = new Double[N];
            for (int i = 0; i < a.length; i++) {
                a[i] = StdRandom.uniform();
            }
            sort(a);
        }
        return accessCounts;
    }
}
