package com.dokyme.alg4.sorting.priorityqueue;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/11-10:22
 * Description:
 */
public class IndexMinPQ<Item extends Comparable<Item>> {

    private int n;

    /**
     *
     */
    private Item[] items;

    /**
     * pq[i]是第i小的元素在items中所在位置
     */
    private int[] pq;

    /**
     * qp[i]是items中第i个元素是队列中所处位次，即pq中下标。
     */
    private int[] qp;

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int M) {
        items = (Item[]) new Comparable[M + 1];
        pq = new int[M + 1];
        qp = new int[M + 1];
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    public void insert(int k, Item item) {
        n++;
        pq[n] = k;
        qp[k] = n;
        items[k] = item;
        swim(n);
    }

    public void change(int k, Item item) {
        items[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k) {
        return qp[k] == -1;
    }

    public void delete(int k) {
        int delth = qp[k];
        exch(n--, delth);
        swim(delth);
        sink(delth);

        //gc
        items[k] = null;
        qp[k] = -1;
    }

    public Item min() {
        return items[pq[1]];
    }

    public int minIndex() {
        return qp[pq[1]];
    }

    public int delMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
//        pq[n + 1] = -1;
        items[min] = null;
        qp[min] = -1;
        return min;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    /**
     * 交换pq中第i小和第j小的元素。
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public static void main(String[] args) {
        IndexMinPQ<Double> doubleIndexMinPQ = new IndexMinPQ<>(10);
        for (int i = 0; i < 10; i++) {
            doubleIndexMinPQ.insert(i, i * 2.5);
        }
    }
}
