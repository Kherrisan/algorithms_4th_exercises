package com.dokyme.alg4.sorting.priorityqueue;

import java.util.PriorityQueue;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.4.31
 *
 * @author dokym
 * @date 2018/5/18-12:15
 * Description:
 */
public class QuickMinHeap<T extends Comparable<T>> extends AbstractPriorityQueue<T> implements IMinPQ<T> {

    @Override
    public void insert(T t) {
        pq[++n] = t;
        swim(n);
    }

    @Override
    public T delMin() {
        T min = pq[1];
        exch(1, n);
        pq[n--] = null;
        sink(1);
        return min;
    }

    @Override
    public T min() {
        return pq[1];
    }

    /**
     * 快速上浮（二分查找）1,2,4,8,16,32,64
     *
     * @param k
     */
    private void swim(int k) {
        int lo = ((int) (Math.log(k) / Math.log(2))),
                hi = 0,
                mid;
        while (lo <= hi) {
            mid = hi + (lo - hi) / 2;
            int posMid = k / ((int) Math.pow(2, mid));
            if (less(posMid, k)) {
                lo = mid + 1;
            } else if (less(k, posMid)) {
                hi = mid - 1;
            } else {

            }
        }
    }

    /**
     * 快速下沉（二分查找）
     *
     * @param k
     */
    private void sink(int k) {

    }

    public static void main(String[] args) {

    }
}
