package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.priorityqueue.MaxHeap;

import static com.dokyme.alg4.sorting.basic.Example.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 *
 * @author dokym
 * @date 2018/5/31-21:03
 * Description:
 */
public class StableMaxHeap<T extends Comparable<T>> implements Sorting {

    private int sid = 0;

    private class Element implements Comparable<Element> {

        private int index;

        private T val;

        public Element(T val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            int cmp = val.compareTo(o.val);
            if (cmp != 0) {
                return 0;
            }
            return index - o.index;
        }
    }

    private Element[] pq;

    private int size;

    public StableMaxHeap(T[] array) {
//        pq = new Element[array.length + 1];
        for (int i = 0; i < array.length; i++) {
//            pq[i + 1] = new Element<>(array[i], sid++);
        }
        size = array.length;
        for (int i = size / 2; i > 0; i--) {
            sink(i);
        }
    }

    public StableMaxHeap(int size) {
        this.size = size;
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {

    }

    @Override
    public void sort(Comparable[] array) {
//        pq = new Element[array.length + 1];
        for (int i = 0; i < array.length; i++) {
//            pq[i + 1] = new Element<>(array[i], sid++);
        }
        size = array.length;
        for (int i = size / 2; i > 0; i--) {
            sink(i);
        }
        int n = size;
        while (n > 1) {
            exch(1, n--);
            sink(1);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = pq[i + 1].val;
        }
    }

    public void insert(T data) {
        size++;
        if (size == pq.length - 1) {
            resize(size * 2);
        }
        pq[size] = new Element(data, sid++);
        swim(size);
    }

    public T max() {
        return pq[1].val;
    }

    public T delMax() {
        T data = pq[1].val;
        pq[1] = pq[size];
        pq[size--] = null;
        sink(1);
        return data;
    }

    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) {
            exch(i, i / 2);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= size) {
            int k = 2 * i;
            if (k < size && less(k, k + 1)) {
                k++;
            }
            if (less(k, i)) {
                break;
            }
            exch(i, k);
            i = k;
        }
    }

    private void exch(int i, int j) {
        Element e = pq[i];
        pq[i] = pq[j];
        pq[j] = e;
    }

    private boolean less(int i, int j) {
        return pq[i].val.compareTo(pq[j].val) < 0;
    }

    private void resize(int newSize) {
        Element[] newPQ = null;
        System.arraycopy(pq, 1, newPQ, 1, size);
        pq = newPQ;
    }

    public static void main(String[] args) {
        testSorting(new StableMaxHeap<>(10));
        String[] strArray = new String[10];
        Object[] objArray = strArray;
        List<String> li = new ArrayList();
    }
}
