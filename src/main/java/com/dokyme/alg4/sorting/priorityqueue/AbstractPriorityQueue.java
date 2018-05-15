package com.dokyme.alg4.sorting.priorityqueue;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/9-23:39
 * Description:
 */
public abstract class AbstractPriorityQueue<T extends Comparable> implements PQ<T> {

    protected T[] pq;

    protected int n = 0;

    protected boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    protected boolean less(T i, T j) {
        return i.compareTo(j) < 0;
    }

    protected void exch(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }
}

interface PQ<T> {
    void insert(T t);

    boolean isEmpty();

    int size();
}

interface IMaxPQ<T> extends PQ<T> {
    T delMax();
}

interface IMinPQ<T> extends PQ<T> {
    T delMin();
}