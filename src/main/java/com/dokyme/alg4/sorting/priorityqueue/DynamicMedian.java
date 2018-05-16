package com.dokyme.alg4.sorting.priorityqueue;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.4.30
 *
 * @author dokym
 * @date 2018/5/17-10:55
 * Description:
 */
public class DynamicMedian {

    private MinHeap<Integer> minHeap;
    private MaxHeap<Integer> maxHeap;
    private int n;
    private int medianLow;
    private int medianHigh;

    public DynamicMedian() {
        minHeap = new MinHeap<>(10);
        maxHeap = new MaxHeap<>(10);
    }

    /**
     * logN
     *
     * @param e
     */
    public void insert(Integer e) {
        if (n % 2 != 0) {
            //插入之前有奇数个元素
            if (e < medianLow) {
                maxHeap.insert(e);
                medianHigh = medianLow;
                medianLow = maxHeap.delMax();
            } else {
                minHeap.insert(e);
                medianHigh = minHeap.delMin();
            }
        } else {
            //插入前有偶数个元素
            if (e > medianHigh) {
                minHeap.insert(e);
                maxHeap.insert(medianLow);
                medianLow = medianHigh;
            } else if (e < medianLow) {
                maxHeap.insert(e);
                minHeap.insert(medianHigh);
                medianHigh = medianLow;
            } else {
                maxHeap.insert(medianLow);
                minHeap.insert(medianHigh);
                medianLow = medianHigh = e;
            }
        }
        n++;
    }

    /**
     * N
     *
     * @return
     */
    public Double median() {
        return (1.0 * medianLow + medianHigh) / 2;
    }

    /**
     * logN
     *
     * @return
     */
    public Double delMedian() {
        Double m = median();
        if (n % 2 == 0) {
            medianLow = maxHeap.delMax();
            medianHigh = minHeap.delMin();
        } else {
            medianLow = maxHeap.delMax();
        }
        n--;
        return m;
    }

    public static void main(String[] args) {
        DynamicMedian median = new DynamicMedian();
        for (int i = 0; i < 10; i++) {
            median.insert(i);
        }
        StdOut.println(median.delMedian());
        median.insert(1);
        StdOut.println(median.delMedian());
        median.insert(2);
        StdOut.println(median.delMedian());
    }
}
