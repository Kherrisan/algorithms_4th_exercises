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

    private MinHeap<Integer> highHeap;
    private MaxHeap<Integer> lowHeap;
    private int n;

    public DynamicMedian() {
        n = 0;
        highHeap = new MinHeap<>(10);
        lowHeap = new MaxHeap<>(10);
    }

    /**
     * O(logN)
     *
     * @param e
     */
    public void insert(Integer e) {
        n++;
        if (highHeap.size() == lowHeap.size()) {
            if (!highHeap.isEmpty() && e > highHeap.min()) {
                //e比中位数大
                lowHeap.insert(highHeap.delMin());
                highHeap.insert(e);
            } else {
                //e比中位数小
                lowHeap.insert(e);
            }
        } else {
            //小半部分元素数量大于大半部分元素数量
            if (e < lowHeap.max()) {
                //e比中位数小
                highHeap.insert(lowHeap.delMax());
                lowHeap.insert(e);
            } else {
                //e比中位数大
                highHeap.insert(e);
            }
        }
    }

    /**
     * O(N)
     *
     * @return
     */
    public Double median() {
        if (lowHeap.isEmpty()) {
            return 0d;
        }
        if (lowHeap.size() == highHeap.size()) {
            return (lowHeap.max() * 1.0 + highHeap.min()) / 2;
        } else {
            return lowHeap.max() * 1.0;
        }
    }

    /**
     * O(logN)
     * 这里定义“删除中位数”的语义为：如果总个数为偶数，那么删除最中间的两个元素。
     *
     * @return
     */
    public Double delMedian() {
        if (lowHeap.size() == highHeap.size()) {
            double mid = median();
            lowHeap.delMax();
            highHeap.delMin();
            return mid;
        } else {
            //最大堆比最小堆多一个元素。
            return lowHeap.delMax() * 1.0;
        }
    }

    public static void main(String[] args) {
        DynamicMedian median = new DynamicMedian();
        for (int i = 0; i < 10; i++) {
            median.insert(i);
        }
        StdOut.println(median.median());
        median.insert(1);
        StdOut.println(median.median());
        median.insert(2);
        StdOut.println(median.median());
    }
}
