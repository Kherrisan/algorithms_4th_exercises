package com.dokyme.alg4.sorting.priorityqueue;

import edu.princeton.cs.algs4.StdIn;

import java.lang.reflect.Array;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 二叉最小堆实现
 *
 * @author dokym
 * @date 2018/5/8-22:07
 * Description:
 */
public class MinPQ<T extends Comparable> {

    private T[] pq;
    private int N = 0;

    public MinPQ(Class cls, int n) {
        pq = (T[]) new Comparable[n + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(T t) {
        pq[++N] = t;
        swim(N);
    }

    /**
     * 弹出最小堆中的最小值
     *
     * @return
     */
    public T delMin() {
        T min = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return min;
    }

    /**
     * 下沉
     *
     * @param k
     */
    private void sink(int k) {
        while (2 * k + 1 <= N) {
            int n = k * 2;
            if (less(n + 1, n)) {
                //找到最小的子节点
                n += 1;
            }
            if (less(k, n)) {
                //如果k比最小的子节点小，说明下沉完毕
                break;
            }
            exch(k, n);
            k = n;
        }
    }

    /**
     * 上浮
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {

    }
}
