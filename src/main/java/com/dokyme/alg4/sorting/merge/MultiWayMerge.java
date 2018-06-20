package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import com.dokyme.alg4.sorting.priorityqueue.IndexMinPQ;

import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * k路归并排序
 *
 * @author dokym
 * @date 2018/6/20-14:35
 * Description:
 */
public class MultiWayMerge implements Sorting {

    private int k;

    public MultiWayMerge(int k) {
        this.k = k;
    }

    @Override
    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, aux);
    }

    public void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (lo >= hi) {
            return;
        }
        //划分k个子数组
        int[][] startend = new int[2][];
        int p = partion(startend, lo, hi);
        for (int i = 0; i < k; i++) {
            sort(a, startend[0][i], startend[1][i], aux);
        }
        merge(a, startend[0], startend[1], p, aux);
    }

    private int partion(int[][] startend, int lo, int hi) {
        int[] startIndex = new int[k];
        int[] endIndex = new int[k];
        startend[0] = startIndex;
        startend[1] = endIndex;

        int step = (hi - lo + 1) / k;
        int remaining = (hi - lo + 1) % k;

        int i = 0;
        for (; i < k; i++) {
            if (i == 0) {
                startIndex[i] = lo;
            } else {
                startIndex[i] = Math.min(endIndex[i - 1] + 1, hi);
            }
            int extra = 0;
            if (remaining > 0) {
                extra++;
                remaining--;
            }
            endIndex[i] = Math.min(hi, startIndex[i] + step - 1 + extra);
            if (endIndex[i] == hi) {
                break;
            }
        }

        return i + 1;
    }

    /**
     * k路归并
     *
     * @param aux:辅助数组
     */
    public void merge(Comparable[] a, int[] startIndex, int[] endIndex, int p, Comparable[] aux) {
        int lo = startIndex[0];
        int hi = endIndex[p - 1];
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        IndexMinPQ pq = new IndexMinPQ(k);
        for (int i = 0; i < p; i++) {
            pq.insert(i, aux[startIndex[i]]);
        }
        for (int k = lo; k <= hi; k++) {
            a[k] = pq.min();
            int minIndex = pq.delMin();
            startIndex[minIndex]++;
            if (startIndex[minIndex] == endIndex[minIndex] + 1 || startIndex[minIndex] > hi) {
                continue;
            }
            pq.insert(minIndex, aux[startIndex[minIndex]]);
        }
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(way:" + k + ")";
    }

    public static void main(String[] args) {
        Example.testSorting(new MultiWayMerge(3));
    }
}
