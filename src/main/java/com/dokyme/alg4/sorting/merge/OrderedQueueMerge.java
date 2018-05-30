package com.dokyme.alg4.sorting.merge;

import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.basic.Example;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import static com.dokyme.alg4.sorting.merge.Merge.merge;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.2.15
 *
 * @author dokym
 * @date 2018/4/23-1:39
 * Description:
 */
public class OrderedQueueMerge implements Sorting {

    @Override
    public void sort(Comparable[] a, Comparator c) {
        throw new RuntimeException();
    }

    @Override
    public void sort(Comparable[] a) {
        Queue<Queue<Comparable>> qq = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            Queue<Comparable> q = new LinkedList<>();
            q.add(a[i]);
            qq.add(q);
        }
        while (qq.size() > 1) {
            qq.add(Merge.merge(qq.poll(), qq.poll()));
        }
        Queue<Comparable> r = qq.poll();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.poll();
        }
    }

    public static void main(String[] args) {
        Double[] array = new Double[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform();
        }
        new OrderedQueueMerge().sort(array);
        assert Example.isSorted(array);
        StdOut.print(array);
    }
}
