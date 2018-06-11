package com.dokyme.alg4.sorting.priorityqueue;

import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdRandom;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/6/11-22:12
 * Description:
 */
public class FastInsertPQ<T extends Comparable<T>> implements IMinPQ<T> {

    private T[] pq;

    private int size;

    public FastInsertPQ(int initialCapacity) {
        pq = (T[]) new Comparable[initialCapacity + 1];
    }

    private void resize(int newsize) {
        T[] temp = (T[]) new Comparable[newsize];
        System.arraycopy(pq, 0, temp, 0, pq.length);
        pq = temp;
    }

    private void exch(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void sink(int n) {
        while (2 * n <= size) {
            int p = 2 * n;
            if (p < size && less(p + 1, p)) {
                p++;
            }
            if (less(n, p)) {
                break;
            }
            exch(n, p);
            n = p;
        }
    }

    /**
     * 快速上浮操作
     *
     * @param n
     */
    private void swim(int n) {
        if (n == 1) {
            return;
        }
        int length = (int) (Math.log10(n) / Math.log10(2));
        //构造一个索引数组，避免每次都做对数运算的巨大计算量
        int[] indexes = new int[length + 1];
        for (int i = n; i >= 1; i /= 2) {
            indexes[length--] = i;
        }
        int lo = 0;
        int hi = indexes.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (less(n, indexes[mid])) {
                hi = mid - 1;
            } else if (less(indexes[mid], n)) {
                lo = mid + 1;
            } else {
                break;
            }
        }
        while (n / 2 >= indexes[lo]) {
            exch(n, n / 2);
            n /= 2;
        }
    }

    /**
     * 快速插入（二分查找）
     *
     * @param t
     */
    @Override
    public void insert(T t) {
        if (size == pq.length - 1) {
            resize(pq.length * 2);
        }
        pq[++size] = t;
        swim(size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T delMin() {
        T val = pq[1];
        exch(1, size);
        sink(1);
        pq[size--] = null;
        return val;
    }

    @Override
    public T min() {
        return pq[1];
    }

    public static <T extends Comparable<T>> boolean test(FastInsertPQ<T> minHeap) {
        T[] pq = minHeap.pq;
        for (int i = 2; i <= minHeap.size; i++) {
            if (Example.less(pq[i], pq[i / 2])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FastInsertPQ<Integer> heap = new FastInsertPQ<>(10);
        for (Integer i : generate(Integer.class, 10, new DataMocker<Integer>() {
            @Override
            public Integer mock(int i) {
                return StdRandom.uniform(10);
            }
        })) {
            heap.insert(i);
        }
        assert test(heap);
    }
}
