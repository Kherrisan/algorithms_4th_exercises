package com.dokyme.alg4.sorting.priorityqueue;

import java.util.Arrays;

import static com.dokyme.alg4.sorting.basic.Example.*;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.4.3
 *
 * @author dokym
 * @date 2018/5/9-23:41
 * Description:
 */
public class DifferentPQ {

    public static <T extends Comparable> boolean testMaxPQ(IMaxPQ<T> maxPQ, T[] data) {
        Arrays.sort(data);
        for (T t : data) {
            maxPQ.insert(t);
        }
        T[] newt = (T[]) new Comparable[data.length];
        int n = data.length - 1;
        while (!maxPQ.isEmpty()) {
            newt[n--] = maxPQ.delMax();
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] != newt[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert testMaxPQ(new OrderedArray<>(10), generateTestData(new Double(1), 10));
        assert testMaxPQ(new UnOrderedArray<>(10), generateTestData(new Double(1), 10));
        assert testMaxPQ(new UnOrderedLinkedList<>(), generateTestData(new Double(1), 10));
        assert testMaxPQ(new OrderedArray<>(10), generateTestData(new Double(1), 10));
    }
}

class Node<T> {
    T val;
    Node<T> next;

}

class OrderedLinkedList<T extends Comparable> implements IMaxPQ<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    @Override
    public void insert(T t) {
        size++;
        Node<T> node = new Node<>();
        node.val = t;
        if (first == null) {
            first = last = node;
        } else {
            Node<T> cur = first, pre = first;
            while ((cur = cur.next) != null) {
                if (less(cur.val, node.val)) {
                    pre.next = node;
                    node.next = cur;
                    break;
                }
                pre = pre.next;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public T delMax() {
        size--;
        T max = first.val;
        first = first.next;
        if (size == 0) {
            last = null;
        }
        return max;
    }
}

class UnOrderedLinkedList<T extends Comparable> implements IMaxPQ<T> {

    private int size;

    private Node<T> first;

    private Node<T> last;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(T t) {
        size++;
        if (first == null) {
            Node f = new Node();
            f.val = t;
            first = f;
            last = f;
        } else {
            Node l = new Node();
            l.val = t;
            last.next = l;
            last = l;
        }
    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public T delMax() {
        size--;
        Node<T> max = first, cur = first, pre = first, preMax = first;
        while ((cur = cur.next) != null) {
            if (less(max.val, cur.val)) {
                preMax = pre;
                max = cur;
            }
            pre = pre.next;
        }
        T temp = max.val;
        if (max == first) {
            first = max.next;
        }
        if (size == 0) {
            last = null;
        }
        preMax.next = max.next;
        return temp;
    }
}

class UnOrderedArray<T extends Comparable> extends AbstractPriorityQueue<T> implements IMaxPQ<T> {

    @SuppressWarnings("unchecked")
    public UnOrderedArray(int n) {
        pq = (T[]) new Comparable[n];
    }

    @Override
    public void insert(T t) {
        pq[n++] = t;
    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public T delMax() {
        int index = 0;
        for (int i = 1; i < n; i++) {
            if (less(index, i)) {
                index = i;
            }
        }
        T temp = pq[index];
        for (int i = index; i < n - 1; i++) {
            pq[i] = pq[i + 1];
        }
        pq[--n] = null;
        return temp;
    }
}

class OrderedArray<T extends Comparable> extends AbstractPriorityQueue<T> implements IMaxPQ<T> {

    @SuppressWarnings("unchecked")
    public OrderedArray(int n) {
        pq = (T[]) new Comparable[n];
    }

    @Override
    public void insert(T t) {
        pq[n++] = t;
        int i = n - 1;
        for (; i >= 1 && less(i - 1, i); i--) {
            exch(i, i - 1);
        }
    }

    @Override
    public T max() {
        return pq[1];
    }

    @Override
    public T delMax() {
        T max = pq[0];
        for (int i = 0; i < n - 1; i++) {
            pq[i] = pq[i + 1];
        }
        pq[--n] = null;
        return max;
    }
}
