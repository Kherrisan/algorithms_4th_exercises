package com.dokyme.alg4.sorting.application;

import com.dokyme.alg4.sorting.DefaultComparator;
import com.dokyme.alg4.sorting.Sorting;
import com.dokyme.alg4.sorting.quick.Quick;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * Created by intellij IDEA.But customed by hand of Dokyme.
 * 2.5.18
 *
 * @author dokym
 * @date 2018/5/29-18:02
 * Description:
 */
public class Stablize implements Sorting {

    private static class Element implements Comparable<Element> {
        public int index;
        public Comparable val;

        public Element(Comparable val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            int cmp = val.compareTo(o.val);
            if (cmp != 0) {
                return cmp;
            }
            return index - o.index;
        }
    }

    private Sorting sorting;

    public Stablize(Sorting sorting) {
        this.sorting = sorting;
    }

    @Override
    public void sort(Comparable[] a, Comparator c) {
        sort(a, new DefaultComparator());
    }

    @Override
    public void sort(Comparable[] a) {
        Element[] wrapper = new Element[a.length];
        for (int i = 0; i < wrapper.length; i++) {
            wrapper[i] = new Element(a[i], i);
        }
        sorting.sort(wrapper);
        for (int i = 0; i < wrapper.length; i++) {
            a[i] = wrapper[i].val;
        }
    }

    public static void main(String[] args) {
        Sorting sorting = new Stablize(new Quick());
        StdOut.println(StabilityChecker.check(sorting));
    }
}
