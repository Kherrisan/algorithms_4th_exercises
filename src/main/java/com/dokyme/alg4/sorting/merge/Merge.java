package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;

import java.util.*;

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

    public static <T extends Comparable> Queue<T> merge(Queue<T> a, Queue<T> b) {
        Queue<T> r = new LinkedList<>();
        T ai = a.poll();
        T bi = b.poll();
        while (ai != null && bi != null) {
            if (Example.less(ai, bi)) {
                r.add(ai);
                ai = a.poll();
            } else {
                r.add(bi);
                bi = b.poll();
            }
        }
        if (ai == null) {
            r.add(bi);
            r.addAll(b);
        } else if (bi == null) {
            r.add(ai);
            r.addAll(a);
        }
        return r;
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

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux, Comparator c) {
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
            } else if (Example.less(aux[j], aux[i], c)) {
                //右半边当前元素小于左半边当前元素
                a[k] = aux[j++];
            } else {
                //左半边当前元素小于右半边当前元素
                a[k] = aux[i++];
            }
        }
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, c);
    }

    public void sort(Comparable[] a, int lo, int hi, Comparator c) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, c);
        sort(a, mid + 1, hi, c);
        merge(a, lo, mid, hi, aux, c);
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {
        throw new RuntimeException();
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

    public static void main(String[] args) {

    }

//    public static List<Integer> testAccessMemoryCounts() {
//        accessCounts = new ArrayList<>();
//        for (int N = 1; N < 512; N++) {
//            Double[] pq = new Double[N];
//            for (int i = 0; i < pq.length; i++) {
//                pq[i] = StdRandom.uniform();
//            }
//            sort(pq);
//        }
//        return accessCounts;
//    }
}
