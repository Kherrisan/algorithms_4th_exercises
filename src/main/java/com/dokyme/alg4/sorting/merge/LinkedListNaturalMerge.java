package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

import static com.dokyme.alg4.sorting.basic.Example.*;

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
            size--;
            Node t = first;
            first = first.next;
            if (first == null) {
                last = null;
            }
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
    public void sort(Comparable[] a, Comparator c) {
        throw new RuntimeException();
    }

    @Override
    public void sort(Comparable[] a) {
        LinkedList<Comparable> list = new LinkedList<>();
        //在队首存放一个哨兵节点，减少p数组以及merge的代码复杂性
        list.add(null);
        for (Comparable ae : a) {
            list.add(ae);
        }
        sort(list);
        //删除null哨兵节点
        list.poll();
        LinkedList.Node cur = list.first;
        for (int i = 0; i < a.length; i++) {
            a[i] = cur.data;
            cur = cur.next;
        }
    }

    private int pass(LinkedList a, LinkedList.Node[] p) {
        //p数组存放有序段的最后一个节点
        int pi = 0;
        LinkedList.Node cur = a.first.next;
        LinkedList.Node last = cur;
        p[pi++] = a.first;
        while ((cur = cur.next) != null) {
            if (Example.less(cur.data, last.data)) {
                p[pi++] = last;
            }
            last = cur;
        }
        p[pi++] = last;
        return pi;
    }

    public void sort(LinkedList a) {
        int N = a.size();
        LinkedList.Node[] p = new LinkedList.Node[N];
        int psize = pass(a, p);
        while (psize != 2) {
            for (int i = 0; i < psize; i += 2) {
                merge(a, p[i], p[Math.min(i + 1, psize - 1)], p[Math.min(i + 2, psize - 1)]);
            }
            psize = pass(a, p);
        }
    }

    private void merge(LinkedList a, LinkedList.Node lo, LinkedList.Node mi, LinkedList.Node hi) {
        //归并a[lo+1...mi]和a[mi+1...hi]
        //lo指向修正过后链表的最后一个节点
        LinkedList.Node i = lo.next, j = mi.next;
        //用于标记指针移动到子序列终点
        final LinkedList.Node midNext = mi.next, hiNext = hi.next;
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
        //此时lo指向hi的位置
        lo.next = hiNext;
    }

    public static void main(String[] args) {
//        Double[] array = new Double[]{0.866, 0.201, 0.326, 0.350, 0.375, 0.670, 0.507, 0.488, 0.509, 0.055};
//        new LinkedListNaturalMerge().sort(array);
//        assert isSorted(array);
        for (int j = 0; j < 100; j++) {
            Double[] array = new Double[10];
            Double[] copy=null;
            try {
                for (int i = 0; i < 10; i++) {
                    array[i] = StdRandom.uniform();
                }
                copy = Arrays.copyOf(array, array.length);
                new LinkedListNaturalMerge().sort(array);
                assert isSorted(array);
                StdOut.println(String.format("%dth test finished.", j));
            } catch (NullPointerException e) {
                StdOut.println(Arrays.toString(copy));
                break;
            }
        }
    }
}
