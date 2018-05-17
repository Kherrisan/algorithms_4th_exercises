package com.dokyme.alg4.sorting.priorityqueue;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 二叉最小堆实现
 *
 * @author dokym
 * @date 2018/5/8-22:07
 * Description:
 */
public class MinHeap<T extends Comparable> extends AbstractPriorityQueue<T> implements Sorting, IMinPQ<T> {

    @Override
    public void sort(Comparable[] a) {
        pq = (T[]) new Comparable[a.length + 1];
        System.arraycopy(a, 0, pq, 1, a.length);
        n = a.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        while (n > 1) {
            exch(1, n--);
            sink(1);
        }
    }

    public MinHeap(int n) {
        pq = (T[]) new Comparable[n + 1];
    }

    @Override
    public void insert(T t) {
        if (n + 1 >= pq.length) {
            int newLength = n + n / 2;
            T[] npq = (T[]) new Comparable[newLength];
            System.arraycopy(pq, 0, npq, 0, pq.length);
            pq = npq;
        }
        pq[++n] = t;
        swim(n);
    }

    /**
     * 弹出最小堆中的最小值
     *
     * @return
     */
    @Override
    public T delMin() {
        T min = pq[1];
        exch(1, n--);
        pq[n + 1] = null;
        sink(1);
        return min;
    }

    @Override
    public T min() {
        return pq[1];
    }

    /**
     * 下沉
     *
     * @param k
     */
    protected void sink(int k) {
        while (2 * k <= n) {
            int p = k * 2;
            if (p < n && less(p + 1, p)) {
                //找到最小的子节点
                p++;
            }
            if (less(k, p)) {
                //如果k比最小的子节点小，说明下沉完毕
                break;
            }
            exch(k, p);
            k = p;
        }
    }

    /**
     * 上浮
     *
     * @param k
     */
    protected void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    public static <T extends Comparable> boolean test(MinHeap<T> minHeap) {
        T[] pq = minHeap.pq;
        for (int i = 2; i <= minHeap.n; i++) {
            if (Example.less(pq[i], pq[i / 2])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinHeap<Double> minHeap = new MinHeap<>(100);
        for (Comparable d : generateTestData(new Double(1), 100)) {
            minHeap.insert((Double) d);
        }
        assert test(minHeap);
//        testSorting(new MinHeap<>(Double.class, 10), true);
    }
}
