package com.dokyme.alg4.sorting.priorityqueue;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.4.26
 *
 * @author dokym
 * @date 2018/5/15-21:02
 * Description:
 */
public class MinHeapWithoutExch<T extends Comparable<T>> extends MinHeap<T> {

    public MinHeapWithoutExch(int n) {
        super(n);
    }

    /**
     * 下沉
     *
     * @param k
     */
    @Override
    protected void sink(int k) {
        int dst = k;
        T e = pq[k];
        while (2 * dst < n) {
            int p = 2 * dst;
            if (less(p + 1, p)) {
                p++;
            }
            if (less(e, pq[p])) {
                break;
            }
            pq[dst] = pq[p];
            dst = p;
        }
        pq[dst] = e;
    }

    /**
     * 上浮
     *
     * @param k
     */
    @Override
    protected void swim(int k) {
        int dst = k;
        T e = pq[k];
        while (dst > 1 && less(e, pq[dst / 2])) {
            pq[dst] = pq[dst / 2];
            dst /= 2;
        }
        pq[dst] = e;
    }

    public static void main(String[] args) {
        Comparable[] arrays = generateTestData(1, 100);
        MinHeapWithoutExch<Integer> heap = new MinHeapWithoutExch<>(100);
        for (Comparable i : arrays) {
            heap.insert((Integer) i);
            StdOut.print(i);
            assert MinHeap.test(heap);
        }
        for (int i = 0; i < 10; i++) {
            StdOut.print(heap.delMin() + "\t");
            assert MinHeap.test(heap);
        }
    }
}
