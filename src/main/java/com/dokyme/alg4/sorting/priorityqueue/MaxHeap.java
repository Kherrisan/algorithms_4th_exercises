package com.dokyme.alg4.sorting.priorityqueue;

import com.dokyme.alg4.sorting.Sorting;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/9-22:59
 * Description:
 */
public class MaxHeap<T extends Comparable> extends AbstractPriorityQueue<T> implements Sorting, IMaxPQ<T> {

    @SuppressWarnings("unchecked")
    public MaxHeap(int n) {
        pq = (T[]) new Comparable[n + 1];
    }

    @SuppressWarnings("unchecked")
    public MaxHeap(T[] array) {
        pq = (T[]) new Comparable[array.length + 1];
        System.arraycopy(array, 0, pq, 1, array.length);
        adjustHeap();
    }

    @Override
    public void insert(T t) {
        pq[++n] = t;
        swim(n);
    }

    @Override
    public T delMax() {
        T max = pq[1];
        exch(1, n);
        pq[n--] = null;
        sink(1);
        return max;
    }

    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) {
            exch(i, i / 2);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= n) {
            int k = 2 * i;
            if (k < n && less(k, k + 1)) {
                k++;
            }
            if (less(k, i)) {
                break;
            }
            exch(i, k);
            i = k;
        }
    }

    private void adjustHeap() {
        for (int i = n / 2; i > 0; i--) {
            sink(i);
        }
    }

    @Override
    public void sort(Comparable[] a) {
        pq = (T[]) new Comparable[a.length + 1];
        n = a.length;
        System.arraycopy(a, 0, pq, 1, a.length);
        adjustHeap();
        while (n > 1) {
            exch(1, n--);
            sink(1);
        }
        System.arraycopy(pq, 1, a, 0, a.length);
    }


    public static void main(String[] args) {

    }
}
