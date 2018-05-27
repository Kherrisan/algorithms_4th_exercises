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
     * 直接索引数组，put(i,item)会导致items[i]=item
     */
    private Item[] items;

    /**
     * 二叉堆
     * pq[i]是第i小的元素在items中所在位置，即该元素的索引
     */
    private int[] pq;

    /**
     * qp[i]是items中第i个元素在队列中所处位次，即pq中下标。
     * qp[i]=-1表示items[i]不在优先队列中
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

    public Item get(int k) {
        return items[k];
    }

    public void insert(int k, Item item) {
        n++;
        pq[n] = k;
        qp[k] = n;
        items[k] = item;
        swim(n);
    }

    /**
     * 随机访问堆中元素，修改元素并重新调整堆的结构。
     *
     * @param k
     * @param item
     */
    public void change(int k, Item item) {
        items[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k) {
        return qp[k] == -1;
    }

    public void delete(int k) {
        int posOfK = qp[k];
        exch(n--, posOfK);
        swim(posOfK);
        sink(posOfK);
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
        int indexOfMin = pq[1];
        exch(1, n--);
        sink(1);
        items[indexOfMin] = null;
        qp[indexOfMin] = -1;
        return indexOfMin;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int p = 2 * k;
            if (p < n && less(p + 1, p)) {
                p++;
            }
            if (less(k, p)) {
                break;
            }
            exch(k, p);
            k = p;
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
