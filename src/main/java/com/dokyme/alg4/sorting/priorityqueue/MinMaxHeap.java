package com.dokyme.alg4.sorting.priorityqueue;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.4.29
 * 最小最大堆
 *
 * @author dokym
 * @date 2018/5/16-13:43
 * Description:
 */
public class MinMaxHeap<T extends Comparable<T>> extends AbstractPriorityQueue<T> implements IMinPQ<T>, IMaxPQ<T> {

    private int n;
    private T[] pq;

    public MinMaxHeap(int size) {
        pq = (T[]) new Comparable[size + 1];
    }

    public boolean isMinLevel(int k) {
        int l = (int) (Math.log(k) / Math.log(2));
        return l % 2 == 0;
    }

    public T max() {

    }

    public T min() {

    }

    @Override
    public void insert(T e) {
        pq[++n] = e;
        if (isMinLevel(n / 2)) {
            //如果父节点是最小层节点
            if (less(n, n / 2)) {
                //如果插入项小于父节点，说明插入项有资格进入最小堆
                exch(n, n / 2);
                swimInMin(n / 2);
            } else {
                //如果插入项大于父节点，
                swimInMax(n);
            }
        } else {
            //如果父节点是最大层节点
            if (less(n / 2, n)) {
                //如果插入项大于父节点，说明插入项有资格进入最大堆
                exch(n / 2, n);
                swimInMax(n / 2);
            } else {
                swimInMin(n);
            }
        }
    }

    /**
     * 在最小层下层
     *
     * @param k
     */
    private void sinkInMin(int k) {
        T e = pq[k];
        while (4 * k <= n) {
            int p = 4 * k;
            if (p < n && less(p + 1, p)) {
                p++;
            }
            if (less(e, pq[p])) {
                break;
            }
            pq[k] = pq[p];
            k = p;
        }
        pq[k] = e;
    }

    /**
     * 最小层上浮
     *
     * @param k
     */
    private void swimInMin(int k) {
        T e = pq[k];
        while (k > 3 && less(e, pq[k / 4])) {
            pq[k] = pq[k / 4];
            k /= 4;
        }
        pq[k] = e;
    }

    /**
     * 最大层下沉
     *
     * @param k
     */
    private void sinkInMax(int k) {
        T e = pq[k];
        while (4 * k <= n) {
            int p = 4 * k;
            if (p < n && less(p, p + 1)) {
                p++;
            }
            if (less(pq[p], e)) {
                break;
            }
            pq[k] = pq[p];
            k = p;
        }
        pq[k] = e;
    }

    /**
     * 最大层上浮
     *
     * @param k
     */
    private void swimInMax(int k) {
        T e = pq[k];
        while (k > 7 && less(pq[k / 4], e)) {
            pq[k] = pq[k / 4];
            k /= 4;
        }
        pq[k] = e;
    }

    @Override
    public T delMax() {
        return null;
    }

    @Override
    public T delMin() {
        T min=pq[1];
        exch(1=);
        return min;
    }

    public static void main(String[] args) {

    }
}
