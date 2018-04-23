package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdOut;


/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.17
 *
 * @author dokym
 * @date 2018/4/23-2:38
 * Description:
 */
public class LinkedListNaturalMerge implements Sorting {

    public static class LinkedList<T extends Comparable> {

        public Node first;

        public Node last;

        private int size = 0;

        public LinkedList() {

        }

        public T poll() {
            Node t = first;
            first = first.next;
            return t.data;
        }

        public void add(T data) {
            if (first == null) {
                last = first = new Node(data);
            } else {
                last.next = new Node(data);
                last = last.next;
            }
            size++;
        }

        public int size() {
            return size;
        }

        public class Node {
            public T data;
            public Node next;

            public Node(T data) {
                this.data = data;
            }

            @Override
            public String toString() {
                return data.toString();
            }
        }

    }

    @Override
    public void sort(Comparable[] a) {
        LinkedList<Comparable> list = new LinkedList<>();
        for (Comparable ae : a) {
            list.add(ae);
        }
        sort(list);
        LinkedList.Node cur = list.first;
        for (int i = 0; i < a.length; i++) {
            a[i] = cur.data;
            cur = cur.next;
        }
    }

    private int pass(LinkedList a, LinkedList.Node[] p) {
        //p数组存放有序段的最后一个节点
        int pi = 0;
        LinkedList.Node cur = a.first;
        LinkedList.Node last = a.first;
        p[pi++] = cur;
        while ((cur = cur.next) != null) {
            if (Example.less(cur.data, last.data)) {
                p[pi++] = last;
            }
            last = cur;
        }
        p[pi++] = a.last;
        return pi;
    }

    public void sort(LinkedList a) {
        int N = a.size();
        LinkedList.Node[] p = new LinkedList.Node[N];
        int psize = pass(a, p);
        while (psize != 2) {
            for (int i = 0; i < psize; i += 2) {
                merge(p[i], p[Math.min(i + 1, psize - 1)], p[Math.min(i + 2, psize - 1)]);
            }
            psize = pass(a, p);
        }
    }

    private void merge(LinkedList.Node lo, LinkedList.Node mi, LinkedList.Node hi) {
        //归并a[lo+1...mi]和a[mi+1...hi]
        try {
            LinkedList.Node i = lo.next;
            LinkedList.Node j = mi.next;
            final LinkedList.Node midNext = mi.next;
            final LinkedList.Node hiNext = hi.next;
            while (i != midNext || j != hiNext) {
                if (i == midNext) {
                    lo.next = j;
                    j = j.next;
                } else if (j == hiNext) {
                    lo.next = i;
                    i = i.next;
                } else if (Example.less(i.data, j.data)) {
                    lo.next = i;
                    i = i.next;
                } else {
                    lo.next = j;
                    j = j.next;
                }
                lo = lo.next;
            }
            lo.next = hiNext;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testMergeLinkedList() {
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        for (int i = 0; i < 10; i += 2) {
            l1.add(i);
            l2.add(i + 1);
        }
        l1.add(10);
        l1.add(12);
        l1.last.next = l2.first;
        new LinkedListNaturalMerge().merge(l1.first, l1.last, l2.last);
        LinkedList.Node cur = l1.first;
        while (cur != null) {
            StdOut.print(cur.data);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 5, 3, 9, 7, 8, 4};
        new LinkedListNaturalMerge().sort(array);
        assert Example.isSorted(array);
    }
}
